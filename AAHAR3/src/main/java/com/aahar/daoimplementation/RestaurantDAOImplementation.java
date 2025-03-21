package com.aahar.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aahar.dao.RestaurantDAO;
import com.aahar.model.Restaurant;

import com.aahar.utility.DBConnection;

public class RestaurantDAOImplementation implements RestaurantDAO {
	
	private static final String INSERT_RESTAURANT_QUERY="INSERT INTO `restaurant`(`name`,`address`,`phone`,`cuisine_type`,`is_active`,`eta`,`img_path`) values(?,?,?,?,?,?,?)";
	private static final String GET_RESTAURANT_QUERY="SELECT * FROM `restaurant` WHERE `restaurant_id`=?";
	private static final String UPDATE_RESTAURANT_QUERY="UPDATE `restaurant` SET `name`=? `address`=? `phone`=? `cuisine_type`=? `is_active`=? `eta`=? `img_path`=?";
	private static final String DELETE_RESTAURANT_QUERY="DELETE FROM `restaurant` WHERE `restaurant_id`=?";
	private static final String GET_ALL_RESTAURANTS="SELECT * FROM `restaurant`";
	 
	@Override
	public void addRestaurant(Restaurant restaurant) {
		
		
		try(Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(INSERT_RESTAURANT_QUERY);) {
			 
			prepareStatement.setString(1,restaurant.getName());
			prepareStatement.setString(2,restaurant.getAddress());
			prepareStatement.setString(3,restaurant.getPhone());
			prepareStatement.setString(4,restaurant.getCuisineType());
			prepareStatement.setString(5,restaurant.getIsActive());
			prepareStatement.setDouble(6, restaurant.getEta());
			prepareStatement.setString(7,restaurant.getImgPath()); 
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
		
		
		Restaurant restaurant=null;
		try(Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(GET_RESTAURANT_QUERY);) {
			prepareStatement.setInt(1, restaurantId);
			
			ResultSet resultSet = prepareStatement.executeQuery();
			String name=resultSet.getString("name");
			String address=resultSet.getString("address");
			String phone=resultSet.getString("phone");
			Double rating=resultSet.getDouble("rating");
			String cuisine_type=resultSet.getString("cuisine_type");
			String is_active=resultSet.getString("is_active");
			int eta=resultSet.getInt("eta");
			String img_path=resultSet.getString("img_path");
			
			 restaurant=new Restaurant(restaurantId,name, address, phone, rating, cuisine_type, is_active, eta, img_path, eta);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurant;
		
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		
		
		try(Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(UPDATE_RESTAURANT_QUERY)) {
			prepareStatement.setString(1, restaurant.getName());
			prepareStatement.setString(2, restaurant.getAddress());
			prepareStatement.setString(3, restaurant.getPhone());
			prepareStatement.setString(4, restaurant.getCuisineType());
			prepareStatement.setString(5, restaurant.getIsActive());
			prepareStatement.setDouble(6, restaurant.getEta());
			prepareStatement.setString(7, restaurant.getImgPath());
			
			prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRestaurant(int restaurantId) {
		
		
		try(Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(DELETE_RESTAURANT_QUERY)) {
			prepareStatement.setInt(1, restaurantId);
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		
		ArrayList<Restaurant> restaurantList=new ArrayList<Restaurant>();
		try(Connection connection=DBConnection.getConnection();
				Statement statement= connection.createStatement()) {
			ResultSet resultSet=statement.executeQuery(GET_ALL_RESTAURANTS);
			
			while(resultSet.next()) {
				int restaurantId=resultSet.getInt("restaurant_id");
				String name=resultSet.getString("name");
				String address=resultSet.getString("address");
				String phone=resultSet.getString("phone");
				Double rating=resultSet.getDouble("rating");
				String cuisine_type=resultSet.getString("cuisine_type");
				String is_active=resultSet.getString("is_active");
				int eta=resultSet.getInt("eta");
				String img_path=resultSet.getString("img_path");
				int userId=resultSet.getInt("user_id");
				
				Restaurant restaurant=new Restaurant(restaurantId, name, address, phone, rating, cuisine_type, is_active, eta, img_path, userId);
				restaurantList.add(restaurant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurantList;
	}
	
	
}
