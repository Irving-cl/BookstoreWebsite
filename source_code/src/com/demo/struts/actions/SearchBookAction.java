package com.demo.struts.actions;

import java.util.ArrayList;
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
import com.demo.hibernate.dao.BookDAO;
import com.demo.struts.util.Constants;
import com.sun.istack.internal.logging.Logger;

public class SearchBookAction extends DispatchAction {

	protected BookDAO bookDAO;

	Logger log = Logger.getLogger(this.getClass());

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	@SuppressWarnings("deprecation")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionErrors errors = new ActionErrors();

		String category = new String(request.getParameter("category").getBytes(
				"ISO-8859-1"), "utf-8");
		String bookname = new String(request.getParameter("bookname").getBytes(
				"ISO-8859-1"), "utf-8");

		HttpSession session = request.getSession(true);

		List<Book> list = new ArrayList<Book>();
		if (!category.equals("所有分类")) {
			if (bookname.equals("")) {
				list = getBookDAO().getSomeBooks(category);
			} else {
				Book book = getBookDAO().getBook(bookname);
				if (book != null && book.getCategory().equals(category)) {
					list.add(book);
				}
			}
		} else {
			if (bookname.equals("")) {
				list = getBookDAO().getBooks();
			} else {
				Book book = getBookDAO().getBook(bookname);
				if (book != null)
					list.add(book);
			}
		}
		if (list.isEmpty()) {
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
					"searchrecord.message.failed"));
		}
		session.setAttribute("books", list);
		saveErrors(request, errors);
		ActionForward forward = mapping.findForward(Constants.SUCCESS_KEY);
		return forward;
	}
}
