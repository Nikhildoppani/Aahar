package com.aahar.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.aahar.dao.OrdersDAO;
import com.aahar.model.OrderHistory;
import com.aahar.model.Orders;
import com.aahar.model.User;
import com.aahar.utility.DBConnection;

public class OrdersDAOImplementation implements OrdersDAO {
	private static final String GET_ALL_ORDERINFO="SELECT LPAD(DAY(o.order_date), 2, '0') AS day, DATE_FORMAT(o.order_date, '%b') AS month, YEAR(o.order_date) AS year, TIME(o.order_date) AS time, GROUP_CONCAT(CONCAT(m.item_Name, ' x ', oi.quantity, ' ', FORMAT(m.price, 0)) ORDER BY m.item_Name SEPARATOR ', ') AS ordered_items, FORMAT(SUM(m.price * oi.quantity), 2) AS `items price`, FORMAT(SUM(m.price * oi.quantity) * 0.05, 2) AS TAX, '40' AS Delivery, FORMAT(SUM(m.price * oi.quantity) + (SUM(m.price * oi.quantity) * 0.05) + 40, 2) AS total_amount, o.status FROM orders o JOIN order_item oi ON o.order_id = oi.order_id JOIN menu m ON oi.menu_id = m.menu_id WHERE o.user_id = ? GROUP BY o.order_date, o.status ORDER BY o.order_date;\r\n"
			+ "";
	@Override
	public void addOrder(Orders order) {
		String INSERT_ORDERS_QUERY="INSERT INTO `ORDERS`(``)";
		
	}

	@Override
	public Orders getMenu(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateMenu(Orders menu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMenu(int orderId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Orders> getAllOrders() {
		
		return null;
	}

	@Override
	public List<OrderHistory> getAllOrders(int userId) {
		ArrayList<OrderHistory> orderInfoList=new ArrayList<OrderHistory>();
		try(Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(GET_ALL_ORDERINFO);) {
			prepareStatement.setInt(1, userId);
			ResultSet resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				//int userId=resultSet.getInt("user_id");
				String day=resultSet.getString("day");
				String month=resultSet.getString("month");
				int year=resultSet.getInt("year");
				Time time=resultSet.getTime("time");
				String ordered_items=resultSet.getString("ordered_items");
				String items_price=resultSet.getString("items price");
				String TAX=resultSet.getString("TAX");
				String Delivery=resultSet.getString("Delivery");
				String total_amount=resultSet.getString("total_amount");
				String status=resultSet.getString("status");
				
				OrderHistory orderHistory=new OrderHistory(day, month, year, time, ordered_items, items_price, TAX, Delivery, total_amount, status);
				orderInfoList.add(orderHistory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderInfoList;
	}
	
}