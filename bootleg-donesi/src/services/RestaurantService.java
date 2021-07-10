package services;

import java.io.File;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import model.Admin;
import model.Adress;
import model.Gender;
import model.Location;
import model.MenuItem;
import model.Order;
import model.Restaurant;
import model.RestaurantStatus;
import model.RestaurantType;
import model.Role;
import model.ShoppingCart;
import model.User;

public class RestaurantService {
	
	public static ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
	
	private static void save() {
		try {
		    Gson gson = new Gson();

		    Writer writer = Files.newBufferedWriter(Paths.get("data"+File.separator+"restaurants.json"));

		    gson.toJson(restaurantList, writer);

		    writer.close();

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}
	
	public static void load() {
		try {
		    Gson gson = new Gson();

		    Reader reader = Files.newBufferedReader(Paths.get("data"+File.separator+"restaurants.json"));

		    Restaurant[] restaurants = gson.fromJson(reader, Restaurant[].class);
		    Collections.addAll(restaurantList, restaurants);
		    
		    reader.close();

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		
		
		/*Location location = new Location(33.33,33.33, new Adress("Srpskih Vladara 6", "Melence", "23270","Vojvodina"));
		restaurantList.add(new Restaurant(1,"FLIPERANA",RestaurantType.ITALIAN,RestaurantStatus.CLOSED,location,"restaurantPictures/melenac1.jpg",new ArrayList<Integer>(),"Pera"));
		restaurantList.add(new Restaurant(2,"BOB",RestaurantType.GRILL,RestaurantStatus.OPEN,location,"restaurantPictures/melenac1.jpg",new ArrayList<Integer>(),"" ));
		restaurantList.add(new Restaurant(3,"N&N",RestaurantType.GREEK,RestaurantStatus.OPEN,location,"restaurantPictures/melenac1.jpg",new ArrayList<Integer>(),"" ));
		*/
	}
	
	public static Integer generateID() 
	{
		int id = 0;
		for (Restaurant restaurant : restaurantList) {
			if (restaurant.getEntityID() > id) {
				id = restaurant.getEntityID();
			}
		}
			
		return id + 1;
	}
	

	
	public static ArrayList<Restaurant> getAll() {
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		for (Restaurant restaurant: restaurantList) {
			if (!restaurant.isDeleted()) {
				restaurants.add(restaurant);
			}
		}
		
		restaurants = (ArrayList<Restaurant>) restaurants.stream()
				  .sorted(Comparator.comparing(Restaurant::getStatus))
				  .collect(Collectors.toList());
			
		return restaurants;
	}
	
	public static Restaurant getById(int id) {
		Restaurant returnRes = new Restaurant();
		for (Restaurant restaurant: restaurantList) {
			if (!restaurant.isDeleted() && restaurant.getEntityID() == id) {
				returnRes = restaurant;
			}
		}
			
		return returnRes;
	}
	
	public static ArrayList<Restaurant> getAllOpen() {
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		for (Restaurant restaurant: restaurantList) {
			if (!restaurant.isDeleted() && restaurant.getStatus() == RestaurantStatus.OPEN) {
				restaurants.add(restaurant);
			}
		}

		return restaurants;
	}

	public static ArrayList<Restaurant> getAllForType(String type, String open) {
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		if(open.equals("OPEN")){
			restaurants = getAllOpen();
		}else {
			restaurants = getAll();
		}
		ArrayList<Restaurant> filteredRestaurants = new ArrayList<Restaurant>();
		
		
		
		for (Restaurant restaurant : restaurants) {
			if(type.equals("")) {
				filteredRestaurants.add(restaurant);
			}
			else if(type.equals("ITALIAN") && restaurant.getType() == RestaurantType.ITALIAN) {
				filteredRestaurants.add(restaurant);
			}
			else if(type.equals("CHINESE") && restaurant.getType() == RestaurantType.CHINESE) {
				filteredRestaurants.add(restaurant);
			}
			else if(type.equals("GRILL") && restaurant.getType() == RestaurantType.GRILL) {
				filteredRestaurants.add(restaurant);
			}
			else if(type.equals("GREEK") && restaurant.getType() == RestaurantType.GREEK) {
				filteredRestaurants.add(restaurant);
			}
			else if(type.equals("MEXICAN") && restaurant.getType() == RestaurantType.MEXICAN) {
				filteredRestaurants.add(restaurant);
			}
			
		}
		
		return filteredRestaurants;
		
	}

	public static void delete(int entityID) {
		for (Restaurant restaurant : restaurantList) {
			if (restaurant.getEntityID() == entityID) {
				restaurant.setDeleted(true);
				break;
			}
		}
		save();
		
	}
	
	public static Restaurant getRestaurantById(int id) {
		Restaurant returnRestaurant = new Restaurant();
		for (Restaurant restaurant: restaurantList) {
			if (!restaurant.isDeleted() && restaurant.getEntityID() == id) {
				returnRestaurant = restaurant;
				break;
			}
		}

		return returnRestaurant;
	}

	public static void updateRating(int restaurantID, int calculateRestaurantRating) {
		for (Restaurant restaurant : getAll()) {
			if (restaurant.getEntityID() == restaurantID) {
				restaurant.setRating(calculateRestaurantRating);
				break;
			}
		}
		save();
		
	}

	public static void addRestaurant(Restaurant restaurant) {
		restaurant.setEntityID(generateID());
		restaurant.setDeleted(false);
		restaurant.setMenuItems(new ArrayList<Integer>());
		restaurant.setLogoPath("restaurantPictures/melenac1.jpg");
		restaurant.setStatus(RestaurantStatus.OPEN);
		
		restaurantList.add(restaurant);
		save();
		
	}

	public static void deleteItem(MenuItem menuItem) {
		for (Restaurant restaurant : getAll()) {
			if(restaurant.getEntityID() == menuItem.getRestaurant()) {
				restaurant.getMenuItems().remove((Object)menuItem.getEntityID());
				break;
			}
		}
		save();
	}

	public static void deleteMenager(String username) {
		for (Restaurant restaurant : getAll()) {
			if(restaurant.getUsername().equals(username)) {
				restaurant.setUsername("");
				break;
			}
		}
		save();
		
	}
}
