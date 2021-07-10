package rest;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

import javax.swing.ToolTipManager;

import com.google.gson.Gson;

import dto.RestaurantDTO;
import model.Admin;
import model.Comment;
import model.Courier;
import model.Customer;
import model.Menager;
import model.MenuItem;
import model.Order;
import model.Restaurant;
import model.Role;
import model.User;
import services.AdminService;
import services.CheckerThread;
import services.CommentService;
import services.CourierService;
import services.CustomerService;
import services.MenagerService;
import services.MenuItemService;
import services.OrderService;
import services.RestaurantService;


public class SparkAppMain {

	private static Gson g = new Gson();
	private static CustomerService customerService= new CustomerService();
	private static MenagerService menagerService = new MenagerService();
	private static CourierService courierService = new CourierService();
	private static AdminService adminService = new AdminService();
	private static RestaurantService restaurantService = new RestaurantService();
	private static CommentService commentService = new CommentService();
	private static MenuItemService menuItemService = new MenuItemService();
	private static OrderService orderService = new OrderService();
	
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
		port(9090);		
		customerService.load();
		menagerService.load();
		courierService.load();
		adminService.load();
		restaurantService.load();
		commentService.load();
		menuItemService.load();
		orderService.load();
		
		CheckerThread checker = new CheckerThread(orderService, customerService);
		checker.start();
		
		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		
		post("/registerUser", (req, res) -> {
			res.type("application/json");
			Customer customer = g.fromJson(req.body(), Customer.class);
			if(!customerService.checkUsernameAvailability(customer.getUsername())) {
				res.status(404);
				return "ALREADY EXISTS";
			}
			else if(!menagerService.checkUsernameAvailability(customer.getUsername())) {
				res.status(404);
				return "ALREADY EXISTS";
			}
			else if(!courierService.checkUsernameAvailability(customer.getUsername())) {
				res.status(404);
				return "ALREADY EXISTS";
			}
			else if(!adminService.checkUsernameAvailability(customer.getUsername())) {
				res.status(404);
				return "ALREADY EXISTS";
			}
			else {
				customerService.addCustomer(customer);
				res.status(200);
				return "SUCCESS";
			}

		});
		
		post("/registerEmployee", (req, res) -> {
			res.type("application/json");
			User user = g.fromJson(req.body(), User.class);
			if(!customerService.checkUsernameAvailability(user.getUsername())) {
				res.status(404);
				return "ALREADY EXISTS";
			}
			else if(!menagerService.checkUsernameAvailability(user.getUsername())) {
				res.status(404);
				return "ALREADY EXISTS";
			}
			else if(!courierService.checkUsernameAvailability(user.getUsername())) {
				res.status(404);
				return "ALREADY EXISTS";
			}
			else if(!adminService.checkUsernameAvailability(user.getUsername())) {
				res.status(404);
				return "ALREADY EXISTS";
			}
			else {
				if(user.getRole() == Role.COURIER) {
					Courier courier = g.fromJson(req.body(), Courier.class);
					courierService.addCourier(courier);
					res.status(200);
					return "SUCCESS";
				}
				else if(user.getRole() == Role.MENAGER) {
					Menager menager = g.fromJson(req.body(), Menager.class);
					menagerService.addMenager(menager);
					res.status(200);
					return "SUCCESS";
				}
				else {
					res.status(404);
					return "ALREADY EXISTS";
				}
			}
			

		});
		
		post("/loginUser", (req, res) -> {
			res.type("application/json");
			User user = g.fromJson(req.body(), User.class);
			if(customerService.loginCustomer(user.getUsername(),user.getPassword())) {
				Customer customer = customerService.getCustomerByUsername(user.getUsername());
				res.status(200);
				return g.toJson(customer);
			}
			else if(menagerService.loginMenager(user.getUsername(),user.getPassword())) {
				Menager menager= menagerService.getMenagerByUsername(user.getUsername());
				res.status(200);
				return g.toJson(menager);
			}
			else if(courierService.loginCourier(user.getUsername(),user.getPassword())) {
				Courier courier = courierService.getCourierByUsername(user.getUsername());
				res.status(200);
				return g.toJson(courier);
			}
			else if(adminService.loginAdmin(user.getUsername(),user.getPassword())) {
				Admin admin = adminService.getAdminByUsername(user.getUsername());
				res.status(200);
				res.body(g.toJson(admin));
				return g.toJson(admin);
			}
			else {
				res.status(404);
				return "FAILED LOGIN";
			}
		});
		
