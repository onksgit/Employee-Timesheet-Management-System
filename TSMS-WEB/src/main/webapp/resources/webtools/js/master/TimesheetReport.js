var tcolumnHead, totalHeader;
var reportType, datepicker, datepicker1, branchDropdown,projectDropdown, managerDropdown, employeeDropdown, approvalStatusdropdown,orderById, limitId;
var isBranchSelected = 'false',isProjectSelected='false', isManagerSelected = 'false', isEmployeeSelected = 'false', isApprovalStatusSelected = 'false';

var totalAmount = 0, cont;
var namearray = [];
var countarray = [];
var amountarray = [];
var dataArrayFinal = Array();
var dataArrayFinalForAmount = Array();

$(document).ready(function() {
	dateRangeDiff = $("#dateRangeDiff").val();
	minYear = new Date().getFullYear();
	$('#datediv').show();
	
		$('#fromDate').val('');
		$('#toDate').val('');
			$('#datediv').show();
		    $("#daterangemsg").show();

		            $("#daterangemsg").show();
					reportType = $('input:radio[name=reportType]:checked').val();

					$("#viewJspLoad").hide();
					
					$("#onDownload").click(function() {
						$("#Action").val("DOWNLOAD");
					});
					
					$("#excel").click(function() {
						$("#Action").val("excel");
					});

					$("#Summary").click(function() {
						$("#submit").attr('disabled', false);
					});

			
			

					$("#fromDate").datepicker({
						dateFormat : 'yy-mm-dd',
						changeMonth : true,
						changeYear : true,
						maxDate : '0',
					});
					
					$("#toDate").datepicker({
						dateFormat : 'yy-mm-dd',
						changeMonth : true,
						changeYear : true,
						disabled: true,
						maxDate : '0',
					});
					
					$('.timepicker').timepicker({
						timeFormat : "hh:mm a"
					});

					$(".progControlSelect2").select2({
						placeholder : "All"
					});

					
					$('#excel').change(function() {
						$('#cashregi').show();
					})
					$('#challanShow').show();
					$('#Summary').change(function() {
						$('#challanShow').hide();
					})
					$('#Details').change(function() {
						$('#challanShow').show();
					})


               $("#toDate").change(function(){
						$("#daterangemsg").hide();
					});                  

					$("#fromDate,#toDate,#Details,#Summary")
							.change(
									function() { 
										
										var dateDuration = $('#dateRange').val();
										var dateDurationForDetailRepor = $('#dateRangeForDetailRepor').val();
										
										$("#submit").attr("disabled", false);
										if($('input:radio[name=reportType]:checked').val()!='Graph'){
											$("#excel").attr("disabled", false);
											$("#onDownload").attr("disabled", false);
											$("#paymentStatusIs").attr('disabled', false);
											if($('input:radio[name=reportType]:checked').val()!='Summary'){
												$("#cashregi").attr("disabled", false);/*C R No. 2021-1004 Restrict multiple times pdf download*/
											}
										}
										$("#pdfwarning").hide();
										var id = this.id;
										var res = true;
										var frmDateSplit, frmDate, toDateSplit, toDate;

										if ((id.search('fromDate') == 0)
												|| (id.search('toDate') == 0)) {

										}

										if (($(
												'input:radio[name=reportType]:checked')
												.val().search("Details") == 0)) {
											$('#cashregi').prop('disabled',
													false);

										}
										if (($(
												'input:radio[name=reportType]:checked')
												.val().search("Summary") == 0)) {
											$('#cashregi').prop('disabled',
													true);

										}
										if ($(
												'input:radio[name=reportType]:checked')
												.val().search("Graph") == 0) {
										} else {
											$('#graphShow').hide();

											$('#check1').show();
											$('#onDownload,#excel').prop(
													'disabled', false);
										}

										if ($('#fromDate').val().trim() == "") {
											frmDate = "";
										} else {
											frmDateSplit = $('#fromDate').val()
													.trim().split("-");
											frmDate = frmDateSplit[2] + "-"
													+ frmDateSplit[1] + "-"
													+ frmDateSplit[0];
								
													$("#toDate").datepicker("option", "disabled", false);
													var date11 = new Date(frmDate);
													var date22 = new Date(toDate);
													var newCurrentDate = new Date();
													var currentdatedays = new Date(newCurrentDate);
													var differenceInDays = currentdatedays - date11;
													var daysDifference = Math.floor(differenceInDays / (1000 * 60 * 60 * 24));
														if ((daysDifference - 1) < parseInt(dateDurationForDetailRepor)) {
															var newDate = new Date(date11);
															newDate.setDate((newDate.getDate()+daysDifference - 1));
														} else {
															var newDate = new Date(date11);
															newDate.setDate(newDate.getDate()+parseInt(dateDurationForDetailRepor)-1);
														}
												
													

													
												const originalDate = new Date(newDate);
												const year = originalDate.getFullYear();
												const month = String(originalDate.getMonth() + 1).padStart(2, '0'); // Months are zero-based, so add 1
												const day = String(originalDate.getDate()).padStart(2, '0');
												$("#toDate").datepicker("option", "maxDate", `${day}-${month}-${year}`);

											
										}

										if ($('#toDate').val().trim() == "") {
											toDate = "";
										} else {
											toDateSplit = $('#toDate').val()
													.trim().split("-");
											toDate = toDateSplit[2] + "-"
													+ toDateSplit[1] + "-"
													+ toDateSplit[0];
										}

										if (frmDate == "" || toDate == "") {
											res = false;
											if (frmDate == "") {
												if ((id.search('toDate') == 0)
														|| (id
																.search('fromDate') == 0)) {
													alertMsgModal("Select 'From Date'");
												}

											}
										} 
										else {
											var date1 = new Date(frmDate);
											var date2 = new Date(toDate);
											var year1 = 31536000000;
											
											 var time_difference = date2.getTime() - date1.getTime();
											 var days_difference = time_difference / (86400000);

											if (frmDateSplit[1] <= 2) {
												var leap = 0;
												if ((frmDateSplit[2] % 400 == 0)
														|| (frmDateSplit[2] % 4 == 0 && frmDateSplit[2] % 100 != 0)) // check
												{
													year1 = year1 + 86400000;
												}
											} else if (toDateSplit[1] >= 2) {
												if ((toDateSplit[2] % 400 == 0)
														|| (toDateSplit[2] % 4 == 0 && toDateSplit[2] % 100 != 0)) // check
												{
													year1 = year1 + 86400000;
												}

											}
											var diff_date = date2 - date1;
											var years = Math.floor(diff_date
													/ year1);
											var months = Math
													.floor((diff_date % year1) / 2628000000);
											var days = Math
													.floor(((diff_date % year1) % 2628000000) / 86400000);

										
										
					
											if ($('input:radio[name=reportType]:checked').val() == 'DetailsReport') {
												if(days_difference > dateDurationForDetailRepor ){
													res = false;
														$("#daterangemsg").show();
													alertMsgModalForDateRangeDuration("We have restricted report duration to "+dateDurationForDetailRepor+" days for the performance reason,in case you wanted to generate report for longer duration please contact system admin.");
													$('#toDate').val("");
												}else{
														if (months < 0) {
															res = false;
															$("#daterangemsg").show();
															$('#toDate').val("");
															alertMsgModal("'To Date' should be greater than 'From Date'");
														} 

												}
											} else {
												if(days_difference > dateDuration ){
													res = false;
													$("#daterangemsg").show();
													alertMsgModalForDateRangeDuration("We have restricted report duration to "+dateDuration+" days for the performance reason,in case you wanted to generate report for longer duration please contact system admin.");
													$('#toDate').val("");
												}else{

													if (years < 0) {
														res = false;
														$("#daterangemsg").show();
														$('#toDate').val("");
														alertMsgModal("'To Date' should be greater than 'From Date'");
													}
												}
												
											}

										}
									})

									

					$('#branchId')
							.change(
									function() {
										if ($('#branchId').val() == null) {
											$('#branchId option[value=ALL]').remove();
											$("#branchId").append("<option value='ALL' selected='selected'>All</option>");
										} else {
											var challanId = $('#branchId').val().toString();
											var challanIdSplit = challanId.split(',');
											var challanlength = challanIdSplit.length;
											if (challanlength >= 2) {
												for (var j = 0; j < challanlength; j++) {
													if (challanIdSplit[j] == 'ALL') {
														$('#branchId option[value='+ challanIdSplit[j]+ ']').remove();
														$("#branchId option").eq(0).before(
														$("<option></option>").val('ALL').text("All"));

													}
												}
											}
										}
									});
									
							$('#project')
							.change(
									function() {
										if ($('#project').val() == null) {
											$('#project option[value=ALL]').remove();
											$("#project").append("<option value='ALL' selected='selected'>All</option>");
										} else {
											var challanId = $('#project').val().toString();
											var challanIdSplit = challanId.split(',');
											var challanlength = challanIdSplit.length;
											if (challanlength >= 2) {
												for (var j = 0; j < challanlength; j++) {
													if (challanIdSplit[j] == 'ALL') {
														$('#project option[value='+ challanIdSplit[j]+ ']').remove();
														$("#project option").eq(0).before(
														$("<option></option>").val('ALL').text("All"));

													}
												}
											}
										}
									});
									
							$('#manager')
							.change(
									function() {
										if ($('#manager').val() == null) {
											$('#manager option[value=ALL]').remove();
											$("#manager").append("<option value='ALL' selected='selected'>All</option>");
										} else {
											var challanId = $('#manager').val().toString();
											var challanIdSplit = challanId.split(',');
											var challanlength = challanIdSplit.length;
											if (challanlength >= 2) {
												for (var j = 0; j < challanlength; j++) {
													if (challanIdSplit[j] == 'ALL') {
														$('#manager option[value='+ challanIdSplit[j]+ ']').remove();
														$("#manager option").eq(0).before(
														$("<option></option>").val('ALL').text("All"));

													}
												}
											}
										}
									});	
									
									
									$('#employee')
							.change(
									function() {
										if ($('#employee').val() == null) {
											$('#employee option[value=ALL]').remove();
											$("#employee").append("<option value='ALL' selected='selected'>All</option>");
										} else {
											var challanId = $('#employee').val().toString();
											var challanIdSplit = challanId.split(',');
											var challanlength = challanIdSplit.length;
											if (challanlength >= 2) {
												for (var j = 0; j < challanlength; j++) {
													if (challanIdSplit[j] == 'ALL') {
														$('#employee option[value='+ challanIdSplit[j]+ ']').remove();
														$("#employee option").eq(0).before(
														$("<option></option>").val('ALL').text("All"));

													}
												}
											}
										}
									});	
									
									
							$('#approvalStatus')
							.change(
									function() {
										if ($('#approvalStatus').val() == null) {
											$('#approvalStatus option[value=ALL]').remove();
											$("#approvalStatus").append("<option value='ALL' selected='selected'>All</option>");
										} else {
											var challanId = $('#approvalStatus').val().toString();
											var challanIdSplit = challanId.split(',');
											var challanlength = challanIdSplit.length;
											if (challanlength >= 2) {
												for (var j = 0; j < challanlength; j++) {
													if (challanIdSplit[j] == 'ALL') {
														$('#approvalStatus option[value='+ challanIdSplit[j]+ ']').remove();
														$("#approvalStatus option").eq(0).before(
														$("<option></option>").val('ALL').text("All"));

													}
												}
											}
										}
									});			
									
				});





