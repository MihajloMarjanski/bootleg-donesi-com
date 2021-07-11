package services;

import java.io.File;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import com.google.gson.Gson;

import model.Menager;
import model.MenuItem;
import model.MenuItemType;
import model.QuantityType;


public class MenuItemService {
	public static ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
	
	private static void save() {
		try {
		    Gson gson = new Gson();

		    Writer writer = Files.newBufferedWriter(Paths.get("data"+File.separator+"menuitems.json"));

		    gson.toJson(menuItemList, writer);

		    writer.close();

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}
	
	public static void load() {
		
		try {
		    Gson gson = new Gson();

		    Reader reader = Files.newBufferedReader(Paths.get("data"+File.separator+"menuitems.json"));

		    MenuItem[] menuItems = gson.fromJson(reader, MenuItem[].class);
		    Collections.addAll(menuItemList, menuItems);
		    
		    reader.close();

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}
	
	public static Integer generateID() 
	{
		int id = 0;
		for (MenuItem menuItem : menuItemList) {
			if (menuItem.getEntityID() > id) {
				id = menuItem.getEntityID();
			}
		}
			
		return id + 1;
	}
	

	
	public static ArrayList<MenuItem> getAll() {
		ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
		for (MenuItem menuItem: menuItemList) {
			if (!menuItem.isDeleted()) {
				menuItems.add(menuItem);
			}
		}
	
			
		return menuItems;
	}
	

	public static ArrayList<MenuItem> getAllForRestaurant(int id) {
		ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();	
		
		for (MenuItem menuItem : getAll()) {
			if(menuItem.getRestaurant() == id) {
				menuItems.add(menuItem);
			}
		}
		
		return menuItems;
		
	}

	public static void delete(int entityID) {
		for (MenuItem menuItem : menuItemList) {
			if (menuItem.getEntityID() == entityID) {
				menuItem.setDeleted(true);
				break;
			}
		}
		save();
		
	}

	public static boolean checkNameAvailability(MenuItem item) {
		for (MenuItem menuItem : getAll()) {
			if (menuItem.getRestaurant() == item.getRestaurant() && menuItem.getName().equals(item.getName())){
				return false;
			}
		}
		return true;
	}

	public static boolean checkNameAvailability(MenuItem item, int id) {
		for (MenuItem menuItem : getAll()) {
			if (menuItem.getRestaurant() == item.getRestaurant() && menuItem.getName().equals(item.getName()) && menuItem.getEntityID() != id){
				return false;
			}
		}
		return true;
	}
	
	
	public static void add(MenuItem item) {
		item.setDeleted(false);
		item.setPicturePath("menuPictures"+File.separator+ "MEN" + generateID().toString() + ".png");
		item.setEntityID(generateID());
		item.setCount(0);
		menuItemList.add(item);
		save();
		
	}

	public static void change(MenuItem item) {
		for (MenuItem menuItem : getAll()) {
			if (menuItem.getEntityID() == item.getEntityID()){
				menuItem.setDescription(item.getDescription());
				menuItem.setName(item.getName());
				menuItem.setPrice(item.getPrice());
				menuItem.setQuantity(item.getQuantity());
				menuItem.setQuantityType(item.getQuantityType());
				menuItem.setType(item.getType());
				break;
			}
		}
	}

	public static void deleteForRestaurant(int entityID) {
		for (MenuItem menuItem : getAll()) {
			if(menuItem.getRestaurant() == entityID) {
				menuItem.setDeleted(true);
			}
		}
		save();
		
	}
	
}
