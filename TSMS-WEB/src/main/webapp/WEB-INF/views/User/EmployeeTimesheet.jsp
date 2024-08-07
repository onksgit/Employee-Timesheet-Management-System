<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src='<c:url value="/resources/webtools/js/master/EmployeeTimesheet.js"></c:url>'></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

<script>
document.addEventListener("DOMContentLoaded", function () {

	disableDiv();
    function enabledisableOperation()
    {
        var select = document.getElementById("timesheetType");
        if (select.value === 'Timesheet') {
        	disableDiv();
        } 
        else  if (select.value === 'Leave') {
        	enableDiv();
        	
            const dateInput = document.getElementById('timesheetDateforLeave');
            const now = new Date();
            const year = now.getFullYear();
            const month = now.getMonth(); // Months are zero-based in JavaScript
            const startOfMonth = new Date(year, month, 1);
            const endOfMonth = new Date(year, month + 1, 0);
            const startDate = startOfMonth.toISOString().split('T')[0];
            const endDate = endOfMonth.toISOString().split('T')[0];
            dateInput.setAttribute('min', startDate);
            dateInput.setAttribute('max', endDate);
        } 
        else {
        	disableDiv();
        }
    }

    // Add the change event listener to the select element
    var select = document.getElementById("timesheetType");
    select.addEventListener("change", enabledisableOperation);
    
    function disableDiv() {
    	 var leaveDiv = document.getElementById("leaveDiv");
    	 leaveDiv.style.display = "none";
    	 
    	 var TimesheetDiv = document.getElementById("TimesheetDiv");
    	 TimesheetDiv.style.display = "inline";
    }
    
    function enableDiv() {
    	 var leaveDiv = document.getElementById("leaveDiv");
    	 leaveDiv.style.display = "inline";
    	 
    	 var TimesheetDiv = document.getElementById("TimesheetDiv");
    	 TimesheetDiv.style.display = "none";
    	 
    }

    var requests = "${listRequestDate}";
 	var dates = requests.toString();
	var dates1 = dates.replace(/\[|\]/g, '');
 	var allowedDates=[];
 	var allowedDatesplit=[];
//  	if(dates1 !=null && dates1 !=''){ 	
 		allowedDatesplit = dates1.split(',');
  		for(let i=0;i<allowedDatesplit.length;i++){ 				
  			allowedDates.push(allowedDatesplit[i].trim().substring(0, 10));
  		}
//  	}else{
		var today = new Date();
		var yesterday = new Date();
		yesterday.setDate(today.getDate() - 1);
 	
		var formatDate = function(date) {
			var day = String(date.getDate()).padStart(2, '0');
			var month = String(date.getMonth() + 1).padStart(2, '0');
			var year = date.getFullYear();
			return year + '-' + month + '-' + day;
		};
		allowedDates.push(formatDate(yesterday));
		allowedDates.push(formatDate(today));
//  	}
    function enableSpecificDates(date) {
        var dateString = $.datepicker.formatDate('yy-mm-dd', date);
        return [allowedDates.indexOf(dateString) !== -1];
    }

    $("#timesheetDate").datepicker({
        beforeShowDay: enableSpecificDates,
        dateFormat: 'yy-mm-dd'
    });
    

        var startMeterInput = document.getElementById("startMeterReadingtext");
        var endMeterInput = document.getElementById("endMeterReadingtext");
        var kilometerInput = document.getElementById("kilometer");
        
        kilometer.disabled = true;

        function calculateTotalKilometers() {
            var startMeter = parseFloat(startMeterInput.value);
            var endMeter = parseFloat(endMeterInput.value);

            if (!isNaN(startMeter) && !isNaN(endMeter)) {
                var totalKilometers = endMeter - startMeter;
                if (totalKilometers >= 0) {
                    kilometerInput.value = totalKilometers.toFixed(2);
                    document.getElementById("kilometerErr").textContent = "";
                } else {
                    kilometerInput.value = "";
                    document.getElementById("kilometerErr").textContent = "End meter reading must be greater than start meter reading";
                }
            } else {
                kilometerInput.value = "";
                document.getElementById("kilometerErr").textContent = "";
            }
        }

        function validateStartMeterInput() {
            var startMeterValue = startMeterInput.value.trim();
            if (startMeterValue === "") {
                document.getElementById("startMeterReadingtextErr").textContent = "Start meter reading is required";
            } else if (isNaN(parseFloat(startMeterValue))) {
                document.getElementById("startMeterReadingtextErr").textContent = "Only numbers are allowed";
            } else {
                document.getElementById("startMeterReadingtextErr").textContent = "";
            }
        }

        function validateEndMeterInput() {
            var endMeterValue = endMeterInput.value.trim();
            if (endMeterValue === "") {
                document.getElementById("endMeterReadingtextErr").textContent = "End meter reading is required";
            } else if (isNaN(parseFloat(endMeterValue))) {
                document.getElementById("endMeterReadingtextErr").textContent = "Only numbers are allowed";
            } else {
                document.getElementById("endMeterReadingtextErr").textContent = "";
            }
        }

        startMeterInput.addEventListener("input", validateStartMeterInput);
        startMeterInput.addEventListener("keypress", function(event) {
            // Allow only numeric and control keys (backspace, delete)
            var key = event.key;
            if (!/\d|\./.test(key) && !['Backspace', 'Delete', 'ArrowLeft', 'ArrowRight'].includes(key)) {
                event.preventDefault();
            }
        });

        endMeterInput.addEventListener("input", calculateTotalKilometers);
        endMeterInput.addEventListener("keypress", function(event) {
            // Allow only numeric and control keys (backspace, delete)
            var key = event.key;
            if (!/\d|\./.test(key) && !['Backspace', 'Delete', 'ArrowLeft', 'ArrowRight'].includes(key)) {
                event.preventDefault();
            }
        });

        var form = document.getElementById('yourFormId');
        form.addEventListener('submit', function(event) {
            validateStartMeterInput();
            validateEndMeterInput();

            if (isNaN(parseFloat(endMeterInput.value))) {
                event.preventDefault();
                document.getElementById("endMeterReadingtextErr").textContent = "Only numbers are allowed";
            } else {
                document.getElementById("endMeterReadingtextErr").textContent = "";
            }

            if (!isNaN(parseFloat(startMeterInput.value)) && !isNaN(parseFloat(endMeterInput.value))) {
                calculateTotalKilometers();
            }
        });




    
    

});
</script>
<style>
.content-div {
    display: none;
    transition: opacity 0.5s ease-in-out;
}
 
