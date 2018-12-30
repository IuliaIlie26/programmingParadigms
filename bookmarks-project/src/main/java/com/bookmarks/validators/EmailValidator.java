package com.bookmarks.validators;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.bookmarks.dao.UserDAO;

@FacesValidator("email")
public class EmailValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String email = value.toString();
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		final ResourceBundle bundle;
		final FacesMessage msg;
		if (!result && UserDAO.usernameOrEmailExists(email, "email") == true) {
			bundle = ResourceBundle.getBundle("ApplicationResources_en");
			msg = new FacesMessage(bundle.getString("emailValid"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}
