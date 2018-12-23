package com.bookmarks.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream;
import com.bookmarks.dao.UserDAO;
import com.bookmarks.entities.Users;

public class Main {

	private static EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
	private static EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
	private static EntityTransaction transaction = em.getTransaction();
	private final static Logger logger = Logger.getLogger(UserDAO.class);
	private static JinqJPAStreamProvider streams;

	private static JinqStream<Users> users() {
		return streams.streamAll(em, Users.class);
	}

	public static void test() {

		streams = new JinqJPAStreamProvider(emf);

		users().forEach(c -> System.out.println(c.getName() + " " + c.getName() + " " + c.getLastname()));

	}

	public static void main(String[] args) {
		test();

	}
}
