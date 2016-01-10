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
import com.demo.struts.forms.BookForm;
import com.demo.struts.util.Constants;
import com.sun.istack.internal.logging.Logger;

public class ModifyBookAction extends DispatchAction {

	protected BookDAO bookDAO;
	protected UserDAO userDAO;

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

		try {
			// get parameters
			request.setCharacterEncoding("UTF-8");
			String bookname = new String(request.getParameter("bookname")
					.getBytes("ISO-8859-1"), "utf-8");
			Book book = getBookDAO().getBook(bookname);
			session.setAttribute("bookname", bookname);
			session.setAttribute("img_name", book.getImg_name());
			session.setAttribute("category", book.getCategory());
			session.setAttribute("price", book.getPrice());
			session.setAttribute("stock_num", book.getStock_num());
			session.setAttribute("description", book.getDescription());
		} catch (Exception e) {
			e.printStackTrace();
		}

		ActionForward forward = mapping.findForward(Constants.SUCCESS_KEY);
		return (forward);
	}

	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BookForm bookForm = (BookForm) form;
		try {
			insert(request, bookForm);
			log.info("Modify " + bookForm.getBookname() + " successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}

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

		ActionForward forward = mapping.findForward(Constants.GOBACK_KEY);
		return (forward);
	}

	private void insert(HttpServletRequest request, BookForm bookForm) {
		Book book = new Book();
		book.setBookname(bookForm.getBookname());
		book.setImg_name(bookForm.getImg_name());
		book.setCategory(bookForm.getCategory());
		book.setDescription(bookForm.getDescription());
		book.setPrice(bookForm.getPrice());
		book.setStock_num(bookForm.getStock_num());
		getBookDAO().insertBook(book);
	}
}
