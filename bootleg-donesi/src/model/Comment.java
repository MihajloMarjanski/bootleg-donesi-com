package model;

public class Comment extends Entity{
	
	protected Integer restaurant;
	protected Integer customer;
	protected Integer order;
	protected Integer rating;
	protected String text;
	protected String username;
	protected boolean approved;
	
	
	
	public Comment(int entityID, Integer restaurant, Integer order, Integer customer, Integer rating, String text, boolean approved, String username) {
		super(entityID);
		this.restaurant = restaurant;
		this.customer = customer;
		this.rating = rating;
		this.text = text;
		this.order = order;
		this.approved = approved;
		this.username = username;
	}
	
	public Comment() {
	}
	
	public boolean isAproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	
	public Integer getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Integer restaurant) {
		this.restaurant = restaurant;
	}
	public Integer getCustomer() {
		return customer;
	}
	public void setCustomer(Integer customer) {
		this.customer = customer;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Comment [restaurant=" + restaurant + ", customer=" + customer + ", order=" + order + ", rating="
				+ rating + ", text=" + text + ", approved=" + approved + "]";
	}
	
	
	
}
