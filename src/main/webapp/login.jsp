<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fly Away</title>
</head>
<body>
<%@ include file="index-nav.jsp"%>

	<section class="myform">
		<form class="myform__border" id="loginForm" action="<%request.getContextPath(); %>SiteController?action=login" method="post">
			<h3>Please Login to continue</h3>
			<div class="myform__input">
				<label for="email">Email Id :</label> <input type="Email" id="email" name="emailId"
					placeholder="example@gmail.com" required />
			</div>
			<div class="myform__input">
				<label for="Password">Password :</label> <input type="password" name="password"
					id="password" required />
			</div>
			<div class="myform__button">
				<input class="button" type="submit" value="Login" /> 
				<a class="button" href="SiteController?action=register">New User? Register</a>		
			</div>
		</form>
	</section>
	
<%@ include file="footer.jsp"%>
</body>
</html>