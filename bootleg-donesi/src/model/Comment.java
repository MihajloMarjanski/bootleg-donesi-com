package model;

public class Comment extends Entity{
	
	protected Integer restaurant;
	protected Integer customer;
	protected Integer rating;
	protected String text;
	protected String username;
	protected CommentStatus approved;
	
	
	
	public Comment(int entityID, Integer restaurant, Integer customer, Integer rating, String text, CommentStatus approved, String username) {
		super(entityID);
		this.restaurant = restaurant;
		this.customer = customer;
		this.rating = rating;
		this.text = text;
		this.approved = approved;
		this.username = username;
	}
	
	public Comment() {
	}
	
	public CommentStatus getAproved() {
		return approved;
	}
	public void setApproved(CommentStatus approved) {
		this.approved = approved;
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
		return "Comment [restaurant=" + restaurant + ", customer=" + customer + ", order=" + ", rating="
				+ rating + ", text=" + text + ", approved=" + approved + "]";
	}
	
	
	
}
