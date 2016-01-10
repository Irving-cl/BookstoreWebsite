package com.demo.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.demo.hibernate.beans.Book;
import com.demo.hibernate.dao.BookDAO;
import com.demo.struts.util.Constants;
import com.sun.istack.internal.logging.Logger;

public class BackAction extends DispatchAction {

	protected BookDAO bookDAO;

	Logger log = Logger.getLogger(this.getClass());

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		log.info(username);
		session.setAttribute(Constants.USERNAME_KEY, username);
		List<Book> books = (List<Book>) getBookDAO().getBooks();
		session.setAttribute(Constants.ALL_BOOK_KEY, books);
		forward = mapping.findForward(Constants.SUCCESS_KEY);
		return forward;
	}
}
