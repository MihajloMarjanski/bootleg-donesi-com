package services;

import java.util.ArrayList;


import model.MenuItem;
import model.MenuItemType;
import model.QuantityType;


public class MenuItemService {
	public static ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
	
	private static void save() {
		
	}
	
	public static void load() {
		menuItemList.add(new MenuItem(1, "Cola", 100, MenuItemType.DRINK, 1, 300,
				QuantityType.MILLILITERS, "Coca-Cola can", "menuPictures/melenac1.png"));
		menuItemList.add(new MenuItem(2, "English breakfast", 500, MenuItemType.FOOD, 1, 300,
				QuantityType.GRAMS, "Two cooked eggs and bacon strips", "menuPictures/melenac1.png"));
		
	}
	
	private static Integer generateID() 
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
		item.setPicturePath("menuPictures/melenac1.png");
		item.setEntityID(generateID());
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
	
}
