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
	
	<% if(request.getSession().getAttribute("adminName") == null){
		response.sendRedirect("admin-login.jsp");
	}
	%>
	
	<%@ include file="admin-nav.jsp"%>

	<section>
		<div class="content__greeting">
			<h1>
				Hello <span>Admin</span>
			</h1>
		</div>
	</section>

	<%@ include file="footer.jsp"%>

</body>
</html>
