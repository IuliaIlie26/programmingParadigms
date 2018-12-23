package com.bookmarks.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import com.bookmarks.dao.BookmarksDAO;
import com.bookmarks.dao.UserDAO;

@ManagedBean(name = "bookmarksBean")
@SessionScoped
public class BookmarksBean implements Serializable{

	private static final long serialVersionUID = 2255913338956039930L;
	private final static Logger logger = Logger.getLogger(BookmarksBean.class);
	private static BookmarksDAO dao = new BookmarksDAO();

}
