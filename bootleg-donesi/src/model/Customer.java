package model;

import java.util.ArrayList;
import java.util.Date;

public class Customer extends User{
	
	protected ArrayList<Integer> orders;
	protected ShoppingCart shoppingCart;
	protected int points;
	public Customer(int entityID, String username, String password, String firtName, String lastName, Gender gender,
			Date dateOfBirth, Role role, ArrayList<Integer> orders, ShoppingCart shoppingCart, int points) {
		super(entityID, username, password, firtName, lastName, gender, dateOfBirth, role);
		this.orders = orders;
		this.shoppingCart = shoppingCart;
		this.points = points;
	}
	public ArrayList<Integer> getOrders() {
		return orders;
	}
	public void setOrders(ArrayList<Integer> orders) {
		this.orders = orders;
	}
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	
	
	
}
