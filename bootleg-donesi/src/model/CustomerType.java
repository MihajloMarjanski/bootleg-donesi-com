package model;

public class CustomerType {
	protected String typeName;
	protected double discount;
	protected double requiredPoints;
	
	public CustomerType(){
		
	}
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getRequiredPoints() {
		return requiredPoints;
	}
	public void setRequiredPoints(double requiredPoints) {
		this.requiredPoints = requiredPoints;
	}
	public CustomerType(String typeName, double discount, double requiredPoints) {
		super();
		this.typeName = typeName;
		this.discount = discount;
		this.requiredPoints = requiredPoints;
	}
	
	
}
