package com.flyaway.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyaway.dao.AdminDAO;
import com.flyaway.dao.TripDAO;
import com.flyaway.model.Destination;
import com.flyaway.model.Source;
import com.flyaway.model.Trip;
import com.flyaway.util.IdGenerator;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TripDAO tripDAO;
	private AdminDAO adminDAO;

	IdGenerator idGen = new IdGenerator();
	DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat formatTime = new SimpleDateFormat("HH:mm");

	public AdminController() {

		tripDAO = new TripDAO();
		adminDAO = new AdminDAO();

	}

// Admin page get requests are controlled here
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {
			action = "adminDashboard";
		}
		switch (action) {

		case "adminDashboard":
			request.getRequestDispatcher("admin.jsp").forward(request, response);
			break;
		case "AdminLogin":
			request.getRequestDispatcher("admin-login.jsp").forward(request, response);

		case "airlineList":
		case "locationList":
		case "tripList":

			showMasterList(request, response, action);
			break;

		case "changepassword":
			request.getRequestDispatcher("change-password.jsp").forward(request, response);
			break;
		case "logout":
			request.getSession().invalidate();
			String message = "logged out";
			boolean redirect = true;
			request.setAttribute("redirect", redirect);
			request.setAttribute("validation", message);
			request.getRequestDispatcher("validation-admin.jsp").forward(request, response);

			break;

		case "tripAdd":
			Date todayDate = new Date();
			String today = formatDate.format(todayDate);
			request.setAttribute("today", today);
			request.getRequestDispatcher("trip-add.jsp").forward(request, response);
			break;

		case "tripView":
			retrieveTrip(request, response);
			break;

		case "editTrip":
			populateEditTripForm(request, response);
			break;

		case "deleteTrip":
			deleteTrip(request, response);
			break;

		default:
			response.sendRedirect("admin.jsp");
			break;

		}
	}

// Admin page post requests are controlled here 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		System.out.println(action);

		switch (action) {

		case "adminLogin":
			adminLogin(request, response);
			break;
		case "changePassword":
			changePassword(request, response);
			break;

		case "addTrip":
			addTrip(request, response);
			break;

		case "updateTrip":
			updateTrip(request, response);
			break;

		case "searchTrip":
			tripSearch(request, response);
			break;
		default:
			response.sendRedirect("admin.jsp");
			break;
		}

	}

	//// ************** Admin *******************////

	// Authenticates admin
	public void adminLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String emailId = request.getParameter("emailId");
		String password = request.getParameter("password");

		boolean status = adminDAO.authenticateAdmin(emailId, password);
		if (status) {
			HttpSession session = request.getSession(true);
			session.setMaxInactiveInterval(300);
			session.setAttribute("adminName", emailId);
			request.getRequestDispatcher("AdminController?action=adminDashboard").forward(request, response);
		} else {
			String message = "Username or password is wrong";
			request.setAttribute("validation", message);
			request.getRequestDispatcher("validation.jsp").forward(request, response);
		}
	}

	// Change password for admin
	public void changePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String emailId = request.getParameter("emailId");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");

		boolean status = adminDAO.changePassword(emailId, password, newPassword);
		if (status) {
			String message = "password changed successfully Redirecting.... please wait";
			request.getSession().invalidate();
			boolean redirect = true;
			request.setAttribute("validation", message);
			request.setAttribute("redirect", redirect);
			request.getRequestDispatcher("validation-admin.jsp").forward(request, response);
		} else {
			String message = "old password is wrong";
			boolean redirect = false;
			request.setAttribute("validation", message);
			request.setAttribute("redirect", redirect);
			request.getRequestDispatcher("validation-admin.jsp").forward(request, response);
		}

	}

