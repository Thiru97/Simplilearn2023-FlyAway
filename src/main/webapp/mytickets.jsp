<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	

	<% String message = (String)request.getAttribute("validation"); %>
	<div class="content__description">
		<c:if test="validation ! = null">	
			<h1> <%= message %></h1>
		</c:if>
	</div>
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
					
				</tr>
				<c:forEach var="trip" items="${tripList}">
					<tr>
						<td>${trip.flightNumber }</td>
						<td>${trip.airlineName }</td>
						<td>${trip.source.sourceAirportName }</td>
						<td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${trip.departureDate }" /></td>
						<td><fmt:formatDate pattern = "HH:mm" value = "${trip.departureTime }" /></td>
						<td>${trip.destination.destinationAirportName }</td>
						<td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${trip.arrivalDate }" /></td>
						<td><fmt:formatDate pattern = "HH:mm" value = "${trip.arrivalTime }" /></td>
						<td>${trip.ticketPrice}</td>
				
					</tr>
				</c:forEach>
			</table>
		</div>
	</section>



	<%@ include file="footer.jsp"%>
</body>
</html>