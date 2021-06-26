package model;

public class Admin extends User{

	public Admin() {
		super();
	}

	public Admin(int entityID, String username, String password, String firtName, String lastName, Gender gender,
			String dateOfBirth, Role role) {
		super(entityID, username, password, firtName, lastName, gender, dateOfBirth, role);
	}
	
}
