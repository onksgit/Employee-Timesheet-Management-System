<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript">
$(document).ready(function(){
	const notMatched="${notMatched}";
	if(notMatched != null || notMatched != ""){
		document.getElementById('confirmPass').innerHTML= notMatched;
		return false;
	}
});

</script>
<div class="row">
	<div class="col-lg-12" align="center">
		<h3>Change Password</h3>
	</div>
</div>

<div class="col-lg-1"></div>

<div class="col-lg-2"></div>
<div class="well well-lg col-lg-6">
	<form:form action="ChangePassword.html" method="POST" class="form-horizontal" modelAttribute="changePassword" name="ChangePassword"
		role="form">

		<div class="form-group inputpd">
			<label class="control-label col-sm-5" style="padding-left: 20px;">
				User Name : <span style="color: red;">*</span>
			</label>
			<div class="col-sm-6">
				<form:input type="text" path="userName" value="${sessionDetails.username}" autocomplete="off"
					id="addedTime" class="form-control" placeholder="Old Password" readonly="true"/>
			</div>
		</div>
		
		<div class="form-group inputpd">
			<label class="control-label col-sm-5" style="padding-left: 20px;">
				Old Password : <span style="color: red;">*</span>
			</label>
			<div class="col-sm-6">
				<form:input type="text" path="oldPassword" autocomplete="off"
					id="addedTime" class="form-control" placeholder="Old Password"/> <span
					id="oldPass" style="color: red;"></span>
					<form:errors path="oldPassword" style="color:red;"></form:errors>
			</div>
		</div>

		<div class="form-group inputpd">
			<label class="control-label col-sm-5" style="padding-left: 20px;">
				New Password : <span style="color: red;">*</span>
			</label>
			<div class="col-sm-6">
				<form:input type="text" path="newPassword" autocomplete="off"
					id="addedTime" class="form-control" placeholder="New Password"/> <span
					id="newPass" style="color: red;"></span>
			</div>
		</div>
		
		<div class="form-group inputpd">
			<label class="control-label col-sm-5" style="padding-left: 20px;">
				Confirm Password : <span style="color: red;">*</span>
			</label>
			<div class="col-sm-6">
				<form:input type="text" path="confirmPassword" autocomplete="off"
					id="addedTime" class="form-control" placeholder="Confirm Password"/> <span
					id="confirmPass" style="color: red;"></span>
			</div>
		</div>
		
		<hr class="inputpd hrstyle">
		<div class="form-group" align="center">
			<button class="btn bg-black" id="save" type="submit">Update</button>
			<button class="btn bg-black" type="button"
				onclick="javascript:goBack()">Back</button>
		</div>
	</form:form>
</div>
<div class="col-lg-2"></div>
<div class="col-lg-1"></div>
