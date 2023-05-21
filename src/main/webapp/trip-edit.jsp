<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fly Away</title>
</head>
<body>
	<% if(request.getSession().getAttribute("adminName") == null){
		response.sendRedirect("admin-login.jsp");
	}
	%>
	<%@ include file="admin-nav.jsp"%>

	<section class="content">

		<form class="myform__border"
			action="AdminController?action=updateTrip" method="post">



			<h3>Edit Trip Details</h3>
			<div class="myform">
				<div class="myform__top">
					<div class="myform__left">

						<label for="source">Source :</label>

						<div class="myform__input">
							<input type="hidden" name="id"
								value="<c:out value='${trip.id}' />" /> <input type="hidden"
								name="tripId" value="<c:out value='${trip.tripId}' />" /> <input
								type="hidden" name="flightNumber"
								value="<c:out value='${trip.flightNumber}' />" /> Country Name:<input
								type="text" name="sourceCountryName" placeholder="India"
								value="<c:out value='${trip.source.sourceCountryName}' />" />
						</div>
						<div class="myform__input">
							City Name:<input type="text" name="sourceCityName"
								placeholder="Chennai"
								value="<c:out value='${trip.source.sourceCityName}' />" />
						</div>
						<div class="myform__input">
							Airport Name:<input type="text" name="sourceAirportName"
								placeholder="Chennai International Airport"
								value="<c:out value='${trip.source.sourceAirportName}' />" />
						</div>
						<div class="myform__input">
							Departure date:<input type="date" name="departureDate"
								min="${today}" value="<c:out value='${trip.departureDate}' />" />
						</div>
						<div class="myform__input">
							Departure time:<input type="time" name="departureTime"
								value="<c:out value='${trip.departureTime}' />" />
						</div>
					</div>
					<div class="myform__right">
						<label for="source">Destination :</label>
						<div class="myform__input">
							Country Name:<input type="text" name="destinationCountryName"
								placeholder="India"
								value="<c:out value='${trip.destination.destinationCountryName}' />" />
						</div>
						<div class="myform__input">
							City Name:<input type="text" name="destinationCityName"
								placeholder="Banglore"
								value="<c:out value='${trip.destination.destinationCityName}' />" />
						</div>
						<div class="myform__input">
							Airport Name:<input type="text" name="destinationAirportName"
								placeholder="Kempa Gowda International"
								value="<c:out value='${trip.destination.destinationAirportName}' />" />
						</div>
						<div class="myform__input">
							Arrival date:<input type="date" name="arrivalDate" min="${today}"
								value="<c:out value='${trip.arrivalDate}' />" />
						</div>
						<div class="myform__input">
							Arrival time:<input type="time" name="arrivalTime"
								value="<c:out value='${trip.arrivalTime}' />" />
						</div>
					</div>
				</div>
				<div class="myform__top">
					<div class="myform__left">
						<label for="source">Airline Details :</label>
						<div class="myform__input">
							Airline Name:<input type="text" name="airlineName"
								placeholder="Indigo"
								value="<c:out value='${trip.airlineName}' />" />
						</div>
						<div class="myform__input">
							Max Passengers :<input type="number" name="maxPassengers"
								placeholder="30" value="<c:out value='${trip.maxPassengers}' />" />
						</div>
					</div>
					<div class="myform__right">
						<br />
						<div class="myform__input">
							Aircraft Manufacturer :<input type="text"
								name="aircraftManufacturer" placeholder="Airbus"
								value="<c:out value='${trip.aircraftManufacturer}' />" />
						</div>
						<div class="myform__input">
							Fare per Passenger :<input type="number" name="ticketPrice"
								placeholder="10000"
								value="<c:out value='${trip.ticketPrice}' />" />
						</div>
					</div>
				</div>
				<div class="myform__button">
					<input class="button" type="submit" value="Save" />
				</div>
			</div>
		</form>
	</section>

	<%@ include file="footer.jsp"%>
</body>
</html>