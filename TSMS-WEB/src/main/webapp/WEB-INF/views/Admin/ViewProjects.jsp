<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css"/>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
</head>

<script>
$(document).ready(function() {
    $('#AllProjects').DataTable();
});
</script>

<div class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
		<div class="col-lg-12" align="left">
			<h3 align="center" class="title">All Projects</h3>
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
</div>

<div class="row">
<div class="col-lg-1"></div>
<div class="col-lg-10" align="left">
		<div class="table-responsive">
			<table class="table table-hover" id="AllProjects">
				<thead>
					<tr class="btn-primary">
						<th><b>Project Name</b></th>
						<th><b>Project Description</b></th>
						<th><b>Project Manager</b></th>
<!-- 						<th><b>Department</b></th> -->
						<th><b>Edit</b></th>
					</tr>
				</thead>
				<tbody>   
					<c:choose>
						<c:when test="${not empty projectList}">
							<c:forEach var="projectList" items="${projectList}">
								<tr>
									<td>${projectList.projectName}</td>
									<td>${projectList.projectDescription}</td>
									<td>${projectList.projectManager.firstName} ${projectList.projectManager.lastName}</td>
<%-- 									<td>${projectList.department}</td> --%>
									<td><a href="EditProject.html?projectId=${projectList.primaryId}" style="text-align: center;"><i class="glyphicon glyphicon-pencil"></i></a></td>
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
		</div>
		<div class="col-lg-1"></div>
</div>



