<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-lg-12" align="center">
		<h4 class="title">Send Messages</h4>
		<form:form action="sendHrMessage.html" method="POST"
			class="form-horizontal" modelAttribute="hrSendMSGDto" role="form"
			enctype="multipart/form-data"
			onsubmit="return validateTimesheetForm()">
			<div class="container">
				<div class="message-box">
					<div class="receiver-container">
						<form:select id="msgReciver" path="msgReciver"
							class="receiver-dropdown">
							<option value="" disabled selected>Select Receiver</option>
							<form:option value="All">All</form:option>
							<form:option value="AllManager">All Manager's</form:option>
							<option value="AllEmployee">All Employee</option>
							<c:forEach var="AllEmployeeList" items="${AllEmployeeList}">
								<form:option value="${AllEmployeeList.empCode}">${AllEmployeeList.firstName} ${AllEmployeeList.lastName}</form:option>
							</c:forEach>
						</form:select>
					</div>
					<div class="input-container">
						<form:textarea id="message" path="msgString" class="message-input"
							placeholder="Type your message..."></form:textarea>
						<form:button class="send-button" id="save" type="submit">Send</form:button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
	
	
</div>



<style>
.container {
    width: 100%;
    max-width: 600px;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}

.message-box {
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    width: 90%;
    height: 80%;
    max-height: 500px;
    overflow: hidden;
    padding: 20px;
    
}

.receiver-container {
    background-color: #f7f7f7;
    padding: 10px;
    border-bottom: 1px solid #ddd;
    border-radius: 5px;
}

.receiver-dropdown {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 16px;
    background-color: #fff;
    cursor: pointer;
}

.input-container {
    display: flex;
    align-items: center;
    padding: 10px;
    border-top: 1px solid #ddd;
    background-color: #fafafa;
    flex: 1;
    border-radius: 5px;
    margin-top: 20px;
}

.message-input {
    flex: 1;
    height: 80px;
    border: 1px solid #ccc;
    border-radius: 20px;
    padding: 15px;
    font-size: 16px;
    resize: none;
    margin-right: 10px;
}

.send-button {
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 50%;
    width: 50px;
    height: 50px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    display: flex;
    justify-content: center;
    align-items: center;
}

.send-button:hover {
    background-color: #0056b3;
}

@media (max-width: 600px) {
    .container {
        padding: 0;
    }

    .message-box {
        border-radius: 0;
        height: 100vh;
        width: 100%;
        max-height: none;
        padding: 10px;
    }

    .receiver-container,
    .input-container {
        padding: 10px;
    }

    .receiver-dropdown,
    .message-input,
    .send-button {
        font-size: 14px;
        padding: 8px;
    }
}
</style>
</html>