var format;
function submitCall() {
	format = "view";
}
function pdfCall() {
	format = "PDF";
}
function excelCall() {
	format = "excel";
}

function limitChangedCalled() {

	reportType = $('input:radio[name=reportType]:checked').val();

	if (reportType.search("Summary") == 0) {
		summaryTimeshetReportCall($('#limit').val(), "0", "1");
		} else {
		detailedTimeshetReportCall($('#limit').val(), "0", "1");
	}

}

function summaryTimeshetReportCall(limit,offset, selectedId) {
	tcolumnHead = "";

	
	reportType = $('input:radio[name=reportType]:checked').val();
	datepicker = $('#fromDate').val();
	datepicker1 = $('#toDate').val();
	branchDropdown = $('#branchId').val();
	projectDropdown = $('#project').val();
	employeeDropdown = $('#employee').val();
	managerDropdown = $('#manager').val();
	approvalStatusdropdown = $('#approvalStatus').val();
	
   	isBranchSelected = 'false', isProjectSelected='false',isManagerSelected = 'false', isEmployeeSelected = 'false', isApprovalStatusSelected = 'false',	totalAmount = 0, cont = 1;
	var levelId ,pkIds;


		tcolumnHead = tcolumnHead + '<th align="center">Branch</th>';
		
		if($('#isProjectSelected').is(':checked')){
		isProjectSelected='true';
		tcolumnHead = tcolumnHead + '<th align="center">Project</th>';
		}
	
		if($('#isManagerSelected').is(':checked')){
		isManagerSelected='true';
		tcolumnHead = tcolumnHead + '<th align="center">Manager</th>';
		}
		if($('#isEmployeeSelected').is(':checked')){
		isEmployeeSelected='true';
		tcolumnHead = tcolumnHead + '<th align="center">Employee</th>';
		}
		tcolumnHead = tcolumnHead + '<th align="center">Total Timesheet</th>';
		  if (approvalStatusdropdown == 'Pending') {
		    isApprovalStatusSelected = 'true';
		    tcolumnHead += '<th align="center">Pending Timesheet</th>';
		} else if (approvalStatusdropdown == 'Approved') {
		    isApprovalStatusSelected = 'true';
		    tcolumnHead += '<th align="center">Approved Timesheet</th>';
		} else if (approvalStatusdropdown == 'ALL') {
		    isApprovalStatusSelected = 'true';
		    tcolumnHead += '<th align="center">Pending Timesheet</th>';
		    tcolumnHead += '<th align="center">Approved Timesheet</th>';
		}else if (approvalStatusdropdown == 'Pending,Approved' ) {
			 isApprovalStatusSelected = 'true';
		    tcolumnHead += '<th align="center">Pending Timesheet</th>';
		    tcolumnHead += '<th align="center">Approved Timesheet</th>';
			}


		

  $.ajax({
    type: "POST",
    contentType: "application/json",
    url: "summaryTimeshetReportCall.html?frmDate=" + datepicker
				+ "&toDate=" + datepicker1 
				+ "&branchDropdown="+ branchDropdown 
				+ "&projectDropdown="+ projectDropdown 
				+ "&managerDropdown="+ managerDropdown 
				+ "&employeeDropdown="+ employeeDropdown 
				+ "&approvalStatusdropdown="+ approvalStatusdropdown 
			    + "&reportType=" + reportType
	        	+ "&isProjectSelected="+isProjectSelected
	        	+ "&isManagerSelected="+isManagerSelected
	        	+"&isEmployeeSelected="+isEmployeeSelected
	        	+"&limit="
	        	+limit
	        	+"&offset="
	        	+offset,
    success: function(data, status, xhr) {
        $("#viewJspLoad,#paginationDiv").show();
        showDynamicTableForsummaryTimeshetReportCall(xhr.responseText, limit,offset, selectedId);
    }
});

}


