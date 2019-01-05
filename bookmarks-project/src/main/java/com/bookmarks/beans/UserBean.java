package com.bookmarks.beans;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;
import com.bookmarks.dao.UserDAO;
import com.bookmarks.util.AESEncryptionUtil;

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
	private String oldPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
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

	public String register() {

		logger.info("Saving user: " + username);
		dao.insert(name, lastname, email, username, password);
		return "mainpage";
	}

	public void saveChanges() {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("username");
		if (email != null || email != "")
			try {
				InternetAddress emailAddr = new InternetAddress(email);
				emailAddr.validate();

			} catch (AddressException ex) {
				logger.error("Email Address is wrong.");
			}
		dao.update(username, name, lastname, email);
		logger.info("User data updated.");

	}

	public void changePassword() {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("username");
		if (password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")) {
			if (UserDAO.validate(username, AESEncryptionUtil.encrypt(oldPassword))) {
				dao.changePassword(username, password);
				logger.info("Password changed.");
			} else {
				logger.error("Old password validation failed.");
				final ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources_en");
				final FacesMessage msg = new FacesMessage(bundle.getString("failed"));
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			}
		} else {
			logger.error("Password policy not met.");
			final ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources_en");
			final FacesMessage msg = new FacesMessage(bundle.getString("passwordPolicy"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		}

	}
}
