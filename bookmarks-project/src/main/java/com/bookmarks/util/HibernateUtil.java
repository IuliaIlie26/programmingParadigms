package com.bookmarks.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.bookmarks.entities.Bookmarks;
import com.bookmarks.entities.Collection;
import com.bookmarks.entities.User;

public class HibernateUtil {

	private static final SessionFactory session = buildSessionFactory();
	private static final String HIBERNATE_CFG = "hibernate.cfg.xml";

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration config = new Configuration().addResource(HIBERNATE_CFG).configure();
			config.addAnnotatedClass(User.class);
			config.addAnnotatedClass(Bookmarks.class);
			config.addAnnotatedClass(Collection.class);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(config.getProperties())
					.build();
			return config.buildSessionFactory(serviceRegistry);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Factory error");
		}
	}

	public static SessionFactory getSessionFactory() {
		return session;
	}

}