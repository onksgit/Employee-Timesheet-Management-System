<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src='<c:url value="/resources/webtools/js/master/AddProject.js"></c:url>'></script>

<div class="row">
	<div class="col-lg-1"></div>
	<div class="col-lg-10">
		<div class="col-lg-12" align="left">
			<h3 align="center" class="title">Add Project</h3>
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
</div>

<div class="col-lg-1"></div>

<div class="col-lg-2"></div>
<div class="well well-lg col-lg-6">
	<form:form name="addProject" method="POST" class="form-horizontal"
		modelAttribute="projectMaster" role="form"
		action="saveProjectToDB.html"
		onsubmit="return validate()">

		<div class="form-group inputpd">
			<label class="control-label col-sm-5" style="padding-left: 20px;">
				Project Name:<span style="color: red;">*</span>
			</label>
			<div class="col-sm-5">
				<form:input type="text" path="projectName" id="projectName"
					autocomplete="off" class="form-control" value=""
					placeholder="Project Name" />
				<span id="pnameerror" style="color: red;"></span>
			</div>
		</div>

		<div class="form-group inputpd">
			<label class="control-label col-sm-5" style="padding-left: 20px;">
				Project Description
			</label>
			<div class="col-sm-5">
				<form:textarea path="projectDescription" autocomplete="off"
					id="projectDescription" class="form-control"
					placeholder="Project Description" onblur=""
					onkeypress="return isValidInput(event)"></form:textarea>
				<span id="pdescerror" style="color: red;"></span>
			</div>
		</div>

		<div class="form-group inputpd">
			<label class="control-label col-sm-5" style="padding-left: 20px;">
				Project Manager:
			</label>
			<div class="col-sm-5">
				<form:select type="" path="projectManager" id="projectManager" class="form-control">
					<option value="0" selected>-Select-</option>
					<c:forEach var="managerList" items="${managerList}">
						<form:option value="${managerList.primaryId}">${managerList.firstName} ${managerList.lastName}</form:option>
					</c:forEach>
				</form:select>
				<span id="pManagererror" style="color: red;"></span>
			</div>
		</div>

		<hr class="inputpd hrstyle">
		<div class="form-group" align="center">
			<form:button class="btn bg-black" id="save" type="submit">Add</form:button>
			<form:button class="btn bg-black" type="button" onclick="goBack()">Back</form:button>
		</div>
	</form:form>
</div>
<div class="col-lg-2"></div>
<div class="col-lg-1"></div>



