<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${userName == null}">
		<%@ include file="index-nav.jsp"%>
	</c:if>
	<c:if test="${userName != null}">
		<%@ include file="user-nav.jsp"%>
	</c:if>

	<section class="content__description">
		<h1>${message }</h1>

	</section>
	<section class="content">
		<div class="mytable">
			<table>
				<tr>
					<th>Flight Number</th>
					<th>Airline</th>
					<th>Departure Airport</th>
					<th>Departure Date and Time</th>
					<th>Arrival Airport</th>
					<th>Arrival Date and Time </th>
					<th>No of Passengers</th>
					
				</tr>
				<tr>

				<tr>
					<td>${trip.flightNumber }</td>
					<td>${trip.airlineName}</td>
					<td>${trip.source.sourceAirportName}</td>
					<td><fmt:formatDate pattern="dd-MM-yyyy"
							value="${trip.departureDate }" /> 
							<span>&nbsp</span> 
						<fmt:formatDate	pattern="HH:mm" value="${trip.departureTime }" />
					</td>
					<td>${trip.destination.destinationAirportName }</td>
					<td><fmt:formatDate pattern="dd-MM-yyyy"
							value="${trip.arrivalDate }" /> 
							<span>&nbsp</span> 
						<fmt:formatDate	pattern="HH:mm" value="${trip.arrivalTime }" />
					</td>
					<td>${noOfPassengers }</td>
				</tr>

			</table>
		</div>
	</section>


	<%@ include file="footer.jsp"%>

</body>
</html>