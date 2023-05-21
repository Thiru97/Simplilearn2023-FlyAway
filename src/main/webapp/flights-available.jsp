<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fly Away</title>
</head>
<body>

<c:if test="${userName == null}">
 <%@ include file="index-nav.jsp"%>
</c:if>
 <c:if test="${userName != null}">
 <%@ include file="user-nav.jsp"%>
</c:if>
	

	<section class="content__description">
		<h1>Flights from...</h1>
		<h2>${sourceCity } to ${destinationCity }</h2>
	</section>
	<section class="content">
		<div class="mytable">
			<table>
				<tr>
					<th>Flight Number</th>
					<th>Airline</th>
					<th>Departure</th>
					<th>Departure Date</th> 
					<th>Departure Time</th>
					<th>Arrival</th>
					<th>Arrival Date</th>
					<th>Arrival time</th>
					<th>Fare</th>
					<th>Book</th>
				</tr>
				<c:forEach var="flight" items="${flights }">
					<tr>
						<td>${flight.flightNumber }</td>
						<td>${flight.airlineName }</td>
						<td>${flight.source.sourceCityName }</td>
						<td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${flight.departureDate }" /></td>
						<td><fmt:formatDate pattern = "HH:mm" value = "${flight.departureTime }" /></td>
						<td>${flight.destination.destinationCityName }</td>
						<td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${flight.arrivalDate }" /></td>
						<td><fmt:formatDate pattern = "HH:mm" value = "${flight.arrivalTime }" /></td>
						<td>${flight.ticketPrice}</td>
						<td><a href="SiteController?action=bookFlight&id=${flight.id }" class="button"
							type="submit">Buy</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</section>



	<%@ include file="footer.jsp"%>
</body>
</html>