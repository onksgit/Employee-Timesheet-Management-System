<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css"/>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
    
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
			<h3 align="center" class="title">Select Month and Year</h3>
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
</div>

<div class="row">
<div class="col-lg-12">
	<div class="col-lg-1"></div>
	<div class="well well-lg col-lg-10">
<form action="ViewAwardNomination.html" method="POST">
 <div class="select-container">
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
<div class="col-lg-12">
	<div class="col-lg-1"></div>
	<div class="well well-lg col-lg-10">
	<h3 align="center" class="title" style="margin-top: 3%;">Nominees</h3>
		<div class="table-responsive">
			<table class="table table-hover" id="awardNominationTable">
				<thead class="btn-primary">
					<tr>
						<th style="text-align: center;"><b>Employee Name</b></th>
						<th style="text-align: center;"><b>Manager Name</b></th>
						<th style="text-align: center;"><b>Award</b></th>
						<th style="text-align: center;"><b>Date</b></th>
						<th style="text-align: center;"><b>Remark</b></th>
						<th style="text-align: center;"><b>Justifications</b></th>
						<th style="text-align: center;"><b>Rating</b></th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty awardNominationsList}">
							<c:forEach var="awardNominationsList" items="${awardNominationsList}">
								<tr>
									<td>${awardNominationsList.employeeName}</td>
									<td>${awardNominationsList.managerName}</td>
									<td>${awardNominationsList.awardName}</td>
									<td>${awardNominationsList.awardDate}</td>
									<td>${awardNominationsList.remark}</td>
									<td>${awardNominationsList.justification}</td>
									<td>${awardNominationsList.rating}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="7">No records</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
	</div>
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
