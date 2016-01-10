package com.demo.hibernate.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.demo.hibernate.beans.Order;

public class OrderDAO extends HibernateDaoSupport implements IOrderDAO {

	public void insertOrder(Order order) {
		getHibernateTemplate().saveOrUpdate(order);
	}

	public Order getOrder(Integer id) {
		return (Order) getHibernateTemplate().get(Order.class, new Integer(id));
	}

	@SuppressWarnings("unchecked")
	public List<Order> getOrders(String username) {
		Object[] value = { username };
		String hql = "from Order where username = ?";
		return getHibernateTemplate().find(hql, value);
	}

	public void deleteOrder(Integer id) {
		Object p = getHibernateTemplate().load(Order.class, new Integer(id));
		getHibernateTemplate().delete(p);
	}
}
