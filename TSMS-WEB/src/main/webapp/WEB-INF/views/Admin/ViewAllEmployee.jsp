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
    $('#AllEmployee').DataTable();
});
</script>

<div class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
		<div class="col-lg-12" align="left">
			<h3 align="center" class="title">All Employee</h3>
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
</div>

<div class="row">
<div class="col-lg-1"></div>
<div class="col-lg-10" align="left">
		<div class="table-responsive">
			<table class="table table-hover" id="AllEmployee">
				<thead>
					<tr class="btn-primary">
						<th><b>Employee Name</b></th>
						<th><b>Employee Code</b></th>
						<th><b>Designation</b></th>
<!-- 			        	<th><b>Reporting Manager</b></th>  -->
						<th><b>Joining Date</b></th>
						<th><b>Edit</b></th>
					</tr>
				</thead>
				<tbody>   
					<c:choose>
						<c:when test="${not empty AllEmployeeList}">
							<c:forEach var="AllEmployeeList" items="${AllEmployeeList}">
								<tr>
									<td>${AllEmployeeList.firstName} ${AllEmployeeList.lastName}</td>
									<td>${AllEmployeeList.empCode}</td>
									<td>${AllEmployeeList.fkdesignation.designationName}</td>
<%-- 									<td>${AllEmployeeList.fkManager.firstName} ${AllEmployeeList.fkManager.lastName} ${AllEmployeeList.fkManager.empCode}</td> --%>
									<td><fmt:formatDate value="${AllEmployeeList.joinDate}" pattern="dd-MM-yyyy"/></td>
									<td><a href="EditEmployee.html?empCode=${AllEmployeeList.empCode}" style="text-align: center;"><i class="glyphicon glyphicon-pencil"></i></a></td>
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



