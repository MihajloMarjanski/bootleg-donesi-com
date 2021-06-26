package services;

import java.util.ArrayList;

import model.Courier;
import model.Role;

public class CourierService {
	
	public static ArrayList<Courier> courierList = new ArrayList<Courier>();
	
	private static void save() {
		
	}
	
	public static void load() {
		
	}
	
	public static void addCourier(Courier courier) {
		
		courier.setRole(Role.COURIER);
		courier.setEntityID(generateID());
		courier.setOrders(new ArrayList<Integer>());
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
			if (courier.getUsername().equals(username)) {
				return false;
			}
		}
			
		return true;
	}
	
	public static boolean loginCourier(String username, String password) {
		for (Courier courier : courierList) {
			if (courier.getUsername().equals(username) && courier.getPassword().equals(password)) {
				return true;
			}
		}
			
		return false;
	}

	public static Courier getCourierByUsername(String username) {
		for (Courier courier: courierList) {
			if (courier.getUsername().equals(username)) {
				return courier;
			}
		}
			
		return null;
	}
	
	
}
