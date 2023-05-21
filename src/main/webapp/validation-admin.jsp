<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<c:if test="${redirect }">
<% response.setHeader("Refresh", "2; URL=AdminController?action=adminDashboard"); %>
</c:if>

	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="css/styles.css" />
		<title>Fly Away</title>
	</head>
	<body>
		<% String message = (String)request.getAttribute("validation"); %>
	
		<%@ include file = "admin-nav.jsp" %>

			<div class="content__description">
			
			<h1> <%= message %></h1>
		</div>
		<%@ include file = "footer.jsp" %>
	</body>
</html>
