<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FlyAway</title>
</head>
<body>
		<header>
			<nav class="navbar">
				<div class="navbar__logo">
					<a href="<%request.getContextPath(); %>SiteController?action=home">Fly<span>Away</span></a>
				</div>
				<div class="navbar__links">
					<ul>
						<li><a href="<%request.getContextPath(); %>SiteController?action=about">About</a></li>
						<li><a href="<%request.getContextPath(); %>AdminController?action=AdminLogin" >Login</a></li>
					</ul>
				</div>
			</nav>
		</header>
</body>
</html>