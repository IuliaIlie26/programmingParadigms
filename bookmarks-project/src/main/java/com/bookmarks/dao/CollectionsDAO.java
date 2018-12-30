package com.bookmarks.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream;

import com.bookmarks.entities.Bookmarks;
import com.bookmarks.entities.Collections;
import com.bookmarks.util.JPAUtil;

public class CollectionsDAO {

	private static EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
	private static EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
	private static JinqJPAStreamProvider streams = new JinqJPAStreamProvider(emf);
	private static EntityTransaction transaction;
	private final static Logger logger = Logger.getLogger(CollectionsDAO.class);

	private static JinqStream<Collections> collections() {

		if (!em.isOpen()) {
			em = emf.createEntityManager();
		}
		return streams.streamAll(em, Collections.class);
	}

	public List<Collections> search(CharSequence criteria) {

		return collections().where(c -> c.getName().contains(criteria)).toList();

	}

	public static List<Collections> getPrivate(Long user) {

		List<Collections> list = collections().where(c -> c.getUserid() == user).toList();
		java.util.Collections.sort(list, new Comparator<Collections>() {

			@Override
			public int compare(Collections o1, Collections o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		return list;
	}

	public static Collection<Collections> getPublic() {
		List<Collections> list = collections().where(c -> c.getUserid() == 0).toList();

		java.util.Collections.sort(list, new Comparator<Collections>() {

			@Override
			public int compare(Collections o1, Collections o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		return list;
	}

	public static void insert(String name, Long owner, boolean shared) {

		try {
			if (!em.isOpen()) {
				em = emf.createEntityManager();
			}

			transaction = em.getTransaction();
			transaction.begin();

			Collections collection = new Collections();
			collection.setName(name);
			collection.setShared(shared);
			collection.setUserid(owner);
			collection.setBookmarks(new ArrayList<Bookmarks>());
			collection.setVotes(0);
			em.persist(collection);
			transaction.commit();
			logger.info("Collection saved!");

		} catch (Exception e) {
			transaction.rollback();
			logger.error("Collection cannot be saved. Cause: ");
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

	public static Map<Collections, List<Bookmarks>> getMyCollectionsByUserId(Long userid) {
		Map<Collections, List<Bookmarks>> map = new HashMap<>();

		collections().where(c -> c.getUserid() == userid).forEach(c -> map.put(c, c.getBookmarks()));

		return map;
	}

	public void vote(Collections collection) {
		try {
			if (!em.isOpen()) {
				em = emf.createEntityManager();
			}

			transaction = em.getTransaction();
			transaction.begin();
			collection.setVotes(collection.getVotes() + 1);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			logger.error("Voting not posible: could not update the bookmark: ");
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

	public void rename(Collections c, String name) {
		try {
			if (!em.isOpen()) {
				em = emf.createEntityManager();
			}

			transaction = em.getTransaction();
			transaction.begin();

			c.setName(name);
			transaction.commit();
			logger.info("Collection renamed!");

		} catch (Exception e) {
			transaction.rollback();
			logger.error("Collection cannot be renamed. Cause: ");
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

	public void share(Collections collection) {
		try {
			if (!em.isOpen()) {
				em = emf.createEntityManager();
			}

			transaction = em.getTransaction();
			transaction.begin();
			collection.setShared(true);
			transaction.commit();
			logger.info("Collection shared  " + collection.getName());

		} catch (Exception e) {
			transaction.rollback();
			logger.error("Collection cannot be shared. Cause: ");
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
