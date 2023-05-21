<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<c:if test="${userName == null}">
 <%@ include file="index-nav.jsp"%>
</c:if>
 <c:if test="${userName != null}">
 <%@ include file="user-nav.jsp"%>
</c:if>
	
	<div class="content__greeting">
		<h1>
			Welcome to Fly <span>Away </span>
		</h1>
		<h2>Your journey begins here</h2>
	</div>
	<section class="content">
		<div class="myform">

			<form class="myform__border" action="<% request.getContextPath(); %>SiteController?action=flightsAvailable" method="post">
				<h3>Search for flights here...</h3>
				<div class="myform__input">
					<label for="Departure">From:</label> <select name="sourceCity"
						id="" required>
						<c:forEach var="sourceCity" items="${sourceCities}" >
							<option value = "${sourceCity }">${sourceCity }</option>
						</c:forEach>
					</select>
				</div>
				<div class="myform__input">
					<label for="Arrival">To:</label> <select name="destinationCity"
						id="" required>
						<c:forEach var="destinationCity" items="${destinationCities}">
							<option value="${destinationCity}" >${destinationCity} </option>

						</c:forEach>
					</select>
				</div>
				<div class="myform__input">
					Departure date : <input type="date" name="departureDate"
						min="${today }" required />
				</div>
				<div class="myform__input">
					Number of Passengers : <input type="number"  name="noOfPassengers"
						min="1" value= "1"/>
				</div>
				<div class="myform__button">
					<input class="button" type="Submit" value="Search" />
				</div>
			</form>
		</div>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>
