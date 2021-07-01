package model;


public class Menager extends User{

	protected Integer restaurant;
	
	public Menager() {
		restaurant = 0;
	}
	
	public Menager(int entityID, String username, String password, String firtName, String lastName, Gender gender,
			String dateOfBirth, Role role, Integer restoran) {
		super(entityID, username, password, firtName, lastName, gender, dateOfBirth, role);
		this.restaurant = restoran;
	}

	public Integer getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Integer restoran) {
		this.restaurant = restoran;
	}
	
	
	
	
}
