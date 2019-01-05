package com.bookmarks.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import com.bookmarks.dao.UserDAO;
import com.bookmarks.util.SessionUtils;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -5963536288790371310L;
	private final static Logger logger = Logger.getLogger(LoginBean.class);

	private String username;
	private String password;
	private String isLoggedIn="none", isLoggedOut="block";
	private String lastname = null;

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getIsLoggedOut() {
		return isLoggedOut;
	}

	public void setIsLoggedOut(String isLoggedOut) {
		this.isLoggedOut = isLoggedOut;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setIsLoggedIn(String isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String validateLogin() {
		HttpSession session = SessionUtils.getSession();
		session.setAttribute("username", username);
		logger.debug("User " + username + " logged in.");
		isLoggedIn = "block";
		isLoggedOut = "none";
		lastname = UserDAO.getLastname(username);
		return "index";
	}

	public String logout() {

		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		logger.debug("User logged out.");
		isLoggedIn = "none";
		isLoggedOut = "block";
		return "index";
	}
}