		get("/allUsers", (req, res) -> {
			res.type("application/json");
			ArrayList<User> users = new ArrayList<User>();
			for (User user : customerService.getAll()) {
				users.add(user);
			}
			for (User user : menagerService.getAll()) {
				users.add(user);
			}		
			for (User user : courierService.getAll()) {
				users.add(user);
			}
			for (User user : adminService.getAll()) {
				users.add(user);
			}
			res.status(200);
			return g.toJson(users);
		});
		
		post("/searchUsers", (req, res) -> {
			res.type("application/json");
			HashMap<String, String> searchParams = g.fromJson(req.body(), HashMap.class);
			ArrayList<User> users = new ArrayList<User>();
			ArrayList<User> searchedUsers = new ArrayList<User>();
			String firstName = searchParams.get("firstName");
			String lastName = searchParams.get("lastName");
			String username = searchParams.get("username");
			String role = searchParams.get("role");
			String type = searchParams.get("type");
			String sort = searchParams.get("sort");
			String suspicious = searchParams.get("suspicious");
			
			if (role.equals("")) {
				for (User user : customerService.getAll()) {
					users.add(user);
				}
				for (User user : menagerService.getAll()) {
					users.add(user);
				}		
				for (User user : courierService.getAll()) {
					users.add(user);
				}
				for (User user : adminService.getAll()) {
					users.add(user);
				}
			}
			
			else if(role.equals("CUSTOMER")) {
				ArrayList<Customer> customers = new ArrayList<Customer>();
				if(type.equals("")) {
					for (Customer user : customerService.getAll()) {
						customers.add(user);
					}
				}
				else{
					for (Customer user : customerService.getAll()) {
						if(user.getCustomerType().getTypeName().equals(type)) {
							customers.add(user);
						}
					}
				}
				if(sort.equals("ASCPOINTS")) {
					customers = (ArrayList<Customer>) customers.stream()
							  .sorted(Comparator.comparing(Customer::getPoints))
							  .collect(Collectors.toList());
				}
				else if(sort.equals("DESCPOINTS")) {
					customers = (ArrayList<Customer>) customers.stream()
							  .sorted(Comparator.comparing(Customer::getPoints).reversed())
							  .collect(Collectors.toList());
				}
				for (Customer customer : customers) {
					users.add(customer);
				}

			}
			else if(role.equals("MENAGER")) {
				for (User user : menagerService.getAll()) {
					users.add(user);
				}
			}
			else if(role.equals("COURIER")) {
				for (User user : courierService.getAll()) {
					users.add(user);
				}
			}
			else if(role.equals("ADMINISTRATOR")) {
				for (User user : adminService.getAll()) {
					users.add(user);
				}
			}

			for (User user : users) {
				if(user.getFirstName().toLowerCase().contains(firstName.toLowerCase()) && user.getLastName().toLowerCase().contains(lastName.toLowerCase()) && user.getUsername().toLowerCase().contains(username.toLowerCase())) {
					if(suspicious.equals("yes")) {
						if(user.isSuspicious()) {
							searchedUsers.add(user);
						}
					}
					else{
						searchedUsers.add(user);
					}
				}
			}
			
			if(sort.equals("ASCFNAME")) {
				searchedUsers = (ArrayList<User>) searchedUsers.stream()
						  .sorted(Comparator.comparing(User::getFirstName))
						  .collect(Collectors.toList());
			}
			else if(sort.equals("DESCFNAME")) {
				searchedUsers = (ArrayList<User>) searchedUsers.stream()
						  .sorted(Comparator.comparing(User::getFirstName).reversed())
						  .collect(Collectors.toList());
			}
			else if(sort.equals("ASCLNAME")) {
				searchedUsers = (ArrayList<User>) searchedUsers.stream()
						  .sorted(Comparator.comparing(User::getLastName))
						  .collect(Collectors.toList());
			}
			else if(sort.equals("DESCLNAME")) {
				searchedUsers = (ArrayList<User>) searchedUsers.stream()
						  .sorted(Comparator.comparing(User::getLastName).reversed())
						  .collect(Collectors.toList());
			}
			else if(sort.equals("ASCUNAME")) {
				searchedUsers = (ArrayList<User>) searchedUsers.stream()
						  .sorted(Comparator.comparing(User::getUsername))
						  .collect(Collectors.toList());
			}
			else if(sort.equals("DESCUNAME")) {
				searchedUsers = (ArrayList<User>) searchedUsers.stream()
						  .sorted(Comparator.comparing(User::getUsername).reversed())
						  .collect(Collectors.toList());
			}
			
			
			res.status(200);
			return g.toJson(searchedUsers);
		});
		
