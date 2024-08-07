<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src='<c:url value="/resources/webtools/js/master/EmployeeTimesheet.js"></c:url>'></script>

<script type="text/javascript">
document.addEventListener("DOMContentLoaded", function () {
	var currentDate = new Date();
    var year = currentDate.getFullYear();
    var month = currentDate.getMonth() + 1;

    if (month < 10) {
      month = '0' + month;
    }

    var startDate = year + '-' + month + '-01';

    var endDate = new Date(year, currentDate.getMonth() + 1, 0);
    var endDay = endDate.getDate();

    if (endDay < 10) {
      endDay = '0' + endDay;
    }

    var endDateString = year + '-' + month + '-' + endDay;
    var dateInput = document.getElementById('requestDateInput');
    dateInput.setAttribute('min', startDate);
    dateInput.setAttribute('max', endDateString);

});
</script>

<div class="row">
<div class="col-lg-12" align="center" style="margin-top: 20px;">
            <div class="content-div" id="requestDateDiv">
                <div class="col-lg-12">
                    <div class="col-lg-3"></div>
                    <div class="col-lg-6">
                        <h3 align="center" class="title">Request Date</h3>
                    </div>
                    <div class="col-lg-3"></div>
                </div>
                <div class="col-lg-2"></div>
                <div class="well well-lg col-lg-8">
                   <form:form method="POST" class="form-horizontal"
						modelAttribute="timesheetUnlock" role="form"
						action="RequestDate.html" onsubmit="return requestValidation()">
						<form:hidden path="userId" value="${sessionDetails.loggedInUser.primaryId}" />
						<form:hidden path="firstName" value="${sessionDetails.loggedInUser.firstName}" />
						<form:hidden path="lastName" value="${sessionDetails.loggedInUser.lastName}" />
						<div class="form-group inputpd">
							<label class="control-label col-sm-5" style="padding-left: 20px;">
								Date:<span style="color: red;">*</span>
							</label>
							<div class="col-sm-6">
								<form:input type="date" path="requestDate" id="requestDateInput"
									autocomplete="off" class="form-control"
									placeholder="requestDate" />
								<span id="requestDateerr" style="color: red;"></span>
							</div>
						</div> 
						
							<div class="form-group inputpd">
							<label class="control-label col-sm-5" style="padding-left: 20px;">
								Manager:<span style="color: red;">*</span>
							</label>
							<div class="col-sm-6">
							<form:select type="" path="fkManagerId" id="fkManagerId" required="true"
								class="form-control" onselect="return isValidInput(event)">
								<option value="0" disabled="disabled" selected>SELECT</option>
								<c:forEach var="projectManagerList"
									items="${projectManagerList}">
									<form:option value="${projectManagerList.fkManager.primaryId}">${projectManagerList.fkManager.firstName} ${projectManagerList.fkManager.lastName}</form:option>
								</c:forEach>
							</form:select>
							<span id="managererr" style="color: red;"></span>
						</div>
						</div>
						
							<div class="form-group inputpd">
							<label class="control-label col-sm-5" style="padding-left: 20px;">
								Reason:<span style="color: red;">*</span>
							</label>
							<div class="col-sm-6">
								<form:textarea type="text" path="reason" id="requestDateReason"
									autocomplete="off" class="form-control"
									placeholder="Request Date Reason" ></form:textarea>
								<span id="requestDateReasonerr" style="color: red;"></span>
							</div>
						</div>	
						<hr class="inputpd hrstyle">
						<div class="form-group" align="center">
							<button class="btn btn-primary" id="save" type="submit">Submit
							</button>
						</div>
					</form:form>
                </div>
            </div>
            </div>
  </div>
            
            
<div class="row">
<div class="col-lg-12">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
	<h3 align="center" class="title">Requested Dates</h3>
		<div class="table-responsive">
			<table class="table table-hover">
				<thead class="btn-primary">
					<tr>
						<th><b>Employee Name</b></th>
						<th><b>Request Date</b></th>
						<th><b>Reason</b></th>
						<th><b>Approval Status</b></th>
<!-- 						<th><b>Action</b></th> -->
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty listRequestDate}">
							<c:forEach var="listRequestDate" items="${listRequestDate}">
								<tr>
									<td>${listRequestDate.firstName} ${listRequestDate.lastName} </td>
									<td><fmt:formatDate value="${listRequestDate.requestDate}" pattern="dd-MM-yyyy"/></td>
									<td>${listRequestDate.reason}</td>
									<td>${listRequestDate.status}</td>
<!-- 									<td> -->
<!-- 								  <button class="btn btn-primary" type="submit"  -->
<%-- 								          onclick="fillTimesheet('${listRequestDate.requestDate}')" --%>
<%-- 								          ${listRequestDate.status == 'Approved' ? '' : 'disabled'}> --%>
<!-- 								    Fill Timesheet -->
<!-- 								  </button> -->
<!-- 								</td> -->
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="5">No records</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
	</div>
</div>     
            
   <script>
//    function fillTimesheet(requestDate){
// 		location.href="SaveEmpTimesheet.html?requestDate="+requestDate;
// 	}
   </script>         