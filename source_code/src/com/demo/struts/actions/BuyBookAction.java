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
import com.demo.hibernate.beans.Order;
import com.demo.hibernate.dao.BookDAO;
import com.demo.hibernate.dao.OrderDAO;
import com.demo.struts.util.Constants;
import com.sun.istack.internal.logging.Logger;

public class BuyBookAction extends DispatchAction {

	protected OrderDAO orderDAO;
	protected BookDAO bookDAO;

	Logger log = Logger.getLogger(this.getClass());

	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		request.setCharacterEncoding("UTF-8");
		String bookname = new String(request.getParameter("bookname").getBytes(
				"ISO-8859-1"), "utf-8");

		Book book = getBookDAO().getBook(bookname);
		session.setAttribute("bookname", bookname);
		session.setAttribute("img_name", book.getImg_name());
		session.setAttribute("category", book.getCategory());
		session.setAttribute("description", book.getDescription());
		session.setAttribute("price", book.getPrice());
		session.setAttribute("stock_num", book.getStock_num());
		session.setAttribute("username", username);

		ActionForward forward = mapping.findForward(Constants.SUCCESS_KEY);
		return (forward);
	}

	@SuppressWarnings("deprecation")
	public ActionForward make_order(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		try {
			HttpSession session = request.getSession(true);
			String username = (String) session.getAttribute("username");
			String bookname = (String) session.getAttribute("bookname");
			String str = request.getParameter("num");
			if (!str.matches("[0-9]+")) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
						"buybook.message.failed"));
			} else {
				Integer num = Integer.parseInt(str);

				// validate
				Integer stock_num = getBookDAO().getBook(bookname)
						.getStock_num();
				if (num <= 0 || num > stock_num) {
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
							"buybook.message.failed"));
				} else {
					// insert order
					Order order = new Order();
					order.setBookname(bookname);
					order.setUsername(username);
					order.setNum(num);
					getOrderDAO().insertOrder(order);
					// change stock_num in book
					Book book = getBookDAO().getBook(bookname);
					book.setStock_num(stock_num - num);
					getBookDAO().insertBook(book);
				}
			}

			// get all orders made by the user
			List<Order> orders = getOrderDAO().getOrders(username);
			session.setAttribute(Constants.ALL_ORDER_KEY, orders);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			forward = mapping.findForward(Constants.SUCCESS_KEY);
		} else {
			forward = mapping.findForward(Constants.GOBACK_KEY);
		}
		return (forward);
	}
}
