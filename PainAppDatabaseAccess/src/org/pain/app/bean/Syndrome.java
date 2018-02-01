package org.pain.app.bean;

public class Syndrome {

	int id;
	BodyRegion bodyRegion;
	SpecificBodyRegion sBodyRegion;
	String name;
	//String description;
	//double likelihood;
	
	public Syndrome() {
		
	}
	
	public Syndrome(int id, String name, SpecificBodyRegion sBodyRegion) {
		this.id = id;
		this.bodyRegion = sBodyRegion.getBodyRegion();
		this.sBodyRegion = sBodyRegion;
		this.name = name;
	}
	
	public Syndrome(int id, String name, BodyRegion bodyRegion, SpecificBodyRegion sBodyRegion) {
		this.id = id;
		this.bodyRegion = bodyRegion;
		this.sBodyRegion = sBodyRegion;
		this.name = name;
	}
	
/*	public Syndrome(int id, BodyRegion bodyRegion, SpecificBodyRegion sBodyRegion, String name, String description, double likelihood) {
		this.id = id;
		this.bodyRegion = bodyRegion;
		this.sBodyRegion = sBodyRegion;
		this.name = name;
		this.description = description;
		this.likelihood = likelihood;
	}*/



	public BodyRegion getBodyRegion() {
		return bodyRegion;
	}

	public void setBodyRegion(BodyRegion bodyRegion) {
		this.bodyRegion = bodyRegion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SpecificBodyRegion getsBodyRegion() {
		return sBodyRegion;
	}

	public void setsBodyRegion(SpecificBodyRegion sBodyRegion) {
		this.sBodyRegion = sBodyRegion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

/*	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getLikelihood() {
		return likelihood;
	}

	public void setLikelihood(double likelihood) {
		this.likelihood = likelihood;
	}*/

	

}