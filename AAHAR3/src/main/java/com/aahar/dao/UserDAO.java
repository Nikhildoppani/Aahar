package com.aahar.dao;

import java.util.List;

import com.aahar.model.User;

public interface UserDAO {
	void addUser(User user);
	User getUser(int userId);
	void updateUser(User user);
	void daleteUser(int userId);
	List<User> getAllUsers();
	List<User> getUserInfo(int userId);
}
