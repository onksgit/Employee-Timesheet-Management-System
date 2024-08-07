<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<head>
<style>
.card {
	border: 1px solid #ddd;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	max-width: 100%;
}

.card-body {
	padding: 20px;
}

.profile-view {
	display: flex;
	flex-wrap: wrap;
}

.profile-img {
	margin-top: 5px;
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

.profile-basic {
	flex: 1;
}

.profile-info-left {
	margin-bottom: 20px;
}

.user-name {
	margin: 0;
	font-size: 24px;
}

.text-muted {
	color: #777;
}

.staff-id, .doj {
	margin-top: 5px;
}

.personal-info {
	list-style-type: none;
	padding: 0;
}

.personal-info li {
	margin-bottom: 10px;
}

.personal-info .title {
	font-weight: bold;
}

.staff-msg {
	margin-top: 20px;
}

.staff-msg .btn {
	padding: 8px 16px;
	font-size: 14px;
}

h4 {
	text-align: center;
	font-size: 25px;
	color: #333;
}

.vertical-line {
	width: 1px;
	height: 100%;
	background-color: #ccc;
	margin: 0 20px;
}

.title1::before {
	content: "\2022"; /* Bullet character */
	margin-right: 5px; /* Adjust as needed */
	color: #000; /* Color of the bullet */
}

/* Media Queries */
@media ( max-width : 280px) {
	.profile-view {
		flex-direction: column;
		align-items: center;
	}
	.profile-img {
		margin-right: 0;
		margin-bottom: 20px;
	}
	.profile-basic {
		text-align: center;
	}
	.profile-info-left {
		margin-bottom: 20px;
	}
	.user-name {
		font-size: 20px;
	}
	.staff-msg .btn {
		padding: 8px 16px;
		font-size: 12px;
	}
	.personal-info .title {
		font-size: 14px;
	}
	.personal-info .text {
		font-size: 12px;
	}
}

@media ( min-width : 577px) and (max-width: 768px) {
	.profile-img {
		margin-right: 30px;
	}
	.profile-basic {
		flex: 1;
	}
	.profile-info-left {
		margin-bottom: 0;
	}
	.personal-info .title {
		font-size: 16px;
	}
	.personal-info .text {
		font-size: 14px;
	}
}

@media ( min-width : 769px) and (max-width: 1200px) {
	.profile-img {
		width: 150px;
		height: 150px;
		margin-right: 40px;
	}
	.user-name {
		font-size: 24px;
	}
	.staff-msg .btn {
		padding: 10px 20px;
		font-size: 14px;
	}
}

@media ( min-width : 1201px) {
	.profile-img {
		width: 200px;
		height: 200px;
		margin-right: 50px;
	}
	.user-name {
		font-size: 28px;
	}
	.staff-msg .btn {
		padding: 10px 20px;
		font-size: 16px;
	}
}
</style>
</head>
<h4>Employee Profile</h4>
<br>
<div class="card mb-20">
	<div class="card-body">
		<div class="row">
		<div class="col-md-1"></div>
			<div class="col-md-10">
				<div class="profile-view">
					<div class="profile-img-wrap">
						<div class="profile-img">
							<img alt="" src="ImageReadServlet?param=${user.profileImage}">
						</div>
					</div>
					<div class="profile-basic">
						<div class="row">
							<div class="col-md-5">
								<div class="profile-info-left">
									<h3 class="user-name m-t-0 mb-0">${sessionDetails.firstName}
										${sessionDetails.lastName}</h3>
									<small class="text-muted"> Designation
										:${sessionDetails.loggedInUser.fkdesignation.designationName}</small>
									<div class="staff-id">User Name :
										${sessionDetails.username}</div>
									<div class="small doj text-muted">
										Date of Join :
										<fmt:formatDate
											value="${sessionDetails.loggedInUser.joinDate}"
											pattern="yyyy-MM-dd" />
									</div>
									<div class="staff-msg change_password">
										<!-- <a class="btn btn-custom" href="#">Change Password</a> -->
										<a href="EditProfile.html?empCode=${sessionDetails.empCode}">
											<button type="button" class="btn btn-custom pw_modal_btn"
												data-toggle="modal" data-target="#change_pw_modal"
												style="background-color: #337ab7; color: black;">Edit
												Profile</button>
										</a>
									</div>
								</div>
							</div>
							<div class="col-md-7">
								<ul class="personal-info">
									<li>
										<div class="title1">Address</div>
										<div class="text">${sessionDetails.loggedInUser.presentAddress}</div>
									</li>
									<li>
										<div class="title1">Personal Email</div>
										<div class="text">
											<a href="">${sessionDetails.personalEmail}</a>
										</div>
									</li>
									<li>
										<div class="title1">Official Email</div>
										<div class="text">
											<a href="">${sessionDetails.officeEmail}</a>
										</div>
									</li>
									<li>
										<div class="title1">Mobile Number</div>
										<div class="text">${sessionDetails.loggedInUser.mobileNumber}</div>
									</li>
									<li>
										<div class="title1">Gender</div>
										<div class="text">${sessionDetails.loggedInUser.gender}</div>
									</li>
									<li>
										<div class="title1">Marital Status</div>
										<div class="text">${sessionDetails.loggedInUser.maritalStatus}</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>
	</div>
</div>
