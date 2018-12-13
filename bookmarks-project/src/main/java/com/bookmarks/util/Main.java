package com.bookmarks.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.bookmarks.entities.Bookmarks;
import com.bookmarks.entities.Collection;
import com.bookmarks.entities.User;

public class Main {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		User user = new User();
		user.setUsername("tessssstwwsssws");
		user.setPassword("test");
		user.setEmailAddress("emwsssssswail1 address");
		user.setLastname("last name");
		user.setName("name1");
		session.save(user);

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

		session.save(bk);
		session.getTransaction().commit();
		session.close();

	}
}
