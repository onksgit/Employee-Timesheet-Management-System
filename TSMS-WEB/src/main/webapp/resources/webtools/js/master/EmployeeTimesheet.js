function validateTimesheetForm(){
	$('#dateerr').text('');
	$('#projectnamerr').text('');
	$('#managererr').text('');
	$('#workdonerr').text('');
	$('#WorkImagePatherror').text('');

	var timesheetDate = $("#timesheetDate").val();
	var projectId = $("#projectId").val();
	var fkManager = $("#fkManager").val();
	var workDone = $("#workDone").val();
	let WorkImageFile = $('#WorkImageFile').val();
	var select = document.getElementById("timesheetType");
      
    if (select.value === 'Timesheet') {
	
	if (timesheetDate.trim() == "" || timesheetDate == null) {
		document.getElementById("dateerr").innerHTML = "Please select timesheet date.";
		return false;
	}
	if (projectId == 0) {
		document.getElementById("projectnamerr").innerHTML = "Please select project.";
		return false;
	}
	if (fkManager == 0) {
		document.getElementById("managererr").innerHTML = "Please select manager.";
		return false;
	}
	if (workDone.trim() == "" || workDone == null) {
		document.getElementById("workdonerr").innerHTML = "Please write some text here.";
		return false;
	}
	if(WorkImageFile==null || WorkImageFile==""){
		document.getElementById('WorkImagePatherror').innerHTML="Select an Image";
		return false;
	}
	}
}
