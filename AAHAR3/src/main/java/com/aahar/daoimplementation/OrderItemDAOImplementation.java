package com.aahar.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aahar.dao.OrderItemDAO;
import com.aahar.model.OrderItem;
import com.aahar.utility.DBConnection;

public class OrderItemDAOImplementation implements OrderItemDAO {

	@Override
	public void addOrderItem(OrderItem orderItem) {
		Connection connection=DBConnection.getConnection();
		
		String INSERT_ORDERITEM_QUERY="INSERT INTO `order_item` (`order_id`,`menu_id`,`quantity`,`total_price`) VALUES(?,?,?,?)";
		
		try(PreparedStatement prepareStatement= connection.prepareStatement(INSERT_ORDERITEM_QUERY);) {
			
			prepareStatement.setInt(1, orderItem.getOrderId());
			prepareStatement.setInt(2, orderItem.getMenuId());
			prepareStatement.setInt(3, orderItem.getQuantity());
			prepareStatement.setDouble(4, orderItem.getTotalPrice());
			
			prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public OrderItem getOrderItem(int orderItemId) {
		String GET_ORDERITEM_QUERY="SELECT * FROM `order_item` WHERE `order_item_id`=?";
		
		OrderItem orderItem=null;
		try(Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(GET_ORDERITEM_QUERY);) {
			
			prepareStatement.setInt(1, orderItemId);
			ResultSet resultSet = prepareStatement.executeQuery();
			
			int order_id=resultSet.getInt("order_id"); 
			int menu_id=resultSet.getInt("menu_id");
			int quantity=resultSet.getInt("quantity");
			double total_price=resultSet.getDouble("total_price");
			
			 orderItem=new OrderItem(orderItemId, order_id, menu_id, quantity, total_price);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderItem;
	}

	@Override
	public void updateOrderItem(OrderItem orderItem) {
		
		String UPDATE_ORDERITEM_QUERY="UPDATE `order_item` SET `order_id`=?,`menu_id`=?, `quantity`=?, `total_price`=?";
		try(Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(UPDATE_ORDERITEM_QUERY);) {
			prepareStatement.setInt(1, orderItem.getOrderId());
			prepareStatement.setInt(2, orderItem.getMenuId());
			prepareStatement.setInt(3, orderItem.getQuantity());
			prepareStatement.setDouble(4, orderItem.getTotalPrice());
			
			prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteOrderItem(int orderItemId) {
		
		String DELETE_ORDERITEM_QUERY="DELETE FROM `order_item` WHERE `order_item_id`=?";
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(DELETE_ORDERITEM_QUERY);) {
			prepareStatement.setInt(1, orderItemId);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<OrderItem> getAllOrderItemId() {
		
		String GET_ALL_ORDERITEMLIST_QUERY="SELECT * FROM `order_item`";
		OrderItem orderItem=null;
		
		ArrayList<OrderItem> orderItemList=new ArrayList<OrderItem>();
		try(Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery(GET_ALL_ORDERITEMLIST_QUERY);
			while(resultSet.next()) {
				int order_item_id=resultSet.getInt("order_item_id");
				int order_id=resultSet.getInt("order_id");
				int menu_id=resultSet.getInt("menu_id");
				int quantity=resultSet.getInt("quantity");
				double total_price=resultSet.getDouble("total_price");
				
				 orderItem=new OrderItem(order_item_id, order_id, menu_id, quantity, total_price);
				 orderItemList.add(orderItem);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderItemList;
	}

}
