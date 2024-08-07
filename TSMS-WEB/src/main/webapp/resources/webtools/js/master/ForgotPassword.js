$(document).ready(function(){

});
	
function validation(){
	const userName = $('#userName').val();
	
	if(userName==null || userName==""){
		document.getElementById('userNameErr').innerHTML="Enter UserName";
		return false;
	}
}

function goBack() { 
	location.href = "Login.html";
}