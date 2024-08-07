<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
		<div class="col-lg-12" align="left">
			<h3 align="center" class="title">Monthly Submission</h3>
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
</div>
  
 <div class="row">
 <div class="col-lg-1"></div>
	<div class="col-lg-10 well well-lg">
<!-- 		<div class="col-lg-12" align="left"> -->
  <div class="card">
<!--             <h3 align="center" style="background-color: #337ab7; height: 40px;">Monthly Work</h3> -->
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead class="btn-primary">
                        <tr>
                            <th>Employee Code</th>
                            <th><b>Date</b></th>
                            <th align="center"><b>Work Done</b></th>
                            <th><b>Approval Status</b></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty MonthlyTimesheetWorkList}">
                                <c:forEach var="timesheet" items="${MonthlyTimesheetWorkList}">
                                    <tr>
                                   	    <td>${timesheet.empCode}</td>
                                        <td><fmt:formatDate value="${timesheet.timesheetDate}" pattern="dd-MMM-yyyy" /></td>
                                        <td>${timesheet.workDone}</td>
                                        <td>${timesheet.approvalStatus}</td>
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
<!--     </div> -->
    </div>
	<div class="col-lg-1" align="right"></div>
</div>

<div class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
		<div class="col-lg-12" align="center">
   	<a href="giveMonthlyAwardPage.html?empCode=${MonthlyTimesheetWorkList[0].empCode}"><button type="submit" class="btn login-btn"><b>Submit</b></button></a>
   	<a href="MonthlyAwards.html"><button type="submit" class="btn login-btn"><b>Back</b></button></a>

		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
</div>

