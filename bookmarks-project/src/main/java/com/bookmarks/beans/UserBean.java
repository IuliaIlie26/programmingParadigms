package com.bookmarks.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;
import com.bookmarks.dao.UserDAO;


@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {
	private final static Logger logger = Logger.getLogger(UserBean.class);

	public String save() {

		logger.info("Save user");
		UserDAO.insert();
		return "index";
	}
}
