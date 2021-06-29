package dto;

import java.util.ArrayList;

import model.Comment;
import model.Entity;
import model.Location;
import model.MenuItem;
import model.RestaurantStatus;
import model.RestaurantType;

public class RestaurantDTO extends Entity{
	
	protected String name;
	protected RestaurantType type;
	protected RestaurantStatus status;
	protected Location location;
	protected String logoPath;
	protected Integer rating; 
	protected ArrayList<MenuItem> menuItems;
	protected ArrayList<Comment> comments;
	
	
	public RestaurantDTO() {
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

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(ArrayList<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	public String getTownAndCountry() {
		return location.getAdress().getTown() + " " + location.getAdress().getCountry();
	}
	public String getLoc() {
		return location.getAdress().getCountry() + " " +  location.getAdress().getTown() + " " +  location.getAdress().getStreet();
	}

	@Override
	public String toString() {
		return "RestaurantDTO [name=" + name + ", type=" + type + ", status=" + status + ", location=" + location
				+ ", logoPath=" + logoPath + ", rating=" + rating + ", menuItems=" + menuItems + ", comments="
				+ comments + "]";
	}
	
	
	
	
}
