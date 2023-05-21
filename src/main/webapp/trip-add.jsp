<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<section class="content">
		<form class="myform__border" action="AdminController?action=addTrip"
			method="post" autocomplete="on">
			<h3>Add Trip Details</h3>
			<div class="myform">
				<div class="myform__top">
					<div class="myform__left">
						<label for="source">Source :</label>
						<div class="myform__input">
							Country Name:<input type="text" name="sourceCountryName"
								placeholder="India" required />
						</div>
						<div class="myform__input">
							City Name:<input type="text" name="sourceCityName"
								placeholder="Chennai" required />
						</div>
						<div class="myform__input">
							Airport Name:<input type="text" name="sourceAirportName"
								placeholder="Chennai International Airport" required />
						</div>
						<div class="myform__input">
							Departure date:<input type="date" name="departureDate"
								min="${today}" required />
						</div>
						<div class="myform__input">
							Departure time:<input type="time" name="departureTime" required />
						</div>
					</div>
					<div class="myform__right">
						<label for="source">Destination :</label>
						<div class="myform__input">
							Country Name:<input type="text" name="destinationCountryName"
								placeholder="India" required />
						</div>
						<div class="myform__input">
							City Name:<input type="text" name="destinationCityName"
								placeholder="Banglore" required />
						</div>
						<div class="myform__input">
							Airport Name:<input type="text" name="destinationAirportName"
								placeholder="Banglore International Airport" required />
						</div>
						<div class="myform__input">
							Arrival date:<input type="date" name="arrivalDate" min="${today}"
								required />
						</div>
						<div class="myform__input">
							Arrival time:<input type="time" name="arrivalTime" required />
						</div>
					</div>
				</div>
				<div class="myform__top">
					<div class="myform__left">
						<label for="source">Airline Details :</label>
						<div class="myform__input">
							Airline Name:<input type="text" name="airlineName"
								placeholder="Indigo" required />
						</div>
						<div class="myform__input">
							Max Passengers :<input type="number" name="maxPassengers"
								placeholder="30" required />
						</div>
					</div>
					<div class="myform__right">
						<br />
						<div class="myform__input">
							Aircraft Manufacturer :<input type="text"
								name="aircraftManufacturer" placeholder="Airbus" required />
						</div>
						<div class="myform__input">
							Fare per Passenger :<input type="number" name="ticketPrice"
								placeholder="10000" required />
						</div>
					</div>
				</div>
				<div class="myform__button">
					<input class="button" type="submit" value="Add Trip" />
				</div>
			</div>
		</form>
	</section>

	<%@ include file="footer.jsp"%>
</body>
</html>
