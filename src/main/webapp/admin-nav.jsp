<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" type="text/css" href="css/styles.css" />
<title>FlyAway</title>
</head>
<body>
	<header>
		<nav class="navbar">
			<div class="navbar__logo">
				<a href="<%request.getContextPath();%>SiteController?action=home">Fly<span>Away</span></a>
				<a href="<%request.getContextPath();%>AdminController?action=admin">Admin</a>
			</div>

			<div class="navbar__links">
				<div class="navbar__dropdown">
					<button class="navbar__dropbtn">Flight Trip data</button>
					<div class="navbar__dropdown-content">
						<a
							href="<%request.getContextPath();%>AdminController?action=tripAdd">Add
							Trip</a> <a
							href="<%request.getContextPath();%>AdminController?action=tripView">View
							Trip</a>
					</div>
				</div>


				<div class="navbar__dropdown">
					<button class="navbar__dropbtn">Master List</button>

					<div class="navbar__dropdown-content">
						<a
							href="<%request.getContextPath();%>AdminController?action=airlineList">Airline
							List</a> <a
							href="<%request.getContextPath();%>AdminController?action=locationList">Source
							and Destination Master List</a> <a
							href="<%request.getContextPath();%>AdminController?action=tripList">Trip
							Master List</a>
					</div>
				</div>

				<div class="navbar__dropdown">
					<button class="navbar__dropbtn">Profile</button>

					<div class="navbar__dropdown-content">
						<a href="AdminController?action=changepassword">Change
							Password</a> <a href="AdminController?action=logout">Logout</a>
					</div>
				</div>
			</div>

		</nav>
	</header>
</body>
</html>