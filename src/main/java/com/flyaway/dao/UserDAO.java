package com.flyaway.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.flyaway.model.Trip;
import com.flyaway.model.User;
import com.flyaway.util.HibernateUtil;

public class UserDAO {

	// addUser
	public boolean addUser(User user) {
		Session session = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();
			// Saving object
			session.save(user);
			// commits transaction
			System.out.println("User added successfully");
			session.getTransaction().commit();
			// closing the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// updateUser
	public boolean updateUser(User user) {

		Session session = null;

		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();
			// Saving object
			session.update(user);
			// commits transaction
			System.out.println("User updated successfully");
			session.getTransaction().commit();
			// closing the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// finds whether userName exits to avoid duplicate
	public boolean isUserNameExists(String email) {
		Session session = null;
		User user = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();

			String hql = "from User WHERE emailId =:email ";
			Query query = session.createQuery(hql);
			query.setParameter("email", email);

			user = (User) query.getResultList();
			session.getTransaction().commit();
			// closing the session
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user == null)
			return false;
		else
			return true;
	}

	// authenticate user
	public boolean authenticateUser(String email, String password) {
		Session session = null;
		User user = new User();
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();

			String hql = "from User  WHERE emailId =:email";
			user = (User) session.createQuery(hql).setParameter("email", email).uniqueResult();

			if (user != null & user.getPassword().equals(password)) {
				System.out.println("User details retrieved " + user.getFirstName());
				return true;
			}
			session.getTransaction().commit();
			// closing the session
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// getUser by username
	public User getUser(String emailId) {
		Session session = null;
		User user = new User();
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();

			String hql = "from User WHERE emailId =:emailId";
			Query query = session.createQuery(hql);
			query.setParameter("emailId", emailId);
			user = (User) query.getSingleResult();

			if (user != null) {
				System.out.println("User details retrieved " + user.getFirstName());
				return user;
			}
			session.getTransaction().commit();
			// closing the session
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	// getting user's tickets
	@SuppressWarnings("unchecked")
	public List<Trip> getUserTickets(String userName) {
		Session session = null;
		List<Trip> tripList = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();

			String hql = " Select trips from User user WHERE user.emailId =: userName";
			Query query = session.createQuery(hql);
			query.setParameter("userName", userName);
			tripList = query.getResultList();

			if (!tripList.isEmpty()) {
				System.out.println("Trip details retrieved ");
				return tripList;
			}
			session.getTransaction().commit();
			// closing the session
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tripList;
	}
}
