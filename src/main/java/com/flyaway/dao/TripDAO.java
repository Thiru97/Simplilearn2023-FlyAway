package com.flyaway.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.hibernate.Session;

import com.flyaway.model.Trip;
import com.flyaway.util.HibernateUtil;

public class TripDAO {

	// Add trip
	public boolean addTrip(Trip trip) {

		Session session = null;

		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();
			// Saving object
			session.save(trip);
			// commits transaction
			System.out.println("trip added successfully");
			session.getTransaction().commit();
			// closing the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// Retrieve trip
	@SuppressWarnings("unchecked")
	public List<Trip> getTripList() {
		Session session = null;
		List<Trip> tripList = null;
		try {
			session = HibernateUtil.getSession();

			session.beginTransaction();
			String hql = " FROM Trip ";
			Query query = session.createQuery(hql);
			tripList = query.getResultList();

			System.out.println("triplist retrived");

			session.getTransaction().commit();
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			// handle exception here
		}

		return tripList;
	}

	// Delete trip
	public boolean deleteTrip(int id) {
		Session session = null;
		Trip trip = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			trip = session.get(Trip.class, id);
			if (trip != null) {
				session.delete(trip);
				System.out.println("deleted successfully");
			}
			session.getTransaction().commit();
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			// handle exception here
		}
		return true;
	}

	// update trip
	public boolean updateTrip(Trip trip) {

		Session session = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();
			// Saving object
			session.update(trip);
			// commits transaction
			System.out.println("update successfull");
			session.getTransaction().commit();
			// closing the session
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// get Trip by id
	public Trip getTripById(int id) {
		Session session = null;
		Trip trip = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			trip = session.get(Trip.class, id);

			System.out.println("trip details retrieved by id ");
			session.getTransaction().commit();
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			// handle exception here
		}
		return trip;
	}

//gettrip by Source City Name 
	@SuppressWarnings("unchecked")
	public List<Trip> getTripsBySource(String source) {
		Session session = null;
		List<Trip> tripList = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			System.out.println(source);

			String hql = "FROM Trip WHERE source.sourceCityName = :source";
			Query query = session.createQuery(hql);
			query.setParameter("source", source);

			tripList = query.getResultList();

			System.out.println("trip details retrieved by source ");
			session.getTransaction().commit();
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			// handle exception here
		}
		return tripList;
	}

//gettrip by airline number
	@SuppressWarnings("unchecked")
	public List<Trip> getTripsByAirline(String airline) {
		Session session = null;
		List<Trip> tripList = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			String hql = "FROM Trip  WHERE airlineName = :airline";
			Query query = session.createQuery(hql);
			query.setParameter("airline", airline);

			tripList = query.getResultList();

			System.out.println("trip details retrieved by airline ");
			session.getTransaction().commit();
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			// handle exception here
		}
		return tripList;
	}

//get trip by destination city Name
	@SuppressWarnings("unchecked")
	public List<Trip> getTripsByDestination(String destination) {
		Session session = null;
		List<Trip> tripList = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			String hql = "FROM Trip WHERE destination.destinationCityName = :destination";
			Query query = session.createQuery(hql);
			query.setParameter("destination", destination);

			tripList = query.getResultList();

			System.out.println("trip details retrieved by destination ");
			session.getTransaction().commit();
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			// handle exception here
		}
		return tripList;
	}

	// Get Source distinct source cities to populate the user search form
	@SuppressWarnings("unchecked")
	public List<Trip> getSourceCity() {
		Session session = null;
		List<Trip> tripList = null;

		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			String hql = "SELECT DISTINCT source.sourceCityName FROM Trip";
			Query query = session.createQuery(hql);

			tripList = query.getResultList();

			System.out.println("trip details retrieved by destination ");
			session.getTransaction().commit();
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			// handle exception here
		}
		return tripList;
	}

	// Get destination distinct source cities to populate the user search form
	@SuppressWarnings("unchecked")
	public List<Trip> getDestinationCity() {
		Session session = null;
		List<Trip> tripList = null;

		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			String hql = "SELECT DISTINCT destination.destinationCityName FROM Trip";
			Query query = session.createQuery(hql);

			tripList = query.getResultList();

			System.out.println("trip details retrieved by destination ");
			session.getTransaction().commit();
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			// handle exception here
		}
		return tripList;
	}

	// Gets flights available for the queried source and destination cities for the
	// given date
	@SuppressWarnings("unchecked")
	public List<Trip> getFlights(String source, String destination, Date date) {
		Session session = null;
		List<Trip> tripList = null;

		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			String hql = "from Trip WHERE source.sourceCityName =:source "
					+ "AND destination.destinationCityName =:destination " + "and departureDate = :date";
			Query query = session.createQuery(hql);
			query.setParameter("source", source);
			query.setParameter("destination", destination);
			query.setParameter("date", date, TemporalType.DATE);

			tripList = query.getResultList();

			System.out.println("flights retrieved by soure, destination, date ");
			session.getTransaction().commit();
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			// handle exception here
		}
		return tripList;
	}

}
