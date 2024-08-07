<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript" src='<c:url value="/resources/webtools/js/master/TimesheetReport.js"></c:url>'></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style>
.progControlSelect2 {
	width: 100%;
}

.selected {
	background-color: #0C0287;
	color: white;
}

.setting {
	margin-left: 169px;
	margin-top: -21px;
}

form {
	margin-top: 2px;
}

.paginationDivStyle {
	display: inline-block;
	padding-left: 0;
	margin: 20px 0;
	border-radius: 4px;
}

.paginationDivStyle>li {
	display: inline;
}

.paginationDivStyle>li>a {
	position: relative;
	float: left;
	padding: 6px 12px;
	margin-left: -1px;
	line-height: 1.42857143;
	text-decoration: none;
	border: 1px solid #B6B6B6;
}

.inputpd {
	padding-right: -15px;
	padding-left: -15px;
	margin-bottom: 5px;
	margin-top: 5px;
}

.hrstyle {
	border-color: rgba(0, 0, 0, 0.35)
}

.container{
	margin-top:-18px;
}
/* .ui-datepicker { */
/*     background-color: #333;  */
/* } */

/* .ui-datepicker { */
/*     color: #fff;  */
/* } */
/*  .ui-datepicker { */
/*         background-color: #333; */
/*         color: #fff; */
/*         border: 1px solid #555; */
/*     } */
</style>



<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DRA | Timesheet Report</title>
</head>

<div class="container col-lg-12"><br>
	<h4 align="center" >Timesheet Report</h4>
	<div class="well well-lg col-sm-6 col-sm-offset-3">
		<form:form action="TimesheetReportPdfOrExcel.html" class="form-horizontal"
			role="form" modelAttribute="timesheetReportReq"
			name="TimesheetReportForm" validate="" method="POST"
			onsubmit="return validation()">
		    <form:hidden path="excelOrPdf" id="excelOrPdfId"/>
			<form:hidden path="action" id="Action" />
<%-- 			<form:hidden path="" id="userLevelFk" value="${locationId}"></form:hidden> --%>
<%-- 			<form:hidden path="" id="userLevel" value="${userLevel}"></form:hidden> --%>
<%-- 			<form:hidden path="liveCall" id="liveCallId"></form:hidden> --%>
<%-- 			<form:hidden path="dateRange" id="dateRange"></form:hidden> --%>
<%-- 			<form:hidden path="dateRangeForDetailRepor" id="dateRangeForDetailRepor"></form:hidden> --%>
<%-- 			<form:hidden path="" id="dateRangeDiff" value="${dateRangeDiff}"></form:hidden> --%>

			<hr class="inputpd hrstyle">
			<div class="form-group inputpd" align="center">
				<form:radiobutton path="reportType" value="DetailsReport"
					id="Details" checked="checked" />
				Detailed Report &nbsp;&nbsp;&nbsp;&nbsp;
				<form:radiobutton path="reportType" value="Summary" id="Summary" />
				Summary Report &nbsp;&nbsp;&nbsp;&nbsp;
<%-- 				<form:radiobutton path="reportType" value="Graph" id="graphId" /> --%>
<!-- 				Graph Report -->
			</div>
			<hr class="inputpd hrstyle">

			<div class="form-inline inputpd" id="datediv">
			<h5 id='daterangemsg' style="color: red;" align="center">please select the 'Date Range'</h5>
<!-- 			<div class="form-inline inputpd" style="margin-top: -18px;"> -->
				<label>From Date <span style="color: red;">*</span></label>
				<form:input type="text" class="form-control date" path="fromDate"
					placeholder="DD-MM-YYYY" id="fromDate" style="width: 30%;" readonly="true"></form:input>
				&nbsp;&nbsp;&nbsp; <label>To Date <span style="color: red;">*</span></label>
				<form:input type="text" class="form-control date" path="toDate"
					placeholder="DD-MM-YYYY" id="toDate" style="width: 30%;" readonly="true"></form:input>
				<span id="dateError" style="color: red;"></span> <span
					id="todateError" style="color: red;"></span>
			</div>
			
			<hr class="inputpd hrstyle">

			<div class="form-group inputpd">
				<label class="control-label col-sm-3">Branch</label>
