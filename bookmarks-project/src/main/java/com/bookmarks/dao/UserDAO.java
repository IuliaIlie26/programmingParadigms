package com.bookmarks.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.apache.log4j.Logger;
import com.bookmarks.entities.Bookmarks;
import com.bookmarks.entities.Collection;
import com.bookmarks.entities.User;
import com.bookmarks.util.JPAUtil;

public class UserDAO {

	private static EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
	private static EntityTransaction transaction = em.getTransaction();
	private final static Logger logger = Logger.getLogger(UserDAO.class);

	public static void insert() {

		if (!transaction.isActive()) {
			transaction.begin();
			logger.info("Transaction began");
		}
		try {
			User user = new User();
			user.setUsername("ALOHA");
			user.setPassword("test");
			user.setEmailAddress("ALOHA address");
			user.setLastname("last name");
			user.setName("name1");
			em.persist(user);
			logger.info("User persisted");

			Collection col = new Collection();
			col.setName("My Bookmarks");
			col.setUserid(user.getUserId());

			Bookmarks bk = new Bookmarks();
			bk.setUserid(user.getUserId());
			bk.setLink("link");
			bk.setName("name");
			bk.setVotes(0);
			List<Collection> colList = new ArrayList<>();
			colList.add(col);
			bk.setCollection(colList);

			ArrayList<Bookmarks> bkList = new ArrayList<>();
			bkList.add(bk);
			col.setBookmarks(bkList);
			em.persist(bk);
			logger.info("Bookmark & Collection persisted");
			transaction.commit();
		} catch (Exception e) {
			logger.info("Exception catched: ");
			e.printStackTrace();
		}
	}
}
