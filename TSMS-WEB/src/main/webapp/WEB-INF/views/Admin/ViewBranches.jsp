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
    $('#AllBranches').DataTable();
});
</script>

<div class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
		<div class="col-lg-12" align="left">
			<h3 align="center" class="title">All Branches</h3>
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
</div>

<div class="row">
<div class="col-lg-1"></div>
<div class="col-lg-10" align="left">
		<div class="table-responsive">
			<table class="table table-hover" id="AllBranches">
				<thead>
					<tr class="btn-primary">
						<th><b>Branch Name</b></th>
						<th><b>Edit</b></th>
					</tr>
				</thead>
				<tbody>   
					<c:choose>
						<c:when test="${not empty branchtList}">
							<c:forEach var="branchtList" items="${branchtList}">
								<tr>
									<td>${branchtList.branchName}</td>
									<td><a href="EditBranch.html?branchId=${branchtList.primaryId}" style="text-align: center;"><i class="glyphicon glyphicon-pencil"></i></a></td>
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



