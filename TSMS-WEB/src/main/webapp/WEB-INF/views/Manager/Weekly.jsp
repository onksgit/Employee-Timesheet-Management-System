<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script type="text/javascript">
function goBack(){
	location.href='TimesheetStatus.html'
}

function openImage(imagepath){
	window.open(imagepath,'_blank');
}

function chkboxval(){
	var array = []
	var checkboxes = document.querySelectorAll('input[type=checkbox]:checked')
	for (var i = 0; i < checkboxes.length; i++) {
		array.push(checkboxes[i].value);
     }
	console.log(array)
	document.getElementById('myvalues').value=array;
}

$(document).ready(function(){
	$('#Submit').hide();
	$('.CheckBox').click(function(){
		let selectedCheckbox = document.querySelectorAll('input[name=challanno]:checked');
		let allCheckbox = document.querySelectorAll('input[name=challanno]');
		$('#Submit').hide();
		if(selectedCheckbox.length>0){
			$('#Submit').show();
		}
		if(selectedCheckbox.length != allCheckbox.length){
		   $("#maincheckBox").prop('checked', false);
		}else{
		   $("#maincheckBox").prop('checked', true);
		}
	});
	
	$('#maincheckBox').click(function(){
		if($("#maincheckBox").prop('checked')!=false){
			$(".CheckBox").prop('checked', true);
		}
		else{
			if($(".CheckBox").prop('checked')!=true){
				$("#maincheckBox").prop('checked', false);
			}
			$(".CheckBox").prop('checked', false);
		}
		var checkboxes1 = document.querySelectorAll('input[type=checkbox]:checked');
		if(checkboxes1.length<=0){
			$('#Submit').hide();
		}
		if(checkboxes1.length>0){
			$('#Submit').show();
		}
	});
});
</script>

<div class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
		<div class="col-lg-12" align="left">
			<h3 align="center" class="title">Weekly Timesheet</h3>
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
</div>

<div class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
		<div class="col-lg-12" align="center">
		<form:form action="AddRemark.html" class="form-horizontal"
		role="form" modelAttribute="timesheetStatusDto" name="" validate=""
		method="POST">
			<div class="table-responsive">
				<table class="table table-bordered table-striped" id="mytable"
					style="width: 88%; margin-top: 20px;">
					<thead>
						<tr class="title">
							<th colspan="11">Weekdays</th>
						</tr>
						<tr class="btn-primary">
							<th style="text-align: center;">Action
								<c:if test="${fn:length(timesheetMasterDtoList) > 0}">
									<input id="maincheckBox" value="" type="checkbox">
								</c:if>
							</th>
							<th>Employee Code</th>
							<th>Timesheet Date</th>
							<th>Work Done</th>
							<th>Work Done Image</th>
							<th>Start meter reading</th>
							<th>End meter reading</th>
							<th>Location</th>
							<th>Kilometer</th>
							<th>Approval Status</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty timesheetMasterDtoList}">
								<c:forEach var="Emp" items="${timesheetMasterDtoList}">
									<tr>
										<td class="print" style="text-align: center; width: 2%">
											<input type="checkbox" class="CheckBox" name="challanno"
											id="challanno" value="${Emp.primaryId}">
										</td>
										<td>${Emp.empCode}</td>
										<td><fmt:formatDate value="${Emp.timesheetDate}"
												pattern="dd-MMM-yyyy" /></td>
										<td>${Emp.workDone}</td>
										<td>
<!-- 										<div style="display: flex; justify-content: space-around;"> -->
											<div style="margin-right: 10px;">
													<img alt=""
													src="ImageReadServlet?param=${Emp.workDoneImage}"
													height="50px" width="50px" class="zoom"  onclick="openImage('ImageReadServlet?param=${Emp.workDoneImage}')"/>
											</div> 
										</td>
										<td>
										<div style="margin-right: 10px;">
												<img alt=""
												src="ImageReadServlet?param=${Emp.travellingImage1}"
												height="50px" width="50px" class="zoom"  onclick="openImage('ImageReadServlet?param=${Emp.travellingImage1}')"/>
										</div>
										</td>
										<td>
										<div style="margin-right: 10px;">
													<img alt=""
													src="ImageReadServlet?param=${Emp.travellingImage2}"
													height="50px" width="50px" class="zoom"  onclick="openImage('ImageReadServlet?param=${Emp.travellingImage2}')"/>
											</div> 
										</td>
										<td>${Emp.startLocation} -> ${Emp.intermediateLocation} -> ${Emp.endLocation}</td>
										<td>${Emp.kilometer}</td>
										<td>${Emp.approvalStatus}</td>
										<c:set value="${Emp.empCode}" var="code"></c:set>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="11">No records</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<div class="col-lg-12">
				<div class="col-lg-1"></div>
				<div class="col-lg-2"></div>
			    <div class="col-lg-6">
			    <label class="control-label" style="padding-left: 20px;">
				Remark :<br><br>
			</label>
					<form:textarea path="remark" id="remark" placeholder="Please tell us something.." value="" type="text" class="form-control"/>
					<form:hidden path="empCode" value="${code}"></form:hidden>
					<form:hidden path="weeklyTimesheetId" value="" id="myvalues"></form:hidden><br><br>
				</div>
				<div class="col-lg-1"></div>
				<div class="col-lg-2"></div>
				</div>
				<button class="btn bg-black" type="submit" type="button" id="Submit" onclick="chkboxval()">Submit</button>
				<button class="btn bg-black" type="button" onclick="goBack()">Back</button>
			</div>
			</form:form>
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
</div>

