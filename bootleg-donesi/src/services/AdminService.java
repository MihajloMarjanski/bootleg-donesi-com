package services;

import java.util.ArrayList;

import com.google.gson.JsonElement;

import model.Admin;
import model.Gender;
import model.Role;
import model.User;

public class AdminService {

	public static ArrayList<Admin> adminList = new ArrayList<Admin>();
	
	private static void save() {
		
	}
	
	public static void load() {
		adminList.add(new Admin(1, "Mihajlo", "123", "Mihajlo", "Marjanski", Gender.MALE,
			"1999-09-15", Role.ADMINISTRATOR));
		adminList.add(new Admin(2, "Andrija", "123", "Andrija", "Bosnjakovic", Gender.MALE,
				"1999-09-15", Role.ADMINISTRATOR));
	}
	
	/*private static Integer generateID() 
	{
		int id = 0;
		for (Admin admin : adminList) {
			if (admin.getEntityID() > id) {
				id = admin.getEntityID();
			}
		}
			
		return id + 1;
	}*/
	
	public static boolean checkUsernameAvailability(String username) {
		for (Admin admin : adminList) {
			if (admin.getUsername().equals(username) && !admin.isDeleted()) {
				return false;
			}
		}
			
		return true;
	}
	
	public static boolean checkUsernameAvailability(String username, int id) {
		for (Admin admin : adminList) {
			if (admin.getUsername().equals(username) && !admin.isDeleted() && admin.getEntityID() != id) {
				return false;
			}
		}
			
		return true;
	}
	
	public static boolean loginAdmin(String username, String password) {
		for (Admin admin : adminList) {
			if (admin.getUsername().equals(username) && admin.getPassword().equals(password) && !admin.isDeleted()) {
				return true;
			}
		}
			
		return false;
	}
	
	public static Admin getAdminByID(int id) {
		for (Admin admin: adminList) {
			if (admin.getEntityID() == id && !admin.isDeleted()) {
				return admin;
			}
		}
			
		return null;
	}
	
	public static Admin getAdminByUsername(String username) {
		for (Admin admin: adminList) {
			if (admin.getUsername().equals(username) && !admin.isDeleted()) {
				return admin;
			}
		}
			
		return null;
	}
	
	public static ArrayList<Admin> getAll() {
		ArrayList<Admin> admins = new ArrayList<Admin>();
		for (Admin admin: adminList) {
			if (!admin.isDeleted()) {
				admins.add(admin);
			}
		}
			
		return admins;
	}

	public static Admin change(User user) {
		for (Admin admin : getAll()) {
			if(admin.getEntityID() == user.getEntityID()) {
				admin.setFirstName(user.getFirstName());
				admin.setLastName(user.getLastName());
				admin.setGender(user.getGender());
				admin.setPassword(user.getPassword());
				admin.setUsername(user.getUsername());
				admin.setDateOfBirth(user.getDateOfBirth());
				
				save();
				return admin;
			}
		}
		return null;
	}
}
