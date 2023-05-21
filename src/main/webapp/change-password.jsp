<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/styles.css" />
<title>FlyAway</title>
</head>
<body>
	<%@ include file="admin-nav.jsp"%>

	<section name="content">
		<div class="myform">


			<form class="myform__border" action="<%request.getContextPath(); %>AdminController?action=changePassword" method="post">
				<h3>Change Password</h3>
				<div class="myform__input">
					Email Id : <input type="email" name="emailId"
						placeholder="admin@gmail.com">
				</div>
				<div class="myform__input">
					Previous Password : <input type="password" name="password">
				</div>
				<div class="myform__input">
					New Password: <input type="password" name="newPassword">
				</div>

				<div class="myform__button">

					<input class="button" type="Submit" value="Change Password" />
				</div>

			</form>
		</div>
	
	</section>

	<%@ include file="footer.jsp"%>

</body>
</html>
