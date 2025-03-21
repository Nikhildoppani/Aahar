package com.aahar.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aahar.dao.MenuDAO;
import com.aahar.model.Menu;
import com.aahar.utility.DBConnection;

public class MenuDAOImplementation implements MenuDAO{
	private static final String INSERT_MENU_QUERY="INSERT INTO `menu`(`item_Name`,`description`,`price`,`is_Available`,`img_path`) values(?,?,?,?,?)";
	private static final String GET_MENU_QUERY="SELECT * FROM `menu` WHERE `menu_id`=?";
	private static final String UPDATE_MENU_QUERY="UPDATE `menu` SET `item_Name`=?,`description`=?,`price`=?,`is_Available`=?,`img_path`=?";
	private static final String DELETE_MENU_QUERY="DELETE FROM `menu` WHERE `menu_id`=?";
	private static final String GET_ALL_MENUS="SELECT * FROM `menu`";
	private static final String GET_ALL_MENUS_IN_RESTAURANT="SELECT * FROM `menu` WHERE `restaurant_id`=?";
	
	@Override
	public void addMenu(Menu menu) {
		
		try(Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(INSERT_MENU_QUERY);) {
			prepareStatement.setString(1, menu.getItemname());
			prepareStatement.setString(2, menu.getDiscription());
			prepareStatement.setDouble(3, menu.getPrice());
			prepareStatement.setString(4, menu.getIsAvailable());
			prepareStatement.setString(5, menu.getImgPath());
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Menu getMenu(int menuId) {
		
		
		Menu menu=null;
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement prepareStatement=connection.prepareStatement(GET_MENU_QUERY);) {
			
			prepareStatement.setInt(1, menuId);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {  // Ensure there is a record
	            int restaurantId = resultSet.getInt("restaurant_id");
	            String itemname = resultSet.getString("item_Name");
	            String description = resultSet.getString("description");
	            Double price = resultSet.getDouble("price");
	            Double ratings = resultSet.getDouble("ratings");
	            String isAvailable = resultSet.getString("is_Available");
	            String imgPath = resultSet.getString("img_path");

	            menu = new Menu(menuId, restaurantId, itemname, description, price, ratings, isAvailable, imgPath);
	        }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return menu;
	}

	@Override
	public void updateMenu(Menu menu) {
		
		
		try(Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(UPDATE_MENU_QUERY);) {
			prepareStatement.setString(1, menu.getItemname());
			prepareStatement.setString(2, menu.getDiscription());
			prepareStatement.setDouble(3, menu.getPrice());
			prepareStatement.setString(4, menu.getIsAvailable());
			prepareStatement.setString(5, menu.getImgPath());
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteMenu(int menuId) {
		
		
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(DELETE_MENU_QUERY);) {
			prepareStatement.setInt(1, menuId);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Menu> getAllMenus() {
		
		
		ArrayList<Menu> menuList=new ArrayList<Menu>();
		try(Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery(GET_ALL_MENUS);
			
			while(resultSet.next()) {
				int menuId=resultSet.getInt("menu_id");
				int restaurantId=resultSet.getInt("restaurant_id");
				String itemname=resultSet.getString("item_Name");
				String discription=resultSet.getString("description");
				Double price=resultSet.getDouble("price");
				Double ratings=resultSet.getDouble("ratings");
				String isAvailable=resultSet.getString("is_Available");
				String imgPath=resultSet.getString("img_path");
				
				Menu menu=new Menu(menuId, restaurantId, itemname, discription, price, ratings, isAvailable, imgPath);
				menuList.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}
	
	@Override
	public List<Menu> getAllMenus(int restaurantId) {
		
		
		ArrayList<Menu> menuRestaurantList=new ArrayList<Menu>();
		try(Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(GET_ALL_MENUS_IN_RESTAURANT);) {
			prepareStatement.setInt(1, restaurantId);
			ResultSet resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				int menuId=resultSet.getInt("menu_id");
				int restaurantId1=resultSet.getInt("restaurant_id");
				String itemname=resultSet.getString("item_Name");
				String discription=resultSet.getString("description");
				Double price=resultSet.getDouble("price");
				Double ratings=resultSet.getDouble("ratings");
				String isAvailable=resultSet.getString("is_Available");
				String imgPath=resultSet.getString("img_path");
				
				Menu menu=new Menu(menuId, restaurantId1, itemname, discription, price, ratings, isAvailable, imgPath);
				menuRestaurantList.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuRestaurantList;
	}

}
