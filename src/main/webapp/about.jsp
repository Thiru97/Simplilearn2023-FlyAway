<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${userName == null}">
 <%@ include file="index-nav.jsp"%>
</c:if>
 <c:if test="${userName != null}">
 <%@ include file="user-nav.jsp"%>
</c:if>
	<section class="content__greeting">
			<h1>Welcome to Fly <span>Away </span></h1>
			<h2>Your journey begins here</h2>
			<p>
				FlyAway is a ticket-booking portal that lets people book flights on their website.
			</p>
		</section>
<%@ include file="footer.jsp"%>
</body>
</html>