package com.bookmarks.validators;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import com.bookmarks.dao.UserDAO;

@FacesValidator("username")
public class UsernameValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String username = (String) value;
		if (username == null || username.equals("")) {
			final ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources_en");

			final FacesMessage msg = new FacesMessage(bundle.getString("enterUsername"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		if (UserDAO.usernameOrEmailExists(username, "username") == true) {
			final ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources_en");

			final FacesMessage msg = new FacesMessage(bundle.getString("userTaken"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

	}

}
