package com.aahar.daoimplementation;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.aahar.dao.UserDAO;
import com.aahar.model.User;
import com.aahar.utility.DBConnection;


public class UserDAOImplementation implements UserDAO{
	private static final String INSERT_USER_QUERY="INSERT INTO `USER` (`name`,`user_name`,`password`,`email`,`phone`,`address`,`role`) VALUES(?,?,?,?,?,?,?)";
	private static final String UPDATE_USER_QUERY="UPDATE `user` SET `name`=?,`password`=?,`phone`=?,`address`=?,`role`=?";
	private static final String GET_USER_QUERY="SELECT * FROM `user` WHERE `user_id` =?";
	private static final String DELETE_USER_QUERY="DELETE FROM `user` WHERE `user_id` = ?";
	private static final String GET_ALL_USERS_QUERY="SELECT * FROM `user`";
	private static final String GET_ALL_USERINFO="SELECT * from `user` where `user_id`=?";
	
	@Override
	public void addUser(User user) {
		
		Connection connection=DBConnection.getConnection();
		
		
		
		try(PreparedStatement prepareStatement= connection.prepareStatement(INSERT_USER_QUERY);) {
			
			prepareStatement.setString(1,user.getName());
			prepareStatement.setString(2,user.getUsername());
			prepareStatement.setString(3,user.getPassword());
			prepareStatement.setString(4,user.getEmail());
			prepareStatement.setString(5,user.getPhone());
			prepareStatement.setString(6,user.getAddress());
			prepareStatement.setString(7,user.getRole());
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(int userId) {
		User user=null;
		
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement prepareStatement=connection.prepareStatement(GET_USER_QUERY);) {
			
			prepareStatement.setInt(1, userId);
			ResultSet resultSet = prepareStatement.executeQuery();
			 if (resultSet.next()) {
			String name=resultSet.getString("name");
			String username=resultSet.getString("user_name");
			String password=resultSet.getString("password");
			String email=resultSet.getString("email");
			String phone=resultSet.getString("phone");
			String address=resultSet.getString("address");
			String role=resultSet.getString("role");
			
			user=new User(userId, name, username, password, email, phone, address, role, null, null);
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void updateUser(User user) {
		
		
		try(Connection connection=DBConnection.getConnection();
			PreparedStatement prepareStatement=connection.prepareStatement(UPDATE_USER_QUERY);) {
			
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getPassword());
			prepareStatement.setString(3, user.getPhone());
			prepareStatement.setString(4, user.getAddress());
			prepareStatement.setString(5, user.getRole());
			
			prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void daleteUser(int userId) {
		
		
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(DELETE_USER_QUERY);) {
			prepareStatement.setInt(1, userId);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAllUsers() {
		
		
		
		ArrayList<User> usersList=new ArrayList<User>();
		try(Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_QUERY);
			
			
			while(resultSet.next()) {
				int userId=resultSet.getInt("user_id");
				String name=resultSet.getString("name");
				String username=resultSet.getString("user_name");
				String password=resultSet.getString("password");
				String email=resultSet.getString("email");
				String phone=resultSet.getString("phone");
				String address=resultSet.getString("address");
				String role=resultSet.getString("role");
				
				User user=new User(userId, name, username, password, email, phone, address, role, null, null);
				usersList.add(user);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usersList;
	}
	
	@Override
	public List<User> getUserInfo(int userId) {
		ArrayList<User> userInfoList=new ArrayList<User>();
		try(Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(GET_ALL_USERINFO);) {
			prepareStatement.setInt(1, userId);
			ResultSet resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				//int userId=resultSet.getInt("user_id");
				String name=resultSet.getString("name");
				String username=resultSet.getString("user_name");
				String password=resultSet.getString("password");
				String email=resultSet.getString("email");
				String phone=resultSet.getString("phone");
				String address=resultSet.getString("address");
				String role=resultSet.getString("role");
				
				User user=new User(userId, name, username, password, email, phone, address, role, null, null);
				userInfoList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		return userInfoList;
	}

}
