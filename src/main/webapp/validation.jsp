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


<% String message = (String)request.getAttribute("validation"); %>
	<div class="content__description">
			
			<h1> <%= message %></h1>
	</div>
	
<%@ include file="footer.jsp"%>

</body>
</html>