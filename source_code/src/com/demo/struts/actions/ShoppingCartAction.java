package com.demo.struts.actions;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.demo.hibernate.beans.Book;
import com.demo.hibernate.beans.Order;
import com.demo.hibernate.beans.SalesRecord;
import com.demo.hibernate.dao.BookDAO;
import com.demo.hibernate.dao.OrderDAO;
import com.demo.hibernate.dao.SalesRecordDAO;
import com.demo.struts.util.Constants;
import com.sun.istack.internal.logging.Logger;

public class ShoppingCartAction extends DispatchAction {

	protected OrderDAO orderDAO;
	protected BookDAO bookDAO;
	protected SalesRecordDAO recordDAO;

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

	public SalesRecordDAO getRecordDAO() {
		return recordDAO;
	}

	public void setRecordDAO(SalesRecordDAO recordDAO) {
		this.recordDAO = recordDAO;
	}

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		log.info("username:" + username);

		// get all orders made by the user
		List<Order> orders = getOrderDAO().getOrders(username);
		session.setAttribute(Constants.ALL_ORDER_KEY, orders);

		ActionForward forward = mapping.findForward(Constants.SUCCESS_KEY);
		return (forward);
	}

	public ActionForward remove_order(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");

		try {

			Integer order_id = Integer.parseInt(request
					.getParameter("order_id"));
			Order order = getOrderDAO().getOrder(order_id);
			String bookname = order.getBookname();
			Book book = getBookDAO().getBook(bookname);
			book.setStock_num(book.getStock_num() + order.getNum());
			getBookDAO().insertBook(book);
			getOrderDAO().deleteOrder(order_id);

			// get all orders made by the user
			List<Order> orders = getOrderDAO().getOrders(username);
			session.setAttribute(Constants.ALL_ORDER_KEY, orders);
			session.setAttribute(Constants.USERNAME_KEY, username);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ActionForward forward = mapping.findForward(Constants.SUCCESS_KEY);
		return (forward);
	}

	public ActionForward pay(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");

		try {
			Integer order_id = Integer.parseInt(request
					.getParameter("order_id"));
			Order order = getOrderDAO().getOrder(order_id);
			String bookname = order.getBookname();
			Integer num = order.getNum();

			// make a sales record and save it
			SalesRecord record = new SalesRecord();
			record.setBookname(bookname);
			record.setUsername(username);
			record.setNum(num);
			record.setDate(new Timestamp(System.currentTimeMillis()));
			getRecordDAO().insertRecord(record);

			// delete order
			getOrderDAO().deleteOrder(order_id);

			// get all orders made by the user
			List<Order> orders = getOrderDAO().getOrders(username);
			session.setAttribute(Constants.ALL_ORDER_KEY, orders);
			session.setAttribute(Constants.USERNAME_KEY, username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ActionForward forward = mapping.findForward(Constants.SUCCESS_KEY);
		return (forward);
	}

	public ActionForward pay_all(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return null;
	}
}
