package com.bookmarks.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import com.bookmarks.entities.Bookmarks;
import com.bookmarks.entities.Collections;
import com.bookmarks.entities.Users;
import com.bookmarks.util.JPAUtil;

public class BookmarksDAO {

	private static EntityManager em;
	private static EntityTransaction transaction;
	private final static Logger logger = Logger.getLogger(UserDAO.class);

	public static void insert(Users user, String link, String name, Collections col) {

		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			transaction = em.getTransaction();
			Bookmarks bk = new Bookmarks();
			bk.setUserid(user.getUserId());
			bk.setLink(link);
			bk.setName(name);
			bk.setVotes(0);
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
			logger.info("Could not save the bookmark: ");
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
}
