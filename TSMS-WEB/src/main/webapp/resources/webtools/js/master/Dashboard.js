document.addEventListener('DOMContentLoaded', function() {
	var daily = parseFloat($('#daily').val());
	var weekly = parseFloat($('#weekly').val());
	var monthly = parseFloat($('#monthly').val());

	const jsonData = [
		{ "category": "Today", "value": 30 },
		{ "category": "Weekly", "value": 25 },
		{ "category": "Monthly", "value": 60 },
	];
	
	jsonData.forEach(item => {
    	if (item.category === "Today") {
        	item.value = daily;
    	}
    	if (item.category === "Weekly") {
        	item.value = weekly;
    	}
    	if (item.category === "Monthly") {
        	item.value = monthly;
    	}
	});


	const categories = jsonData.map(item => item.category);
	const values = jsonData.map(item => item.value);

	Highcharts.chart('barChart', {
		chart: {
			type: 'column'//bar,line,scatter,column(for histogram),area,pie
		},
		title: {
			text: 'Timesheet Filled'
		},
		xAxis: {
			categories: categories,
			title: {
				text: 'Timesheet Filled Count'
			}
		},
		yAxis: {
			min: 0,
			title: {
				text: 'Quantity'
			}
		},
		series: [{
			name: 'Quantity',
			data: values
		}],
		exporting: {
			enabled: true, // Enable exporting
			buttons: {
				contextButton: {
					menuItems: ['downloadPNG', 'downloadJPEG', 'downloadPDF', 'downloadSVG', 'separator', 'downloadCSV', 'downloadXLS', 'separator', 'viewFullscreen', 'printChart'] // Types of formats available for download
				}
			}
		}
	});
	
	var dailyAppr = parseFloat($('#dailyAppr').val());
	var weeklyAppr = parseFloat($('#weeklyAppr').val());
	var monthlyAppr = parseFloat($('#monthlyAppr').val());
	const jsonData1 = [
		{ "category": "dailyAppr", "value": 30 },
		{ "category": "weeklyAppr", "value": 25 },
		{ "category": "monthlyAppr", "value": 60 },
	];
	
	jsonData1.forEach(item1 => {
    	if (item1.category === "dailyAppr") {
        	item1.value = dailyAppr;
    	}
    	if (item1.category === "weeklyAppr") {
        	item1.value = weeklyAppr;
    	}
    	if (item1.category === "monthlyAppr") {
        	item1.value = monthlyAppr;
    	}
	});

	const values1 = jsonData1.map(item1 => item1.value);

	Highcharts.chart('barChart1', {
		chart: {
			type: 'column'//bar,line,scatter,column(for histogram),area,pie
		},
		title: {
			text: 'Timesheet Approved'
		},
		xAxis: {
			categories: categories,
			title: {
				text: 'Timesheet Approved Count'
			}
		},
		yAxis: {
			min: 0,
			title: {
				text: 'Quantity'
			}
		},
		series: [{
			name: 'Quantity',
			data: values1
		}],
		exporting: {
			enabled: true, // Enable exporting
			buttons: {
				contextButton: {
					menuItems: ['downloadPNG', 'downloadJPEG', 'downloadPDF', 'downloadSVG', 'separator', 'downloadCSV', 'downloadXLS', 'separator', 'viewFullscreen', 'printChart'] // Types of formats available for download
				}
			}
		}
	});
});

function sendBirthdayWish(mailid, fname){
	if(mailid==null || mailid=='')
	{
		Swal.fire({
				title : 'Not Available!',
				text : 'Employee email address is currently unavailable,Please add it first.',
				icon : 'error',
				confirmButtonText : 'Close',
				showCloseButton : true
			});
	}
	else
	{
	
	$.ajax({
	    url: "BirthdayWish.html?mailid="+mailid+"&fname="+fname,
	    type: "POST",
	    dataType: 'json',
	    success: function (data) {
	       Swal.fire({
				title : 'Success!',
				text : 'Mail send success',
				icon : 'success',
				confirmButtonText : 'Close',
				showCloseButton : true
			});

	    },
	    error: function (data) {
			Swal.fire({
				title : 'Failure!',
				text : 'failure in Mail send',
				icon : 'error',
				confirmButtonText : 'Close',
				showCloseButton : true
			});
		}
	});
	}
}

function sendAnnivarsayWish(mailid, fname){
		if(mailid==null || mailid=='')
	{
		Swal.fire({
				title : 'Mail Adress is Not Presnet!',
				text : 'Employee email address is currently unavailable,Please add it first.',
				icon : 'error',
				confirmButtonText : 'Close',
				showCloseButton : true
			});
	}
	else
	{
	
	$.ajax({
	    url: "AnnivarsayWish.html?mailid="+mailid+"&fname="+fname,
	    type: "POST",
	    dataType: 'json',
	    success: function (data) {
	        Swal.fire({
				title : 'Success!',
				text : 'Mail send success',
				icon : 'success',
				confirmButtonText : 'Close',
				showCloseButton : true
			});
	    },
	    error: function (data) {
			Swal.fire({
				title : 'Failure!',
				text : 'failure in Mail send',
				icon : 'error',
				confirmButtonText : 'Close',
				showCloseButton : true
			});
		}
	});
	
	}
}


$(document).ready(function() {
				
					$(document).ajaxStart(function() {
						$("#myModal").modal('show');
					}).ajaxStop(function() {
						$("#myModal").modal('hide');
					}).ajaxError(function(data, status, xhr) {
						   var ready = status.readyState;
					if (ready != "0")
						 window.location.href = "Logout.html";
					});
					});
					
	