function showDynamicTableForsummaryTimeshetReportCall(data, limit, offset,selectedId) {
	
	d = $.parseJSON(data);
   
	$.each(d, function(i, item) {
		if (i == 0) {
			overAllTimesheetCount = item['overAllTimesheetCount'];
			totalRecordCount = item['totalRecordCount'];
		}
		i++;
	});
	
	$('#total').val(totalRecordCount);
	createPagination('summaryTimeshetReport', totalRecordCount, offset, limit,selectedId);

	$('#tableDiv').text("");
	var tableString = "<table class='table table-bordered' border='1'>";

	if (d.length > 0) {

	tableString = tableString
				+ "<thead ><th style='text-align: left' colspan=11"
				+ ">Total Timeshets : "
				+ overAllTimesheetCount
			    +" </th></thead>";

		tableString = tableString + "<thead><tr class='info'>" + tcolumnHead
				+ "</tr></thead>";


		$.each(d, function(i, item) {

			tableString += "<tr class='data'>";

			tableString = tableString + "<td>" + item['branchName']
			+ "</td>";
			if($('#isProjectSelected').is(':checked')){
		    tableString = tableString + "<td>" + item['projectName']
		    + "</td>";
		    }
		    if($('#isManagerSelected').is(':checked')){
            tableString = tableString + "<td>" + item['managerName']
            + "</td>";
            }
            if($('#isEmployeeSelected').is(':checked')){
            tableString = tableString + "<td>" + item['employeeName']
            + "</td>";
            }
            tableString = tableString + "<td>" + item['totalTimesheetCount']
            + "</td>";
			 if (approvalStatusdropdown == 'Pending') {
			 tableString = tableString + "<td>" + item['pendingTimesheetCount']
			 + "</td>";
 
			} else if (approvalStatusdropdown == 'Approved') {
			 tableString = tableString + "<td>" + item['approvedTimesheetCount']
			 + "</td>";

			} else if (approvalStatusdropdown == 'ALL') {
			 tableString = tableString + "<td>" + item['pendingTimesheetCount']
			 + "</td>";
			 tableString = tableString + "<td>" + item['approvedTimesheetCount']
			 + "</td>";
			}else if (approvalStatusdropdown == 'Pending,Approved' ) {
			 tableString = tableString + "<td>" + item['pendingTimesheetCount']
			 + "</td>";
			 tableString = tableString + "<td>" + item['approvedTimesheetCount']
			 + "</td>";
			}
		
			tableString = tableString + "</tr>";
			tableString += "</tr>";

		});
		tableString += "</table>";
		$('#tableDiv').html(tableString);
	} else {

		$("#paginationDiv").hide();
		tableString = tableString + "<thead><tr class='info'>" + tcolumnHead
		+ "</tr></thead>";
		
		tableString += "<tr class='data'>";
		tableString = tableString + "<td align='left' style='font: bold;' colspan=" + cont + 1	+ ">No Record Found</td>";

		tableString += "</tr>";
		tableString += "</table>";
		$('#tableDiv').html(tableString);

	}
}


