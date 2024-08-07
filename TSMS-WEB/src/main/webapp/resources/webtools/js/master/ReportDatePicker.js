$(document).ready(function() {
	var minYear = 2019;
	$("#fromDate").datepicker({
		dateFormat: 'dd-mm-yy',
		changeMonth: true,
		changeYear: true,
		maxDate: '-1',
		minDate: '01-01-' + minYear
	});

	$("#EndDate").datepicker({
		dateFormat: 'dd-mm-yy',
		changeMonth: true,
		changeYear: true,
		disabled: true,
		maxDate: '0',
		minDate: '01-01-' + minYear
	});
	
	var startDate = $('#fromDate').val();
	var endDate = $('#EndDate').val();

	$("#fromDate,#EndDate").change(function() {
		var dateDuration = 370;
		var dateDurationForDetailRepor = 31;

		if ($('#fromDate').val().trim() == "") {
			frmDate = "";
		} else {
			frmDateSplit = $('#fromDate').val().trim().split("-");
			frmDate = frmDateSplit[2] + "-" + frmDateSplit[1] + "-" + frmDateSplit[0];

			//if ($('input:radio[name=isHistory]:checked').val().search("history") == 0) {
			$("#EndDate").datepicker("option", "disabled", false);
			var date11 = new Date(frmDate);
			var newCurrentDate = new Date();
			var currentdatedays = new Date(newCurrentDate);
			var differenceInDays = currentdatedays - date11;
			var daysDifference = Math.floor(differenceInDays / (1000 * 60 * 60 * 24));
			if ($('input:radio[name=reportType]:checked').val() == 'DetailsReport') {
				if ((daysDifference - 1) < parseInt(dateDurationForDetailRepor)) {
					var newDate = new Date(date11);
					newDate.setDate((newDate.getDate() + daysDifference - 1));
				} else {
					var newDate = new Date(date11);
					newDate.setDate(newDate.getDate() + parseInt(dateDurationForDetailRepor) - 1);
				}
			} else {
				//				if ((daysDifference - 1) < parseInt(dateDuration)) {
				//					var newDate = new Date(date11);
				//					newDate.setDate((newDate.getDate() + daysDifference - 1));
				//				} else {
				//					var newDate = new Date(date11);
				//					newDate.setDate(newDate.getDate() + parseInt(dateDuration) - 1);
				//				}
				if ((daysDifference - 1) < parseInt(dateDurationForDetailRepor)) {
					var newDate = new Date(date11);
					newDate.setDate((newDate.getDate() + daysDifference - 1));
				} else {
					var newDate = new Date(date11);
					newDate.setDate(newDate.getDate() + parseInt(dateDurationForDetailRepor) - 1);
				}
			}

			const originalDate = new Date(newDate);
			const year = originalDate.getFullYear();
			const month = String(originalDate.getMonth() + 1).padStart(2, '0');
			const day = String(originalDate.getDate()).padStart(2, '0');
			$("#EndDate").datepicker("option", "maxDate", `${day}-${month}-${year}`);
			//}
		}

		if ($('#EndDate').val().trim() == "") {
			toDate = "";
		} else {
			toDateSplit = $('#EndDate').val().trim().split("-");
			toDate = toDateSplit[2] + "-" + toDateSplit[1] + "-" + toDateSplit[0];
		}

		if (frmDate == "" || toDate == "") {
			res = false;
			if (frmDate == "") {
				if ((id.search('EndDate') == 0) || (id.search('fromDate') == 0)) {
					alert("Select 'From Date'");
				}
			}
		} else {
			var date1 = new Date(frmDate);
			var date2 = new Date(toDate);
			var year1 = 31536000000;
			var time_difference = date2.getTime() - date1.getTime();
			var days_difference = time_difference / (86400000);

			if (frmDateSplit[1] <= 2) {
				//var leap = 0;
				if ((frmDateSplit[2] % 400 == 0) || (frmDateSplit[2] % 4 == 0 && frmDateSplit[2] % 100 != 0)) {
					year1 = year1 + 86400000;
				}
			} else if (toDateSplit[1] >= 2) {
				if ((toDateSplit[2] % 400 == 0) || (toDateSplit[2] % 4 == 0 && toDateSplit[2] % 100 != 0)) {
					year1 = year1 + 86400000;
				}
			}

			var diff_date = date2 - date1;
			var years = Math.floor(diff_date / year1);
			var months = Math.floor((diff_date % year1) / 2628000000);
			var days = Math.floor(((diff_date % year1) % 2628000000) / 86400000);

			if ($('input:radio[name=reportType]:checked').val() == 'DetailsReport') {
				if (days_difference > dateDurationForDetailRepor) {
					res = false;
					alert("We have restricted report duration to " + dateDurationForDetailRepor + " days for the performance reason,in case you wanted to generate report for longer duration please contact system admin.");
					$('#EndDate').val("");
				} else {
					if (months < 0) {
						res = false;
						$('#EndDate').val("");
						alert("'To Date' should be greater than 'From Date'");
					}
				}
			} else {
				if (days_difference > dateDuration) {
					res = false;
					alert("We have restricted report duration to " + dateDuration + " days for the performance reason,in case you wanted to generate report for longer duration please contact system admin.");
					$('#EndDate').val("");
				} else {
					if (years < 0) {
						res = false;
						$('#EndDate').val("");
						alert("'To Date' should be greater than 'From Date'");
					}
				}
			}
		}
	});
	
	if (startDate != "" && endDate != "") {
		$("#EndDate").datepicker("option", "disabled", false);
	}
});



function validate() {

	if ($('#fromDate').val().trim() == "") {
		alert("Select 'From Date'");
		return false;
	}

	if ($('#EndDate').val().trim() == "") {
		alert("Select 'To Date'");
		return false;
	}
}

