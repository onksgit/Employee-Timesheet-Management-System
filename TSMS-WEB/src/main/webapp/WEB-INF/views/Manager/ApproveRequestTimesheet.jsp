<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  

<script>
function approveDateLock(id){
	location.href="ApprovedDateLock.html?id="+id;
}
</script>

<div class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
		<div class="col-lg-12" align="left">
			<h3 align="center" class="title">Approve Timesheet Date</h3>
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
</div>

<div class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr class="btn-primary">
						<th><b>Employee Name</b></th>
						<th><b>Request Date</b></th>
						<th><b>Reason</b></th>
						<th><b>Approval Status</b></th>
						<th><b>Action</b></th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty list}">
							<c:forEach var="list" items="${list}">
								<tr>
									<td>${list.firstName} ${list.lastName}</td>
									<td><fmt:formatDate value="${list.requestDate}" pattern="dd-MM-yyyy"/></td>
									<td>${list.reason}</td>
									<td>${list.status}</td>
									<td>
									<c:if test="${list.status eq 'Pending'}"> <button class="btn btn-primary" id="buttonid" type="submit" 
									onclick="approveDateLock(${list.id})">${list.status} 
										</button></c:if>
									</td>
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