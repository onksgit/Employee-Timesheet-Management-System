
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script>
function goBack() {
	location.href = "UserProfile.html";
}
</script>
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

<div class="container col-lg-12">
	
	<div class="row">
		<div class="col-lg-1"></div>
			<div class="col-lg-12" align="center">
				<h3 class="title">User Information</h3>
			</div>
		</div>
	</div>

	<div class="col-lg-1"></div>
	<div class="well well-lg col-lg-10">
	
	<div class="row" align="center">
	<div class="profile-img-wrap">
		<div class="profile-img">
			<img alt="" src="ImageReadServlet?param=${userMasterDto.profileImage}">
		</div>
	</div>
	</div>

	<form:form action="userPostDetails.html" method="POST"
		      class="form-horizontal" modelAttribute="addUserMasterPojo" role="form"
		     enctype="multipart/form-data">
			<form:hidden path="primaryId" id="primaryId" value="${userMasterDto.primaryId}" htmlEscape="true" />	
			<div class="row">
				<div class="form-group col-md-1"></div>
				<div class="form-group col-md-5">
					<label for="firstName">First Name </label>
<%-- 					<form:input id="firstName" class="form-control" --%>
<%-- 						value="${user.firstName}" path="firstName" placeholder="Enter firstName" minlength="2" maxlength="30" onkeydown="return /[a-z, ]/i.test(event.key)"></form:input> --%>
					<div class="form-control" path="firstName" style="background-color: #F5F5F5">${userMasterDto.firstName}</div>
				</div>
				<div class="form-group col-md-1"></div>
				<div class="form-group col-md-5">
				 <label for="lastName">Last Name </label>
					<div class="form-control" path="lastName" style="background-color: #F5F5F5">${userMasterDto.lastName}</div>
				</div>
			</div>

			<div class="row">
			<div class="form-group col-md-1"></div>
				<div class="form-group col-md-5">
					<label for="empCode">Emp Code </label>
					<div class="form-control" path="empCode" style="background-color: #F5F5F5">${userMasterDto.empCode}</div>
				</div>
				<div class="form-group col-md-1"></div>
				<div class="form-group col-md-5">
					<label for="mobileno">Mobile No. </label>				
					<form:input cssClass="form-control" path="mobileNumber"
					placeholder="Enter mobileNumber" value="${userMasterDto.mobileNumber}" id="mobileNumberId"
					maxlength="10"></form:input>
				</div>
		  </div>			
				<div class="row">
				<div class="form-group col-md-1"></div>
					<div class="form-group col-md-5">
					<label for="maritalstatus">Marital Status</label> 
					<form:select path="maritalStatus" name="maritalStatus" class="form-control" id='maritalStatusID'>						
						<option value="${userMasterDto.maritalStatus}" selected="selected" disabled="disabled">${userMasterDto.maritalStatus}</option>						
						<form:option value="Married">Married</form:option>
					    <form:option value="Unmarried">Unmarried</form:option>
					</form:select>
					</div>
				<div class="form-group col-md-1"></div>			
			<div class="form-group col-md-5">
				<label for="email"> Offical Email</label>
				<form:input cssClass="form-control" path="officeEmail"
					id="officeEmailId" name="officeEmail" value="${userMasterDto.officeEmail}"
					placeholder="Enter Email"></form:input>
			</div>
				</div>
				
				<div class="row">
				<div class="form-group col-md-1"></div>
				<div class="form-group col-md-5">
				<label for="adharNo">Adhar No.:</label>
				<form:input type="text" path="adharNo" id="adharNo"
					autocomplete="off" class="form-control"
					value="${userMasterDto.adharNo}" placeholder="Adhar Number"
					maxlength="12" />
				<span id="adharNoerror" style="color: red;"> </span>
				</div>
				
				<div class="form-group col-md-1"></div>
				<div class="form-group col-md-5">
				<label for="panNo">Pan No.:</label>
				<form:input type="text" path="panNo" id="panNo"
					autocomplete="off" class="form-control"
					value="${userMasterDto.panNo}" placeholder="Pan Number"
					maxlength="12" />
				<span id="panNoerror" style="color: red;"> </span>
				</div>
				</div>
				
				<div class="row">
				<div class="form-group col-md-1"></div>
				<div class="form-group col-md-5">
				<label for="presentAddress">Present Address:</label>
				<form:textarea type="text" path="presentAddress" id="presentAddress"
					autocomplete="off" class="form-control"
					value="${userMasterDto.presentAddress}" placeholder="Present Address"
					 ></form:textarea>
				<span id="presentAddresserror" style="color: red;"> </span>
				</div>
				
				<div class="form-group col-md-1"></div>
				<div class="form-group col-md-5">
				<label for="permanentAddress">Permanent Address</label>
				<form:textarea type="text" path="permanentAddress" id="permanentAddress"
					autocomplete="off" class="form-control"
					value="${userMasterDto.permanentAddress}" placeholder="PermanentAddress"
					 ></form:textarea>
				<span id="permanentAddresserror" style="color: red;"> </span>
				</div>
				</div>
				
				
				<div class="row">
			<div class="form-group col-md-1"></div>			
			<div class="form-group col-md-5">
				<label for="profileImage"> Profile Image </label>
				<form:input type="File" path="profileImageFile"
					id="profileImageFile" class="form-control" />
			</div>
				<form:hidden path="profileImage" id="profileImage" value="${userMasterDto.profileImage}" htmlEscape="true" />	
			
			</div>
				<br><div class="row" align="center">
					<div class="form-group col-md-12">
					<button type="submit" class="btn bg-black">Update</button>
					<button onclick="javascript:goBack()" type="reset" class="btn bg-black">Back</button>
					</div>
				</div>
			</form:form>
	</div>
	<div class="col-lg-1"></div>


