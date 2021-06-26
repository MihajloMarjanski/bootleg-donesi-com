package rest;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.io.File;
import java.util.ArrayList;

import com.google.gson.Gson;

import model.Admin;
import model.Courier;
import model.Customer;
import model.Menager;
import model.Role;
import model.User;
import services.AdminService;
import services.CourierService;
import services.CustomerService;
import services.MenagerService;


public class SparkAppMain {

	private static Gson g = new Gson();
	private static CustomerService customerService= new CustomerService();
	private static MenagerService menagerService = new MenagerService();
	private static CourierService courierService = new CourierService();
	private static AdminService adminService = new AdminService();
	
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
		port(8080);
		
		customerService.load();
		menagerService.load();
		courierService.load();
		adminService.load();
		
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
			for (User user : customerService.customerList) {
				users.add(user);
			}
			for (User user : menagerService.menagerList) {
				users.add(user);
			}		
			for (User user : courierService.courierList) {
				users.add(user);
			}
			for (User user : adminService.adminList) {
				users.add(user);
			}
			res.status(200);
			return g.toJson(users);
		});
	}
}
