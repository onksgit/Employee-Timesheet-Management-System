<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
		<div class="col-lg-12" align="left">
			<h3 align="center" class="title">Monthly Awards</h3>
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
</div>

<script>
$(document).ready(function() {
	 var selectElement = document.getElementById("month-select");
     var currentMonth = new Date().getMonth() + 1;

     selectElement.value = currentMonth;
	
    $('#awardNominationTable').DataTable();
});
</script>

<div class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
		<div class="col-lg-12" align="left">
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
</div>

<div class="row">
<div class="col-lg-12">
	<div class="col-lg-1"></div>
	<div class="well well-lg col-lg-10">
<form action="MonthlyAwards.html" method="POST">
 <div class="select-container">
 			<h5 align="center" class="">Select Month and Year</h5>
        <select class="select-box" name="month" id="month-select" required="required">
            <option value="1">January</option>
            <option value="2">February</option>
            <option value="3">March</option>
            <option value="4">April</option>
            <option value="5">May</option>
            <option value="6">June</option>
            <option value="7">July</option>
            <option value="8">August</option>
            <option value="9">September</option>
            <option value="10">October</option>
            <option value="11">November</option>
            <option value="12">December</option>
        </select>
        <select class="select-box" id="year-select" name="year" required="required"></select>
    </div><hr>
    <div>
    <button type="submit" class="btn btn-primary">View</button>
    </div>
</form>
</div>
<div class="col-lg-1" align="right"></div>
</div>
</div>



<div class="row">
	<div class="col-lg-3"></div>
	<div class="col-lg-6 well well-lg" align="left">
		<div class="card">
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr class="btn-primary">
								<th>Employee Name</th>
								<th>Is Already Submitted</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty timesheetMasterDtoList}">
									<c:forEach var="timesheetMasterDtoList"
										items="${timesheetMasterDtoList}">
										<tr>
											<td>${timesheetMasterDtoList.firstName}
												${timesheetMasterDtoList.lastName}
												(${timesheetMasterDtoList.empCode})</td>
										<c:choose>
										    <c:when test="${timesheetMasterDtoList.isawardGiven == 'Yes'}">
										        <td>Yes</td>
										    </c:when>
										    <c:otherwise>
										        <td>No</td>
										    </c:otherwise>
										</c:choose>
												<td align="center"><a href="viewEmployeeMonthlyWorkForAwards.html?empCode=${timesheetMasterDtoList.empCode}"><button type="submit" class="btn login-btn">View</button></a></td>
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
	</div>
	<div class="col-lg-3" align="right"></div>
</div>


    <script>
        const yearSelect = document.getElementById('year-select');
        const currentYear = new Date().getFullYear();
        const startYear = currentYear - 10; // You can adjust the range of years

        for (let year = currentYear; year >= startYear; year--) {
            const option = document.createElement('option');
            option.value = year;
            option.textContent = year;
            yearSelect.appendChild(option);
        }
    </script>


 <style>
        .container {
            text-align: center;
            background: #fff;
            padding: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .container h1 {
            margin-bottom: 10px;
        }
        .select-box {
            width: 200px;
            padding: 10px;
            font-size: 16px;
        }
    </style>
