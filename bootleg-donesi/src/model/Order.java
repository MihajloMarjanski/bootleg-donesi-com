package model;

import java.util.ArrayList;
import java.util.Date;

public class Order extends Entity{
	
	
	protected String orderID;
	protected ArrayList<Integer> menuItems;
	protected Integer restaurant;
	protected Date timeOfOrder;
	protected Double price;
	protected Integer customer;
	protected OrderStatus orderStatus;
	protected Integer courier;
	protected String restaurantName;
	protected RestaurantType restaurantType;
	
	public Order() {
		
	}
	
	
	public Order(int entityID, String orderID, ArrayList<Integer> menuItems, Integer restaurant, Date timeOfOrder,
			Double price, Integer customer, OrderStatus orderStatus, Integer courier, String restaurantName, RestaurantType restaurantType) {
		super(entityID);
		this.orderID = orderID;
		this.menuItems = menuItems;
		this.restaurant = restaurant;
		this.timeOfOrder = timeOfOrder;
		this.price = price;
		this.customer = customer;
		this.orderStatus = orderStatus;
		this.courier = courier;
		this.restaurantName = restaurantName;
		this.restaurantType = restaurantType;
	}
	
	
	
	
	public String getRestaurantName() {
		return restaurantName;
	}


	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}


	public RestaurantType getRestaurantType() {
		return restaurantType;
	}


	public void setRestaurantType(RestaurantType restaurantType) {
		this.restaurantType = restaurantType;
	}


	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public ArrayList<Integer> getMenuItems() {
		return menuItems;
	}
	public void setMenuItems(ArrayList<Integer> menuItems) {
		this.menuItems = menuItems;
	}
	public Integer getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Integer restaurant) {
		this.restaurant = restaurant;
	}
	public Date getTimeOfOrder() {
		return timeOfOrder;
	}
	public void setTimeOfOrder(Date timeOfOrder) {
		this.timeOfOrder = timeOfOrder;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getCustomer() {
		return customer;
	}
	public void setCustomer(Integer customer) {
		this.customer = customer;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getCourier() {
		return courier;
	}
	public void setCourier(Integer courier) {
		this.courier = courier;
	}
	
	
	
	
	
	
}