function detailedTimeshetReportCall(limit, offset, selectedId) {
	tcolumnHead = "";

	reportType = $('input:radio[name=reportType]:checked').val();
	datepicker = $('#fromDate').val();
	datepicker1 = $('#toDate').val();
	branchDropdown = $('#branchId').val();
	projectDropdown = $('#project').val();
	employeeDropdown = $('#employee').val();
	managerDropdown = $('#manager').val();
	approvalStatusdropdown = $('#approvalStatus').val();


		isBranchSelected = 'false', isProjectSelected='false',isManagerSelected = 'false', isEmployeeSelected = 'false';
		isApprovalStatusSelected = 'false';


		tcolumnHead = tcolumnHead + '<th align="center">Branch</th>';
		
		if($('#isProjectSelected').is(':checked')){
		isProjectSelected='true';
		tcolumnHead = tcolumnHead + '<th align="center">Project</th>';
		}
		if($('#isManagerSelected').is(':checked')){
		isManagerSelected='true';
		tcolumnHead = tcolumnHead + '<th align="center">Manager</th>';
		}
		if($('#isEmployeeSelected').is(':checked')){
		isEmployeeSelected='true';
		tcolumnHead = tcolumnHead + '<th align="center">Employee</th>';
		}
		tcolumnHead = tcolumnHead + '<th align="center">Work Done</th>';
		tcolumnHead = tcolumnHead + '<th align="center">Timesheet Status</th>';


  $.ajax({
    type: "POST",
    contentType: "application/json",
    url: "detailedTimeshetReportCall.html?frmDate=" + datepicker
				+ "&toDate=" + datepicker1 
				+ "&branchDropdown="+ branchDropdown 
				+ "&projectDropdown="+ projectDropdown 
				+ "&managerDropdown="+ managerDropdown 
				+ "&employeeDropdown="+ employeeDropdown 
				+ "&approvalStatusdropdown="+ approvalStatusdropdown 
			    + "&reportType=" + reportType
	        	+ "&isProjectSelected="+isProjectSelected
	        	+ "&isManagerSelected="+isManagerSelected
	        	+"&isEmployeeSelected="+isEmployeeSelected
	        	+"&limit="
	        	+limit
	        	+"&offset="
	        	+offset,
   		 success: function(data, status, xhr) {
        $("#viewJspLoad,#paginationDiv").show();
        showDynamicTableFordetailedTimeshetReportCall(xhr.responseText, limit,offset, selectedId);
    }
});

}


