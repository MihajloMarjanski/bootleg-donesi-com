package services;

import java.time.Duration;
import java.time.Instant;
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
		Order order = new Order(2, "OR2", new ArrayList<Integer>(), 1, new Date(), 777.0, 1,OrderStatus.WAITING, 0,"FLIPERANA",RestaurantType.ITALIAN);
		order.getRequests().add("courier");
		orderList.add(order);
		orderList.add(new Order(3, "OR3", new ArrayList<Integer>(), 1, new Date(), 666.0, 1,OrderStatus.INPREP, 0,"FLIPERANA",RestaurantType.ITALIAN));
		orderList.add(new Order(4, "OR4", new ArrayList<Integer>(), 1, new Date(), 666.0, 1,OrderStatus.DELIVERED, 1,"FLIPERANA",RestaurantType.ITALIAN));
		orderList.add(new Order(5, "OR5", new ArrayList<Integer>(), 1, new Date(), 4214.0, 1,OrderStatus.PROCESSING, 0,"FLIPERANA",RestaurantType.ITALIAN));
		orderList.add(new Order(6, "OR6", new ArrayList<Integer>(), 1, new Date(), 4214.0, 1,OrderStatus.PROCESSING, 0,"FLIPERANA",RestaurantType.ITALIAN));
		orderList.add(new Order(7, "OR7", new ArrayList<Integer>(), 1, new Date(), 4214.0, 1,OrderStatus.PROCESSING, 0,"FLIPERANA",RestaurantType.ITALIAN));
		orderList.add(new Order(8, "OR8", new ArrayList<Integer>(), 1, new Date(), 4214.0, 1,OrderStatus.PROCESSING, 0,"FLIPERANA",RestaurantType.ITALIAN));
		orderList.add(new Order(9, "OR9", new ArrayList<Integer>(), 1, new Date(), 4214.0, 1,OrderStatus.PROCESSING, 0,"FLIPERANA",RestaurantType.ITALIAN));
		orderList.add(new Order(10, "OR10", new ArrayList<Integer>(), 1, new Date(), 4214.0, 1,OrderStatus.CANCELED, 0,"FLIPERANA",RestaurantType.ITALIAN));
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

	public static ArrayList<Order> getForCourier(int entityID, String username) {
		ArrayList<Order> orders = new ArrayList<Order>();
		for (Order order: getAll()) {
			if (order.getCourier() == entityID && order.getOrderStatus() != OrderStatus.DELIVERED && order.getOrderStatus() != OrderStatus.CANCELED) {
				orders.add(order);
			}
			if(order.getOrderStatus() == OrderStatus.WAITING && !order.getRequests().contains(username)) {
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

	public static ArrayList<Order> getForCourier(int entityID, String ordreStatus, String restaurantType, String username) {
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
			if(order.getOrderStatus() == OrderStatus.WAITING && !order.getRequests().contains(username)) {
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

	public static void finishOrder(int entityID) {
		for (Order order : getAll()) {
			if(order.getEntityID() == entityID && order.getOrderStatus() == OrderStatus.INPREP){
				order.setOrderStatus(OrderStatus.WAITING);
				break;
			}
		}
		save();
	}

	public static void deliverOrder(int entityID) {
		for (Order order : getAll()) {
			if(order.getEntityID() == entityID && order.getOrderStatus() == OrderStatus.TRANSPORT){
				order.setOrderStatus(OrderStatus.DELIVERED);
				break;
			}
		}
		save();
		
	}

	public static void addRequest(int entityID, String username) {
		for (Order order : getAll()) {
			if(order.getEntityID() == entityID && order.getOrderStatus() == OrderStatus.WAITING){
				order.getRequests().add(username);
				break;
			}
		}
		save();
		
	}

	public static ArrayList<Integer> getCustomerIDs(int restaurantID) {
		ArrayList<Integer> returnIDs = new ArrayList<Integer>();
		for (Order order : getAll()) {
			if(order.getRestaurant() == restaurantID && !returnIDs.contains(order.getCustomer())){
				returnIDs.add(order.getCustomer());
			}
		}
		return returnIDs;
	}

	public static ArrayList<Order> getRequests(int restaurantID) {
		ArrayList<Order> requests = new ArrayList<Order>();
		for (Order order : getAll()) {
			if(order.getRestaurant() == restaurantID && !order.getRequests().isEmpty()){
				requests.add(order);
			}
		}
		return requests;
	}

	public static void approveTransportFor(int orderIDInt, int entityID) {
		for (Order order : getAll()) {
			if(order.getEntityID() == orderIDInt){
				order.setCourier(entityID);
				order.getRequests().clear();
				order.setOrderStatus(OrderStatus.TRANSPORT);
				break;
			}
		}
		save();
	}
	
	public static void denyTransportFor(int orderIDInt, String username) {
		for (Order order : getAll()) {
			if(order.getEntityID() == orderIDInt){
				order.getRequests().remove(username);
				break;
			}
		}
		save();
	}
	
	public static boolean checkSuspicion(int customerID) {
		Instant now = Instant.now();
		Instant before = now.minus(Duration.ofDays(30));
		Date date30DaysAgo = Date.from(before);
		
		
		ArrayList<Order> forCheck = new ArrayList<Order>();
		for (Order order : getAll()) {
			if(order.getCustomer() == customerID && order.getTimeOfOrder().after(date30DaysAgo)) {
				forCheck.add(order);
			}
		}
		
		int canceledNum = 0;
		
		for (Order order : forCheck) {
			if(order.getOrderStatus() == OrderStatus.CANCELED) {
				canceledNum++;
			}
			if (canceledNum > 4) {
				
				return true;
			}
		}
		return false;
	}

	public static void cancelOrder(int orderIDInt) {
		for (Order order : getAll()) {
			if(order.getEntityID() == orderIDInt){
				order.setOrderStatus(OrderStatus.CANCELED);;
				break;
			}
		}
		save();
		
	}

	public static Order getOrderByID(int orderIDInt) {
		for (Order order : getAll()) {
			if(order.getEntityID() == orderIDInt){
				return order;
			}
		}
		return null;
	}

	public static int getDeliveredForCustomerAndRestaurant(int user, int restaurant) {
		int delivered = 0;
		for (Order order : getAll()) {
			if(order.getCustomer() == user && order.getRestaurant() == restaurant && order.getOrderStatus() == OrderStatus.DELIVERED) {
				delivered++;
			}
		}
		
		return delivered;
	}
	
}
