package model;


public class MenuItem extends Entity{
	
	protected String name;
	protected double price;
	protected MenuItemType type;
	protected Integer restaurant;
	protected double quantity;
	protected QuantityType quantityType;
	protected String description;
	protected String picturePath;
	
	
	
	public MenuItem(int entityID, String name, double price, MenuItemType type, Integer restaurant, double quantity,
			QuantityType quantityType, String description, String picturePath) {
		super(entityID);
		this.name = name;
		this.price = price;
		this.type = type;
		this.restaurant = restaurant;
		this.quantity = quantity;
		this.quantityType = quantityType;
		this.description = description;
		this.picturePath = picturePath;
	}
	
	public MenuItem() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public MenuItemType getType() {
		return type;
	}
	public void setType(MenuItemType type) {
		this.type = type;
	}
	public Integer getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Integer restaurant) {
		this.restaurant = restaurant;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public QuantityType getQuantityType() {
		return quantityType;
	}
	public void setQuantityType(QuantityType quantityType) {
		this.quantityType = quantityType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
	
	
}
