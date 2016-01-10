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

import com.demo.hibernate.beans.SalesRecord;
import com.demo.hibernate.dao.BookDAO;
import com.demo.hibernate.dao.SalesRecordDAO;
import com.demo.struts.util.Constants;
import com.sun.istack.internal.logging.Logger;

public class SearchRecordAction extends DispatchAction {

	protected SalesRecordDAO recordDAO;
	protected BookDAO bookDAO;

	Logger log = Logger.getLogger(this.getClass());

	public SalesRecordDAO getRecordDAO() {
		return recordDAO;
	}

	public void setRecordDAO(SalesRecordDAO recordDAO) {
		this.recordDAO = recordDAO;
	}

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
		String username = (String) request.getParameter("username");
		HttpSession session = request.getSession(true);
		List<SalesRecord> records = getRecordDAO().getSomeRecords(bookname,
				username);

		if (!category.equals("所有分类")) {
			List<SalesRecord> result = new ArrayList<SalesRecord>();
			for (SalesRecord record : records) {
				String c = getBookDAO().getBook(record.getBookname())
						.getCategory();
				if (c.equals(category)) {
					result.add(record);
				}
			}
			session.setAttribute("records", result);
			if (result.isEmpty()) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
						"searchrecord.message.failed"));
			}
		} else {
			session.setAttribute("records", records);
			if (records.isEmpty()) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
						"searchrecord.message.failed"));
			}
		}
		saveErrors(request, errors);
		ActionForward forward = mapping.findForward(Constants.SUCCESS_KEY);
		return forward;
	}
}
