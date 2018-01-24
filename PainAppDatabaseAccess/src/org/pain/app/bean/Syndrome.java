package org.pain.app.bean;

public class Syndrome {

	int id;
	String bodyRegion;
	String sBodyRegion;
	String name;
	String description;
	double likelihood;
	
	public Syndrome(int id, String bodyRegion, String sBodyRegion, String name) {
		this.id = id;
		this.bodyRegion = bodyRegion;
		this.sBodyRegion = sBodyRegion;
		this.name = name;
	}
	
	public Syndrome(int id, String bodyRegion, String sBodyRegion, String name, String description, double likelihood) {
		this.id = id;
		this.bodyRegion = bodyRegion;
		this.sBodyRegion = sBodyRegion;
		this.name = name;
		this.description = description;
		this.likelihood = likelihood;
	}



	public String getBodyRegion() {
		return bodyRegion;
	}

	public void setBodyRegion(String bodyRegion) {
		this.bodyRegion = bodyRegion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getsBodyRegion() {
		return sBodyRegion;
	}

	public void setsBodyRegion(String sBodyRegion) {
		this.sBodyRegion = sBodyRegion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
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
	}

	

}