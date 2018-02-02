package org.pain.app.bean;

public class Syndrome {

	int id;
	String name;
	//String description;
	//double likelihood;
	
	public Syndrome() {
		
	}
	
	public Syndrome(int id, String name) {
		this.id = id;
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