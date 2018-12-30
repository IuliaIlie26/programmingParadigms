package com.bookmarks.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream;

import com.bookmarks.entities.Bookmarks;
import com.bookmarks.entities.Collections;
import com.bookmarks.util.JPAUtil;

public class BookmarksDAO {

	private static EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
	private static EntityTransaction transaction;
	private static EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
	private static JinqJPAStreamProvider streams = new JinqJPAStreamProvider(emf);
	private final static Logger logger = Logger.getLogger(BookmarksDAO.class);

	public static JinqStream<Bookmarks> bookmarks() {

		if (!em.isOpen()) {
			em = emf.createEntityManager();
		}
		return streams.streamAll(em, Bookmarks.class);
	}

	public List<Bookmarks> search(CharSequence criteria) {

		return bookmarks().where(b -> b.getName().contains(criteria)).toList();

	}

	public static void insert(Long user, String link, String name, Collections col) {
		try {

			if (!em.isOpen()) {
				em = emf.createEntityManager();
			}

			transaction = em.getTransaction();
			transaction.begin();

			Bookmarks bk = new Bookmarks();
			bk.setUserid(user);
			bk.setLink(link);
			bk.setName(name);
			List<Collections> colList = new ArrayList<>();
			colList.add(col);
			bk.setCollection(colList);
			ArrayList<Bookmarks> bkList = new ArrayList<>();
			bkList.add(bk);
			col.setBookmarks(bkList);
			em.persist(bk);
			transaction.commit();
			logger.info("Bookmark " + bk.getName() + " was saved in collection " + col.getName());

		} catch (Exception e) {
			transaction.rollback();
			logger.error("Could not save the bookmark: ");
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

	public void rename(Bookmarks bk, String name) {
		try {
			if (!em.isOpen()) {
				em = emf.createEntityManager();
			}

			transaction = em.getTransaction();
			transaction.begin();

			bk.setName(name);
			transaction.commit();
			logger.info("Bookmark renamed!");

		} catch (Exception e) {
			transaction.rollback();
			logger.error("Bookmark cannot be renamed. Cause: ");
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
