package model;


public class MenuItem extends Entity{
	
	protected String name;
	protected double price;
	protected MenuItemType type;
	protected Integer restaurant;
	protected Integer quantity;
	protected QuantityType quantityType;
	protected String description;
	protected String picturePath;
	protected Integer count;
	
	
	
	public MenuItem(int entityID, String name, double price, MenuItemType type, Integer restaurant, Integer quantity,
			QuantityType quantityType, String description, String picturePath,Integer count) {
		super(entityID);
		this.name = name;
		this.price = price;
		this.type = type;
		this.restaurant = restaurant;
		this.quantity = quantity;
		this.quantityType = quantityType;
		this.description = description;
		this.picturePath = picturePath;
		this.count = count;
	}
	
	public MenuItem() {
		
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
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

	@Override
	public String toString() {
		return "MenuItem [name=" + name + ", price=" + price + ", type=" + type + ", restaurant=" + restaurant
				+ ", quantity=" + quantity + ", quantityType=" + quantityType + ", description=" + description
				+ ", picturePath=" + picturePath + ", count=" + count + "]";
	}
	
	
	
}
