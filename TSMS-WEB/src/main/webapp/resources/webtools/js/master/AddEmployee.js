$(document).ready(function(){
	
	 			$(".progControlSelect2").select2({
						placeholder : "select"
					});
		
				$('#fkProject')
							.change(
									function() {
										if ($('#fkProject').val() == null) {
											$('#fkProject option[value=SELECT]').remove();
											$("#fkProject").append("<option value='SELECT' selected='selected' >select</option>");
										} else {
											var challanId = $('#fkProject').val().toString();
											var challanIdSplit = challanId.split(',');
											var challanlength = challanIdSplit.length;
											if (challanlength >= 2) {
												for (var j = 0; j < challanlength; j++) {
													if (challanIdSplit[j] == 'SELECT') {
														$('#fkProject option[value='+ challanIdSplit[j]+ ']').remove();
														$("#fkProject option").eq(0).before(
														$("<option></option>").val('SELECT').text("select"));

													}
												}
											}
										}
									});
									
									

});

function valiadte(){
	let firstName = $('#firstName').val();
	let fatherName = $('#fatherName').val();
	let lastName = $('#lastName').val();
	let gender = $('#gender').val();
	let mobileNumber = $('#mobileNumber').val();
	let birthDate = $('#birthDate').val();
	let presentAddress = $('#presentAddress').val();
	let permanentAddress = $('#permanentAddress').val();
	let personalEmail = $('#personalEmail').val();
	let officeEmail = $('#officeEmail').val();
	let adharNo = $('#adharNo').val();
	let panNo = $('#panNo').val();
	let maritalStatus = $('#maritalStatus').val();
	let joinDate = $('#joinDate').val();
	let empCode = $('#empCode').val();
	let salary = $('#salary').val();
	let department = $('#department').val();
	let fkDesignation = $('#fkDesignation').val();
	let title = $('#title').val();
	let branch = $('#branch').val();
	let accessRole = $('#fkUserRoleId').val();
	let fkProject = $('#fkProject').val();
	let fkManager = $('#fkManager').val();
	let profileImageFile = $('#profileImageFile').val();
	
	if(title=="NA"){
		document.getElementById('titleerror').innerHTML="Select Title";
		return false;
	}
	
	if(firstName==null || firstName==""){
		document.getElementById('fnameerror').innerHTML="Enter first name";
		return false;
	}
	
//	if(fatherName==null || fatherName==""){
//		document.getElementById('fathernameerror').innerHTML="Enter father Name";
//		return false;
//	}
	
	if(lastName==null || lastName==""){
		document.getElementById('lnameerror').innerHTML="Enter last Name";
		return false;
	}
	
	if(gender=="NA"){
		document.getElementById('gendererror').innerHTML="select Gender";
		return false;
	}
	
//	if(mobileNumber==null || mobileNumber==""){
//		document.getElementById('mobileNumbererror').innerHTML="Enter Mobile No";
//		return false;
//	}
	
//	if(presentAddress==null || presentAddress==""){
//		document.getElementById('presentadderror').innerHTML="Enter Present Address";
//		return false;
//	}
//	
//	if(permanentAddress==null || permanentAddress==""){
//		document.getElementById('permanentadderror').innerHTML="Enter permanent Address";
//		return false;
//	}
	
	if(personalEmail==null || personalEmail==""){
		document.getElementById('pemailerror').innerHTML="Enter personal mail";
		return false;
	}
	
//	if(officeEmail==null || officeEmail==""){
//		document.getElementById('ofemailerror').innerHTML="Enter official mail";
//		return false;
//	}
	
//	if(adharNo==null || adharNo==""){
//		document.getElementById('adharNoerror').innerHTML="Enter aadhar no";
//		return false;
//	}
//	
//	if(panNo==null || panNo==""){
//		document.getElementById('panNoerror').innerHTML="Enter pan card no";
//		return false;
//	}
//	
//	if(birthDate==null || birthDate==""){
//		document.getElementById('birthDateerror').innerHTML="Select birth Date";
//		return false;
//	}
	
	if(empCode==null || empCode==""){
		document.getElementById('empCoderror').innerHTML="Enter emp code";
		return false;
	}
//	if(maritalStatus=="NA" || maritalStatus==null){
////		document.getElementById('maritalStatusererror').innerHTML="select marital status";
//		return false;
//	}
	
	if(department==null || department==""){
		document.getElementById('depterror').innerHTML="Enter department";
		return false;
	}
	
	if(salary==0.0){
		document.getElementById('salaryerror').innerHTML="Enter Salary";
		return false;
	}
	
	if(joinDate==null || joinDate==""){
		document.getElementById('joinDateerror').innerHTML="select joining date";
		return false;
	}
	
	if(fkDesignation==0){
		document.getElementById('fkDesignationerror').innerHTML="select designation";
		return false;
	}
	
	if(branch==0){
		document.getElementById('branherror').innerHTML="Select branch";
		return false;
	}
	
	if(accessRole==0){
		document.getElementById('accessRoleerror').innerHTML="Select access role";
		return false;
	}
	
	if(fkProject==0 || fkProject=='SELECT'){
		document.getElementById('prjnmerror').innerHTML="Select Project";
		return false;
	}
	
//	if(fkManager==0){
//		document.getElementById('fkManagererror').innerHTML="Select Manager";
//		return false;
//	}
	
//	if(profileImageFile==null || profileImageFile==""){
//		document.getElementById('profileImagePatherror').innerHTML="Select Image";
//		return false;
//	}
}

function goBack(){
	location.href="ViewAllEmployee.html";
}

