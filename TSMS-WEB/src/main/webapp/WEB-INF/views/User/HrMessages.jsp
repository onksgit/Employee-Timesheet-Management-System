<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
 <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WhatsApp Message View</title>
</head>
<body>

    <center>
    <div class="chat-container">
        <div class="message">
        <c:forEach var="msg" items="${mHrSendMSGs}">
            <div class="message-from">From:${msg.msgSender}</div>
              <div class="message-from">To:${msg.msgReciver}</div>
             <div class="message-date" data-datetime="${msg.msgSentDate}"></div>
            <div class="message-content">${msg.msgString}</div><br>
            </c:forEach>
        </div>        
    </div>
    </center>
</body>
<script>
        document.addEventListener("DOMContentLoaded", function() {
            const dateElements = document.querySelectorAll('.message-date');
            dateElements.forEach(function(element) {
                const dateTimeString = element.getAttribute('data-datetime');
                const formattedDateTime = moment(dateTimeString).format('MM/DD/YYYY hh:mm A');
                element.innerText = formattedDateTime;
            });
        });
    </script>

<style>

/* Container */
.chat-container {
    width: 60%;
    height: 80%;
    background-color: #fff;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    border-radius: 20px;
    padding: 20px;
    overflow-y: auto;
    max-height: 80vh;
    border: 1px solid #ddd;
    margin: 0 auto;
}

/* Individual Message */
.message {
    margin-bottom: 20px;
}

.message-from{
    font-size: 10px; /* Smaller text for the date */
    color: #999;
    text-align: left; /* Align text to the left */
    margin-bottom: 5px;
}

/* Message Date */
.message-date {
    font-size: 10px; /* Smaller text for the date */
    color: #999;
    text-align: left; /* Align text to the left */
    margin-bottom: 5px;
}


/* Message Content */
.message-content {
    background-color: #dcf8c6;
    text-align: left; 
    color: #000;
    padding: 15px; /* Larger padding for more prominent messages */
    border-radius: 15px; /* More rounded corners */
    display: block; /* Block display for full-width alignment */
    width: calc(100% - 30px); /* Adjust width to account for padding */
    word-wrap: break-word;
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
    margin-left: 0; /* Align messages to the left */
    font-size: 14px; /* Larger text for the message content */
    margin-top: 5px; /* Spacing between date and message content */
}

/* Optional: Styling for a sender name or "from" label */
.message-from {
    font-size: 12px;
    color: #555;
    text-align: left;
    margin-bottom: 2px;
}

/* Responsive Design */
@media screen and (max-width: 768px) {
    .chat-container {
        width: 90%;
    }
    
    .message-content {
        font-size: 12px; /* Slightly smaller font size for mobile */
        padding: 10px; /* Adjust padding for smaller screens */
        width: calc(100% - 20px); /* Adjust width for smaller padding */
    }
}

</style>
</html>