		post("/getUser", (req, res) -> {
			res.type("application/json");
			User user = g.fromJson(req.body(), User.class);
			if(user.getRole() == Role.ADMINISTRATOR) {
				res.status(200);
				return g.toJson(adminService.getAdminByID(user.getEntityID()));
			}
			else if (user.getRole() == Role.MENAGER) {
				res.status(200); 
				return g.toJson(menagerService.getMenagerByID(user.getEntityID()));
			}
			else if (user.getRole() == Role.COURIER) {
				res.status(200);
				return g.toJson(courierService.getCourierByID(user.getEntityID()));
			}
			else if (user.getRole() == Role.CUSTOMER) {
				res.status(200);
				return g.toJson(customerService.getCustomerByID(user.getEntityID()));
			}
			else {
				res.status(404);
				return "NOT FOUND";
			}

		});
		
		post("/changeUser", (req, res) -> {
			res.type("application/json");
			User user = g.fromJson(req.body(), User.class);
			if(!customerService.checkUsernameAvailability(user.getUsername(), user.getEntityID())) {
				res.status(404);
				return "ALREADY EXISTS";
			}
			else if(!menagerService.checkUsernameAvailability(user.getUsername(), user.getEntityID())) {
				res.status(404);
				return "ALREADY EXISTS";
			}
			else if(!courierService.checkUsernameAvailability(user.getUsername(), user.getEntityID())) {
				res.status(404);
				return "ALREADY EXISTS";
			}
			else if(!adminService.checkUsernameAvailability(user.getUsername(), user.getEntityID())) {
				res.status(404);
				return "ALREADY EXISTS";
			}
			
			if(user.getRole() == Role.ADMINISTRATOR) {
				res.status(200);
				return g.toJson(adminService.change(user));
			}
			else if (user.getRole() == Role.MENAGER) {
				res.status(200);
				return g.toJson(menagerService.change(user));
			}
			else if (user.getRole() == Role.COURIER) {
				res.status(200);
				return g.toJson(courierService.change(user));
			}
			else if (user.getRole() == Role.CUSTOMER) {
				res.status(200);
				return g.toJson(customerService.change(user));
			}
			
			res.status(404);
			return "NOTHING";
		});
		
		get("/allRestaurants", (req, res) -> {
			res.type("application/json");
			res.status(200);
			return g.toJson(restaurantService.getAll());
		});
		
		post("/searchRestaurants", (req, res) -> {
			res.type("application/json");
			HashMap<String, String> searchParams = g.fromJson(req.body(), HashMap.class);
			ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
			ArrayList<Restaurant> searchedRestaurants = new ArrayList<Restaurant>();
			String name = searchParams.get("name");
			String location = searchParams.get("location");
			String rating = searchParams.get("rating");
			String type = searchParams.get("type");
			String sort = searchParams.get("sort");
			String open = searchParams.get("open");
			
			
			restaurants = RestaurantService.getAllForType(type,open);
			
			
			for (Restaurant restaurant : restaurants) {
				if(restaurant.getName().toLowerCase().contains(name.toLowerCase()) && (restaurant.getTownAndCountry().toLowerCase().contains(location.toLowerCase()) && restaurant.getRating().toString().contains(rating))) {
					searchedRestaurants.add(restaurant);
				}
			}
			
			if(sort.equals("ASCNAME")) {
				searchedRestaurants = (ArrayList<Restaurant>) searchedRestaurants.stream()
						  .sorted(Comparator.comparing(Restaurant::getName))
						  .collect(Collectors.toList());
			}
			else if(sort.equals("DESCNAME")) {
				searchedRestaurants = (ArrayList<Restaurant>) searchedRestaurants.stream()
						  .sorted(Comparator.comparing(Restaurant::getName).reversed())
						  .collect(Collectors.toList());
			}
			else if(sort.equals("ASCLOC")) {
				searchedRestaurants = (ArrayList<Restaurant>) searchedRestaurants.stream()
						  .sorted(Comparator.comparing(Restaurant::getLoc))
						  .collect(Collectors.toList());
			}
			else if(sort.equals("DESCLOC")) {
				searchedRestaurants = (ArrayList<Restaurant>) searchedRestaurants.stream()
						  .sorted(Comparator.comparing(Restaurant::getLoc).reversed())
						  .collect(Collectors.toList());
			}
			else if(sort.equals("ASCRATING")) {
				searchedRestaurants = (ArrayList<Restaurant>) searchedRestaurants.stream()
						  .sorted(Comparator.comparing(Restaurant::getRating))
						  .collect(Collectors.toList());
			}
			else if(sort.equals("DESCRATING")) {
				searchedRestaurants = (ArrayList<Restaurant>) searchedRestaurants.stream()
						  .sorted(Comparator.comparing(Restaurant::getRating).reversed())
						  .collect(Collectors.toList());
			}
			
			
			res.status(200);
			return g.toJson(searchedRestaurants);
			
		});
		
