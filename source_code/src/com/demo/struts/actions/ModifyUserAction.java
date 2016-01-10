package com.demo.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.DispatchAction;

import com.demo.hibernate.beans.Book;
import com.demo.hibernate.beans.User;
import com.demo.hibernate.dao.BookDAO;
import com.demo.hibernate.dao.UserDAO;
import com.demo.struts.forms.LoginForm;
import com.demo.struts.util.Constants;
import com.sun.istack.internal.logging.Logger;

public class ModifyUserAction extends DispatchAction {

	protected UserDAO userDAO;
	protected BookDAO bookDAO;

	Logger log = Logger.getLogger(this.getClass());

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	@SuppressWarnings({ "deprecation" })
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionErrors errors = new ActionErrors();
		LoginForm modifyForm = (LoginForm) form;

		// invalidate the original session if exists
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		// create a new session for the user
		session = request.getSession(true);

		try {
			// check existence
			if (!isExist(request, modifyForm)) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
						"admin.message.failed"));
			} else {
				modify(request, modifyForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<User> users = (List<User>) getUserDAO().getUsers();
		session.setAttribute(Constants.ALL_USER_KEY, users);
		List<Book> books = (List<Book>) getBookDAO().getBooks();
		session.setAttribute(Constants.ALL_BOOK_KEY, books);

		if (!errors.isEmpty())
			saveErrors(request, errors);
		ActionForward forward = mapping.findForward(Constants.SUCCESS_KEY);
		return (forward);
	}

	private boolean isExist(HttpServletRequest request, LoginForm modifyForm) {
		if (getUserDAO().isExist(modifyForm.getUsername())) {
			return true;
		} else {
			return false;
		}
	}

	private void modify(HttpServletRequest request, LoginForm modifyForm) {
		User user = new User();
		user.setUsername(modifyForm.getUsername());
		user.setPassword(modifyForm.getPassword());
		getUserDAO().insertUser(user);
	}
}
