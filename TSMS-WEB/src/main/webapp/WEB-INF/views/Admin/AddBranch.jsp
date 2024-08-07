<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript">
function validate(){
	let branchName = $('#branchName').val();
	if(branchName==null || branchName==""){
		document.getElementById('branchNameerror').innerHTML="Enter branch name";
		return false;
	}
}

function goBack(){
	location.href = "ViewBranches.html";
}
</script>

<div class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
		<div class="col-lg-12" align="left">
			<h3 align="center" class="title">Add Branch</h3>
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
</div>

<div class="col-lg-1"></div>
<div class="col-lg-2"></div>
<div class="well well-lg col-lg-6">
	<form:form name="addBranch" method="POST" class="form-horizontal"
		modelAttribute="branchMaster" role="form"
		action="saveBranchToDB.html"
		onsubmit="return validate()">

		<div class="form-group inputpd">
			<label class="control-label col-sm-5" style="padding-left: 20px;">
				Branch Name:<span style="color: red;">*</span>
			</label>
			<div class="col-sm-5">
				<form:input type="text" path="branchName" id="branchName"
					autocomplete="off" class="form-control" value=""
					placeholder="Branch Name" />
				<span id="branchNameerror" style="color: red;"></span>
			</div>
		</div>

		<hr class="inputpd hrstyle">
		<div class="form-group" align="center">
			<form:button class="btn bg-black" id="save" type="submit">Add</form:button>
			<a href="HrDashboard.html"><button class="btn bg-black" type="button" onclick="goBack()">Back</button></a>
		</div>
	</form:form>
</div>
<div class="col-lg-2"></div>
<div class="col-lg-1"></div>

