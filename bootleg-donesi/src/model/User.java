package model;

import java.util.Date;

public class User extends Entity{
	
	protected String username;
	protected String password;
	protected String firtName;
	protected String lastName;
	protected Gender gender;
	protected Date dateOfBirth;
	protected Role role;
	
	
	
	public User(int entityID, String username, String password, String firtName, String lastName, Gender gender,
			Date dateOfBirth, Role role) {
		super(entityID);
		this.username = username;
		this.password = password;
		this.firtName = firtName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
	}
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirtName() {
		return firtName;
	}
	public void setFirtName(String firtName) {
		this.firtName = firtName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}	
	
}
