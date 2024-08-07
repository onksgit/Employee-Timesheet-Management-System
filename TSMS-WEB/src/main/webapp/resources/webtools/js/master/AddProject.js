$(document).ready(function(){

});

function validate(){
	let projectName = $('#projectName').val();
	let projectDesc = $('#projectDescription').val();
	let projectManager = $('#projectManager').val();
	let department = $('#department').val();
	
	if(projectName==null || projectName==""){
		document.getElementById('pnameerror').innerHTML="Enter project Name";
		return false;
	}
	
//	if(projectDesc==null || projectDesc==""){
//		document.getElementById('pdescerror').innerHTML="Enter project Desc";
//		return false;
	//}

//	if(projectManager==0){
	//	document.getElementById('pManagererror').innerHTML="Select Project Manager";
	//	return false;
	//}
	
	//if(department==null || department==""){
	//	document.getElementById('depterror').innerHTML="Enter department";
	//	return false;
	//}
	
}

function goBack() {
	location.href = "ViewProjects.html";
}