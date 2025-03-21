package com.aahar.dao;

import java.util.List;

import com.aahar.model.OrderItem;



public interface OrderItemDAO {
	void addOrderItem(OrderItem orderItem);
	OrderItem getOrderItem(int orderItemId);
	void updateOrderItem(OrderItem orderItem);
	void deleteOrderItem(int orderItemId);
	List<OrderItem> getAllOrderItemId();
}
