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
		Location location = new Location(33.33,33.33, new Adress("Srpskih Vladara 6", "Melence", "23270"));
		restaurantList.add(new Restaurant(1,"FLIPERANA",RestaurantType.ITALIAN,RestaurantStatus.CLOSED,location,"restaurantPictures/melenac1.jpg",new ArrayList<Integer>()));
		restaurantList.add(new Restaurant(1,"BOB",RestaurantType.GRILL,RestaurantStatus.OPEN,location,"restaurantPictures/melenac1.jpg",new ArrayList<Integer>()));
		restaurantList.add(new Restaurant(1,"N&N",RestaurantType.GREEK,RestaurantStatus.OPEN,location,"restaurantPictures/melenac1.jpg",new ArrayList<Integer>()));
		
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
}
