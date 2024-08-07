<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%-- <script type="text/javascript" src='<c:url value="/resources/webtools/js/master/AddEmployee.js"></c:url>'></script> --%>

<style>
.profile-img {
	margin-top: 1px;
	margin-bottom: 20px;
	width: 120px;
	height: 120px;
	border-radius: 50%;
	overflow: hidden;
	margin-right: 20px;
}

.profile-img img {
	margin-bottom: 20px;
	width: 100%;
	height: 100%;
	object-fit: cover;
}
</style>

<div class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
		<div class="col-lg-12" align="left">
			<h3 align="center" class="title">Edit Employee</h3>
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
</div>

<div class="col-lg-1"></div>
<div class="well well-lg col-lg-10">
	<form:form action="UpdateEmployeeToDb.html" method="POST"
		class="form-horizontal" modelAttribute="addUserMasterPojo" role="form"
		enctype="multipart/form-data" onsubmit="return valiadte()">

		<div class="row" align="center">
			<div class="profile-img-wrap">
				<div class="profile-img">
					<img alt=""
						src="ImageReadServlet?param=${userMasterDto.profileImage}">
				</div>
			</div>
		</div>

		<div class="form-group inputpd">
			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Title: 
			</label>
			<div class="col-sm-4">
				<form:select path="title" id="title" class="form-control">
					<option value="NA" selected disabled="disabled">${userMasterDto.title}</option>
