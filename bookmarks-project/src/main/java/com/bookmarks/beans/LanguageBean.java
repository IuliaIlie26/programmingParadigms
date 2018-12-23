package com.bookmarks.beans;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

@ManagedBean(name = "languageBean", eager = true)
@SessionScoped
public class LanguageBean implements Serializable {

	private static final long serialVersionUID = 3282514621029050543L;
	private static Map<String, Object> countries;
	private String locale;
	private final static Logger logger = Logger.getLogger(LanguageBean.class);

	static {
		countries = new LinkedHashMap<>();
		countries.put("English", Locale.ENGLISH);
		countries.put("Deutsch", Locale.GERMAN);
		countries.put("Romana", Locale.ROOT);
	}

	public Map<String, Object> getCountriesInMap() {
		return countries;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String localeCode) {
		this.locale = localeCode;
	}

	public Map<String, Object> getCountries() {
		return countries;
	}

	public void setCountries(Map<String, Object> countries) {
		LanguageBean.countries = countries;
	}

	public void change() {

		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		String language = params.get("language");
		for (Map.Entry<String, Object> entry : countries.entrySet()) {
			logger.info(entry.getValue().toString());
			if (entry.getValue().toString().equals(language)) {
				FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
			}
		}
	}

}
