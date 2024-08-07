<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-inverse cm-navbar-colour">
  <div class="container-fluid">
    <div class="navbar-header cm-text-colour">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>  
      </button>
      	<a class="navbar-brand navigation-logo white-text-col" href="#">
      	<img class="mrg-heading-h1-cm" style="width: 120px; margin-top:0;" src='<c:url value="/resources/images/DRA-logo-new.png"></c:url>'>
	  	</a>
	  	
    </div>
    <div class="collapse navbar-collapse navigation-section" id="myNavbar">
    <c:choose>
    	<c:when test="${sessionDetails.loggedInUser.fkUserRoleId.primaryId == 1}">
    		<ul class="nav navbar-nav">
    			<li class="cm-text-colour"><a class="single-cm-text-colour" href="AdminDashboard.html">DashBoard</a></li>
    			<li class="dropdown cm-text-colour">
          			<a class="dropdown-toggle white-text-col" data-toggle="dropdown" href="#">Project<span class="caret"></span></a>
          			<ul class="dropdown-menu">
           			<li class="cm-text-colour dropdown-cm-hover"><a  href="AddProject.html">Add Project</a></li>
					<li class="cm-text-colour dropdown-cm-hover"><a href="ViewProjects.html">View Project</a></li>
          			</ul>
        		</li>
        			<li class="dropdown cm-text-colour">
          			<a class="dropdown-toggle white-text-col" data-toggle="dropdown" href="#">Branch<span class="caret"></span></a>
          			<ul class="dropdown-menu">
           			<li class="cm-text-colour dropdown-cm-hover"><a  href="AddBranch.html">Add Branch</a></li>
					<li class="cm-text-colour dropdown-cm-hover"><a href="ViewBranches.html">View Branch</a></li>
          			</ul>
        		</li>
    			<li class="dropdown cm-text-colour">
          			<a class="dropdown-toggle white-text-col" data-toggle="dropdown" href="#">Employee<span class="caret"></span></a>
          			<ul class="dropdown-menu">
           				<li class="cm-text-colour dropdown-cm-hover"><a  href="AddEmployee.html">Add Employee</a></li>
					<li class="cm-text-colour dropdown-cm-hover"><a href="ViewAllEmployee.html">All Employee</a></li>
          			</ul>
        		</li>
    			<li class="cm-text-colour"><a class="single-cm-text-colour" href="TimesheetStatus.html">Timesheet Status</a></li>
<!--     			<li class="cm-text-colour"><a class="single-cm-text-colour" href="ApproveRequestDate.html">Date Request's</a></li> -->
    			<li class="dropdown cm-text-colour">
          			<a class="dropdown-toggle white-text-col" data-toggle="dropdown" href="#">Reports<span class="caret"></span></a>
          			<ul class="dropdown-menu">
          			<li class="cm-text-colour dropdown-cm-hover"><a href="BranchWiseReport.html">Branch Wise Report</a></li>
           				<li class="cm-text-colour dropdown-cm-hover"><a href="ProjectWiseReport.html">Project Wise Report</a></li>
           				<li class="cm-text-colour dropdown-cm-hover"><a href="ManagerWiseReport.html">Manager Wise Report</a></li>
           				<li class="cm-text-colour dropdown-cm-hover"><a href="TimesheetNotFilled.html">Timesheet Not Filled Report</a></li>
           			   <li class="cm-text-colour dropdown-cm-hover"><a href="EmployeeWiseReport.html">Employee Wise Report</a></li>
           				
<!--            				  <li class="cm-text-colour dropdown-cm-hover"><a href="TimesheetReport.html">Timesheet Report</a></li> -->
          			</ul>
        		</li>      
    		</ul>
    	</c:when>
    	
    	<c:when test="${sessionDetails.loggedInUser.fkUserRoleId.primaryId ==2}">
    		<ul class="nav navbar-nav">
    			<li class="cm-text-colour"><a class="single-cm-text-colour" href="HrDashboard.html">DashBoard</a></li>
    			<li class="dropdown cm-text-colour">
          			<a class="dropdown-toggle white-text-col" data-toggle="dropdown" href="#">Project<span class="caret"></span></a>
          			<ul class="dropdown-menu">
           			<li class="cm-text-colour dropdown-cm-hover"><a  href="AddProject.html">Add Project</a></li>
					<li class="cm-text-colour dropdown-cm-hover"><a href="ViewProjects.html">View Project</a></li>
          			</ul>
        		</li>
        			<li class="dropdown cm-text-colour">
          			<a class="dropdown-toggle white-text-col" data-toggle="dropdown" href="#">Branch<span class="caret"></span></a>
          			<ul class="dropdown-menu">
           			<li class="cm-text-colour dropdown-cm-hover"><a  href="AddBranch.html">Add Branch</a></li>
					<li class="cm-text-colour dropdown-cm-hover"><a href="ViewBranches.html">View Branch</a></li>
          			</ul>
        		</li>
        		
				<li class="dropdown cm-text-colour">
          			<a class="dropdown-toggle white-text-col" data-toggle="dropdown" href="#">Employee<span class="caret"></span></a>
          			<ul class="dropdown-menu">
           			<li class="cm-text-colour dropdown-cm-hover"><a  href="AddEmployee.html">Add Employee</a></li>
					<li class="cm-text-colour dropdown-cm-hover"><a href="ViewAllEmployee.html">All Employee</a></li>
          		    <li class="cm-text-colour dropdown-cm-hover"><a href="AwardNominations.html">Award Nominations</a></li>
          			</ul>
        		</li>
     	        <li class="dropdown cm-text-colour">
          			<a class="dropdown-toggle white-text-col" data-toggle="dropdown" href="#">Timesheet<span class="caret"></span></a>
          			<ul class="dropdown-menu">
           			<li class="cm-text-colour dropdown-cm-hover"><a  href="SaveEmpTimesheet.html">Fill Timesheet</a></li>
           			<li class="cm-text-colour dropdown-cm-hover"><a  href="WeaklyTimesheet.html">Weekly Timesheet</a></li>
