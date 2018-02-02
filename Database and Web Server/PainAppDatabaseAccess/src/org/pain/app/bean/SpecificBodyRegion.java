package org.pain.app.bean;

import java.util.ArrayList;
import java.util.List;

public class SpecificBodyRegion {

	int id;
	String name;
	List listOfSyndromes; 
	
	public SpecificBodyRegion() {
		
	}
	
	public SpecificBodyRegion(int id, String name,  ArrayList listOfSyndromes) {
		this.id = id;
		this.name = name;
		this.listOfSyndromes = new ArrayList<Syndrome>();
		for(int i = 0; i < listOfSyndromes.size(); i++)
			this.listOfSyndromes.add(listOfSyndromes.get(i));
	}

	public List getListOfSyndromes() {
		return listOfSyndromes;
	}

	public void setListOfSyndromes(List listOfSyndromes) {
		this.listOfSyndromes = listOfSyndromes;
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