		post("/deleteUser", (req, res) -> {
			res.type("application/json");
			
			User userForDeletion = g.fromJson(req.body(), User.class);
			if(userForDeletion.getRole() == Role.MENAGER) {
				menagerService.delete(userForDeletion.getEntityID());
				restaurantService.deleteMenager(userForDeletion.getUsername());
			}
			else if(userForDeletion.getRole() == Role.CUSTOMER) {
				customerService.delete(userForDeletion.getEntityID());
			}
			else if(userForDeletion.getRole() == Role.COURIER) {
				courierService.delete(userForDeletion.getEntityID());
			}
			
			res.status(200);
			return g.toJson(userForDeletion);
		});
		
		post("/blockUser", (req, res) -> {
			res.type("application/json");
			
			User userToBlock = g.fromJson(req.body(), User.class);
			if(userToBlock.getRole() == Role.MENAGER) {
				menagerService.block(userToBlock.getEntityID());
			}
			else if(userToBlock.getRole() == Role.CUSTOMER) {
				customerService.block(userToBlock.getEntityID());
			}
			else if(userToBlock.getRole() == Role.COURIER) {
				courierService.block(userToBlock.getEntityID());
			}
			
			res.status(200);
			return g.toJson(userToBlock);
		});
		
		post("/deleteRestaurant", (req, res) -> {
			res.type("application/json");
			
			Restaurant restaurant = g.fromJson(req.body(), Restaurant.class);
			restaurantService.delete(restaurant.getEntityID());
			menuItemService.deleteForRestaurant(restaurant.getEntityID());
			menagerService.deleteRestaurant(restaurant.getEntityID());
			
			
			res.status(200);
			return g.toJson(restaurantService.getAll());
		});
		
		post("/deleteMenuItem", (req, res) -> {
			res.type("application/json");
			
			MenuItem menuItem= g.fromJson(req.body(), MenuItem.class);
			restaurantService.deleteItem(menuItem);
			menuItemService.delete(menuItem.getEntityID());
			
			
			res.status(200);
			return "SUCCES";
		});
		
		post("/viewRestaurant", (req, res) -> {
			res.type("application/json");
			RestaurantDTO returnDTO = new RestaurantDTO();
			Restaurant restaurant = new Restaurant();
			User user = g.fromJson(req.body(), User.class);
			restaurant = restaurantService.getRestaurantById(user.getEntityID());
			returnDTO.setName(restaurant.getName());
			returnDTO.setLocation(restaurant.getLocation());
			returnDTO.setLogoPath(restaurant.getLogoPath());
			returnDTO.setRating(restaurant.getRating());
			returnDTO.setStatus(restaurant.getStatus());
			returnDTO.setType(restaurant.getType());
			returnDTO.setEntityID(restaurant.getEntityID());
			if(user.getRole() == Role.ADMINISTRATOR) {
				returnDTO.setComments(commentService.getAllForRestaurant(user.getEntityID()));
			}
			else {
				returnDTO.setComments(commentService.getApprovedForRestaurant(user.getEntityID()));
			}
			returnDTO.setMenuItems(menuItemService.getAllForRestaurant(user.getEntityID()));
			
			
			res.status(200);
			return g.toJson(returnDTO);
		});
		
		post("/getRestaurantInfo", (req, res) -> {
			res.type("application/json");
			HashMap<String, String> user = g.fromJson(req.body(), HashMap.class);
					
			Restaurant restaurant = restaurantService.getRestaurantById(menagerService.getRestaurantID(user.get("username")));
		
			res.status(200);
			return g.toJson(restaurant);
		});
		
