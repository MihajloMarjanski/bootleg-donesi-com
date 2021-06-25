package model;

import java.util.Date;

public class Menager extends User{

	protected Integer restoran;

	public Menager(int entityID, String username, String password, String firtName, String lastName, Gender gender,
			String dateOfBirth, Role role, Integer restoran) {
		super(entityID, username, password, firtName, lastName, gender, dateOfBirth, role);
		this.restoran = restoran;
	}

	public Integer getRestoran() {
		return restoran;
	}

	public void setRestoran(Integer restoran) {
		this.restoran = restoran;
	}
	
	
	
	
}
