<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<section class="content">
			<div class="myform">
				<form class="myform__border" action="SiteController?action=ticketManager" method="post">
					<h3>Enter Card Details</h3>
					<input type ="hidden" name="id" value="${id}"/>
					<div class="myform__input">
						<label for="cardHolderName">Card Holder Name :</label>
						<input type="text" />
					</div>
					<div class="myform__input">
						<label for="cardNumber">Card Number :</label>
						<input type="number"  />
					</div>
					<div class="myform__input">
						<label for="cardHolderName">Amount:</label>
						<input type="number" value="${totalAmount}" disabled />
					</div>
					<div class="myform__button">
						<input class="button" type="submit" value="Pay" />
						<a class="button" href="SiteController?action=home">Cancel</a>
					</div>
				</form>
			</div>
		</section>


	
	
	
	<%@ include file="footer.jsp"%>

</body>
</html>