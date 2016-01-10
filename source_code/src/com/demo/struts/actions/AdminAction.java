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
import com.demo.hibernate.beans.User;
import com.demo.hibernate.dao.BookDAO;
import com.demo.hibernate.dao.UserDAO;
import com.demo.struts.util.Constants;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.sun.istack.internal.logging.Logger;

public class AdminAction extends DispatchAction {

	Mongo mongo = null;

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

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// invalidate the original session if exists
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		// create a new session for the user
		session = request.getSession(true);

		// get all users and books
		List<User> users = (List<User>) getUserDAO().getUsers();
		session.setAttribute(Constants.ALL_USER_KEY, users);
		List<Book> books = (List<Book>) getBookDAO().getBooks();
		session.setAttribute(Constants.ALL_BOOK_KEY, books);

		ActionForward forward = mapping.findForward(Constants.SUCCESS_KEY);
		return (forward);
	}

	public ActionForward removeUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// invalidate the original session if exists
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		// create a new session for the user
		session = request.getSession(true);

		try {
			// get parameters
			String username = request.getParameter("username").toString();
			getUserDAO().deleteUser(username);

			// remove user profile in mongodb
			if (mongo == null) {
				mongo = new Mongo("127.0.0.1", 27017);
			}
			DB db = mongo.getDB("mydb");
			db.authenticate("uid", "pwd".toCharArray());
			DBCollection coll = db.getCollection("user");
			BasicDBObject doc = new BasicDBObject();
			doc.put("name", username);
			coll.remove(doc);

		} catch (Exception e) {
			e.printStackTrace();
		}

		List<User> users = (List<User>) getUserDAO().getUsers();
		session.setAttribute(Constants.ALL_USER_KEY, users);
		List<Book> books = (List<Book>) getBookDAO().getBooks();
		session.setAttribute(Constants.ALL_BOOK_KEY, books);

		ActionForward forward = mapping.findForward(Constants.SUCCESS_KEY);
		return (forward);
	}

	public ActionForward removeBook(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// invalidate the original session if exists
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		// create a new session for the user
		session = request.getSession(true);

		try {
			// get parameters
			request.setCharacterEncoding("UTF-8");
			String bookname = new String(request.getParameter("bookname")
					.getBytes("ISO-8859-1"), "utf-8");
			log.info("É¾³ý£º" + bookname);
			getBookDAO().deleteBook(bookname);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<User> users = (List<User>) getUserDAO().getUsers();
		session.setAttribute(Constants.ALL_USER_KEY, users);
		List<Book> books = (List<Book>) getBookDAO().getBooks();
		session.setAttribute(Constants.ALL_BOOK_KEY, books);

		ActionForward forward = mapping.findForward(Constants.SUCCESS_KEY);
		return (forward);
	}

	public ActionForward quit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// invalidate the original session if exists
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		ActionForward forward = mapping.findForward(Constants.LOGOUT_KEY);
		return (forward);
	}
}
