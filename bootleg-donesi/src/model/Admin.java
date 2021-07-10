package model;

public class Admin extends User{

	public Admin() {
		super();
	}

	public Admin(int entityID, String username, String password, String firtName, String lastName, Gender gender,
			String dateOfBirth, Role role) {
		super(entityID, username, password, firtName, lastName, gender, dateOfBirth, role);
	}

	@Override
	public String toString() {
		return "Admin [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", role=" + role + ", suspicious="
				+ suspicious + ", blocked=" + blocked + ", deleted=" + deleted + ", entityID=" + entityID
				+ ", toString()=" + super.toString() + ", isSuspicious()=" + isSuspicious() + ", getUsername()="
				+ getUsername() + ", getPassword()=" + getPassword() + ", getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + ", getGender()=" + getGender() + ", getDateOfBirth()="
				+ getDateOfBirth() + ", getRole()=" + getRole() + ", isBlocked()=" + isBlocked() + ", isDeleted()="
				+ isDeleted() + ", getEntityID()=" + getEntityID() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}
	
}
