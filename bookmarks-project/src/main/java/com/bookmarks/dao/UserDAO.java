package com.bookmarks.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.apache.log4j.Logger;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream;

import com.bookmarks.entities.Users;
import com.bookmarks.util.JPAUtil;

public class UserDAO {

	private static EntityManager em;
	private static EntityTransaction transaction;
	private static EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
	private static JinqJPAStreamProvider streams = new JinqJPAStreamProvider(emf);
	private final static Logger logger = Logger.getLogger(UserDAO.class);

	private JinqStream<Users> users() {
		return streams.streamAll(em, Users.class);
	}

	public void getNameAndLastName() {

		users().forEach(u -> logger.info(u.getName() + " " + u.getLastname()));

	}

	public void insert(String name, String lastname, String email, String username, String password) {

		try {

			em = emf.createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();
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
			logger.info("User cannot be saved. Cause: ");
			e.printStackTrace();
		} finally {
			try {
				em.close();
			} catch (Exception e) {
				logger.info("Exception caught. Cannot close EntityManager.");
				e.printStackTrace();
			}
		}

	}

}
