<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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

	<section>
		<div class="content__description">
			<h1>Trip data</h1>
		</div>
	</section>
	<section class="content">
		<div class="content__table">
			<div class="mytable">
				<table>
					<tr>
						<th>Trip Id</th>
						<th>Flight Number</th>
						<th>Airline</th>
						<th>Source Country Name</th>
						<th>Source City Name</th>
						<th>Source Airport Name</th>
						<th>Departure Date</th>
						<th>Departure Time</th>
						<th>Destination Country Name</th>
						<th>Destination City Name</th>
						<th>Destination Airport Name</th>
						<th>Arrival Date</th>
						<th>Arrival Time</th>
						<th>Max Passengers</th>
						<th>Ticket Price</th>

					</tr>
					<c:forEach var="trip" items="${tripList }">
						<tr>
							<td>${trip.tripId }</td>
							<td>${trip.flightNumber }</td>
							<td>${trip.airlineName }</td>
							<td>${trip.source.sourceCountryName }</td>
							<td>${trip.source.sourceCityName }</td>
							<td>${trip.source.sourceAirportName }</td>
							<td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${trip.departureDate }" /></td>
							<td><fmt:formatDate pattern = "HH:mm" value = "${trip.departureTime }" /></td>
							<td>${trip.destination.destinationCountryName }</td>
							<td>${trip.destination.destinationCityName }</td>
							<td>${trip.destination.destinationAirportName }</td>
							<td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${trip.arrivalDate }" /></td>
							<td><fmt:formatDate pattern = "HH:mm" value = "${trip.arrivalTime }" /></td>
							<td>${trip.maxPassengers}</td>
							<td>${trip.ticketPrice}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</section>

	<%@ include file="footer.jsp"%>

</body>
</html>