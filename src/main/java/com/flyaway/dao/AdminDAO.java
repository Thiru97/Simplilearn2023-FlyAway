package com.flyaway.dao;

import org.hibernate.Session;

import com.flyaway.model.Admin;
import com.flyaway.util.HibernateUtil;

public class AdminDAO {

	// Adds admin
	public boolean addAdmin(Admin admin) {

		Session session = null;

		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();
			// Saving object
			session.save(admin);
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

	// authenticate admin
	public boolean authenticateAdmin(String email, String password) {
		Session session = null;
		Admin admin = new Admin();
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();

			String hql = "from Admin  WHERE emailId =:email";
			admin = (Admin) session.createQuery(hql).setParameter("email", email).uniqueResult();

			if (admin != null & admin.getPassword().equals(password)) {
				System.out.println("Admin authenticated successfully" + admin.getEmailId());
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

	// change password for admin
	public boolean changePassword(String email, String password, String newPassword) {
		Session session = null;
		Admin admin = new Admin();
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();

			String hql = "from Admin  WHERE emailId =:email";
			admin = (Admin) session.createQuery(hql).setParameter("email", email).uniqueResult();

			System.out.println(admin);
			// checks whether old password is same
			if (admin != null && admin.getPassword().equals(password)) {
				admin.setPassword(newPassword);
				System.out.println(admin);
				session.saveOrUpdate(admin);

				session.getTransaction().commit();
				// closing the session
				session.close();
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
