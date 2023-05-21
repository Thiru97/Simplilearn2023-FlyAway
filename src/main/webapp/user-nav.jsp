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
		<header>
			<nav class="navbar">
				<div class="navbar__logo">
					<a href="<%request.getContextPath(); %>SiteController?action=home">Fly<span>Away</span></a>
				</div>
				<div class="navbar__links">
					<ul>
						<li><a href="<%request.getContextPath(); %>SiteController?action=about">About</a></li>
				
						<li>
							<a href="<%request.getContextPath(); %>SiteController?action=myTickets">My Tickets</a>
						</li>
						<li><a href="<%request.getContextPath(); %>SiteController?action=logout">Logout</a></li>
					</ul>
				</div>
			</nav>
		</header>
	</body>
</html>
