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

	<section>
		<div class="content__description">
			<h1>Trip related operations</h1>
		</div>
	</section>
	<section class="content--trip">
		<div class="content__search">
			<div class="myform--airline">
				<form action="AdminController?action=searchTrip" method="post">
					<div class="myform__input--airline">
						Search by : <select name="searchBy" id="" required>
							<option value="">Select</option>
							<option value="source">Source</option>
							<option value="destination">Destination</option>
							<option value="airline">Airline</option>
							</select>
							 Please enter value : <input type="text" name="searchValue" id="" required/>
						<input class="button" type="Submit" name="" id="" value="Search"  />
						<a class="button" href="AdminController?action=tripView">Refresh List</a>
						<a class="button" href="AdminController?action=tripAdd">Add More Trip</a>
					</div>
				</form>
			</div>
		</div>
		<div class="content__table">
			<div class="mytable">
				<table>
					<tr>
						<th>TripId</th>
						<th>Actions</th>
						<th>Source City Name</th>
						<th>Destination City Name</th>
						<th>Airline</th>
						<th>Max Passengers</th>
						<th>Ticket Price</th>
					</tr>
					
					<c:forEach var="trip" items="${tripList}">
								<input type="hidden" name =id value="<c:out value="${trip.id}"></c:out>"/>
						<tr>
					
							<td><c:out value="${trip.tripId}"></c:out></td>
							<td class="mytable__button">
							<a class="button" href="<% request.getContextPath();%>AdminController?action=editTrip&id=<c:out value="${trip.id}"></c:out>"> EDIT</a>	
							<a class="button" href="<% request.getContextPath();%>AdminController?action=deleteTrip&id=<c:out value="${trip.id}"></c:out>"> DELETE</a>
							</td>
							<td><c:out value="${trip.source.sourceCityName}"></c:out></td>
							<td><c:out
									value="${trip.destination.destinationCityName}"></c:out></td>
							<td><c:out value="${trip.airlineName}"></c:out></td>
							<td><c:out value="${trip.maxPassengers}"></c:out></td>
							<td><c:out value="${trip.ticketPrice}"></c:out></td>

						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</section>

	<%@ include file="footer.jsp"%>
</body>
</html>