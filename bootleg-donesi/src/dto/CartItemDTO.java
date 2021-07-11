package dto;

import model.MenuItem;

public class CartItemDTO {
	protected Integer entityID;
	protected MenuItem menuItem;
	
	public Integer getEntityID() {
		return entityID;
	}
	public void setEntityID(Integer entityID) {
		this.entityID = entityID;
	}
	public MenuItem getMenuItem() {
		return menuItem;
	}
	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}
	public CartItemDTO(Integer entityID, MenuItem menuItem) {
		super();
		this.entityID = entityID;
		this.menuItem = menuItem;
	}
	
	public CartItemDTO() {

	}
	@Override
	public String toString() {
		return "CartItemDTO [entityID=" + entityID + ", menuItem=" + menuItem + "]";
	}
	
}
