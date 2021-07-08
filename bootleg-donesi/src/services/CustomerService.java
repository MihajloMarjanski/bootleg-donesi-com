package services;

import java.util.ArrayList;

import com.google.gson.JsonElement;

import model.Courier;
import model.Customer;
import model.CustomerType;
import model.Gender;
import model.Menager;
import model.Role;
import model.ShoppingCart;
import model.User;

public class CustomerService {
	
	public static ArrayList<Customer> customerList = new ArrayList<Customer>();
	private static CustomerType normal = new CustomerType("NORMAL",0,0);
	private static CustomerType bronze = new CustomerType("BRONZE",1,1000);
	private static CustomerType silver = new CustomerType("SILVER",2,5000);
	private static CustomerType gold = new CustomerType("GOLD",5,20000);
	
	private static void save() {
		
	}
	
	public static void load() {
		Customer customer = new Customer(1, "customer", "123", "Neki", "Tamo", Gender.MALE, "1999-09-15", Role.CUSTOMER, new ArrayList<Integer>(), new ShoppingCart(1), 0);
		customer.setCustomerType(normal);
		customerList.add(customer);
	}
	
	private static Integer generateID() 
	{
		int id = 0;
		for (Customer customer : customerList) {
			if (customer.getEntityID() > id) {
				id = customer.getEntityID();
			}
		}
			
		return id + 1;
	}
	
	public static boolean checkUsernameAvailability(String username) {
		for (Customer customer : customerList) {
			if (customer.getUsername().equals(username) && !customer.isDeleted()) {
				return false;
			}
		}
			
		return true;
	}
	
	public static boolean checkUsernameAvailability(String username, int id) {
		for (Customer customer : customerList) {
			if (customer.getUsername().equals(username) && !customer.isDeleted() && customer.getEntityID() != id) {
				return false;
			}
		}
			
		return true;
	}
	
	public static void addCustomer(String username, String password, String firtName, String lastName, Gender gender,
			String dateOfBirth, Role role) {
		
		Customer customer = new Customer(generateID(), username, password, firtName, lastName, gender, dateOfBirth, Role.CUSTOMER,
				new ArrayList<Integer>(), new ShoppingCart(generateID()), 0);
		customer.setCustomerType(normal);
		customerList.add(customer);
		save();
	}
	
	public static void addCustomer(Customer customer) {
		
		customer.setRole(Role.CUSTOMER);
		customer.setEntityID(generateID());
		customer.setOrders(new ArrayList<Integer>());
		customer.setShoppingCart(new ShoppingCart(generateID()));
		customer.setCustomerType(normal);
		customer.setBlocked(false);
		customer.setSuspicious(false);
		customerList.add(customer);
		save();
	}
	
	public static boolean loginCustomer(String username, String password) {
		for (Customer customer : customerList) {
			if (customer.getUsername().equals(username) && customer.getPassword().equals(password) && !customer.isDeleted() && !customer.isBlocked()) {
				return true;
			}
		}
			
		return false;
	}
	
	public static Customer getCustomerByUsername(String username) {
		for (Customer customer : customerList) {
			if (customer.getUsername().equals(username) && !customer.isDeleted()) {
				return customer;
			}
		}
			
		return null;
	}
	
	public static ArrayList<Customer> getAll() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		for (Customer customer: customerList) {
			if (!customer.isDeleted()) {
				customers.add(customer);
			}
		}
			
		return customers;
	}
	
	public static Customer getCustomerByID(int id) {
		for (Customer customer: customerList) {
			if (customer.getEntityID() == id && !customer.isDeleted()) {
				return customer;
			}
		}
			
		return null;
	}

	public Customer change(User user) {
		for (Customer customer: getAll()) {
			if(customer.getEntityID() == user.getEntityID()) {
				customer.setFirstName(user.getFirstName());
				customer.setLastName(user.getLastName());
				customer.setGender(user.getGender());
				customer.setPassword(user.getPassword());
				customer.setUsername(user.getUsername());
				customer.setDateOfBirth(user.getDateOfBirth());
				
				save();
				return customer;
			}
		}
		return null;
	}

	public static void delete(int entityID) {
		for (Customer customer : customerList) {
			if (customer.getEntityID() == entityID) {
				customer.setDeleted(true);
				break;
			}
		}
		save();
			
	}

	public static void block(int entityID) {
		for (Customer customer : customerList) {
			if (customer.getEntityID() == entityID) {
				customer.setBlocked(true);
				break;
			}
		}
		save();
		
	}

	public static ArrayList<Customer> getForIDs(ArrayList<Integer> customerIDs) {
		ArrayList<Customer> returnList = new ArrayList<Customer>();
		for (Customer customer : getAll()) {
			if (customerIDs.contains(customer.getEntityID())) {
				returnList.add(customer);
			}
		}
		return returnList;
	}

	public static void updateSuspicion(int customerID, boolean checkSuspicion) {
		for (Customer customer : getAll()) {
			if (customer.getEntityID() == customerID) {
				customer.setSuspicious(checkSuspicion);
				break;
			}
		}
		save();
	}

	public static void removePoints(int customerID, double pointsLost) {
		for (Customer customer : getAll()) {
			if (customer.getEntityID() == customerID) {
				customer.setPoints(customer.getPoints() - pointsLost);
				if(customer.getPoints() < bronze.getRequiredPoints()) {
					customer.setCustomerType(normal);
					break;
				}
				else if(customer.getPoints() < silver.getRequiredPoints()) {
					customer.setCustomerType(bronze);
					break;
				}
				else if(customer.getPoints() < gold.getRequiredPoints()) {
					customer.setCustomerType(silver);
					break;
				}
			}
		}
			
		save();
		
	}

}