.content-div.active {
    display: block;
    opacity: 1;
}
</style>



<div class="row">
    <div class="col-lg-1"></div>
    <div class="col-lg-10">
        <div class="content-wrapper">
            <div class="" >
                <div class="col-lg-12">
                    <div class="col-lg-3"></div>
                    <div class="col-lg-6">
                        <h3 align="center" class="title">Add Timesheet</h3>
                    </div>
                    <div class="col-lg-3"></div>
                </div>
                <div class="col-lg-2"></div>
                <div class="well well-lg col-lg-8">
                
                			<div class=" form-group inputpd col-lg-12">
                    <div class="col-lg-3"></div>
                    <div class="col-lg-2">
                        <label class="control-label" style="padding-left: 20px;">
						Type<span style="color: red;">*</span>
					</label>
                    </div>
                   <div class="col-lg-6">
                   		<select name="timesheetType" id="timesheetType" required
							class="form-control" onselect="enabledisableOperation()">
							<option value="Timesheet" selected>Timesheet</option>
							<option value="Leave">Leave</option>
						</select>
						<span id="timesheetTypeerr" style="color: red;"></span>
                    </div>
                    <div class="col-lg-1"></div>
             </div>
            <hr class="inputpd hrstyle">
                
                <div id="TimesheetDiv">
                <form:form action="SaveEmpTimesheet.html" method="POST"
					class="form-horizontal" modelAttribute="timesheetMaster" role="form"
					id="myForm" enctype="multipart/form-data"
					onsubmit="return validateTimesheetForm()">


				<form:hidden path="timesheetType" value="Timesheet"/>
               
				<div class="form-group inputpd" id="DateInput">
					<label class="control-label col-sm-5" style="padding-left: 20px;">
						Date:<span style="color: red;">*</span>
					</label>
					<div class="col-sm-6">
						<form:input type="text" path="timesheetDate" autocomplete="off"
							class="form-control" placeholder="Timesheet Date" id="timesheetDate" value="" />
						<span id="dateerr" style="color: red;"></span>
					</div>
				</div>

				<div class="form-group inputpd" id="projectInput">
					<label class="control-label col-sm-5" style="padding-left: 20px;">
						Project Name:<span style="color: red;">*</span>
					</label>

					<div class="col-sm-6"> 
						<form:select type="" path="projectName" id="projectId"
							class="form-control" onselect="return isValidInput(event)">
							<option value="0" disabled="disabled" selected>SELECT</option>
							<c:forEach var="projectList" items="${projectList}">
								<form:option value="${projectList.primaryId}">${projectList.projectName}</form:option>
							</c:forEach>
						</form:select>
						<span id="projectnamerr" style="color: red;"></span>
					</div>
				</div>

				<div class="form-group inputpd" id="fkManagerInput">
					<label class="control-label col-sm-5" style="padding-left: 20px;">
						Reporting Manager:<span style="color: red;">*</span>
					</label>
					<div class="col-sm-6">
						<form:select type="" path="fkManager" id="fkManager"
							class="form-control" onselect="return isValidInput(event)">
							<option value="0" disabled="disabled" selected="selected">SELECT</option>
							<c:forEach var="projectManagerList" items="${managerList}">
								<form:option value="${projectManagerList.primaryId}">${projectManagerList.firstName} ${projectManagerList.lastName}</form:option>
							</c:forEach>
						</form:select>
						<span id="managererr" style="color: red;"></span>
					</div>
				</div>
	
				<div class="form-group inputpd">
					<label class="control-label col-sm-5" style="padding-left: 20px;">
						Work Done:<span style="color: red;">*</span>
					</label>
					<div class="col-sm-6">
						<form:textarea path="workDone" style="height:100px;"
							autocomplete="off" id="workDone" class="form-control"
							placeholder="Enter Total Work Done "
							></form:textarea>
						<span id="workdonerr" style="color: red;"></span>
					</div>
				</div>
				
				<div class="form-group inputpd">
						<label class="control-label col-sm-5" style="padding-left: 20px;">Work Done Image(Only Image)<span style="color: red;">*</span></label>
					<div class="col-sm-6">
						<form:input type="File" path="workDoneImageFile"
							id="WorkImageFile" class="form-control" />
						<span id="WorkImagePatherror" style="color: red;"></span>
					</div>
				</div>
				
				<div class="form-group inputpd">
					<label class="control-label col-sm-5" style="padding-left: 20px;">
						Start Location:
					</label>
					<div class="col-sm-6">
						<form:input path="startLocation"
							autocomplete="off" id="startLocation" class="form-control"
							placeholder="Enter Start Location"
							></form:input>
						<span id="startLocationErr" style="color: red;"></span>
					</div>
				</div>
				
				<div class="form-group inputpd">
					<label class="control-label col-sm-5" style="padding-left: 20px;">
						Intermediate Location:
					</label>
					<div class="col-sm-6">
						<form:input path="intermediateLocation"
							autocomplete="off" id="intermediateLocation" class="form-control"
							placeholder="Enter intermediate location"
							></form:input>
						<span id="intermediateLocationErr" style="color: red;"></span>
					</div>
				</div>
				
				<div class="form-group inputpd">
					<label class="control-label col-sm-5" style="padding-left: 20px;">
						End Location:
					</label>
					<div class="col-sm-6">
						<form:input path="endLocation"
							autocomplete="off" id="endLocation" class="form-control"
							placeholder="Enter end location"
							></form:input>
						<span id="endLocationErr" style="color: red;"></span>
					</div>
				</div>
				
							<div class="form-group inputpd">
					<label class="control-label col-sm-5" style="padding-left: 20px;">
						Start Meter Reading:
					</label>
					<div class="col-sm-6">
						<form:input path="startMeterReadingtext"
							autocomplete="off" id="startMeterReadingtext" class="form-control"
							placeholder="(In Kilometer)"
							></form:input>
						<span id="startMeterReadingtextErr" style="color: red;"></span>
					</div>
				</div>
				
				<div class="form-group inputpd">
					<label class="control-label col-sm-5" style="padding-left: 20px;">
						End Meter Reading:
					</label>
					<div class="col-sm-6">
						<form:input path="endMeterReadingtext"
							autocomplete="off" id="endMeterReadingtext" class="form-control"
							placeholder="(In Kilometer)"
							></form:input>
						<span id="endMeterReadingtextErr" style="color: red;"></span>
					</div>
				</div>
				
				<div class="form-group inputpd">
					<label class="control-label col-sm-5" style="padding-left: 20px;">
						Total KiloMeter:
					</label>
					<div class="col-sm-6">
						<form:input type="text" path="kilometer"
							autocomplete="off" id="kilometer" class="form-control"
							placeholder="Enter kilometer" disabled="disabled"></form:input>
						<span id="kilometerErr" style="color: red;"></span>
					</div>
				</div>

				<div class="form-group inputpd">
						<label class="control-label col-sm-5" style="padding-left: 20px;">Start meter reading:</label>
					<div class="col-sm-6">
						<form:input type="File" path="travellingImage1File"
							id="Image1File" class="form-control" />
						<span id="Image1Fileerror" style="color: red;"></span>
					</div>
				</div>
				
				<div class="form-group inputpd">
						<label class="control-label col-sm-5" style="padding-left: 20px;">End meter reading:</label>
					<div class="col-sm-6">
						<form:input type="File" path="travellingImage2File"
							id="Image2File" class="form-control" />
						<span id="Image2Fileerror" style="color: red;"></span>
					</div>
				</div>
               
				<hr class="inputpd hrstyle">
				<div class="form-group" align="center">
					<button class="btn bg-black" id="save" type="submit">Submit</button>
				</div>
			</form:form>
			 </div>
			
			<div id="leaveDiv">
		      <form:form action="SaveEmpTimesheet.html" method="POST"
			class="form-horizontal" modelAttribute="timesheetMaster" role="form"
			id="myForm" enctype="multipart/form-data"
			onsubmit="return validateTimesheetForm()"> 
			   
			 <form:hidden path="timesheetType" value="Leave"/>
				
				<div class="form-group inputpd" id="DateInput">
					<label class="control-label col-sm-5" style="padding-left: 20px;">
						Date:<span style="color: red;">*</span>
					</label>
					<div class="col-sm-6">
						<form:input type="date" path="timesheetDate" autocomplete="off"
							class="form-control" placeholder="Timesheet Date" id="timesheetDateforLeave" value="" />
						<span id="dateerr" style="color: red;"></span>
					</div>
				</div>
				     <div class="form-group inputpd">
						<label class="control-label col-sm-5" style="padding-left: 20px;">
						Manager:<span style="color: red;">*</span>
					</label>
					<div class="col-sm-6"> 
						<form:select type="" path="projectName" id="projectId"
							class="form-control" onselect="return isValidInput(event)">
							<option value="0" disabled="disabled" selected>SELECT</option>
							<c:forEach var="projectManagerList" items="${managerList}">
								<form:option value="${projectManagerList.primaryId}">${projectManagerList.firstName} ${projectManagerList.lastName}</form:option>
							</c:forEach>
						</form:select>
						<span id="projectnamerr" style="color: red;"></span>
					</div>
					</div>
					
						 <div class="form-group inputpd">
						<label class="control-label col-sm-5" style="padding-left: 20px;">
						Leave Type:<span style="color: red;">*</span>
					</label>
					<div class="col-sm-6"> 
						<form:input  path="workDone" id="workDone"
							class="form-control" value="leave" onselect="return isValidInput(event)" disabled="true"/>
<!-- 							<option value="0" disabled="disabled" selected>SELECT</option> -->
<%-- 								<form:option value="Leaves">Leaves</form:option> --%>
<%-- 								<form:option value="Leave without pay">Leave without pay</form:option> --%>
<%-- 								<form:option value="Work off">Work off</form:option> --%>
						
						<span id="projectnamerr" style="color: red;"></span>
					</div>
					</div>
				<hr class="inputpd hrstyle">
				<div class="form-group" align="center">
					<button class="btn bg-black" id="save" type="submit">Submit</button>
				</div>
			</form:form>
			</div>
			
                </div>
                 <div class="col-lg-2"></div>
            </div>
        </div>
    </div>
    <div class="col-lg-1"></div>
</div>

