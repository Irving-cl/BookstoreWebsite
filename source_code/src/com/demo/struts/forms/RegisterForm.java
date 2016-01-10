package com.demo.struts.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

@SuppressWarnings("serial")
public class RegisterForm extends ActionForm {

	protected String username = null;
	protected String password1 = null;
	protected String password2 = null;

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getUsername() {
		return username;
	}

	public void getUsername(String username) {
		this.username = username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ActionErrors validate(ActionMapping argo, HttpServletRequest arg1) {
		ActionErrors errors = new ActionErrors();

		String queryString = arg1.getQueryString();
		if (queryString.equalsIgnoreCase("method=register")) {

			if (username == null || username.equals("")) {
				errors.add("username", new ActionMessage(
						"register.error.username"));
			}

			if (password1 == null || password1.equals("")) {
				errors.add("password1", new ActionMessage(
						"register.error.password1"));
			}

			if (password2 == null || !password2.equals(password1)) {
				errors.add("password2", new ActionMessage(
						"register.error.password2"));
			}
		}
		return errors;
	}
}
