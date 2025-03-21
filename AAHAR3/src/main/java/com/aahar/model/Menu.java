package com.aahar.model;

public class Menu {
	private int menuId;
	private int restaurantId;
	private String itemname;
	private String discription;
	private double price;
	private double ratings;
	private String isAvailable;
	private String imgPath;
	
	public Menu() {
		// TODO Auto-generated constructor stub
	}

	public Menu(int menuId, int restaurantId, String itemname, String discription, double price, double ratings,
			String isAvailable, String imgPath) {
		super();
		this.menuId = menuId;
		this.restaurantId = restaurantId;
		this.itemname = itemname;
		this.discription = discription;
		this.price = price;
		this.ratings = ratings;
		this.isAvailable = isAvailable;
		this.imgPath = imgPath;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getRatings() {
		return ratings;
	}

	public void setRatings(double ratings) {
		this.ratings = ratings;
	}

	public String getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	@Override
	public String toString() {
		return itemname;
	}
}
