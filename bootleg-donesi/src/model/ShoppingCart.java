package model;

import java.util.ArrayList;

public class ShoppingCart {
	protected Integer customer;
	protected double price;
	protected ArrayList<MenuItem> menuItems;
	
	public ShoppingCart(Integer customer) {
		super();
		this.customer = customer;
		this.price = 0;
		this.menuItems = new ArrayList<MenuItem>();
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
	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}
	public void setMenuItems(ArrayList<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}
	
	
	
}
