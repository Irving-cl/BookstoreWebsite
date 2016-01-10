package com.demo.hibernate.dao;

import java.util.List;

import com.demo.hibernate.beans.Order;

public interface IOrderDAO {

	public void insertOrder(Order order);

	public Order getOrder(Integer id);

	public List<Order> getOrders(String username);

	public void deleteOrder(Integer id);

}
