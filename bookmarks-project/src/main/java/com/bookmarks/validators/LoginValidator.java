package com.bookmarks.validators;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.bookmarks.dao.UserDAO;
import com.bookmarks.util.AESEncryptionUtil;

@FacesValidator("login")
public class LoginValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String username = (String) value;
		UIInput uiInputConfirmPassword = (UIInput) component.getAttributes().get("password");
		String password = uiInputConfirmPassword.getSubmittedValue().toString();
		final ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources_en");
		FacesMessage msg;
		if (password == null || password.equals("")) {

			msg = new FacesMessage(bundle.getString("enterPassword"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

		password = AESEncryptionUtil.encrypt(password);

		if (!UserDAO.validate(username, password)) {
			msg = new FacesMessage(bundle.getString("failed"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}