//Model methods

	/// ***************Trip Methods ********************************////

	public void addTrip(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			// admin login are verified in JSp page
			// adding a Trip
			Trip trip = new Trip();
			Source source = new Source();
			Random random = new Random();
			IdGenerator idGen = new IdGenerator();
			Destination destination = new Destination();

			// Trip Id generation logic
			String tripId = "TRIP" + random.nextInt(10000);
			boolean status = false;

			// Trip ID
			trip.setTripId(tripId);

			// Source data
			source.setSourceCountryName(request.getParameter("sourceCountryName"));
			source.setSourceCityName(request.getParameter("sourceCityName"));
			source.setSourceAirportName(request.getParameter("sourceAirportName") + " International Airport");
			trip.setSource(source);

			// Destination data
			destination.setDestinationCountryName(request.getParameter("destinationCountryName"));
			destination.setDestinationCityName(request.getParameter("destinationCityName"));
			destination.setDestinationAirportName(
					request.getParameter("destinationAirportName") + " International Airport");
			trip.setDestination(destination);

			// Departure date and time formating with the help of simple date format
			Date depDate = formatDate.parse(request.getParameter("departureDate"));
			trip.setDepartureDate(depDate);
			// departure Time
			Date depTime = formatTime.parse(request.getParameter("departureTime"));
			trip.setDepartureTime(depTime);
			// Arrival date and time formating with the help of simple date format
			Date arrDate = formatDate.parse(request.getParameter("arrivalDate"));
			trip.setArrivalDate(arrDate);
			// Arrival time
			Date arrTime = formatTime.parse(request.getParameter("arrivalTime"));
			System.out.println(arrTime);
			trip.setArrivalTime(arrTime);

			// Airline details
			trip.setAirlineName(request.getParameter("airlineName"));

			// Flight number generation
			String flightNumber = idGen.flightNumberGenerator(request.getParameter("airlineName"));
			trip.setFlightNumber(flightNumber);
			trip.setAircraftManufacturer(request.getParameter("aircraftManufacturer"));
			trip.setMaxPassengers(Integer.parseInt(request.getParameter("maxPassengers")));
			trip.setTicketPrice(Integer.parseInt(request.getParameter("ticketPrice")));

			// calling Trip DAO to add a Trip
			status = tripDAO.addTrip(trip);

			if (status) {
				response.sendRedirect("AdminController?action=tripView");
			} else {

				String message = "Add operation failed";
				request.setAttribute("validation", message);
				request.getRequestDispatcher("validation-admin.jsp").forward(request, response);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void retrieveTrip(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Gets entire trip list
		List<Trip> tripList = tripDAO.getTripList();

		request.setAttribute("tripList", tripList);
		request.getRequestDispatcher("trip-view.jsp").forward(request, response);
	}

	public void populateEditTripForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// getting id from form
		Date todayDate = new Date();
		String today = formatDate.format(todayDate);

		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		if (id != 0) {

			// getting the trip corresponding to the user's edit option
			Trip trip = (Trip) tripDAO.getTripById(id);

			request.setAttribute("trip", trip);
			request.setAttribute("today", today);
			request.getRequestDispatcher("trip-edit.jsp").forward(request, response);
		}

	}

	public void updateTrip(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Trip trip = new Trip();
			Source source = new Source();
			Destination destination = new Destination();

			int id = Integer.parseInt(request.getParameter("id"));
			if (id != 0) {
				trip.setId(id);
				System.out.println(id);

				trip.setTripId(request.getParameter("tripId"));
				trip.setFlightNumber(request.getParameter("flightNumber"));

				source.setSourceCountryName(request.getParameter("sourceCountryName"));
				source.setSourceCityName(request.getParameter("sourceCityName"));
				source.setSourceAirportName(request.getParameter("sourceAirportName"));
				trip.setSource(source);

				// Destination data
				destination.setDestinationCountryName(request.getParameter("destinationCountryName"));
				destination.setDestinationCityName(request.getParameter("destinationCityName"));
				destination.setDestinationAirportName(request.getParameter("destinationAirportName"));
				trip.setDestination(destination);

				// Departure date and time
				Date depDate = formatDate.parse(request.getParameter("departureDate"));
				trip.setDepartureDate(depDate);
				// departure Time
				Date depTime = formatTime.parse(request.getParameter("departureTime"));
				trip.setDepartureTime(depTime);
				// Arrival date and time
				Date arrDate = formatDate.parse(request.getParameter("arrivalDate"));
				trip.setArrivalDate(arrDate);
				// Arrival time
				Date arrTime = formatTime.parse(request.getParameter("arrivalTime"));
				trip.setArrivalTime(arrTime);

				// Airline details
				trip.setAirlineName(request.getParameter("airlineName"));
				trip.setAircraftManufacturer(request.getParameter("aircraftManufacturer"));
				trip.setMaxPassengers(Integer.parseInt(request.getParameter("maxPassengers")));
				trip.setTicketPrice(Integer.parseInt(request.getParameter("ticketPrice")));

				// calling Trip DAO to update
				boolean status = tripDAO.updateTrip(trip);
				if (status) {
					response.sendRedirect("AdminController?action=tripView");
				}
			} else {
				System.out.println("id is null");
				String message = "update failed";
				request.setAttribute("validation", message);
				request.getRequestDispatcher("validation-admin.jsp").forward(request, response);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteTrip(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		// calling Trip DAO to delete
		boolean status = tripDAO.deleteTrip(id);
		if (status) {
			response.sendRedirect("AdminController?action=tripView");
		} else {
			System.out.println("id is null");
			String message = "delete failed";
			request.setAttribute("validation", message);
			request.getRequestDispatcher("validation-admin.jsp").forward(request, response);
		}
	}

	public void tripSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String searchBy = request.getParameter("searchBy");
		System.out.println(searchBy);
		String searchValue = request.getParameter("searchValue");

		// getting search value from user and searching them accordingly
		switch (searchBy) {
		case "source":
			// calling Trip DAO to get trips by source Airport Name
			List<Trip> tripList = tripDAO.getTripsBySource(searchValue);
			if (tripList != null && !tripList.isEmpty()) {
				request.setAttribute("tripList", tripList);
				request.getRequestDispatcher("trip-view.jsp").forward(request, response);
			} else {
				String message = "Please Enter details properly";
				request.setAttribute("validation", message);
				request.getRequestDispatcher("validation-admin.jsp").forward(request, response);
				break;
			}
			break;
		case "destination":
			// calling Trip DAO to get trips by destination Airport Name
			tripList = tripDAO.getTripsByDestination(searchValue);

			if (tripList != null && !tripList.isEmpty()) {

				request.setAttribute("tripList", tripList);
				request.getRequestDispatcher("trip-view.jsp").forward(request, response);
				break;
			} else {
				String message = "Please Enter details properly";
				request.setAttribute("validation", message);
				request.getRequestDispatcher("validation-admin.jsp").forward(request, response);
				break;
			}

		case "airline":
			// calling Trip DAO to get trips by Airline
			tripList = tripDAO.getTripsByAirline(searchValue);
			if (tripList != null && !tripList.isEmpty()) {
				request.setAttribute("tripList", tripList);
				request.getRequestDispatcher("trip-view.jsp").forward(request, response);
				break;
			} else {
				String message = "Please Enter details properly";
				request.setAttribute("validation", message);
				request.getRequestDispatcher("validation-admin.jsp").forward(request, response);
				break;
			}
		}
	}
	/// *********************Listing Master list****************************///

	public void showMasterList(HttpServletRequest request, HttpServletResponse response, String action)
			throws ServletException, IOException {
		List<Trip> tripList = tripDAO.getTripList();
		request.setAttribute("tripList", tripList);

		switch (action) {

		case "airlineList":
			if (tripList != null && !tripList.isEmpty()) {
				request.getRequestDispatcher("master-airline.jsp").forward(request, response);
			} else {
				String message = "Add Data First";
				request.setAttribute("validation", message);
				request.getRequestDispatcher("validation-admin.jsp").forward(request, response);

			}

			break;

		case "locationList":
			if (tripList != null && !tripList.isEmpty()) {
				request.getRequestDispatcher("master-location.jsp").forward(request, response);
			} else {
				String message = "Add Data First";
				request.setAttribute("validation", message);
				request.getRequestDispatcher("validation-admin.jsp").forward(request, response);

			}

			break;

		case "tripList":
			if (tripList != null && !tripList.isEmpty()) {
				request.getRequestDispatcher("master-trip.jsp").forward(request, response);
			} else {
				String message = "Add Data First";
				request.setAttribute("validation", message);
				request.getRequestDispatcher("validation-admin.jsp").forward(request, response);

			}

			break;
		}

	}
}
