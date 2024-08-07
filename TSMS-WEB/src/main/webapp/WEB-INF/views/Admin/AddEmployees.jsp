<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript" src='<c:url value="/resources/webtools/js/master/AddEmployee.js"></c:url>'></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


<div class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
		<div class="col-lg-12" align="left">
			<h3 align="center" class="title">Add Employee</h3>
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
</div>

<div class="col-lg-1"></div>

<div class="well well-lg col-lg-10">
	<form:form action="SaveEmployeeToDb.html" method="POST"
		class="form-horizontal" modelAttribute="addUserMasterPojo" role="form"
		enctype="multipart/form-data"
		onsubmit="return valiadte()">


		<div class="form-group inputpd">
				<label class="control-label col-sm-3" style="padding-left: 20px;">
				Title<span style="color: red;">*</span>
			</label>
			<div class="col-sm-3">
			<form:select path="title" id="title" class="form-control">
					<option value="NA" selected>SELECT</option>
					<form:option value="Mr.">Mr.</form:option>
					<form:option value="Mrs.">Mrs.</form:option>
					<form:option value="Ms.">Ms.</form:option>
				</form:select>
				<span id="titleerror" style="color: red;"></span>
			</div>
			
			<label class="control-label col-sm-2" style="padding-left: 20px;">First
				Name:<span style="color: red;">*</span>
			</label>
			<div class="col-sm-3">
				<form:input type="text" path="firstName" id="firstName"
					autocomplete="off" class="form-control" value=""
					placeholder="First Name" />
				<span id="fnameerror" style="color: red;"></span>
			</div>
		</div>

		<div class="form-group inputpd">
					<label class="control-label col-sm-3" style="padding-left: 20px;">Father
				Name:
			</label>
			<div class="col-sm-3">
				<form:input type="text" path="fatherName" id="fatherName"
					autocomplete="off" class="form-control" value=""
					placeholder="Father Name" />
				<span id="fathernameerror" style="color: red;"></span>
			</div>
			
			<label class="control-label col-sm-2" style="padding-left: 20px;">Last
				Name:<span style="color: red;">*</span>
			</label>
			<div class="col-sm-3">
				<form:input type="text" path="lastName" id="lastName"
					autocomplete="off" class="form-control" value=""
					placeholder="Last Name" />
				<span id="lnameerror" style="color: red;"></span>
			</div>
		</div>

		<div class="form-group inputpd">
		
			<label class="control-label col-sm-3" style="padding-left: 20px;">
				Gender<span style="color: red;">*</span>
			</label>
			<div class="col-sm-3">
				<form:select path="gender" id="gender" class="form-control" >
					<option value="NA" selected>SELECT</option>
					<form:option value="male">MALE</form:option>
					<form:option value="female">FEMALE</form:option>
					<form:option value="other">OTHER</form:option>
				</form:select>
				<span id="gendererror" style="color: red;"></span>
			</div>
			
			<label class="control-label col-sm-2" style="padding-left: 20px;">Mobile No.: </label>
			<div class="col-sm-3">
				<form:input type="text" path="mobileNumber" id="mobileNumber"
					autocomplete="off" class="form-control" value=""
					placeholder="Mobile Number" maxlength="10"/>
				<span id="mobileNumbererror" style="color: red;"></span>
			</div>
		</div>

		<div class="form-group inputpd">
			<label class="control-label col-sm-3" style="padding-left: 20px;"> Present Address</label>
			<div class="col-sm-3">
				<form:textarea path="presentAddress" autocomplete="off"
					id="presentAddress" class="form-control"
					placeholder="Present Address"></form:textarea>
				<span id="presentadderror" style="color: red;"></span>
			</div>

			<label class="control-label col-sm-2" style="padding-left: 20px;">Permanent Address</label>
			<div class="col-sm-3">
				<form:textarea path="permanentAddress" autocomplete="off"
					id="permanentAddress" class="form-control"
					placeholder="Enter Permanent Address"></form:textarea>
				<span id="permanentadderror" style="color: red;"></span>
			</div>
		</div>


		<div class="form-group inputpd">
			<label class="control-label col-sm-3" style="padding-left: 20px;">Personal
				Email:<span style="color: red;">*</span>
			</label>
			<div class="col-sm-3">
				<form:input type="text" path="personalEmail" id="personalEmail"
					autocomplete="off" class="form-control" value=""
					placeholder="Personal Email" />
				<span id="pemailerror" style="color: red;"></span>
			</div>

			<label class="control-label col-sm-2" style="padding-left: 20px;">Official
				Email:
			</label>
			<div class="col-sm-3">
				<form:input type="text" path="officeEmail" id="officeEmail"
					autocomplete="off" class="form-control" value=""
					placeholder="Office Email" />
				<span id="ofemailerror" style="color: red;"></span>
			</div>
		</div>

		<div class="form-group inputpd">
			<label class="control-label col-sm-3" style="padding-left: 20px;">Aadhaar
				No.:
			</label>
			<div class="col-sm-3">
				<form:input type="text" path="adharNo" id="adharNo"
					autocomplete="off" class="form-control" value=""
					placeholder="Aadhaar Number" maxlength="12"/>
				<span id="adharNoerror" style="color: red;"> </span>
			</div>

			<label class="control-label col-sm-2" style="padding-left: 20px;">Pan
				No.:
			</label>
			<div class="col-sm-3">
				<form:input type="text" path="panNo" id="panNo" autocomplete="off"
					class="form-control" value="" placeholder="Pan Number" maxlength="10"/>
				<span id="panNoerror" style="color: red;"></span>
			</div>
		</div>


		<div class="form-group inputpd">
			<label class="control-label col-sm-3" style="padding-left: 20px;">Marital Status</label>
			<div class="col-sm-3">
				<form:select path="maritalStatus" id="maritalStatus"
					class="form-control">
					<option value="NA" selected disabled>SELECT</option>
					<form:option value="married">Married</form:option>
					<form:option value="unmarried">Unmarried</form:option>
				</form:select>
				<span id="maritalStatusererror" style="color: red;"></span>
			</div>
			
			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Birth Date:
			</label>
			<div class="col-sm-3">
				<form:input type="date" path="birthDate" id="birthDate"
					autocomplete="off" class="form-control" placeholder="Birth Date" />
				<span id="birthDateerror" style="color: red;"></span>
			</div>
		</div>

		<div class="form-group inputpd">
			<label class="control-label col-sm-3" style="padding-left: 20px;">
				Employee Code<span style="color: red;">*</span>
			</label>
			<div class="col-sm-3">
				<form:input path="empCode" id="empCode" class="form-control" placeholder="Enter emp code"/>
				<span id="empCoderror" style="color: red;"></span>
			</div>

			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Department<span style="color: red;">*</span>
			</label>
			<div class="col-sm-3">
				<form:input path="department" id="department" class="form-control" placeholder="Enter Department" />
				<span id="depterror" style="color: red;"></span>
			</div>
		</div>

		<div class="form-group inputpd">
			<label class="control-label col-sm-3" style="padding-left: 20px;">
				Salary<span style="color: red;">*</span>
			</label>
			<div class="col-sm-3">
				<form:input type="text" path="salary" autocomplete="off" id="salary"
					class="form-control" placeholder="Salary" />
				<span id="salaryerror" style="color: red;"></span>
			</div>
			
			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Joining Date:<span style="color: red;">*</span>
			</label>
			<div class="col-sm-3">
				<form:input type="date" path="joinDate" id="joinDate"
					autocomplete="off" class="form-control" placeholder="Joining Date" />
				<span id="joinDateerror" style="color: red;"></span>
			</div>
		</div>
		
	<div class="form-group inputpd">
			<label class="control-label col-sm-3" style="padding-left: 20px;">
				Designation:<span style="color: red;">*</span>
			</label>
			<div class="col-sm-3">
				<form:select path="fkDesignation" id="fkDesignation" class="form-control">
					<option value="0" selected>SELECT</option>
					<c:forEach var="designationList" items="${designationList}">
						<form:option value="${designationList.primaryId}">${designationList.designationName}</form:option>
					</c:forEach>
				</form:select>
				<span id="fkDesignationerror" style="color: red;"></span>
			</div>
			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Branch:<span style="color: red;">*</span>
			</label>
			<div class="col-sm-3">
				<form:select path="branch" id="branch" class="form-control">
					<option value="0" selected>SELECT</option>
					<c:forEach var="branchList" items="${branchList}">
						<form:option value="${branchList.branchName}">${branchList.branchName}</form:option>
					</c:forEach>
				</form:select>
				<span id="branherror" style="color: red;"></span>
			</div>
		</div>
		
		<div class="form-group inputpd">
			<label class="control-label col-sm-3" style="padding-left: 20px;">
				Access Role:<span style="color: red;">*</span>
			</label>
			<div class="col-sm-3">
				<form:select path="fkUserRoleId" id="fkUserRoleId" class="form-control"> 
					<option value="0" selected disabled="disabled">SELECT</option>
						<c:forEach var="userRoleMasters" items="${userRoleMasters}">
						<form:option value="${userRoleMasters.primaryId}">${userRoleMasters.profileName}</form:option>
					</c:forEach>
				</form:select>
				<span id="accessRoleerror" style="color: red;"></span>
			</div>
			<label class="control-label col-sm-2" style="padding-left: 20px;">
				Assign Project:<span style="color: red;">*</span>
			</label>
			<div class="col-sm-3">
				<form:select path="fkProject" id="fkProject" class="form-control progControlSelect2"> 
					<option value='SELECT' selected='selected' >select</option>
					<c:forEach var="projectList" items="${projectList}">
						<form:option value="${projectList.primaryId}">${projectList.projectName}</form:option>
					</c:forEach>
				</form:select>
				<span id="prjnmerror" style="color: red;"></span>
			</div>
		</div>

		<div class="form-group inputpd">