<%-- 					<form:option value="Mr.">Mr.</form:option> --%>
<%-- 					<form:option value="Mrs.">Mrs.</form:option> --%>
<%-- 					<form:option value="Ms.">Ms.</form:option> --%>
				</form:select>
				<span id="titleerror" style="color: red;"></span>
			</div>

			<label class="control-label col-sm-2" style="padding-left: 20px;">First
				Name:  
			</label>
			<div class="col-sm-4">
				<form:input type="text" path="firstName" id="firstName"
					autocomplete="off" class="form-control"  disabled="disabled"
					value="${userMasterDto.firstName}" placeholder="First Name" />
				<span id="fnameerror" style="color: red;"></span>
			</div>
		</div>

		<div class="form-group inputpd">
			<label class="control-label col-sm-2" style="padding-left: 20px;">Father
				Name:  
			</label>
			<div class="col-sm-4">
				<form:input type="text" path="fatherName" id="fatherName"
					autocomplete="off" class="form-control" disabled="disabled"
					value="${userMasterDto.fatherName}" placeholder="Father Name" />
				<span id="fathernameerror" style="color: red;"></span>
			</div>

			<label class="control-label col-sm-2" style="padding-left: 20px;">Last
				Name:  
			</label>
			<div class="col-sm-4">
				<form:input type="text" path="lastName" id="lastName"
					autocomplete="off" class="form-control" disabled="disabled"
					value="${userMasterDto.lastName}" placeholder="Last Name" />
				<span id="lnameerror" style="color: red;"></span>
			</div>
		</div>

		<div class="form-group inputpd">

			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Gender:  
			</label>
			<div class="col-sm-4">
				<form:select path="gender" id="gender" class="form-control">
					<option value="NA" selected disabled="disabled">${userMasterDto.gender}</option>
					<form:option value="male">MALE</form:option>
					<form:option value="female">FEMALE</form:option>
					<form:option value="other">OTHER</form:option>
				</form:select>
				<span id="gendererror" style="color: red;"></span>
			</div>

			<label class="control-label col-sm-2" style="padding-left: 20px;">Mobile
				No.:  
			</label>
			<div class="col-sm-4">
				<form:input type="text" path="mobileNumber" id="mobileNumber"
					autocomplete="off" class="form-control"
					value="${userMasterDto.mobileNumber}" placeholder="Mobile Number"
					maxlength="10" />
				<span id="mobileNumbererror" style="color: red;"></span>
			</div>
		</div>

		<div class="form-group inputpd">
			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Present Address: 
			</label>
			<div class="col-sm-4">
			<form:textarea path="presentAddress" autocomplete="off"
			   id="presentAddress" class="form-control"
			   value="${userMasterDto.presentAddress}"
			   placeholder="Present Address"/>
			<span id="presentadderror" style="color: red;"></span>
			</div>
			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Permanent Address:  
			</label>
			<div class="col-sm-4">
				<form:textarea path="permanentAddress" autocomplete="off"
					id="permanentAddress" class="form-control"
					value="${userMasterDto.permanentAddress}"
					placeholder="Enter Permanent Address"></form:textarea>
				<span id="permanentadderror" style="color: red;"></span>
			</div>
		</div>


		<div class="form-group inputpd">
			<label class="control-label col-sm-2" style="padding-left: 20px;">Personal
				Email:  
			</label>
			<div class="col-sm-4">
				<form:input type="text" path="personalEmail" id="personalEmail"
					autocomplete="off" class="form-control"
					value="${userMasterDto.personalEmail}" placeholder="Personal Email" />
				<span id="pemailerror" style="color: red;"></span>
			</div>

			<label class="control-label col-sm-2" style="padding-left: 20px;">D.R.A
				Email:  
			</label>
			<div class="col-sm-4">
				<form:input type="text" path="officeEmail" id="officeEmail"
					autocomplete="off" class="form-control"
					value="${userMasterDto.officeEmail}" placeholder="Office Email" />
				<span id="ofemailerror" style="color: red;"></span>
			</div>
		</div>

		<div class="form-group inputpd">
			<label class="control-label col-sm-2" style="padding-left: 20px;">Aadhaar
				No:  
			</label>
			<div class="col-sm-4">
				<form:input type="text" path="adharNo" id="adharNo"
					autocomplete="off" class="form-control"
					value="${userMasterDto.adharNo}" placeholder="Aadhaar Number"
					maxlength="12" />
				<span id="adharNoerror" style="color: red;"> </span>
			</div>

			<label class="control-label col-sm-2" style="padding-left: 20px;">Pan
				No.:  
			</label>
			<div class="col-sm-4">
				<form:input type="text" path="panNo" id="panNo" autocomplete="off"
					class="form-control" value="${userMasterDto.panNo}"
					placeholder="Pan Number" maxlength="10" />
				<span id="panNoerror" style="color: red;"></span>
			</div>
		</div>


		<div class="form-group inputpd">
			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Marital Status:  
			</label>
			<div class="col-sm-4">
				<form:select path="maritalStatus" id="maritalStatus"
					class="form-control">
					<option value="NA" selected disabled="disabled">${userMasterDto.maritalStatus}</option>
					<form:option value="Married">Married</form:option>
					<form:option value="Unmarried">Unmarried</form:option>
				</form:select>
				<span id="maritalStatusererror" style="color: red;"></span>
			</div>

			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Birth Date:  
			</label>
			<div class="col-sm-4">
				<form:input type="date" path="birthDate" id="birthDate"
					value="${userMasterDto.birthDate}" autocomplete="off"
					class="form-control" placeholder="Birth Date" />
				<span id="birthDateerror" style="color: red;"></span>
			</div>
		</div>

		<div class="form-group inputpd">
			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Employee Code:  
			</label>
			<div class="col-sm-4">
				<form:input path="empCode" id="empCode"
					value="${userMasterDto.empCode}" class="form-control"
					placeholder="Enter emp code"  disabled="" readonly="true"/>
				<span id="empCoderror" style="color: red;"></span>
			</div>

			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Department:  
			</label>
			<div class="col-sm-4">
				<form:input path="department" id="department" class="form-control"
					value="${userMasterDto.department}" placeholder="Enter Department" />
				<span id="depterror" style="color: red;"></span>
			</div>
		</div>

		<div class="form-group inputpd">
			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Salary:  
			</label>
			<div class="col-sm-4">
				<form:input type="text" path="salary" autocomplete="off" id="salary"
					class="form-control" placeholder="Salary"
					value="${userMasterDto.salary}" />
				<span id="salaryerror" style="color: red;"></span>
			</div>

			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Joining Date:  
			</label>
			<div class="col-sm-4">
				<form:input type="date" path="joinDate" id="joinDate"
					value="${userMasterDto.joinDate}" autocomplete="off"
					class="form-control" placeholder="Joining Date" />
				<span id="joinDateerror" style="color: red;"></span>
			</div>
		</div>

		<div class="form-group inputpd">
			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Designation:  
			</label>
			<div class="col-sm-4">
				<form:select path="fkDesignation" id="fkDesignation"
					class="form-control">
					<option selected="selected" disabled="disabled" value="${userMasterDto.fkDesignation.primaryId}">${userMasterDto.fkDesignation.designationName}</option>
					<c:forEach var="designationList" items="${designationList}">
						<form:option value="${designationList.primaryId}">${designationList.designationName}</form:option>
					</c:forEach>
				</form:select>
				<span id="fkDesignationerror" style="color: red;"></span>
			</div>
			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Branch:  
			</label>
			<div class="col-sm-4">
				<form:select path="branch" id="branch" class="form-control">
					<option value="${userMasterDto.branch}" selected>${userMasterDto.branch}</option>
					<c:forEach var="branchList" items="${branchList}">
						<form:option value="${branchList.branchName}">${branchList.branchName}</form:option>
