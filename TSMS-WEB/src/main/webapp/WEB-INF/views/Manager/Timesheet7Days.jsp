<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script type="text/javascript">
function myweek(value){
	location.href='WeeklyWork.html?empcode='+value
}
</script>

<div class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
		<div class="col-lg-12" align="left">
			<h3 align="center" class="title">Timesheet Approval</h3>
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
</div>

<div class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10 well well-lg" style="margin-bottom: 1%">
		<div class="card">
				<div class="table-responsive">
					<table class="table table-hover">
						<thead class="btn-primary">
							<tr>
								<th>Name</th>
								<th>Branch</th>
<!-- 								<th>Department</th> -->
<!-- 								<th>Grade</th> -->
								<th>Designation</th>
								<th>Approval Status</th>
<!-- 								<th>Is Active</th> -->
								<th>Timesheet Details</th>
							</tr>
						</thead>
						<tbody>TimesheetDataPojo
							<c:choose>
								<c:when test="${not empty list}">
									<c:forEach var="Emp" items="${list}">
										<tr>
											<td>${Emp.title} ${Emp.firstName} ${Emp.lastName} (${Emp.empCode})</td>
											<td>${Emp.branch}</td>
											<td>${Emp.designation}</td>
<%-- 											<td>${Emp.department}</td> --%>
<%-- 											<td>${Emp.grade}</td> --%>
<%-- 											<td>${Emp.isActive}</td> --%>
											<td>${Emp.approvalStatus}</td>
											<td><a class="btn bg-black" onclick="myweek('${Emp.empCode}')">show more</a></td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="6">No records</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
		</div>
	</div>
	<div class="col-lg-1"></div>
</div>
