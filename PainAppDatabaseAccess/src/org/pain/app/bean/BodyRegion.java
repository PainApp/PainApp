package org.pain.app.bean;

import java.util.ArrayList;
import java.util.List;

public class BodyRegion {

	int id;
	String name;
	List listOfSpecificBodyRegions; 
	
	public BodyRegion() {
		
	}
	
	public BodyRegion(int id, String name, ArrayList listOfSpecificBodyRegions){
		this.id = id;
		this.name = name;
		this.listOfSpecificBodyRegions = new ArrayList<SpecificBodyRegion>();
		for(int i = 0; i < listOfSpecificBodyRegions.size(); i++)
			this.listOfSpecificBodyRegions.add(listOfSpecificBodyRegions.get(i));
	}

	public List getListOfSpecificBodyRegions() {
		return listOfSpecificBodyRegions;
	}

	public void setListOfSpecificBodyRegions(List listOfSpecificBodyRegions) {
		this.listOfSpecificBodyRegions = listOfSpecificBodyRegions;
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
	
}
