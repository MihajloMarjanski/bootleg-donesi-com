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
	
}
