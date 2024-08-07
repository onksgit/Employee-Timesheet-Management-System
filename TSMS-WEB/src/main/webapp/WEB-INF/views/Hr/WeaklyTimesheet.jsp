<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>	
<script>
function openImage(imagepath){
	window.open(imagepath,'_blank');
}
</script>			
			
			<div class="col-lg-1"></div>
			<div class="col-lg-10 well well-lg" align="center" style="margin-top: 3%;">
				<div class="col-lg-12">
					<div class="card">
						<h3 align="center" class="title">Weekly Work</h3>
						<div class="table-responsive">
							<table class="table table-hover">
								<thead class="btn-primary">
									<tr>
										<th>Employee Code</th>
										<th>Timesheet Date</th>
										<th>Work Done</th>
										<th>Work Done Image</th>
										<th>Start meter reading</th>
										<th>End meter reading</th>
										<th>Location</th>
										<th>Kilometer</th>
										<th>Approval Status</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${not empty listTimesheetDto}">
											<c:forEach var="timesheet" items="${listTimesheetDto}">
												<tr>
													<td>${timesheet.empCode}</td>
													<td><fmt:formatDate value="${timesheet.timesheetDate}"
															pattern="dd-MMM-yyyy" /></td>
													<td>${timesheet.workDone}</td>
													<td>
														<div style="margin-right: 10px;">
															<img alt=""
															src="ImageReadServlet?param=${timesheet.workDoneImage}"
															height="70px" width="70px" class="zoom"  onclick="openImage('ImageReadServlet?param=${timesheet.workDoneImage}')"/>
														</div> 
													</td>
													<td>
														<div style="margin-right: 10px;">
															<img alt=""
															src="ImageReadServlet?param=${timesheet.travellingImage1}"
															height="70px" width="70px" class="zoom"  onclick="openImage('ImageReadServlet?param=${timesheet.travellingImage1}')"/>
														</div>
													</td>
													<td>
														<div style="margin-right: 10px;">
															<img alt=""
															src="ImageReadServlet?param=${timesheet.travellingImage2}"
															height="70px" width="70px" class="zoom"  onclick="openImage('ImageReadServlet?param=${timesheet.travellingImage2}')"/>
														</div> 
													</td>
													<td>${timesheet.startLocation} -> ${timesheet.intermediateLocation} -> ${timesheet.endLocation}</td>
													<td>${timesheet.kilometer}</td>
													<td>${timesheet.approvalStatus}</td>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr>
												<td colspan="9">No records</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-1"></div>