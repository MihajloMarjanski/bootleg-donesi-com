package rest;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;

import model.Customer;
import services.CustomerService;


public class SparkAppMain {

	private static Gson g = new Gson();
	private static CustomerService customerService= new CustomerService();
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		port(8080);

		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		
		post("/registerUser", (req, res) -> {
			res.type("application/json");
			Customer customer = g.fromJson(req.body(), Customer.class);
			if(!customerService.checkUsernameAvailability(customer.getUsername())) {
				res.status(404);
				return "ALREADY EXISTS";
			}
			customerService.addCustomer(customer);
			res.status(200);
			return "SUCCESS";
		});
		
		post("rest/products/add", (req, res) -> {
			res.type("application/json");
			return "SUCCESS";
		});
	}
}
