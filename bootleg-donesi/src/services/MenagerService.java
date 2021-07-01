package services;

import java.util.ArrayList;

import com.google.gson.JsonElement;

import model.Admin;
import model.Gender;
import model.Menager;
import model.Role;
import model.User;

public class MenagerService {
	
	public static ArrayList<Menager> menagerList = new ArrayList<Menager>();
	
	private static void save() {
		
	}
	
	public static void load() {
		menagerList.add(new Menager(1, "Pera", "123", "Petar", "Petrovic", Gender.MALE,
				"1999-09-15", Role.MENAGER, 1));
	}
	
	public static void addMenager(Menager menager) {
		
		menager.setRole(Role.MENAGER);
		menager.setEntityID(generateID());
		menager.setRestaurant(0);
		menager.setBlocked(false);
		menager.setSuspicious(false);
		menagerList.add(menager);
		save();
	}
	
	private static Integer generateID() 
	{
		int id = 0;
		for (Menager menager : menagerList) {
			if (menager.getEntityID() > id) {
				id = menager.getEntityID();
			}
		}
			
		return id + 1;
	}
	
	public static boolean checkUsernameAvailability(String username) {
		for (Menager menager : menagerList) {
			if (menager.getUsername().equals(username) && !menager.isDeleted()) {
				return false;
			}
		}
			
		return true;
	}
	
	public static boolean checkUsernameAvailability(String username, int id) {
		for (Menager menager : menagerList) {
			if (menager.getUsername().equals(username) && !menager.isDeleted() && menager.getEntityID() != id) {
				return false;
			}
		}
			
		return true;
	}
	
	public static boolean loginMenager(String username, String password) {
		for (Menager menager : menagerList) {
			if (menager.getUsername().equals(username) && menager.getPassword().equals(password) && !menager.isDeleted() && !menager.isBlocked()) {
				return true;
			}
		}
			
		return false;
	}

	public static Menager getMenagerByUsername(String username) {
		for (Menager menager: menagerList) {
			if (menager.getUsername().equals(username) && !menager.isDeleted()) {
				return menager;
			}
		}
			
		return null;
	}
	
	public static ArrayList<Menager> getAll() {
		ArrayList<Menager> menagers = new ArrayList<Menager>();
		for (Menager menager: menagerList) {
			if (!menager.isDeleted()) {
				menagers.add(menager);
			}
		}
			
		return menagers;
	}
	
	public static Menager getMenagerByID(int id) {
		for (Menager menager: menagerList) {
			if (menager.getEntityID() == id && !menager.isDeleted()) {
				return menager;
			}
		}
			
		return null;
	}

	public static int getRestaurantID(String username) {
		for (Menager menager: menagerList) {
			if (menager.getUsername().equals(username) && !menager.isDeleted()) {
				return menager.getRestaurant();
			}
		}
			
		return 0;
	}
	
	public static int getRestaurantID(int id) {
		for (Menager menager: menagerList) {
			if (menager.getEntityID() == id && !menager.isDeleted()) {
				return menager.getRestaurant();
			}
		}
			
		return 0;
	}
	
	public Menager change(User user) {
		for (Menager menager: getAll()) {
			if(menager.getEntityID() == user.getEntityID()) {
				menager.setFirstName(user.getFirstName());
				menager.setLastName(user.getLastName());
				menager.setGender(user.getGender());
				menager.setPassword(user.getPassword());
				menager.setUsername(user.getUsername());
				menager.setDateOfBirth(user.getDateOfBirth());
				
				save();
				return menager;
			}
		}
		return null;
	}

	public static void delete(int entityID) {
		for (Menager menager : menagerList) {
			if (menager.getEntityID() == entityID) {
				menager.setDeleted(true);
				break;
			}
		}
		save();
		
	}

	public static void block(int entityID) {
		for (Menager menager : menagerList) {
			if (menager.getEntityID() == entityID) {
				menager.setBlocked(true);
				break;
			}
		}
		save();
		
	}
	
}
