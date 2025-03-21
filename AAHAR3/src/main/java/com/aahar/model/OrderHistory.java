package com.aahar.model;

import java.sql.Time;

public class OrderHistory {
	private String day;
	private String month;
	private int year;
	private Time time;
	private String ordered_items;
	private String items_price;
	private String TAX;
	private String Delivery;
	private String total_amount;
	private String status;
	
	public OrderHistory() {
		// TODO Auto-generated constructor stub
	}

	public OrderHistory(String day, String month, int year, Time time, String ordered_items, String items_price,
			String tAX, String delivery, String total_amount, String status) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
		this.time = time;
		this.ordered_items = ordered_items;
		this.items_price = items_price;
		TAX = tAX;
		Delivery = delivery;
		this.total_amount = total_amount;
		this.status = status;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getOrdered_items() {
		return ordered_items;
	}

	public void setOrdered_items(String ordered_items) {
		this.ordered_items = ordered_items;
	}

	public String getItems_price() {
		return items_price;
	}

	public void setItems_price(String items_price) {
		this.items_price = items_price;
	}

	public String getTAX() {
		return TAX;
	}

	public void setTAX(String tAX) {
		TAX = tAX;
	}

	public String getDelivery() {
		return Delivery;
	}

	public void setDelivery(String delivery) {
		Delivery = delivery;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
