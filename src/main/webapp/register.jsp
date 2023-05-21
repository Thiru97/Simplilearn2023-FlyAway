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
		<form class="myform__border" action="SiteController?action=registerUser" method="post">
			<h3> Register </h3>
			<div class="myform__input">
				<label for="First Name">First Name :</label> <input type="text" name = "firstName"
					id="fName" placeholder="John" required />
			</div>

			<div class="myform__input">
				<label for="Last Name">Last Name :</label> <input type="text" name = "lastName"
					id="lName" placeholder="Doe" required />
			</div>

			<div class="myform__input">
				<label for="Age">Age :</label> <input type="number" id="age" name ="age"
					placeholder="20" required />
			</div>

			<div class="myform__input">
				<label for="Email Address">Email Address :</label> <input
				name="emailId"	type="email" id="email" placeholder="example@gmail.com" required />
			</div>
			<div class="myform__input">
				<label for="Password">Password :</label> <input type="password" name="password"
					id="password" required />
			</div>
			<div class="myform__input">
				<label for="Confirm Password">Confirm Password:</label> <input
				name="cPassword"	type="password" id="cPassword"  required />
			</div>
			<div class="myform__button">
				<input class="button" type="Submit" value="Submit" />
			</div>
		</form>
	</section>

<%@ include file="footer.jsp"%>
</body>
</html>