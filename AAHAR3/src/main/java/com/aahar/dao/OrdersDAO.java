package com.aahar.dao;

import java.util.List;

import com.aahar.model.OrderHistory;
import com.aahar.model.Orders;



public interface OrdersDAO {
	void addOrder(Orders order);
	Orders getMenu(int orderId);
	void updateMenu(Orders menu);
	void deleteMenu(int orderId);
	List<Orders> getAllOrders();
	List<OrderHistory> getAllOrders(int userId);
}
