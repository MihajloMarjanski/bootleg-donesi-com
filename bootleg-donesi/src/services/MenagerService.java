package services;

import java.util.ArrayList;

import model.Menager;
import model.Role;

public class MenagerService {
	
	public static ArrayList<Menager> menagerList = new ArrayList<Menager>();
	
	private static void save() {
		
	}
	
	public static void load() {
		
	}
	
	public static void addMenager(Menager menager) {
		
		menager.setRole(Role.MENAGER);
		menager.setEntityID(generateID());
		menager.setRestoran(0);
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
	
	public static boolean loginMenager(String username, String password) {
		for (Menager menager : menagerList) {
			if (menager.getUsername().equals(username) && menager.getPassword().equals(password) && !menager.isDeleted()) {
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
	
}
