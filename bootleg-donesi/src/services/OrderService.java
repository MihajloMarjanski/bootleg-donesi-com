package services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;

import model.Adress;
import model.Location;
import model.Order;
import model.OrderStatus;
import model.Restaurant;
import model.RestaurantStatus;
import model.RestaurantType;

public class OrderService {

	public static ArrayList<Order> orderList = new ArrayList<Order>();
	
	private static void save() {
		
	}
	
	public static void load() {
		orderList.add(new Order(1, "OR1", new ArrayList<Integer>(), 1, new Date(), 220.0, 1,OrderStatus.TRANSPORT, 1,"FLIPERANA",RestaurantType.ITALIAN));
		orderList.add(new Order(2, "OR2", new ArrayList<Integer>(), 1, new Date(), 777.0, 1,OrderStatus.WAITING, 0,"FLIPERANA",RestaurantType.ITALIAN));
		orderList.add(new Order(3, "OR3", new ArrayList<Integer>(), 1, new Date(), 666.0, 1,OrderStatus.INPREP, 0,"FLIPERANA",RestaurantType.ITALIAN));
		orderList.add(new Order(4, "OR4", new ArrayList<Integer>(), 1, new Date(), 666.0, 1,OrderStatus.DELIVERED, 1,"FLIPERANA",RestaurantType.ITALIAN));
		
	}
	
	private static Integer generateID() 
	{
		int id = 0;
		for (Order order : orderList) {
			if (order.getEntityID() > id) {
				id = order.getEntityID();
			}
		}
			
		return id + 1;
	}
	
	public static ArrayList<Order> getAll() {
		ArrayList<Order> orders = new ArrayList<Order>();
		for (Order order: orderList) {
			if (!order.isDeleted()) {
				orders.add(order);
			}
		}	
		return orders;
	}

	public static ArrayList<Order> getForCustomer(int entityID) {
		ArrayList<Order> orders = new ArrayList<Order>();
		for (Order order: getAll()) {
			if (order.getCustomer() == entityID && order.getOrderStatus() != OrderStatus.DELIVERED && order.getOrderStatus() != OrderStatus.CANCELED) {
				orders.add(order);
			}
		}	
		return orders;
	}

	public static ArrayList<Order> getForCourier(int entityID) {
		ArrayList<Order> orders = new ArrayList<Order>();
		for (Order order: getAll()) {
			if (order.getCourier() == entityID && order.getOrderStatus() != OrderStatus.DELIVERED && order.getOrderStatus() != OrderStatus.CANCELED) {
				orders.add(order);
			}
			if(order.getOrderStatus() == OrderStatus.WAITING) {
				orders.add(order);
			}
		}	
		return orders;
	}
	
	public static ArrayList<Order> getForRestaurant(int entityID) {
		ArrayList<Order> orders = new ArrayList<Order>();
		for (Order order: getAll()) {
			if (order.getRestaurant() == entityID) {
				orders.add(order);
			}
		}	
		return orders;
	}

	public ArrayList<Order> getForCustomer(int entityID, String ordreStatus, String restaurantType) {
		RestaurantType type = null;
		OrderStatus status = null;
		
		if(restaurantType.equals("ITALIAN")) {
			type = RestaurantType.ITALIAN;
		}
		else if(restaurantType.equals("CHINESE")) {
			type = RestaurantType.CHINESE;
		}
		else if(restaurantType.equals("GREEK")) {
			type = RestaurantType.GREEK;
		}
		else if(restaurantType.equals("GRILL")) {
			type = RestaurantType.GRILL;
		}
		else if(restaurantType.equals("MEXICAN")) {
			type = RestaurantType.MEXICAN;
		}
		
		if(ordreStatus.equals("CANCELED")) {
			status = OrderStatus.CANCELED;
		}
		else if(ordreStatus.equals("DELIVERED")) {
			status = OrderStatus.DELIVERED;
		}
		else if(ordreStatus.equals("INPREP")) {
			status = OrderStatus.INPREP;
		}
		else if(ordreStatus.equals("PROCESSING")) {
			status = OrderStatus.PROCESSING;
		}
		else if(ordreStatus.equals("TRANSPORT")) {
			status = OrderStatus.TRANSPORT;
		}
		else if(ordreStatus.equals("WAITING")) {
			status = OrderStatus.WAITING;
		}
		
		
		ArrayList<Order> orders = new ArrayList<Order>();
		for (Order order: getAll()) {
			if (order.getCustomer() == entityID && order.getOrderStatus() != OrderStatus.DELIVERED && order.getOrderStatus() != OrderStatus.CANCELED) {
				orders.add(order);
			}
		}
		
		ArrayList<Order> found = new ArrayList<Order>();
		for(Order order : orders){
			if(status != null) {
				if(order.getOrderStatus() != status && !found.contains(order)){
			        found.add(order);
			    }
			}
			
			if(type != null) {
			    if(order.getRestaurantType() != type && !found.contains(order)){
			        found.add(order);
			    }
			}


		}
		
		orders.removeAll(found);
		
		
		return orders;
	}

