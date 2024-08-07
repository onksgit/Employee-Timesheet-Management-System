<%@page import="java.sql.ResultSet"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/commonpages/MetaDescKey.jsp"%>
<%@include file="/WEB-INF/commonpages/commomcssandjs.jsp"%>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<style>
   .comments {
       position: fixed;
       bottom: 20px;
       right: 20px;
       z-index: 1000;
   }

</style>

<script type="text/javascript">

function showDialog() {
	$("#addComments").modal({
		backdrop : 'static',
		keyboard : false
	});
}
</script>
<body>

<%@include file="/WEB-INF/views/General/NavigationBar.jsp"%>
<c:set var="title" value="${bundleUtil.message(title)}"></c:set>
	<div class="container col-lg-12">
	<c:choose>
			<c:when test="${messageSuccess!=null}">
				<script>
					document.addEventListener('DOMContentLoaded', function() {
						Swal.fire({
							title : 'Success!',
							text : '<c:out value="${bundleUtil.message(messageSuccess)}" />',
							icon : 'success',
							confirmButtonText : 'Close',
							showCloseButton : true
						});
					});
				</script>
			</c:when>
			<c:otherwise></c:otherwise>
	</c:choose>
	<c:choose>
			<c:when test="${messageError != null}">
				<script>
					document.addEventListener('DOMContentLoaded', function() {
						Swal.fire({
							title : 'Failure!',
							text : '<c:out value="${bundleUtil.message(messageError)}" />',
							icon : 'error',
							confirmButtonText : 'Close',
							showCloseButton : true
						});
					});
				</script>
			</c:when>
			<c:otherwise></c:otherwise>
	</c:choose>

		<c:choose>
				<c:when test="${urlLink == null}">
				</c:when>
				<c:otherwise>
					<jsp:include page="${bundleUtil.url(urlLink)}" flush="true" />
				</c:otherwise>
		</c:choose>
	</div>

</body>

</html>