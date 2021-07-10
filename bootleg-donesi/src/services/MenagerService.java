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

import model.Admin;
import model.Customer;
import model.Gender;
import model.Menager;
import model.Role;
import model.User;

public class MenagerService {
	
	public static ArrayList<Menager> menagerList = new ArrayList<Menager>();
	
	private static void save() {
		try {
		    Gson gson = new Gson();

		    Writer writer = Files.newBufferedWriter(Paths.get("data"+File.separator+"menagers.json"));

		    gson.toJson(menagerList, writer);

		    writer.close();

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}
	
	public static void load() {
		
		try {
		    Gson gson = new Gson();

		    Reader reader = Files.newBufferedReader(Paths.get("data"+File.separator+"menagers.json"));

		    Menager[] menagers = gson.fromJson(reader, Menager[].class);
		    Collections.addAll(menagerList, menagers);
		    
		    reader.close();

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		
	}
	
	public static void addMenager(Menager menager) {
		
		menager.setRole(Role.MENAGER);
		menager.setEntityID(generateID());
		menager.setRestaurant(0);
		menager.setBlocked(false);
		menager.setSuspicious(false);
		menager.setDeleted(false);
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

	public ArrayList<Menager> getAvailable() {
		ArrayList<Menager> menagers = new ArrayList<Menager>();
		for (Menager menager : getAll()) {
			if(menager.getRestaurant() == 0) {
				menagers.add(menager);
			}
		}
		return menagers;
	}

	public static void addRestaurantToMenager(String username, Integer generateID) {
		for (Menager menager : getAll()) {
			if(menager.getUsername().equals(username)) {
				menager.setRestaurant(generateID);
				break;
			}
		}
		save();
	}

	public static void deleteRestaurant(int entityID) {
		for (Menager menager : menagerList) {
			if (menager.getEntityID() == entityID) {
				menager.setRestaurant(0);
				break;
			}
		}
		save();
		
	}
	
}
