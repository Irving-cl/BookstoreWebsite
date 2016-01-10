package com.demo.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.DispatchAction;

import com.demo.hibernate.beans.Book;
import com.demo.hibernate.dao.BookDAO;
import com.demo.struts.forms.BookForm;
import com.demo.struts.util.Constants;
import com.sun.istack.internal.logging.Logger;

public class AddBookAction extends DispatchAction {

	protected BookDAO bookDAO;

	Logger log = Logger.getLogger(this.getClass());

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward forward = mapping.findForward(Constants.SUCCESS_KEY);
		return (forward);
	}

	@SuppressWarnings("deprecation")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionErrors errors = new ActionErrors();
		BookForm bookForm = (BookForm) form;

		try {
			// get Parameters
			String bookname = bookForm.getBookname();

			// add a new book
			if (isExist(request, bookForm)) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
						"addbook.message.failed"));
				log.info("book has existed");
			} else {
				insert(request, bookForm);
				log.info("Add book " + bookname + "successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		saveErrors(request, errors);
		ActionForward forward = mapping.findForward(Constants.SUCCESS_KEY);
		return (forward);
	}

	private boolean isExist(HttpServletRequest request, BookForm bookForm) {
		if (getBookDAO().isExist(bookForm.getBookname())) {
			return true;
		} else {
			return false;
		}
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
