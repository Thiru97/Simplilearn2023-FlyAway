<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FlyAway</title>
</head>
<body>

	<c:if test="${userName == null}">
		<%@ include file="index-nav.jsp"%>
	</c:if>
	<c:if test="${userName != null}">
		<%@ include file="user-nav.jsp"%>
	</c:if>
	<section class="content__description">
		<h1>Confirm Booking</h1>
	</section>
	<section class="content">
		<div class="mytable">
			<table>
				<tr>
					<th>Flight Number</th>
					<th>Airline</th>
					<th>Departure</th>
					<th>Departure Date and time</th>
					<th>Arrival</th>
					<th>Arrival date and time</th>
					<th>Number of passengers</th>
				</tr>

				<tr>
					<td>${trip.flightNumber }</td>
					<td>${trip.airlineName}</td>
					<td>${trip.source.sourceAirportName}</td>
					<td><fmt:formatDate pattern="dd-MM-yyyy"
							value="${trip.departureDate }" /> <span>&nbsp</span> <fmt:formatDate
							pattern="HH:mm" value="${trip.departureTime }" /></td>
					<td>${trip.destination.destinationAirportName }</td>
					<td><fmt:formatDate pattern="dd-MM-yyyy"
							value="${trip.arrivalDate }" /> <span>&nbsp</span> <fmt:formatDate
							pattern="HH:mm" value="${trip.arrivalTime }" /></td>
					<td>${noOfPassengers }</td>
				</tr>

			</table>
		</div>
		<div class="mytable">
			<table>
				<tr>
					<th>Fare Per Passenger</th>
					<th>No of Passenger</th>
					<th>Total Amount</th>
				</tr>
				<tr>
					<td>${ticketPrice}</td>
					<td>${noOfPassengers }</td>
					<td>${totalAmount}</td>
				</tr>
			</table>
		</div>
		<div class="content__bottom">
			<a class="button"
				href="<%request.getContextPath();%>SiteController?action=paymentGateWay&id=${trip.id}">Confirm</a>
			<a class="button"
				href="<%request.getContextPath();%>SiteController?action=home">Cancel</a>
		</div>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>