		post("/getMenu", (req, res) -> {
			res.type("application/json");
			HashMap<String, String> user = g.fromJson(req.body(), HashMap.class);
					
			Restaurant restaurant = restaurantService.getRestaurantById(menagerService.getRestaurantID(user.get("username")));
			ArrayList<MenuItem> menuItems = menuItemService.getAllForRestaurant(restaurant.getEntityID());
			
			
			res.status(200);
			return g.toJson(menuItems);
		});
		
		post("/getMyComments", (req, res) -> {
			res.type("application/json");
			HashMap<String, String> user = g.fromJson(req.body(), HashMap.class);
					
			Restaurant restaurant = restaurantService.getRestaurantById(menagerService.getRestaurantID(user.get("username")));
			ArrayList<Comment> comments = commentService.getAllForRestaurant(restaurant.getEntityID());
			
			
			res.status(200);
			return g.toJson(comments);
		});
		
		post("/denyComment", (req, res) -> {
			res.type("application/json");
			Comment comment = g.fromJson(req.body(), Comment.class);
					
			commentService.denyComment(comment.getEntityID());			
			
			res.status(200);
			return "DONE";
		});
		
		post("/approveComment", (req, res) -> {
			res.type("application/json");
			Comment comment = g.fromJson(req.body(), Comment.class);
			int restaurantID = comment.getRestaurant();
			commentService.approveComment(comment.getEntityID());			
			restaurantService.updateRating(restaurantID,commentService.calculateRestaurantRating(restaurantID));
			
			res.status(200);
			return "DONE";
		});
		
		post("/deleteComment", (req, res) -> {
			res.type("application/json");
			Comment comment = g.fromJson(req.body(), Comment.class);
			int restaurantID = comment.getRestaurant();
			commentService.delete(comment.getEntityID());			
			restaurantService.updateRating(restaurantID,commentService.calculateRestaurantRating(restaurantID));
			res.status(200);
			return "DONE";
		});
		
		post("/addItem", (req, res) -> {
			res.type("application/json");
			MenuItem item = g.fromJson(req.body(), MenuItem.class);			
			int restaurantID = menagerService.getRestaurantID(item.getRestaurant());			
			item.setRestaurant(restaurantID);	
			if (menuItemService.checkNameAvailability(item)) {
				menuItemService.add(item);
				res.status(200);
				return "DONE";
			}
			else {
				res.status(404);
				return "ALREADY EXISTS";
			}			
		});
		
		post("/changeItem", (req, res) -> {
			res.type("application/json");
			MenuItem item = g.fromJson(req.body(), MenuItem.class);
				
			if (menuItemService.checkNameAvailability(item,item.getEntityID())) {
				menuItemService.change(item);
				res.status(200);
				return "DONE";
			}
			else {
				res.status(404);
				return "ALREADY EXISTS";
			}			
		});
		
		post("/getMyRestaurant", (req, res) -> {
			res.type("application/json");
			User user = g.fromJson(req.body(), User.class);
			
			
			res.status(200);
			return g.toJson(menagerService.getRestaurantID(user.getEntityID()));
			
		});
		
		post("/getOrders", (req, res) -> {
			res.type("application/json");
			User user = g.fromJson(req.body(), User.class);
			ArrayList<Order> orders  = new ArrayList<Order>();
			if(user.getRole() == Role.CUSTOMER) {
				orders = orderService.getForCustomer(user.getEntityID());
			}
			else if(user.getRole() == Role.COURIER) {
				orders = orderService.getForCourier(user.getEntityID(),user.getUsername());
			}
			else if(user.getRole() == Role.MENAGER) {
				orders = orderService.getForRestaurant(menagerService.getRestaurantID(user.getEntityID()));
			}
			
			
			res.status(200);
			return g.toJson(orders);
			
		});
		
