package org.pain.app.bean;

public class SpecificBodyRegion {

	int id;
	String name;
	BodyRegion bodyRegion;
	
	public SpecificBodyRegion(int id, String name, BodyRegion bodyRegion) {
		this.id = id;
		this.name = name;
		this.bodyRegion = bodyRegion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BodyRegion getBodyRegion() {
		return bodyRegion;
	}

	public void setBodyRegion(BodyRegion bodyRegion) {
		this.bodyRegion = bodyRegion;
	}
	
	
	
	
}
