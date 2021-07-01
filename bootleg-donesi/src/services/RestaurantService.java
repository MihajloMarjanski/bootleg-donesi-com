package services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import model.Admin;
import model.Adress;
import model.Gender;
import model.Location;
import model.Restaurant;
import model.RestaurantStatus;
import model.RestaurantType;
import model.Role;
import model.User;

public class RestaurantService {
	
	public static ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
	
	private static void save() {
		
	}
	
	public static void load() {
		Location location = new Location(33.33,33.33, new Adress("Srpskih Vladara 6", "Melence", "23270","Vojvodina"));
		restaurantList.add(new Restaurant(1,"FLIPERANA",RestaurantType.ITALIAN,RestaurantStatus.CLOSED,location,"restaurantPictures/melenac1.jpg",new ArrayList<Integer>()));
		restaurantList.add(new Restaurant(2,"BOB",RestaurantType.GRILL,RestaurantStatus.OPEN,location,"restaurantPictures/melenac1.jpg",new ArrayList<Integer>()));
		restaurantList.add(new Restaurant(3,"N&N",RestaurantType.GREEK,RestaurantStatus.OPEN,location,"restaurantPictures/melenac1.jpg",new ArrayList<Integer>()));
		
	}
	
	private static Integer generateID() 
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
}
