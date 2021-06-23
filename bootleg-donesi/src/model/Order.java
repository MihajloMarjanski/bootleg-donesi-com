package model;

import java.util.ArrayList;
import java.util.Date;

public class Order {
	
	
	protected String orderID;
	protected ArrayList<Integer> menuItems;
	protected Integer restaurant;
	protected Date timeOfOrder;
	protected Double price;
	protected Integer customer;
	protected OrderStatus orderStatus;
}