function showDynamicTableFordetailedTimeshetReportCall(data, limit, offset, selectedId) {

	d = $.parseJSON(data);
	$.each(d, function(i, item) {
		if (i == 0) {
			overAllTimesheetCount = item['overAllTimesheetCount'];
			totalRecordCount = item['totalRecordCount'];
		}
		i++;
	});
	
	$('#total').val(totalRecordCount);
	createPagination('detailedTimeshetReport', totalRecordCount, offset, limit,selectedId);
	

	$('#tableDiv').text("");
	var tableString = "<table class='table table-bordered' border='1'>";

	if (d.length > 0) {

	tableString = tableString
				+ "<thead ><th style='text-align: left' colspan=11"
				+ ">Total Timeshets : "
				+ overAllTimesheetCount
			    +" </th></thead>";

		tableString = tableString + "<thead><tr class='info'>" + tcolumnHead
				+ "</tr></thead>";


		$.each(d, function(i, item) {

			tableString += "<tr class='data'>";

			tableString = tableString + "<td>" + item['branchName']
			+ "</td>";
			if($('#isProjectSelected').is(':checked')){
		    tableString = tableString + "<td>" + item['projectName']
		    + "</td>";
		    }
		    if($('#isManagerSelected').is(':checked')){
            tableString = tableString + "<td>" + item['managerName']
            + "</td>";
            }
            if($('#isEmployeeSelected').is(':checked')){
            tableString = tableString + "<td>" + item['employeeName']
            + "</td>";
            }
            tableString = tableString + "<td>" + item['timesheetWork']
            + "</td>";
             tableString = tableString + "<td>" + item['approvalStatus']
            + "</td>";
	
			tableString = tableString + "</tr>";
			tableString += "</tr>";

		});
		tableString += "</table>";
		$('#tableDiv').html(tableString);
	} else {

		$("#paginationDiv").hide();
		tableString = tableString + "<thead><tr class='info'>" + tcolumnHead
		+ "</tr></thead>";
		
		tableString += "<tr class='data'>";
		tableString = tableString + "<td align='left' style='font: bold;' colspan=" + cont + 1	+ ">No Record Found</td>";

		tableString += "</tr>";
		tableString += "</table>";
		$('#tableDiv').html(tableString);

	}
}



