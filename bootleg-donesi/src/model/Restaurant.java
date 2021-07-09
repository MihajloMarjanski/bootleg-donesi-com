package model;

import java.util.ArrayList;

public class Restaurant extends Entity{
	
	protected String name;
	protected RestaurantType type;
	protected RestaurantStatus status;
	protected Location location;
	protected String logoPath;
	protected Integer rating; 
	protected String username;
	protected ArrayList<Integer> menuItems;
	
	public Restaurant(int entityID, String name, RestaurantType type, RestaurantStatus status, Location location,
			String logoPath, ArrayList<Integer> menuItems, String username) {
		super(entityID);
		this.name = name;
		this.type = type;
		this.status = status;
		this.location = location;
		this.logoPath = logoPath;
		this.menuItems = menuItems;
		this.rating = 0;
		this.username = username;
	}
	
	
	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public Restaurant() {
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RestaurantType getType() {
		return type;
	}
	public void setType(RestaurantType type) {
		this.type = type;
	}
	public RestaurantStatus getStatus() {
		return status;
	}
	public void setStatus(RestaurantStatus status) {
		this.status = status;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	public ArrayList<Integer> getMenuItems() {
		return menuItems;
	}
	public void setMenuItems(ArrayList<Integer> menuItems) {
		this.menuItems = menuItems;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getTownAndCountry() {
		return location.getAdress().getTown() + " " + location.getAdress().getCountry();
	}
	public String getLoc() {
		return location.getAdress().getCountry() + " " +  location.getAdress().getTown() + " " +  location.getAdress().getStreet();
	}
	
	
}