		post("/searchOrders", (req, res) -> {
			res.type("application/json");
			HashMap<String, String> searchParams = g.fromJson(req.body(), HashMap.class);
			String id = searchParams.get("entityID");
			String role = searchParams.get("role");
			String restaurantName = searchParams.get("restaurantName");
			String priceTo = searchParams.get("priceTo");
			String priceFrom = searchParams.get("priceFrom");
			String dateTo = searchParams.get("dateTo");
			String dateFrom = searchParams.get("dateFrom");
			String restaurantType = searchParams.get("restaurantType");
			String ordreStatus = searchParams.get("ordreStatus");
			String sort = searchParams.get("sort");
			String username = searchParams.get("username");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
			
			
			ArrayList<Order> orders  = new ArrayList<Order>();
			if(role.equals("CUSTOMER")) {
				orders = orderService.getForCustomer(Integer.parseInt(id),ordreStatus,restaurantType);
			}
			else if(role.equals("COURIER")) {
				orders = orderService.getForCourier(Integer.parseInt(id),ordreStatus,restaurantType,username);
			}
			else if(role.equals("MENAGER")) {
				orders = orderService.getForRestaurant(menagerService.getRestaurantID(Integer.parseInt(id)),ordreStatus,restaurantType);
			}
			
			if(!priceTo.equals("")) {
				ArrayList<Order> found  = new ArrayList<Order>();
				for (Order order: orders) {
					if(order.getPrice() >= Double.parseDouble(priceTo)) {
						found.add(order);
					}
				}
				orders.removeAll(found);
			}

			if(!priceFrom.equals("")) {
				ArrayList<Order> found  = new ArrayList<Order>();
				for (Order order: orders) {
					if(order.getPrice() <= Double.parseDouble(priceFrom)) {
						found.add(order);
					}
				}
				orders.removeAll(found);
			}
			
			ArrayList<Order> found3  = new ArrayList<Order>();
			for (Order order: orders) {
				if(!order.getRestaurantName().toLowerCase().contains(restaurantName.toLowerCase())) {
					found3.add(order);
				}
			}
			orders.removeAll(found3);
			
			if(!dateTo.equals("")) {
				ArrayList<Order> found  = new ArrayList<Order>();
				for (Order order: orders) {
					if(order.getTimeOfOrder().after(formatter.parse(dateTo))) {
						found.add(order);
					}
				}
				orders.removeAll(found);
			}
			
			if(!dateFrom.equals("")) {
				ArrayList<Order> found  = new ArrayList<Order>();
				for (Order order: orders) {
					if(order.getTimeOfOrder().before(formatter.parse(dateFrom))) {
						found.add(order);
					}
				}
				orders.removeAll(found);
			}
			
			if(sort.equals("ASCNAME")) {
				orders = (ArrayList<Order>) orders.stream()
						  .sorted(Comparator.comparing(Order::getRestaurantName))
						  .collect(Collectors.toList());
			}
			else if(sort.equals("DESCNAME")) {
				orders = (ArrayList<Order>) orders.stream()
						  .sorted(Comparator.comparing(Order::getRestaurantName).reversed())
						  .collect(Collectors.toList());
			}
			else if(sort.equals("ASCPRICE")) {
				orders = (ArrayList<Order>) orders.stream()
						  .sorted(Comparator.comparing(Order::getTimeOfOrder))
						  .collect(Collectors.toList());
			}
			else if(sort.equals("DESCPRICE")) {
				orders = (ArrayList<Order>) orders.stream()
						  .sorted(Comparator.comparing(Order::getTimeOfOrder).reversed())
						  .collect(Collectors.toList());
			}
			else if(sort.equals("ASCDATE")) {
				orders = (ArrayList<Order>) orders.stream()
						  .sorted(Comparator.comparing(Order::getPrice))
						  .collect(Collectors.toList());
			}
			else if(sort.equals("DESCDATE")) {
				orders = (ArrayList<Order>) orders.stream()
						  .sorted(Comparator.comparing(Order::getPrice).reversed())
						  .collect(Collectors.toList());
			}
			
			res.status(200);
			return g.toJson(orders);
			
		});
		
		post("/finishOrder", (req, res) -> {
			res.type("application/json");
			Order order = g.fromJson(req.body(), Order.class);
			orderService.finishOrder(order.getEntityID());
			
			res.status(200);
			return "OK";
			
		});
		
		post("/deliverOrder", (req, res) -> {
			res.type("application/json");
			Order order = g.fromJson(req.body(), Order.class);
			orderService.deliverOrder(order.getEntityID());
			
			res.status(200);
			return "OK";
			
		});
		
		post("/requestOrder", (req, res) -> {
			res.type("application/json");
			HashMap<String, Object> reqParams = g.fromJson(req.body(), HashMap.class);
			double orderID = (double)reqParams.get("orderID");
			String username = (String)reqParams.get("username");
			int orderIDInt = (int) orderID;
			orderService.addRequest(orderIDInt,username);
			
			res.status(200);
			return "OK";
			
		});
		
