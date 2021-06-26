package model;

import java.util.ArrayList;

public class Courier extends User{

	protected ArrayList<Integer> orders;
	
	public Courier() {
		orders = new ArrayList<Integer>();
	}
	public Courier(int entityID, String username, String password, String firtName, String lastName, Gender gender,
			String dateOfBirth, Role role, ArrayList<Integer> orders) {
		super(entityID, username, password, firtName, lastName, gender, dateOfBirth, role);
		this.orders = orders;
	}

	public ArrayList<Integer> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Integer> orders) {
		this.orders = orders;
	}
	
	
	
}
