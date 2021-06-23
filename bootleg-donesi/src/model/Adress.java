package model;

public class Adress {
	protected String street;
	protected String town;
	protected String postalCode;
	
	public Adress(String street, String town, String postalCode) {
		super();
		this.street = street;
		this.town = town;
		this.postalCode = postalCode;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	
}
