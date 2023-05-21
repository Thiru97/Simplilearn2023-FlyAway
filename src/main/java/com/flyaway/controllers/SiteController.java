package com.flyaway.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyaway.dao.TripDAO;
import com.flyaway.dao.UserDAO;
import com.flyaway.model.Trip;
import com.flyaway.model.User;

@WebServlet("/SiteController")
public class SiteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// admin default email id
	// private static final String DEFAULT_EMAIL_ID = "admin@flyaway.com";

	private TripDAO tripDAO;
	private UserDAO userDAO;

	// private AdminDAO adminDAO;

	// Date format for the entire application
	DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

	public SiteController() {
		super();
		tripDAO = new TripDAO();
		userDAO = new UserDAO();
		// adminDAO = new AdminDAO();

	}

	// Handles HTTP get Requests
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// searches for the word "action" in url
		String action = request.getParameter("action");
		if (action == null) {
			action = "home";
		}
		switch (action) {

		case "home":
			showCityName(request, response);
			break;
		case "about":
			request.getRequestDispatcher("about.jsp").forward(request, response);
			break;
		case "login":
			request.getRequestDispatcher("login.jsp").forward(request, response);
			break;
		case "register":
			request.getRequestDispatcher("register.jsp").forward(request, response);
			break;
		case "logout":
			request.getSession().invalidate();
			String message = "Logged out successfully";
			request.setAttribute("validation", message);
			request.getRequestDispatcher("validation.jsp").forward(request, response);
		case "bookFlight":
			bookFlight(request, response);
			break;
		case "confirmBooking":
			confirmBooking(request, response);
			break;
		case "paymentGateWay":
			paymentGateway(request, response);
			break;
		case "myTickets":
			myTickets(request, response);
			break;
		// if user after login searches for a different url which is not registered he
		// will be redirected to home page
		default:
			showCityName(request, response);
			break;
		}

	}

	// Handles HTTP post method
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		switch (action) {
		case "flightsAvailable":
			flightsAvailable(request, response);
			break;
		case "registerUser":
			register(request, response);
			break;
		case "login":
			login(request, response);
			break;
		case "ticketManager":
			ticketManager(request, response);
			break;
		}
	}

	// Fly Away site methods
	public void showCityName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Date todayDate = new Date();
		String today = formatDate.format(todayDate);

		// Initializing admin for the first time
		/*
		 * String password = "admin"; Admin admin = new Admin(DEFAULT_EMAIL_ID,
		 * password); adminDAO.addAdmin(admin);
		 */

		// getting Cities data to populate the search field
		List<Trip> sourceCities = tripDAO.getSourceCity();
		List<Trip> destinationCities = tripDAO.getDestinationCity();

		// removing previous session values
		request.getSession().removeAttribute("currentPage");
		request.getSession().removeAttribute("noOfPassengers");
		request.getSession().removeAttribute("tripId");
		request.getSession().removeAttribute("totalAmount");

		// setting the return values
		request.setAttribute("today", today);
		request.setAttribute("sourceCities", sourceCities);
		request.setAttribute("destinationCities", destinationCities);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	public void flightsAvailable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String source = request.getParameter("sourceCity");
			String destination = request.getParameter("destinationCity");
			Date customerdepDate = formatDate.parse(request.getParameter("departureDate"));
			int noOfPassengers = Integer.parseInt(request.getParameter("noOfPassengers"));

			HttpSession session = request.getSession(true);

			session.setAttribute("noOfPassengers", noOfPassengers);

			List<Trip> flights = tripDAO.getFlights(source, destination, customerdepDate);

			if (flights.isEmpty()) {
				String message = "Sorry no flights available ";
				request.setAttribute("validation", message);
				request.getRequestDispatcher("validation.jsp").forward(request, response);
			} else {

				request.setAttribute("flights", flights);
				request.setAttribute("sourceCity", source);
				request.setAttribute("destinationCity", destination);

				request.getRequestDispatcher("flights-available.jsp").forward(request, response);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void bookFlight(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int tripId = Integer.parseInt(request.getParameter("id"));
		request.getSession(true).setAttribute("tripID", tripId);

		// user supposed to login here after this page so checking users session
		// management
		if (request.getSession(false).getAttribute("userName") == null) {
			HttpSession session = request.getSession();
			String currentPage = "SiteController?action=confirmBooking";
			session.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("SiteController?action=login").forward(request, response);
		} else {
			request.getRequestDispatcher("SiteController?action=confirmBooking").forward(request, response);
		}

	}

	public void confirmBooking(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// checking whether user has logged in
		if (request.getSession().getAttribute("userName") == null) {
			response.sendRedirect("SiteController?action=login");
		} else {

			int id = (Integer) (request.getSession().getAttribute("tripID"));
			System.out.println(id);
			int noOfPassengers = (int) request.getSession(false).getAttribute("noOfPassengers");

			Trip trip = tripDAO.getTripById(id);

			int ticketPrice = trip.getTicketPrice();

			// calculating total ticket price amount
			int totalAmount = ticketPrice * noOfPassengers;

			request.setAttribute("trip", trip);
			request.setAttribute("noOfPassengers", noOfPassengers);
			request.setAttribute("ticketPrice", ticketPrice);
			request.setAttribute("totalAmount", totalAmount);
			request.getSession(false).setAttribute("totalAmount", totalAmount);
			request.getRequestDispatcher("confirm-booking.jsp").forward(request, response);
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String emailID = request.getParameter("emailId");
		String password = request.getParameter("password");

		request.getSession().removeAttribute("userName");

		// authenticating user's credentials
		boolean status = userDAO.authenticateUser(emailID, password);

		// If user's credentials are present then setting a HTTP session with inactive
		// time limit of 5 minutes
		if (status) {
			HttpSession Session = request.getSession(true);
			Session.setMaxInactiveInterval(300);
			Session.setAttribute("userName", emailID);

			// if user logs in when booking the ticket Redirecting to the booking
			// confirmation page
			String pagename = (String) request.getSession(false).getAttribute("currentPage");
			if (pagename == null) {
				pagename = "SiteController?action=home";
			}
			response.sendRedirect(pagename);
		} else

		{
			// if users password is wrong intimating the same
			String message = "Username or password is wrong";
			request.setAttribute("validation", message);
			request.getRequestDispatcher("validation.jsp").forward(request, response);
		}
	}

	public void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// getting data
		User user = new User();
		String email = request.getParameter("emailId");
		String password = request.getParameter("password");
		String cPassword = request.getParameter("cPassword");

		if (password.equals(cPassword)) {
			user.setFirstName(request.getParameter("firstName"));
			user.setLastName(request.getParameter("lastName"));
			user.setAge(Integer.parseInt(request.getParameter("age")));
			user.setEmailId(request.getParameter("emailId"));
			user.setPassword(password);

			// calling UserDAO to add User
			if (userDAO.isUserNameExists(email)) {
				String message = "User Name Already Exists try logging in";
				request.setAttribute("validation", message);
				request.getRequestDispatcher("validation.jsp").forward(request, response);
			} else {
				boolean status = userDAO.addUser(user);
				if (status) {
					request.getSession().removeAttribute("userName");
					HttpSession session = request.getSession(true);
					session.setAttribute("userName", email);

					// redirecting to the leftover process
					String pagename = (String) request.getSession(false).getAttribute("currentPage");
					if (pagename == null) {
						pagename = "SiteController?action=home";
					}
					response.sendRedirect(pagename);

				} else {
					String message = "failed: user not added";
					request.setAttribute("validation", message);
					request.getRequestDispatcher("validation.jsp").forward(request, response);
				}
			}

		} else {
			String message = "password and confirm password are not same";
			request.setAttribute("validation", message);
			request.getRequestDispatcher("validation.jsp").forward(request, response);
		}
	}

	public void paymentGateway(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// dummy payment gateway
		// checking if user is logged in
		if (request.getSession().getAttribute("userName") == null) {
			response.sendRedirect("SiteController?action=login");
		} else {

			int id = Integer.parseInt(request.getParameter("id"));
			int totalAmount = (int) request.getSession(false).getAttribute("totalAmount");
			request.setAttribute("totalAmount", totalAmount);
			request.setAttribute("id", id);
			request.getRequestDispatcher("payment.jsp").forward(request, response);
		}
	}

	@SuppressWarnings("null")
	public void ticketManager(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// checking if user is logged in
		if (request.getSession().getAttribute("userName") == null) {
			response.sendRedirect("SiteController?action=login");
		} else {

			Trip trip = new Trip();
			User user = new User();
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
			String userName = (String) request.getSession().getAttribute("userName");
			System.out.println(userName);

			// getting respective user id and trip id to validate them in many to many
			// association
			user = userDAO.getUser(userName);
			trip = tripDAO.getTripById(id);

			trip.getUsers().add(user);
			user.getTrips().add(trip);
			boolean status = tripDAO.updateTrip(trip);
			boolean status2 = userDAO.updateUser(user);
			if (status && status2) {
				String message = "Ticket booked successfully";
				request.setAttribute("message", message);
				request.setAttribute("trip", trip);
				request.getRequestDispatcher("confirmation.jsp").forward(request, response);
			} else {
				String message = "Ticket booked successfully";
				request.setAttribute("validation", message);
				request.getRequestDispatcher("validation.jsp").forward(request, response);
			}

		}
	}

	public void myTickets(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// checking if user is logged in
		if (request.getSession().getAttribute("userName") == null) {
			response.sendRedirect("SiteController?action=login");
		} else {
			String userName = (String) request.getSession().getAttribute("userName");

			// getting user's ticket history
			List<Trip> tripList = userDAO.getUserTickets(userName);
			if (!tripList.isEmpty()) {
				request.setAttribute("tripList", tripList);
				request.getRequestDispatcher("mytickets.jsp").forward(request, response);
			} else {
				String message = "You haven't booked any tickets";
				request.setAttribute("validation", message);
				request.getRequestDispatcher("mytickets.jsp").forward(request, response);
			}

		}

	}

}
