<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css"/>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
</head>
<script type="text/javascript" src='<c:url value="/resources/webtools/commonjs/dataTable.min.js"></c:url>'></script>
<script src='<c:url value="/resources/webtools/js/master/ReportDatePicker.js"></c:url>'></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script>
$(document).ready(function() {
    $('#projectReport').DataTable();
});
function myaction(actionType) {
	document.getElementById('Action').value=actionType;
}
</script>

<div class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
		<div class="col-lg-12" align="left">
			<h3 align="center" class="title">Project Wise Report</h3>
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
</div>

<div class="col-lg-1"></div>

<div class="col-lg-2"></div>
<div class="well well-lg col-lg-6">
	<form:form action="ProjectWiseReport.html" name="ProjectWiseReport" method="POST" class="form-horizontal" modelAttribute="reportPojo" role="form"
		onsubmit="return validate()">
		<form:input type="hidden" path="action" value="" id="Action"/>
		
		<div class="form-group inputpd col-lg-12">
			<div class="col-lg-6">
				<label >From Date <span style="color: red;">*</span></label>
				<form:input path="frmDate" class="form-control"
					placeholder="DD-MM-YYYY" id="fromDate" style=""
					readonly="true"></form:input>
			</div>
			<div class="col-lg-6">
				<label>To Date <span style="color: red;">*</span></label>
				<form:input path="toDate" class="form-control"
					placeholder="DD-MM-YYYY" id="EndDate" style=""
					readonly="true"></form:input>
				<div align="center">
					<span id="frmdateError" style="color: red;"></span>
					<span id="todateError" style="color: red;"></span>
				</div>
			</div>
		</div>
		
		<div class="form-group inputpd">
			<label class="control-label col-sm-4" style="padding-left: 20px;">
				Project :<span style="color: red;">*</span>
			</label>
			<div class="col-sm-6">
				<form:select path="project" id="project" class="form-control">
					<option value="0" selected>-Select-</option>
					<c:forEach var="AllProjectList" items="${AllProjectList}">
						<c:choose>
							<c:when test="${AllProjectList.primaryId.toString()==projectid}">
								<option value="${AllProjectList.primaryId}" selected>${AllProjectList.projectName}</option>
							</c:when>
							<c:otherwise>
								<option value="${AllProjectList.primaryId}">${AllProjectList.projectName}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
				<span id="pManagererror" style="color: red;"></span>
			</div>
		</div>
		<hr class="inputpd hrstyle">
		<div class="form-group" align="center">
			<button class="btn bg-black" id="save" value="View" onclick="myaction('View')">View</button>
			<button class="btn bg-black" id="save" value="PDF" onclick="myaction('PDF')">PDF</button>
			<button class="btn bg-black" id="save" value="Excel" onclick="myaction('Excel')">Excel</button>
			<button class="btn bg-black" type="reset">Reset</button>
		</div>
	</form:form>
</div>
<div class="col-lg-2"></div>
<div class="col-lg-1"></div>

<div class="row" id="showtableManagerReport">
	<div class="col-lg-12">
		<div class="col-lg-1"></div>
		<div class="col-lg-10" style="margin-bottom: 3%;">
		<c:choose>
		<c:when test="${showData eq true}">
			<h3 align="center" class="title">Project Wise Report</h3>
			<div class="table-responsive">
				<table class="table table-hover" id="projectReport">
					<thead class="btn-primary">
						<tr>
							<th><b>Project Name</b></th>
							<th><b>Employee Name</b></th>
							<th><b>Timesheet work</b></th>
							<th><b>Approval Status </b></th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty listProjectWiseReport}">
								<c:forEach var="listProjectWiseReport"
									items="${listProjectWiseReport}">
									<tr>
										<td>${listProjectWiseReport.projectName}</td>
										<td>${listProjectWiseReport.employeeName}</td>
										<td>${listProjectWiseReport.timesheetWork}</td>
										<td>${listProjectWiseReport.approvalStatus}</td>
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

