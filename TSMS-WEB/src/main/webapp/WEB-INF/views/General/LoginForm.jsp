<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/commonpages/MetaDescKey.jsp"%>
<%@include file="/WEB-INF/commonpages/npmdistcss.jsp"%>
<script type="text/javascript" src='<c:url value="/resources/webtools/js/master/LoginForm.js"></c:url>'></script>
</head>

<body style="background-color: #f6f6f6;">
	<div class="container-fluid login-container">
		<div class="row login-row">		
			<div class="col-lg-6" style="margin: auto;">
			<div class="login-section" style="background-color:  white; border-radius: 15px;">					
			<div class="login-nav-logo">
<!-- 			<h1>TSMS Login</h1> -->
				<img class=" mrg-heading-h1-cm" style="width: 320px" src='<c:url value="/resources/images/DRA-logo-new.png"></c:url>'>
				<h5 class="mrg-center-cm">Login</h5>
			</div>
				<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-12 login-form-section">
							<form:form action="Login.html" method="POST" class="login-form" modelAttribute="LoginDto" onsubmit="return valiadtion()">
								<div class="form-group">
									<label class="control-label col-sm-12">User ID <span
										style="color: red;">*</span></label>
									<div class="col-lg-12 col-sm-12">
										<form:input class="form-control text-uppercase"
											path="username" id="userName" placeholder="ENTER USER ID" maxlength="12" />
										<span id="userNameErr" style="color: red;"></span>
										<form:errors path="username" style="color:red;"></form:errors>
									</div>
								</div>
								<div class="form-group">
							<label class="control-label col-sm-12">Password <span style="color: red;">*</span></label>
								<div class="col-lg-12 col-sm-12">
									<form:input class="form-control" path="password" id="password" placeholder="ENTER PASSWORD" maxlength="15"/>
									<span id="passwordErr" style="color: red;"></span>
									<form:errors path="password" style="color:red;"></form:errors>
								</div>
						</div>
						<div class="form-group last">
							<div class="col-sm-12">
								<button type="submit" id="SignIn" class="btn login-btn" style="width: 49%; margin-top:5px;">Sign In</button>
						        <button type="reset" class="btn login-btn" style="width: 49%; margin-top:5px;">Reset</button>
							</div>
						</div>
				
						<div class="form-group last">
							<div class="col-sm-offset-6 col-sm-9">
								<label class="col-sm-offset-1">
									<a class="hiper-link-cm" href="ForgotPassword.html" style="text-decoration: none;">Forgot password ? </a>
								</label>
							</div>
						</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>