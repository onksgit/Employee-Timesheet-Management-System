<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href='<c:url value="/resources/webtools/css/Master/Dashboard.css"></c:url>'>
<script type="text/javascript" src='<c:url value="/resources/webtools/js/master/Dashboard.js"></c:url>'></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script>
function validationSendMsgForm()
{
	let message = $('#message').val();
	let msgReciver = $('#msgReciver').val();

	if(msgReciver=="NA" || msgReciver==null || msgReciver==""){
		document.getElementById('recivererror').innerHTML="Please Select Reciver";
		return false;
	}
	
	if(message=="NA" || message==null || message==""){
		document.getElementById('messageerror').innerHTML="Please Enter Message";
		return false;
	}
}
</script>

<div class="col-lg-12" style="margin-bottom: 2%;">
	<div class="col-lg-4">
		<div class="col-lg-12" align="center">
		<h4 align="center" class="title">Send Messages</h4>
		<form:form action="sendHrMessage.html" method="POST"
			class="form-horizontal" modelAttribute="hrSendMSGDto" role="form"
			enctype="multipart/form-data"
			onsubmit="return validationSendMsgForm()">
			<div class="chat-container">
				<div class="message-box" style="margin-top: 5%; margin-bottom: 5%;">
					<div class="receiver-container"  style="background-color:#dcf8c6">
						<form:select id="msgReciver" path="msgReciver"
							class="receiver-dropdown">
							<option value="NA" disabled selected>Select Receiver</option>
							<form:option value="All">All</form:option>
							<form:option value="AllManager">All Manager's</form:option>
							<option value="AllEmployee">All Employee</option>
							<c:forEach var="branchList" items="${branchList}">
								<form:option value="${branchList.branchName}">${branchList.branchName} Branch</form:option>
							</c:forEach>
							
							<c:forEach var="AllEmployeeList" items="${AllEmployeeList}">
								<form:option value="${AllEmployeeList.empCode}">${AllEmployeeList.firstName} ${AllEmployeeList.lastName}</form:option>
							</c:forEach>
						</form:select>
						<span id="recivererror" style="color: blue;"></span>
					</div>
						<div class="input-container" style="background-color:#dcf8c6">
							Attachment:<form:input type="File" path="msgFile"
							id="msgFile" class="form-control" />
					</div>
						<div class="input-container" style="background-color:#dcf8c6">
						<form:textarea id="message" path="msgString" class="message-input"
							placeholder="Type a message..." ></form:textarea>
						<form:button class="send-button" id="save" type="submit"><i class="fa fa-paper-plane"></i></form:button>
					</div>
					<span id="messageerror" style="color: blue;"></span>
					
				</div>
			</div>
		</form:form>
	</div>
	
	

	<div class="col-lg-12" style="margin-top: 4%;">
			<h4 align="center" style="margin-top: 5%;" class="title">Notifications</h4>
			<div class="chat-container">
				<div class="message">
						<c:choose>
						<c:when test="${not empty mHrSendMSGs}">
								<c:forEach var="msg" items="${mHrSendMSGs}">
								<div class="message-from">From:${msg.msgSender}    To:${msg.msgReciver}</div>
								<div class="message-date"><fmt:formatDate value="${msg.msgSentDate}" pattern="dd-MMM-yyyy HH:mm" /></div>
							<c:choose>
							<c:when test="${not empty msg.msgFilePath}">
								<div class="pdf-container">
									<div class="pdf-file">
										<a href="PdfReadServletcon?param=${msg.msgFilePath}"  style="text-decoration: none;"
											target="_blank">
											<div class="message-content">
												<div class="pdf-icon">
                                                   <i class="fa fa-file"></i> 
									            <span style="margin-left: 5px">
               								    Attachment
               								 	</div></a>
												${msg.msgString}
											</div>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="message-content">${msg.msgString}</div>
							</c:otherwise>
						</c:choose>	<br>
							</c:forEach>
						</c:when>
						<c:otherwise>
						<div class="message-content">No Messages</div>
						</c:otherwise>
						</c:choose>
				</div>
			</div>
		</div>
	</div>

	<div class="col-lg-8">
		<h4 align="center" class="title">Timesheet</h4>
		<div class="col-lg-12 well well-lg">
			<div class="col-lg-6">
				<input type="hidden" value="${dailyCount}" id="daily"> <input
					type="hidden" value="${weeklyCount}" id="weekly"> <input
					type="hidden" value="${monthlyCount}" id="monthly">
				<div id="barChart" style="margin-top: 5%;"></div>
			</div>
			<div class="col-lg-6">
				<input type="hidden" value="${dailyApprCount}" id="dailyAppr">
				<input type="hidden" value="${weeklyApprCount}" id="weeklyAppr">
				<input type="hidden" value="${monthlyApprCount}" id="monthlyAppr">
				<div id="barChart1" style="margin-top: 5%;"></div>
			</div>
		</div>

		<div class="col-lg-12">
			<div class="col-lg-5 well well-lg">
				<h3 class="display" style="background-color:#dcf8c6">Today's Birthday</h3>
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
											<h4>${birthday.firstName} ${birthday.lastName}</h4>
											<c:if test="${sessionDetails.loggedInUser.fkUserRoleId.primaryId != 4 and sessionDetails.loggedInUser.fkUserRoleId.primaryId != 3}">
												<button class="btn btn-primary" id="birthdaybtn"
													onclick="sendBirthdayWish('${birthday.personalEmail}','${birthday.firstName}');">Send
													Wishes</button>
											</c:if>
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
			<div class="col-lg-2"></div>
			<div class="col-lg-5 well well-lg">
				<h3 class="display" style="background-color:#dcf8c6">Work Anniversary</h3>
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
											<h4>${annivarsay.firstName} ${annivarsay.lastName}</h4>
											<c:if test="${sessionDetails.loggedInUser.fkUserRoleId.primaryId != 4 and sessionDetails.loggedInUser.fkUserRoleId.primaryId != 3}">
												<button class="btn btn-primary"
													onclick="sendAnnivarsayWish('${annivarsay.personalEmail}','${annivarsay.firstName}');">Send
													Wishes</button>
											</c:if>
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

<div id="myModal" class="modal fade progress_dialog" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<p align="center" style="color: #523F6D;">
					Please Wait...<img alt="" height="60"
						src='<c:url value="/resources/images/loading_150.gif"></c:url>'>
				</p>
			</div>
		</div>
	</div>
</div> 
