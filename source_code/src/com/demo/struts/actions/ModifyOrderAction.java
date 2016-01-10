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

//import com.demo.hibernate.beans.Book;
import com.demo.hibernate.beans.Book;
import com.demo.hibernate.beans.Order;
import com.demo.hibernate.dao.BookDAO;
import com.demo.hibernate.dao.OrderDAO;
import com.demo.struts.util.Constants;
import com.sun.istack.internal.logging.Logger;

public class ModifyOrderAction extends DispatchAction {

	protected BookDAO bookDAO;
	protected OrderDAO orderDAO;

	Logger log = Logger.getLogger(this.getClass());

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession(true);
		try {
			// get parameters
			Integer order_id = Integer.parseInt(request
					.getParameter("order_id"));
			Order order = getOrderDAO().getOrder(order_id);
			String bookname = order.getBookname();
			session.setAttribute("id", order_id);
			session.setAttribute("bookname", bookname);
			session.setAttribute("num", order.getNum());
		} catch (Exception e) {
			e.printStackTrace();
		}

		ActionForward forward = mapping.findForward(Constants.SUCCESS_KEY);
		return (forward);
	}

	@SuppressWarnings("deprecation")
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");

		Integer order_id = Integer.parseInt(request.getParameter("order_id"));
		Integer num = Integer.parseInt(request.getParameter("num"));

		try {
			Order order = getOrderDAO().getOrder(order_id);
			Book book = getBookDAO().getBook(order.getBookname());
			Integer temp_stock_num = order.getNum() + book.getStock_num();
			if (num > temp_stock_num || num < 0) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
						"buybook.message.failed"));
			} else {
				Integer new_stock_num = temp_stock_num - num;
				book.setStock_num(new_stock_num);
				getBookDAO().insertBook(book);
				order.setNum(num);
				getOrderDAO().insertOrder(order);

				// get all orders made by the user
				List<Order> orders = getOrderDAO().getOrders(username);
				session.setAttribute(Constants.ALL_ORDER_KEY, orders);
				session.setAttribute("username", username);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			forward = mapping.findForward(Constants.SUCCESS_KEY);
		} else {
			forward = mapping.findForward(Constants.GOBACK_KEY);
		}
		
		return forward;
	}
}
