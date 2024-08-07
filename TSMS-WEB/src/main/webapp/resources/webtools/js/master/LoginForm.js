$(document).ready(function(){

});

function valiadtion(){
	const userName = $('#userName').val();
	const password = $('#password').val();
	if(userName==null || userName==""){
		document.getElementById('userNameErr').innerHTML="Enter UserName";
		return false;
	}
	
	if(password==null || password==""){
		document.getElementById('passwordErr').innerHTML="Enter Password";
		return false;
	}
}