<%-- 						<c:choose> --%>
<%-- 						    <c:when test="${branchList.primaryId.toString() == userMasterDto.branch}"> --%>
<%-- 						        <form:option value="${branchList.primaryId}" selected="selected">${branchList.branchName}</form:option> --%>
<%-- 						    </c:when> --%>
<%-- 						    <c:otherwise> --%>
<%-- 						        <form:option value="${branchList.primaryId}">${branchList.branchName}</form:option> --%>
<%-- 						    </c:otherwise> --%>
<%-- 						</c:choose> --%>
					</c:forEach>
				</form:select>
				<span id="branherror" style="color: red;"></span>
			</div>
		</div>

		<div class="form-group inputpd">
			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Access Role:  
			</label>
			<div class="col-sm-4">
				<form:select path="fkUserRoleId" id="fkUserRoleId"
					class="form-control">
					<option value="${userMasterDto.fkUserRoleId}" selected disabled="disabled">${userMasterDto.fkUserRoleId.profileName}</option>
					<c:forEach var="userRoleMasters" items="${userRoleMasters}">
						<form:option value="${userRoleMasters.primaryId}">${userRoleMasters.profileName}</form:option>
					</c:forEach>
				</form:select>
				<span id="accessRoleerror" style="color: red;"></span>
			</div>

			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Assign Project:  
			</label>
			<div class="col-sm-4">
				<form:select path="fkProject" id="fkProject" class="form-control progControlSelect2">
					<c:forEach var="userProjectManager" items="${userProjectManager}">
					  <option value="${userProjectManager.fkProject.primaryId}" selected>${userProjectManager.fkProject.projectName}</option>
					</c:forEach>
					<c:forEach var="projectList" items="${projectList}">
						<form:option value="${projectList.primaryId}">${projectList.projectName}</form:option>
					</c:forEach>
				</form:select>
				<span id="prjnmerror" style="color: red;"></span>
			</div>
		</div>
		
		<div class="form-group inputpd">
			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Exit Date:  
			</label>
			<div class="col-sm-4">
				<form:input type="date" path="exitDate" id="exitDate"
					value="${userMasterDto.exitDate}" autocomplete="off"
					class="form-control" placeholder="Exit Date" />
				<span id="exitDateerror" style="color: red;"></span>
			</div>
			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Is Active:
			</label>
			<div class="col-sm-4">
				<label class="radio-inline"> <form:radiobutton
						path="isActive" value="true" /> Yes
				</label> <label class="radio-inline"> <form:radiobutton
						path="isActive" value="false" /> No
				</label> <span id="isActiveerror" style="color: red;"></span>
			</div>
		</div>

		<div class="form-group inputpd">
<!-- 			<label class="control-label col-sm-2" style="padding-left: 20px;"> -->
<!-- 				Reporting Manager:   -->
<!-- 			</label> -->
<!-- 			<div class="col-sm-4"> -->
<%-- 				<form:select path="fkManager" id="fkManager" class="form-control"> --%>
<%-- 					<option value="${userMasterDto.fkManager.primaryId}" selected>${userMasterDto.fkManager.firstName} --%>
<%-- 						${userMasterDto.fkManager.lastName}</option> --%>
<%-- 					<c:forEach var="managerList" items="${managerList}"> --%>
<%-- 						<form:option value="${managerList.primaryId}">${managerList.firstName} ${managerList.lastName}</form:option> --%>
<%-- 					</c:forEach> --%>
<%-- 				</form:select> --%>
<!-- 				<span id="fkManagererror" style="color: red;"></span> -->
<!-- 			</div> -->

			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Profile Image:
			</label>
			<div class="col-sm-4">
				<form:input type="File" path="profileImageFile"
					id="profileImageFile" class="form-control" />
				<span id="profileImagePatherror" style="color: red;"></span>
			</div>
		</div>

		

		<hr class="inputpd hrstyle">
		<div class="form-group" align="center">
			<form:button class="btn bg-black" id="save" type="submit"
				onclick="AddEmployee()">Update 
			</form:button>
			<form:button class="btn bg-black" type="button"
				onclick="javascript:goBack()">Back</form:button>
		</div>
	</form:form>
</div>
<div class="col-lg-1"></div>


<script>
$(document).ready(function(){
	
		$(".progControlSelect2").select2({
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


function goBack() {
	location.href = "ViewAllEmployee.html";
}
</script>
