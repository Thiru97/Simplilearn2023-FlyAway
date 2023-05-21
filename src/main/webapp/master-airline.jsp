<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<h1>Airline data</h1>
		</div>
	</section>
	<section class="content">
		<div class="content__table">
			<div class="mytable">
				<table>
					<tr>
						<th>Flight Number</th>
						<th>Airline Name</th>
						<th>Aircraft Manufacturer</th>
						<th>Max Passengers</th>
						<th>Ticket Price</th>
					</tr>
					<c:forEach var="trip" items="${tripList }">
						<tr>
							<td>${trip.flightNumber}</td>
							<td>${trip.airlineName}</td>
							<td>${trip.aircraftManufacturer }</td>
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