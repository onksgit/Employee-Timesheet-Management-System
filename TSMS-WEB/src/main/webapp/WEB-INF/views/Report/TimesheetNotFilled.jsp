<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css"/>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
</head>
<script src='<c:url value="/resources/webtools/js/master/ReportDatePicker.js"></c:url>'></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script>
$(document).ready(function() {
    $('#TimesheetNotFilledReport').DataTable();
});
function myaction(actionType) {
	document.getElementById('Action').value=actionType;
}
</script>

<div class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
		<div class="col-lg-12" align="left">
<!-- 			<h3 align="center" class="title">Timesheet Not Filled Report</h3> -->
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
</div>

<div class="col-lg-1"></div>

<div class="col-lg-2"></div>
<div class="well well-lg col-lg-6">
	<form action="TimesheetNotFilledReport.html" name="TimesheetNotFilledReport" method="POST" class="form-horizontal"  role="form"
		onsubmit="return validate()">
		
		<input type="hidden" name="action" value="" id="Action"/>
		
		<div class="form-group inputpd">
			<label class="control-label col-sm-4" style="padding-left: 20px;">
			  Date<span style="color: red;">*</span>
			</label>
			<c:choose>
			<c:when test="${not empty fromDate}">
			<div class="col-sm-6">
			<input type="date" name="fromDate" required="required" value="${fromDate}" class="form-control">
			</div>
			</c:when>
			<c:otherwise>
			<div class="col-sm-6">
			<input type="date" name="fromDate" required="required" class="form-control">
			</div>
			</c:otherwise>
			</c:choose>
			</div>
			
<!-- 				<div class="form-group inputpd"> -->
<!-- 				<label class="control-label col-sm-5" style="padding-left: 20px;"> -->
<!-- 				To Date :<span style="color: red;">*</span> -->
<!-- 			</label> -->
<!-- 			<div class="col-sm-5"> -->
			 <input type="hidden" name="toDate" required="required">
<!-- 			</div> -->
<!-- 		</div> -->
		<hr class="inputpd hrstyle">
		<div class="form-group" align="center">
<!-- 					<button type="submit" class="btn bg-black" id="save" value="View" >View</button> -->
			<button class="btn bg-black" id="save" value="View" onclick="myaction('View')">View</button>
			<button class="btn bg-black" id="save" value="PDF" onclick="myaction('PDF')">PDF</button>
			<button class="btn bg-black" id="save" value="Excel" onclick="myaction('Excel')">Excel</button>
			<button class="btn bg-black" type="reset">Reset</button>
		</div>
	</form>
</div>
<div class="col-lg-2"></div>
<div class="col-lg-1"></div>

<div class="row" id="showtableManagerReport">
	<div class="col-lg-12">
		<div class="col-lg-1"></div>
		<div class="col-lg-10" style="margin-bottom: 3%;">
		<c:choose>
		<c:when test="${showData eq true}">
			<h3 align="center" class="title">Timesheet Not Filled Report</h3>
			<div class="table-responsive">
				<table class="table table-hover" id="TimesheetNotFilledReport">
			<thead>
					<tr class="btn-primary">
						<th><b>Employee Name</b></th>
						<th><b>Employee Code</b></th>
						<th><b>Designation</b></th>
<!-- 			        	<th><b>Reporting Manager</b></th>  -->
						<th><b>Date</b></th>
<!-- 						<th><b>Edit</b></th> -->
					</tr>
				</thead>
				<tbody>   
					<c:choose>
						<c:when test="${not empty ListEmployee}">
							<c:forEach var="AllEmployeeList" items="${ListEmployee}">
								<tr>
									<td>${AllEmployeeList.firstName} ${AllEmployeeList.lastName}</td>
									<td>${AllEmployeeList.empCode}</td>
									<td>${AllEmployeeList.fkdesignation.designationName}</td>
<%-- 									<td>${AllEmployeeList.fkManager.firstName} ${AllEmployeeList.fkManager.lastName} ${AllEmployeeList.fkManager.empCode}</td> --%>
									<td><fmt:formatDate value="${fromDate}" pattern="dd-MM-yyyy"/>${fromDate}</td>
<%-- 									<td><a href="EditEmployee.html?empCode=${AllEmployeeList.empCode}" style="text-align: center;"><i class="glyphicon glyphicon-pencil"></i></a></td> --%>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="4">No records</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
				</table>
			</div>
				</c:when>
		</c:choose>
		</div>
		<div class="col-lg-1"></div>
	</div>
</div>

