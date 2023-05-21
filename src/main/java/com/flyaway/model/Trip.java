package com.flyaway.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "trip")
public class Trip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "trip_id")
	private String tripId;

	@Embedded
	private Source source;

	@Embedded
	private Destination destination;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_trip", joinColumns = @JoinColumn(name = "trip_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	List<User> users = new ArrayList<User>();

	@Column(name = "departure_date")
	@Temporal(TemporalType.DATE)
	private Date departureDate;

	@Column(name = "departure_time")
	@Temporal(TemporalType.TIME)
	private Date departureTime;

	@Column(name = "arrival_date")
	@Temporal(TemporalType.DATE)
	private Date arrivalDate;

	@Column(name = "arrival_time")
	@Temporal(TemporalType.TIME)
	private Date arrivalTime;

	@Column(name = "airline_name")
	private String airlineName;

	@Column(name = "flightNumber")
	private String flightNumber;

	@Column(name = "aircraft_manufacturer")
	private String aircraftManufacturer;

	@Column(name = "max_passengers")
	private int maxPassengers;

	@Column(name = "ticket_price")
	private int ticketPrice;

	public Trip() {

	}

	public Trip(int id, String tripId, Source source, Destination destination, List<User> users, Date departureDate,
			Date departureTime, Date arrivalDate, Date arrivalTime, String airlineName, String flightNumber,
			String aircraftManufacturer, int maxPassengers, int ticketPrice) {
		super();
		this.id = id;
		this.tripId = tripId;
		this.source = source;
		this.destination = destination;
		this.users = users;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.airlineName = airlineName;
		this.flightNumber = flightNumber;
		this.aircraftManufacturer = aircraftManufacturer;
		this.maxPassengers = maxPassengers;
		this.ticketPrice = ticketPrice;
	}

	public Trip(String flightNumber) {
		super();
		this.flightNumber = flightNumber;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getAircraftManufacturer() {
		return aircraftManufacturer;
	}

	public void setAircraftManufacturer(String aircraftManufacturer) {
		this.aircraftManufacturer = aircraftManufacturer;
	}

	public int getMaxPassengers() {
		return maxPassengers;
	}

	public void setMaxPassengers(int maxPassengers) {
		this.maxPassengers = maxPassengers;
	}

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	@Override
	public String toString() {
		return "Trip [id=" + id + ", tripId=" + tripId + ", source=" + source + ", destination=" + destination
				+ ", users=" + users + ", departureDate=" + departureDate + ", departureTime=" + departureTime
				+ ", arrivalDate=" + arrivalDate + ", arrivalTime=" + arrivalTime + ", airlineName=" + airlineName
				+ ", flightNumber=" + flightNumber + ", aircraftManufacturer=" + aircraftManufacturer
				+ ", maxPassengers=" + maxPassengers + ", ticketPrice=" + ticketPrice + "]";
	}

}
