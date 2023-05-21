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
			<h1>Source and Destination data</h1>
		</div>
	</section>
	<section class="content">
		<div class="content__table">
			<div class="mytable">
				<table>
					<tr>
						<th>Trip Id</th>
						<th>Source Country</th>
						<th>Source city</th>
						<th>Source Airport</th>
						<th>Destination Country</th>
						<th>Destination City</th>
						<th>Destination Airport</th>
					</tr>
				<c:forEach var="trip" items="${tripList }">
					<tr>
						<td>${trip.tripId}</td>
						<td>${trip.source.sourceCountryName}</td>
						<td>${trip.source.sourceCityName}</td>
						<td>${trip.source.sourceAirportName}</td>
						<td>${trip.destination.destinationCountryName}</td>
						<td>${trip.destination.destinationCityName}</td>
						<td>${trip.destination.destinationAirportName}</td>
					</tr>
				</c:forEach>
				</table>
			</div>
		</div>
	</section>

	<%@ include file="footer.jsp"%>
</body>
</html>