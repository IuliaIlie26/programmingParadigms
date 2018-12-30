package com.bookmarks.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream;

import com.bookmarks.dao.BookmarksDAO;
import com.bookmarks.dao.UserDAO;
import com.bookmarks.entities.Collections;
import com.bookmarks.entities.Users;
import com.sun.tools.xjc.model.SymbolSpace;

public class Main {

	public static void main(String[] args) {

	AESEncryptionUtil crypt = new AESEncryptionUtil();
	try {
		System.out.println(AESEncryptionUtil.encrypt("test"));
		System.out.println(AESEncryptionUtil.decrypt("CzpPD097hwRo/jPh2cQ0iNC6Dxyuo13qcahR/J+J9TwGzaYIA6LtACqwlXtHLHJ7xFsErrrLZgOdrH06Er8/7w=="));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
