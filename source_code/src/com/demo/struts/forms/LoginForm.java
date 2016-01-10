package com.demo.struts.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

@SuppressWarnings("serial")
public class LoginForm extends ActionForm {

	protected String username = null;
	protected String password = null;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ActionErrors validate(ActionMapping argo, HttpServletRequest arg1) {
		ActionErrors errors = new ActionErrors();

		if (username == null || username.equals("")) {
			errors.add("username", new ActionMessage("login.error.username"));
		}

		if (password == null || password.equals("")) {
			errors.add("password", new ActionMessage("login.error.password"));
		}
		arg1.setAttribute("loginFormBean", this);
		return errors;
	}
}
