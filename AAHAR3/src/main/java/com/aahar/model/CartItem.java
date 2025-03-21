package com.aahar.model;

public class CartItem {
	private int itemId;
	private String itemName;
	private String itemDescription;
	private double itemPrice;
	private int quantity;
	private int restaurantId;
	private String imgPath;
	
	public CartItem() {
		// TODO Auto-generated constructor stub
	}

	public CartItem(int itemId, String itemName, String itemDescription, double itemPrice, int quantity, int restaurantId, String imgPath ) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
		this.quantity = quantity;
		this.restaurantId=restaurantId;
		this.imgPath=imgPath;
	}
   
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
}