<!-- 			<label class="control-label col-sm-3" style="padding-left: 20px;"> -->
<!-- 				Reporting Manager:<span style="color: red;">*</span> -->
<!-- 			</label> -->
<!-- 			<div class="col-sm-3"> -->
<%-- 				<form:select path="fkManager" id="fkManager" class="form-control"> --%>
<!-- 					<option value="0" selected>SELECT</option> -->
<%-- 					<c:forEach var="managerList" items="${managerList}"> --%>
<%-- 						<form:option value="${managerList.primaryId}">${managerList.firstName} ${managerList.lastName}</form:option> --%>
<%-- 					</c:forEach> --%>
<%-- 				</form:select> --%>
<!-- 				<span id="fkManagererror" style="color: red;"></span> -->
<!-- 			</div> -->
			
			
			<label class="control-label col-sm-3" style="padding-left: 20px;">Profile Image</label>
			<div class="col-sm-3">
				<form:input type="File" path="profileImageFile"
					id="profileImageFile" class="form-control" />
				<span id="profileImagePatherror" style="color: red;"></span>
			</div>
		</div>

		<hr class="inputpd hrstyle">
		<div class="form-group" align="center">
			<form:button class="btn bg-black" id="save" type="submit"
				onclick="AddEmployee()">Add 
			</form:button>
			<form:button class="btn bg-black" type="button"
				onclick="javascript:goBack()">Back</form:button>
		</div>
	</form:form>
</div>
<div class="col-lg-1"></div>
