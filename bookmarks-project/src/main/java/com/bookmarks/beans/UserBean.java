package com.bookmarks.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;
import com.bookmarks.dao.UserDAO;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = -5963536288790371310L;
	private final static Logger logger = Logger.getLogger(UserBean.class);
	private static UserDAO dao = new UserDAO();

	private String username;
	private String password;
	private String email;
	private String lastname;
	private String name;
	private String confirmPassword;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String save() {

		logger.info("Saving user: " + username);
		dao.insert(name, lastname, email, username, password);
		return "index";
	}
}