function validation() {
	$('html, body').animate({scrollTop : $('#scrolling').position().top}, 'slow');

	$('#dateError').text("");
	$('#todateError').text("");

	var str = /^\d{4}-\d{2}-\d{2}$/;
	var date = document.TimesheetReportForm.fromDate.value;
	var todate = document.TimesheetReportForm.toDate.value;

	if (date == "") {
		alertMsgModal("Select 'From Date'");
		return false;
	}
	if (todate == "") {
		alertMsgModal("Select 'To Date'");
		return false;
	}
	reportType = $('input:radio[name=reportType]:checked').val();
//		$("#show").empty();
		if (format.search("view") == 0) {
			if (reportType.search("Summary") == 0) {
				summaryTimeshetReportCall($('#limit').val(), "0", "1");
			} else {
				
				detailedTimeshetReportCall($('#limit').val(), "0", "1");
			}

			return false;
		} else {
			$("#submit").attr("disabled", true);
			$("#excel").attr("disabled", true);
			$("#onDownload").attr("disabled", true);
			$("#cashregi").attr("disabled", true);
			$("#pdfwarning").show();
			$("#viewJspLoad,#paginationDiv").hide();
			return true;
		}
	
}

function assignDatatoState(data) {
	d = $.parseJSON(data);
	$.each(d, function(i, item) {
		$("#stateDropDown").append(
				"<option value='" + item['pk_id'] + "'>" + item['stateName']
						+ "</option>")

	});
}


function assignDatatoVan(data) {
	$("#vanNoId").text("");
	$("#vanNoId").append(
			"<option value='All' selected='selected'>All Van</option>");

	d = $.parseJSON(data);
	$.each(d, function(i, item) {

		$("#vanNoId").append(
				"<option value='" + item['pkid'] + "'>" + item['vannumber']
						+ "</option>")
	});
   $("#vanNoId").select2('close');
	$("#vanNoId").select2('open');

}