	public static ArrayList<Order> getForCourier(int entityID, String ordreStatus, String restaurantType) {
		RestaurantType type = null;
		OrderStatus status = null;
		
		if(restaurantType.equals("ITALIAN")) {
			type = RestaurantType.ITALIAN;
		}
		else if(restaurantType.equals("CHINESE")) {
			type = RestaurantType.CHINESE;
		}
		else if(restaurantType.equals("GREEK")) {
			type = RestaurantType.GREEK;
		}
		else if(restaurantType.equals("GRILL")) {
			type = RestaurantType.GRILL;
		}
		else if(restaurantType.equals("MEXICAN")) {
			type = RestaurantType.MEXICAN;
		}
		
		if(ordreStatus.equals("CANCELED")) {
			status = OrderStatus.CANCELED;
		}
		else if(ordreStatus.equals("DELIVERED")) {
			status = OrderStatus.DELIVERED;
		}
		else if(ordreStatus.equals("INPREP")) {
			status = OrderStatus.INPREP;
		}
		else if(ordreStatus.equals("PROCESSING")) {
			status = OrderStatus.PROCESSING;
		}
		else if(ordreStatus.equals("TRANSPORT")) {
			status = OrderStatus.TRANSPORT;
		}
		else if(ordreStatus.equals("WAITING")) {
			status = OrderStatus.WAITING;
		}
		
		
		ArrayList<Order> orders = new ArrayList<Order>();
		for (Order order: getAll()) {
			if (order.getCourier() == entityID && order.getOrderStatus() != OrderStatus.DELIVERED && order.getOrderStatus() != OrderStatus.CANCELED) {
				orders.add(order);
			}
			if(order.getOrderStatus() == OrderStatus.WAITING) {
				orders.add(order);
			}
		}
		
		
		ArrayList<Order> found = new ArrayList<Order>();
		for(Order order : orders){
			if(status != null) {
				if(order.getOrderStatus() != status && !found.contains(order)){
			        found.add(order);
			    }
			}
			
			if(type != null) {
			    if(order.getRestaurantType() != type && !found.contains(order)){
			        found.add(order);
			    }
			}
		}
		
		orders.removeAll(found);
		
		
		return orders;
	}

	public ArrayList<Order> getForRestaurant(int entityID, String ordreStatus, String restaurantType) {
		ArrayList<Order> orders = new ArrayList<Order>();
		for (Order order: getAll()) {
			if (order.getRestaurant() == entityID) {
				orders.add(order);
			}
		}
		
		RestaurantType type = null;
		OrderStatus status = null;
		
		if(restaurantType.equals("ITALIAN")) {
			type = RestaurantType.ITALIAN;
		}
		else if(restaurantType.equals("CHINESE")) {
			type = RestaurantType.CHINESE;
		}
		else if(restaurantType.equals("GREEK")) {
			type = RestaurantType.GREEK;
		}
		else if(restaurantType.equals("GRILL")) {
			type = RestaurantType.GRILL;
		}
		else if(restaurantType.equals("MEXICAN")) {
			type = RestaurantType.MEXICAN;
		}
		
		if(ordreStatus.equals("CANCELED")) {
			status = OrderStatus.CANCELED;
		}
		else if(ordreStatus.equals("DELIVERED")) {
			status = OrderStatus.DELIVERED;
		}
		else if(ordreStatus.equals("INPREP")) {
			status = OrderStatus.INPREP;
		}
		else if(ordreStatus.equals("PROCESSING")) {
			status = OrderStatus.PROCESSING;
		}
		else if(ordreStatus.equals("TRANSPORT")) {
			status = OrderStatus.TRANSPORT;
		}
		else if(ordreStatus.equals("WAITING")) {
			status = OrderStatus.WAITING;
		}
		
		ArrayList<Order> found = new ArrayList<Order>();
		for(Order order : orders){
			if(status != null) {
				if(order.getOrderStatus() != status && !found.contains(order)){
			        found.add(order);
			    }
			}
			
			if(type != null) {
			    if(order.getRestaurantType() != type && !found.contains(order)){
			        found.add(order);
			    }
			}
		}
		
		orders.removeAll(found);
		
		
		return orders;
		
	}
	
}
