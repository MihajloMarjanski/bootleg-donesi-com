package model;

import java.util.HashMap;

public class ShoppingCart {
	protected Integer customer;
	protected double price;
	protected HashMap<Integer,Integer> menuItems;
	
	public ShoppingCart(Integer customer) {
		super();
		this.customer = customer;
		this.price = 0;
		this.menuItems = new HashMap<Integer, Integer>();
	}
	public Integer getCustomer() {
		return customer;
	}
	public void setCustomer(Integer customer) {
		this.customer = customer;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public HashMap<Integer, Integer> getMenuItems() {
		return menuItems;
	}
	public void setMenuItems(HashMap<Integer, Integer> menuItems) {
		this.menuItems = menuItems;
	}
	
	
	
}