<!-- 				<div class="col-sm-1"> -->
<!-- 					<input type="checkbox" name="isBranchSelected" id="isBranchSelected" -->
<!-- 						class="checkSelection check2" checked="checked"> -->
<!-- 				</div> -->
				<div class="col-sm-1"></div>
				<div class="col-sm-6">
					<form:select path="branch"
						class='form-control progControlSelect2' multiple="true"
						id="branchId">
				  <option value="ALL" selected="selected">All</option>
					<option value="Assam">Assam</option>
					<option value="Chennai">Chennai</option>
					<option value="Indore">Indore</option>
					<option value="Nagpur">Nagpur</option>
					<option value="Pune">Pune</option>
					</form:select>
				</div>
			</div>
			
						<div class="form-group inputpd">
				<label class="control-label col-sm-3">Project</label>
				<div class="col-sm-1">
				<form:checkbox  path="ProjectSelected" id="isProjectSelected"
						class="checkSelection check2" checked="checked" />
				</div>
				<div class="col-sm-6">
					<form:select path="project"
						class='form-control progControlSelect2' multiple="true"
						id="project">
<!-- 				  <option value="ALL" selected="selected">All</option> -->
					<c:forEach var="projectManagerList" items="${projectManagerList}">
						<form:option value="${projectManagerList.fkProject.primaryId}">${projectManagerList.fkProject.projectName}</form:option>
					</c:forEach>
					</form:select>
				</div>
			</div>
			
			<div class="form-group inputpd">
				<label class="control-label col-sm-3">Manager</label>
				<div class="col-sm-1">
					<form:checkbox   path="ManagerSelected" id="isManagerSelected"
						class="checkSelection check2" checked="checked" />
				</div>
				<div class="col-sm-6">
					<form:select path="manager"
						class='form-control progControlSelect2' multiple="true"
						id="manager">
<!-- 				  <option value="ALL" selected="selected">All</option> -->
					<c:forEach var="projectManagerList" items="${projectManagerList}">
						<form:option value="${projectManagerList.fkManager.primaryId}">${projectManagerList.fkManager.firstName} ${projectManagerList.fkManager.lastName}</form:option>
					</c:forEach>
					</form:select>
				</div>
			</div>
			
			 <div class="form-group inputpd">
				<label class="control-label col-sm-3">Employee</label>
				<div class="col-sm-1">
					<form:checkbox  path="EmployeeSelected" id="isEmployeeSelected"
						class="checkSelection check2" checked="checked" />
				</div>
				<div class="col-sm-6">
					<form:select path="employee"
						class='form-control progControlSelect2' multiple="true"
						id="employee">
				  <option value="ALL" selected="selected">All</option>
				  	<c:forEach var="AllEmployeeList" items="${AllEmployeeList}">
						<form:option value="${AllEmployeeList.primaryId}">${AllEmployeeList.firstName}${AllEmployeeList.lastName}</form:option>
					</c:forEach>
					</form:select>
				</div>
			</div>
			
			<div class="form-group inputpd">
				<label class="control-label col-sm-3">Approval Status</label>
				<div class="col-sm-1"></div>
				<div class="col-sm-6">
					<form:select path="approvalStatus"
						class='form-control progControlSelect2' multiple="true"
						id="approvalStatus">
				  <option value="ALL" selected="selected">All</option>
				  <option value="Pending" >Pending</option>
				  <option value="Approved" >Approved</option>
					</form:select>
				</div>
			</div>
             
			<div class="form-group inputpd">
				<div class="col-sm-12" align="center">
					<button class="btn bg-black col-sm-offset-1" onclick="submitCall()"
						id="submit">View</button>
					<button class="btn bg-black" onclick="pdfCall()"
						id="onDownload">PDF</button>
					<button class="btn bg-black" onclick="excelCall()" id="excel">Excel</button>
					
				</div>
			</div>
			
			<div class="form-group inputpd" style="color:red; display:none" id="pdfwarning">
						This action will take several minutes. Please be patient till your file download.<br>
			Check status of file in your browser download tray. 
			</div>
		</form:form>
		<div id=scrolling></div>
	</div>
</div>

<div class="container col-sm-12">
	<div class="table-responsive col-sm-12" style="margin-top: 1%;"
		id="viewJspLoad">
		<select id="limit" onchange="limitChangedCalled()"><option
				value="10" selected="selected">10</option>
			<option value="15">15</option>
			<option value="20">20</option>
		</select> <input hidden="" type="text" id="total" /><input type="text"
			hidden="" id="noOfPageDisplay" value="10" />
		<div id="tableDiv"></div>
	</div>
	<ul id="paginationDiv" class="paginationDivStyle pull-right"></ul>
	<div id="show"></div>
</div>



