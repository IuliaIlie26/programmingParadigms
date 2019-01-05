package com.bookmarks.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.apache.log4j.Logger;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream;

import com.bookmarks.entities.Users;
import com.bookmarks.util.AESEncryptionUtil;
import com.bookmarks.util.JPAUtil;

public class UserDAO {

	private static EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
	private static EntityManager em = emf.createEntityManager();
	private static EntityTransaction transaction;
	private static JinqJPAStreamProvider streams = new JinqJPAStreamProvider(emf);
	private final static Logger logger = Logger.getLogger(UserDAO.class);

	private static JinqStream<Users> users() {

		if (!em.isOpen()) {
			em = emf.createEntityManager();
		}
		return streams.streamAll(em, Users.class);
	}

	public void getNameAndLastName() {

		users().forEach(u -> logger.info(u.getName() + " " + u.getLastname()));

	}

	public void insert(String name, String lastname, String email, String username, String password) {

		try {
			if (!em.isOpen()) {

				em = emf.createEntityManager();
			}

			transaction = em.getTransaction();
			transaction.begin();
			password = AESEncryptionUtil.encrypt(password);
			Users user = new Users();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmailAddress(email);
			user.setLastname(lastname);
			user.setName(name);
			em.persist(user);
			transaction.commit();
			logger.info("User saved!");

		} catch (Exception e) {
			transaction.rollback();
			logger.error("User cannot be saved. Cause: ");
			e.printStackTrace();
		} finally {
			try {
				em.close();
			} catch (Exception e) {
				logger.error("Exception caught. Cannot close EntityManager.");
				e.printStackTrace();
			}
		}

	}

	public static boolean validate(String username, String password) {
		return !users().where(u -> (u.getUsername().equals(username) || u.getEmailAddress().equals(username))
				&& u.getPassword().equals(password)).toList().isEmpty();
	}

	public static long getUserIdByUsername(String username) {
		return users().where(u -> u.getUsername().equals(username) || u.getEmailAddress().equals(username)).findFirst()
				.get().getUserId();
	}

	public static boolean usernameOrEmailExists(String name, String value) {
		if (name.equals("email"))
			return !users().where(u -> u.getEmailAddress().equals(value)).toList().isEmpty();
		else
			return !users().where(u -> u.getUsername().equals(value)).toList().isEmpty();
	}

	public void changePassword(String username, String password) {

		Users user = users().where(u -> u.getUsername().equals(username)).getOnlyValue();
		try {
			if (!em.isOpen()) {

				em = emf.createEntityManager();
			}

			transaction = em.getTransaction();
			transaction.begin();

			user.setPassword(password);

			transaction.commit();
			logger.info("Password changed!");

		} catch (Exception e) {
			transaction.rollback();
			logger.error("Password cannot be changed. Cause: ");
			e.printStackTrace();
		} finally {
			try {
				em.close();
			} catch (Exception e) {
				logger.error("Exception caught. Cannot close EntityManager.");
				e.printStackTrace();
			}
		}

	}

	public static String getLastname(String username) {
		return users().where(u -> u.getUsername().equals(username)).findFirst().get().getLastname();
	}

	public void update(String username, String name, String lastname, String email) {

		Users user = users().where(u -> u.getUsername().equals(username)).getOnlyValue();
		try {
			if (!em.isOpen()) {

				em = emf.createEntityManager();
			}

			transaction = em.getTransaction();
			transaction.begin();

			if (name != null)
				user.setName(name);
			if (lastname != null)
				user.setLastname(lastname);
			if (email != null)
				user.setEmailAddress(email);

			transaction.commit();
			logger.info("User data persisted!");

		} catch (Exception e) {
			transaction.rollback();
			logger.error("User data cannot be changed. Cause: ");
			e.printStackTrace();
		} finally {
			try {
				em.close();
			} catch (Exception e) {
				logger.error("Exception caught. Cannot close EntityManager.");
				e.printStackTrace();
			}
		}
	}

}