		post("/getMyCustomers", (req, res) -> {
			res.type("application/json");
			User user = g.fromJson(req.body(), User.class);		
			ArrayList<Customer> customers = new ArrayList<Customer>();
			
			ArrayList<Integer> customerIDs = orderService.getCustomerIDs(menagerService.getRestaurantID(user.getUsername()));
			customers = customerService.getForIDs(customerIDs);
			
			res.status(200);
			return g.toJson(customers);
			
		});
		
		post("/getMyRequests", (req, res) -> {
			res.type("application/json");
			User user = g.fromJson(req.body(), User.class);		
			ArrayList<Order> requests = new ArrayList<Order>();
			
			requests = orderService.getRequests(menagerService.getRestaurantID(user.getUsername()));
			
			res.status(200);
			return g.toJson(requests);
			
		});
		
		post("/approveTransport", (req, res) -> {
			res.type("application/json");
			HashMap<String, Object> reqParams = g.fromJson(req.body(), HashMap.class);
			double orderID = (double)reqParams.get("reqEntityID");
			String username = (String)reqParams.get("courier");
			int orderIDInt = (int) orderID;
			
			Courier courier = courierService.getCourierByUsername(username);
			
			orderService.approveTransportFor(orderIDInt,courier.getEntityID());
			courierService.addOrderToCourier(orderIDInt,courier.getEntityID());
					
			res.status(200);
			return "OK";
			
		});
		
		post("/denyTransport", (req, res) -> {
			res.type("application/json");
			HashMap<String, Object> reqParams = g.fromJson(req.body(), HashMap.class);
			double orderID = (double)reqParams.get("reqEntityID");
			String username = (String)reqParams.get("courier");
			int orderIDInt = (int) orderID;

			
			orderService.denyTransportFor(orderIDInt,username);
					
			res.status(200);
			return "OK";
			
		});
		
		post("/cancelOrder", (req, res) -> {
			res.type("application/json");
			HashMap<String, Object> reqParams = g.fromJson(req.body(), HashMap.class);
			double orderID = (double)reqParams.get("orderID");
			String username = (String)reqParams.get("username");
			int orderIDInt = (int) orderID;
			int customerID = customerService.getCustomerByUsername(username).getEntityID();
			Order order = orderService.getOrderByID(orderIDInt);
			double pointsLost = order.getPrice()/1000 * 133 * 4;
			
			orderService.cancelOrder(orderIDInt);
			customerService.updateSuspicion(customerID,orderService.checkSuspicion(customerID));
			customerService.removePoints(customerID, pointsLost);
			
			
			res.status(200);
			return "OK";
			
		});
		
		post("/canIComment", (req, res) -> {
			res.type("application/json");
			User user = g.fromJson(req.body(), User.class);
			Customer customer = customerService.getCustomerByUsername(user.getUsername());
			
			int delivered = orderService.getDeliveredForCustomerAndRestaurant(customer.getEntityID(),user.getEntityID());
			if(delivered == 0) {
				res.status(200);
				return g.toJson(false);
			}
			
			
			if(commentService.checkCommentable(delivered, user.getEntityID(), customer.getEntityID())) {
				res.status(200);
				return g.toJson(true);
			}
			else {
				res.status(200);
				return g.toJson(false);
			}

			
		});
		
		post("/makeComment", (req, res) -> {
			res.type("application/json");
			Comment comment = g.fromJson(req.body(), Comment.class);

			if(comment.getRating() <1 || comment.getRating() > 5) {
				res.status(404);
				return g.toJson(false);
			}
			else {
				commentService.addComment(comment);
				res.status(200);
				return g.toJson(true);
			}
					
		});
		
		get("/availableMenagers", (req, res) -> {
			res.type("application/json");

			ArrayList<Menager> menagers = menagerService.getAvailable();
			if (menagers.isEmpty()) {
				res.status(200);
				return g.toJson("");
			}
			res.status(200);
			return g.toJson(menagers);
					
		});
		
		post("/addRestaurant", (req, res) -> {
			res.type("application/json");
			Restaurant restaurant = g.fromJson(req.body(), Restaurant.class);
			menagerService.addRestaurantToMenager(restaurant.getUsername(),restaurantService.generateID());
			restaurantService.addRestaurant(restaurant);

			res.status(200);
			return g.toJson("");
					
		});
		
		
		
	}
}
