package model;

public class Entity {
	
	protected boolean deleted;
	protected Integer entityID;
	
	public Entity(int entityID) {
		super();
		this.deleted = false;
		this.entityID = entityID;
	}
	
	public Entity() {
		super();
		this.deleted = false;
		this.entityID = 0;
	}
	
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public int getEntityID() {
		return entityID;
	}
	public void setEntityID(int entityID) {
		this.entityID = entityID;
	}
	
	
}
