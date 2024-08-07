 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href='<c:url value="/resources/webtools/css/Master/Dashboard.css"></c:url>'>
<script type="text/javascript" src='<c:url value="/resources/webtools/js/master/Dashboard.js"></c:url>'></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<style>
.display {
	border: none;
	outline: none;
	color: black;
	text-align: center;
	font-weight: 600;
	padding: 15px;
	margin: 30px 0 20px 0;
	background: transparent;
	box-shadow: inset 2px 2px 5px #babecc,
		inset -5px -5px 10px #ffffff;
}
@media screen and (max-width: 325px){
	.display {
		font-size: 15px; 
	}
}
</style>



<div class="row">
	<div class="col-lg-1" align="left"></div>
	<div class="col-lg-10">
		<div class="col-lg-12">
			<div class="col-lg-5 well well-lg">
				<div class="col-lg-12">
					<h3 class="display">Today's Birthday</h3>
					<div class="table-responsive">
						<div class="cards-container">
							<c:choose>
								<c:when test="${not empty birthdays}">
									<c:forEach var="birthday" items="${birthdays}">
										<div class="card">
											<img src="ImageReadServlet?param=${birthday.profileImage}"
												alt="${birthday.firstName}'s profile picture"
												class="profile-image">
											<div class="card-content">
												<h4>${birthday.firstName}</h4>
												<button class="btn btn-primary"
													onclick="sendBirthdayWish('${birthday.personalEmail}','${birthday.firstName}');">Send
													Wishes</button>
											</div>
										</div>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<div class="card" align="center">
										<img
											src='<c:url value="/resources/images/not-found.jpg"></c:url>'
											alt="profile picture" class="profile-image">
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-lg-2"></div>
			
			<div class="col-lg-5 well well-lg">
				<div class="col-lg-12">
					<h3 class="display">Work Anniversary</h3>
					<div class="table-responsive">
						<div class="cards-container">
							<c:choose>
								<c:when test="${not empty annivarsay}">
									<c:forEach var="annivarsay" items="${annivarsay}">
										<div class="card">
											<img src="ImageReadServlet?param=${annivarsay.profileImage}"
												alt="${annivarsay.firstName}'s profile picture"
												class="profile-image">
											<div class="card-content">
												<h4>${annivarsay.firstName}</h4>
												<button class="btn btn-primary"
													onclick="sendAnnivarsayWish('${annivarsay.personalEmail}','${annivarsay.firstName}');">Send
													Wishes</button>
											</div>
										</div>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<div class="card">
										<img
											src='<c:url value="/resources/images/not-found.jpg"></c:url>'
											alt="profile picture" class="profile-image">
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-1" align="right"></div>
</div>
