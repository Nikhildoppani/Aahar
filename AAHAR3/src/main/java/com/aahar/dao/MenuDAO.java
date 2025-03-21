package com.aahar.dao;

import java.util.List;

import com.aahar.model.Menu;

public interface MenuDAO {
	void addMenu(Menu menu);
	Menu getMenu(int menuId);
	void updateMenu(Menu menu);
	void deleteMenu(int MenuId);
	List<Menu> getAllMenus();
	List<Menu> getAllMenus(int restaurantId);
}