<!-- 					<li class="cm-text-colour dropdown-cm-hover"><a href="RequestDate.html">Request Date</a></li> -->
          			</ul>
        		</li>    	    	
    			<li class="dropdown cm-text-colour">
          			<a class="dropdown-toggle white-text-col" data-toggle="dropdown" href="#">Reports<span class="caret"></span></a>
          			<ul class="dropdown-menu">
           				<li class="cm-text-colour dropdown-cm-hover"><a href="BranchWiseReport.html">Branch Wise Report</a></li>
           				<li class="cm-text-colour dropdown-cm-hover"><a href="ProjectWiseReport.html">Project Wise Report</a></li>
           				<li class="cm-text-colour dropdown-cm-hover"><a href="ManagerWiseReport.html">Manager Wise Report</a></li>
           				<li class="cm-text-colour dropdown-cm-hover"><a href="TimesheetNotFilled.html">Timesheet Not Filled Report</a></li>
           			    <li class="cm-text-colour dropdown-cm-hover"><a href="EmployeeWiseReport.html">Employee Wise Report</a></li>
<!--           			    <li class="cm-text-colour dropdown-cm-hover"><a href="TimesheetReport.html">Timesheet Report</a></li> -->
          			</ul>
        		</li>      
    		</ul>
    	</c:when>
    	<c:when test="${sessionDetails.loggedInUser.fkUserRoleId.primaryId ==3}">
    		<ul class="nav navbar-nav">
    		    <li class="cm-text-colour"><a class="single-cm-text-colour" href="ManagerDashboard.html">DashBoard</a></li>
	                <li class="dropdown cm-text-colour">
          			<a class="dropdown-toggle white-text-col" data-toggle="dropdown" href="#">Timesheet<span class="caret"></span></a>
          			<ul class="dropdown-menu">
           			<li class="cm-text-colour dropdown-cm-hover"><a  href="SaveEmpTimesheet.html">Fill Timesheet</a></li>
           			<li class="cm-text-colour dropdown-cm-hover"><a  href="WeaklyTimesheet.html">Weekly Timesheet</a></li>
<!-- 					<li class="cm-text-colour dropdown-cm-hover"><a href="RequestDate.html">Request Date</a></li> -->
          			</ul>
        		    </li>
          			<li class="cm-text-colour"><a class="single-cm-text-colour" href="TimesheetStatus.html">Timesheet Status</a></li>
    			<li class="cm-text-colour"><a class="single-cm-text-colour" href="MonthlyAwards.html">Monthly Submission</a></li>
<!--     		    <li class="cm-text-colour"><a class="single-cm-text-colour" href="ApproveRequestDate.html">Date Request's</a></li> -->
    		</ul>
    	</c:when>
    	<c:otherwise>
    		<ul class="nav navbar-nav">
    		    <li class="cm-text-colour"><a class="single-cm-text-colour" href="EmployeeDashboard.html">DashBoard</a></li>
    		     	<li class="dropdown cm-text-colour">
          			<a class="dropdown-toggle white-text-col" data-toggle="dropdown" href="#">Timesheet<span class="caret"></span></a>
          			<ul class="dropdown-menu">
           			<li class="cm-text-colour dropdown-cm-hover"><a  href="SaveEmpTimesheet.html">Fill Timesheet</a></li>
           			<li class="cm-text-colour dropdown-cm-hover"><a  href="WeaklyTimesheet.html">Weekly Timesheet</a></li>
<!-- 					<li class="cm-text-colour dropdown-cm-hover"><a href="RequestDate.html">Request Date</a></li> -->
          			</ul>
        		</li>
    		    
    		</ul>
    	</c:otherwise>
    </c:choose>
      
      <ul class="nav navbar-nav navbar-right">
      <li class="cm-text-colour"><a class="single-cm-text-colour" href="#">Welcome | ${sessionDetails.firstName}</a></li>
        <li class="dropdown"> 
          <a class="dropdown-toggle cm-text-colour" data-toggle="dropdown" href="#"> 
          <span class="glyphicon glyphicon-user"></span> 
          <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li class="cm-text-colour dropdown-cm-hover"><a href="ChangePassword.html"><span class="glyphicon glyphicon-lock"></span> Change Password</a></li>
            <li class="cm-text-colour dropdown-cm-hover"><a href="UserProfile.html"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
            <li class="cm-text-colour dropdown-cm-hover"><a href="Logout.html"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
          </ul>
        </li>
        
      </ul>
    </div>
  </div>
</nav>