function alertMsgModal(msg) {
		$("#alertMsgModal").modal();
		$("#alertMsgDisplayId").text(msg);
	}
	
	function alertMsgModalForDateRangeDuration(msg) {
		$("#alertMsgModalForDateRangeDuration").modal();
		$("#alertMsgDisplayIdForDateRangeDuration").text(msg);
	}
	
	
	function createPagination(type, totalCount, offset, limit, selectedId) {

	this.type = type;
	var paginationString = "";
	try {
		var paginationEnd = Math.ceil(selectedId
				/ $('#noOfPageDisplay').val())
				* $('#noOfPageDisplay').val();

		var paginationStart = paginationEnd
				- (($('#noOfPageDisplay').val() - 1));

		var lastPage = Math.ceil($('#total').val() / $('#limit').val());

		paginationString = paginationString
				+ "<ul class='paginationDivStyle bootpag'><li id='first' ><a   onclick='navigationDetailed($(this).text(),this.id)' id='pgf'> ← </a></li>"
				+ "                                 <li id='firstPrev' ><a   onclick='navigationDetailed($(this).text(),this.id)' id='pgp'> « </a></li>"

		for (var i = paginationStart; i <= paginationEnd; i++) {
			if (i <= lastPage) {
				paginationString += "<li class='pointer'><a  onclick='paginationDetailed($(this).text(),this.id)' id='pg"
						+ i + "'>" + i + "</a></li>";
			}

		}

		paginationString = paginationString
				+ "<li id='lastNext' ><a  onclick='navigationDetailed($(this).text(),this.id)' id='pgn'> » </a></li>"
				+ "<li id='last' ><a  onclick='navigationDetailed($(this).text(),this.id)' id='pgl'> → </a></li></ul>";
		$('#paginationDiv').text("");
		$('#paginationDiv').html(paginationString);
		$('#pg' + selectedId).addClass('selected');

		hoverCalled($('.selected').text(), Math.ceil($('#total').val()
				/ $('#limit').val()));
	} catch (e) {
	}
}
function paginationDetailed(text, id) {
	

	var offset = (text * $('#limit').val()) - $('#limit').val();
	paginationFuncationCalled(type, $('#limit').val(), offset, text);
}

function navigationDetailed(text, id) {

	if (id == 'pgf') {
		var currentSelectedPage = $('.selected').text();
		if (currentSelectedPage > 1) {
			paginationFuncationCalled(type, $('#limit').val(), 0, '1');
		}
	}
	if (id == 'pgl') {
		var totalRecords = $('#total').val();
		var totalPages = Math.ceil(totalRecords / $('#limit').val());
		var limit = $('#limit').val();

		var b = (totalPages * $('#limit').val()) - ($('#limit').val());

		var c = totalPages;

		if ($('.selected').text() < Math.ceil(totalRecords
				/ $('#limit').val())) {

			paginationFuncationCalled(type, limit, b, c);

		}

	}
	if (id == 'pgp') {
		var currentSelectedPage = $('.selected').text();
		if (currentSelectedPage > 1) {
			currentSelectedPage -= 1;

			paginationFuncationCalled(type, $('#limit').val(),
					(currentSelectedPage * $('#limit').val())
							- ($('#limit').val()), currentSelectedPage);

		}

	}
	if (id == 'pgn') {
		var currentSelectedPage = parseInt($('.selected').text()) + 1;
		var lastPage = Math.ceil($('#total').val() / $('#limit').val());
		if (currentSelectedPage <= lastPage) {
			var limit = $('#limit').val();
			var offset = (currentSelectedPage * $('#limit').val())
					- ($('#limit').val());
			paginationFuncationCalled(type, limit, offset,
					currentSelectedPage);
			//detailedAjax(limit, offset, currentSelectedPage);
		}
	}
}

function hoverCalled(selectedPage, NOofPages) {
	if (NOofPages != 1) {
		if (selectedPage == 1) {
			$('#firstPrev').addClass('not-allowed');
			$('#first').addClass('not-allowed');
			$('#lastNext').addClass('pointer');
			$('#last').addClass('pointer');
		} else {
			if (selectedPage == NOofPages) {
				$('#firstPrev').addClass('pointer');
				$('#first').addClass('pointer');
				$('#lastNext').addClass('not-allowed');
				$('#last').addClass('not-allowed');
			} else {
				$('#firstPrev').addClass('pointer');
				$('#first').addClass('pointer');
				$('#lastNext').addClass('pointer');
				$('#last').addClass('pointer');
			}
		}
	} else {
		$('#firstPrev').addClass('not-allowed');
		$('#first').addClass('not-allowed');
		$('#lastNext').addClass('not-allowed');
		$('#last').addClass('not-allowed');
	}
}

function paginationFuncationCalled(funcName, limit, offset,
		currentSelectedPage) {

	switch (funcName) {
	case 'detailedTimeshetReport':
		detailedTimeshetReportCall(limit, offset, currentSelectedPage);
		break;
	case 'summaryTimeshetReport':
		summaryTimeshetReportCall(limit, offset, currentSelectedPage);
		break;
	default:
      break;
	}

}

