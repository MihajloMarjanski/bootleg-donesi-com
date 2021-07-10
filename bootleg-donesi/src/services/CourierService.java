package services;

import java.io.File;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import model.Comment;
import model.Courier;
import model.Customer;
import model.Gender;
import model.Menager;
import model.Role;
import model.User;

public class CourierService {
	
	public static ArrayList<Courier> courierList = new ArrayList<Courier>();
	
	private static void save() {
		try {
		    Gson gson = new Gson();

		    Writer writer = Files.newBufferedWriter(Paths.get("data"+File.separator+"couriers.json"));

		    gson.toJson(courierList, writer);

		    writer.close();

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}
	
	public static void load() {
		try {
		    Gson gson = new Gson();

		    Reader reader = Files.newBufferedReader(Paths.get("data"+File.separator+"couriers.json"));

		    Courier[] couriers = gson.fromJson(reader, Courier[].class);
		    Collections.addAll(courierList, couriers);
		    
		    reader.close();

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}
	
	public static void addCourier(Courier courier) {
		
		courier.setRole(Role.COURIER);
		courier.setEntityID(generateID());
		courier.setOrders(new ArrayList<Integer>());
		courier.setBlocked(false);
		courier.setSuspicious(false);
		courier.setDeleted(false);
		courierList.add(courier);
		save();
	}
	
	private static Integer generateID() 
	{
		int id = 0;
		for (Courier courier : courierList) {
			if (courier.getEntityID() > id) {
				id = courier.getEntityID();
			}
		}
			
		return id + 1;
	}
	
	public static boolean checkUsernameAvailability(String username) {
		for (Courier courier : courierList) {
			if (courier.getUsername().equals(username) && !courier.isDeleted()) {
				return false;
			}
		}
			
		return true;
	}
	public static boolean checkUsernameAvailability(String username, int id) {
		for (Courier courier : courierList) {
			if (courier.getUsername().equals(username) && !courier.isDeleted() && courier.getEntityID() != id) {
				return false;
			}
		}
			
		return true;
	}
	
	public static boolean loginCourier(String username, String password) {
		for (Courier courier : courierList) {
			if (courier.getUsername().equals(username) && courier.getPassword().equals(password) && !courier.isDeleted() && !courier.isBlocked()) {
				return true;
			}
		}
			
		return false;
	}

	public static Courier getCourierByUsername(String username) {
		for (Courier courier: courierList) {
			if (courier.getUsername().equals(username) && !courier.isDeleted()) {
				return courier;
			}
		}
			
		return null;
	}
	
	public static ArrayList<Courier> getAll() {
		ArrayList<Courier> couriers = new ArrayList<Courier>();
		for (Courier courier: courierList) {
			if (!courier.isDeleted()) {
				couriers.add(courier);
			}
		}
			
		return couriers;
	}
	
	public static Courier getCourierByID(int id) {
		for (Courier courier: courierList) {
			if (courier.getEntityID() == id && !courier.isDeleted()) {
				return courier;
			}
		}
			
		return null;
	}

	public Courier change(User user) {
		for (Courier courier: getAll()) {
			if(courier.getEntityID() == user.getEntityID()) {
				courier.setFirstName(user.getFirstName());
				courier.setLastName(user.getLastName());
				courier.setGender(user.getGender());
				courier.setPassword(user.getPassword());
				courier.setUsername(user.getUsername());
				courier.setDateOfBirth(user.getDateOfBirth());
				
				save();
				return courier;
			}
		}
		return null;
	}

	public static void delete(int entityID) {
		for (Courier courier: courierList) {
			if (courier.getEntityID() == entityID) {
				courier.setDeleted(true);
				break;
			}
		}
		save();
		
	}

	public static void block(int entityID) {
		for (Courier courier: courierList) {
			if (courier.getEntityID() == entityID) {
				courier.setBlocked(true);
				break;
			}
		}
		save();
		
	}

	public void addOrderToCourier(int orderIDInt, int entityID) {
		for (Courier courier: getAll()) {
			if (courier.getEntityID() == entityID) {
				courier.getOrders().add(orderIDInt);
				break;
			}
		}
		save();
	}
	
	
}
