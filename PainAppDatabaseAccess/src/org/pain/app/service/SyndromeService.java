package org.pain.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.pain.app.bean.*;

/*
 * It is just a helper class which should be replaced by database implementation.
 * It is not very well written class, it is just used for demonstration.
 */
public class SyndromeService {

	static HashMap<Integer, Syndrome> syndromeIdMap = getSyndromeIdMap();
	static HashMap<Integer, BodyRegion> bodyRegionIdMap = getBodyRegionIdMap();
	static HashMap<Integer, SpecificBodyRegion> sBodyRegionIdMap = getSBodyRegionIdMap();

	public SyndromeService() {
		super();

		if (syndromeIdMap == null && bodyRegionIdMap == null && sBodyRegionIdMap == null) {
			syndromeIdMap = new HashMap<Integer, Syndrome>();
			bodyRegionIdMap = new HashMap<Integer, BodyRegion>();
			sBodyRegionIdMap = new HashMap<Integer, SpecificBodyRegion>();
			// Creating some objects of Syndrome while initializing
			
					
			Syndrome ovarianCyst = new Syndrome(0, "Ovarian Cyst");
			Syndrome endometriosis = new Syndrome(1, "Endometriosis");
			Syndrome sacralStressFX = new Syndrome(2, "Sacral Stress FX");
			Syndrome ulnarBursitis = new Syndrome(3, "Ulnar Bursitis");
			Syndrome highAnkleSprain = new Syndrome(4, "High Ankle Sprain");

			
			SpecificBodyRegion medial = new SpecificBodyRegion(0, "Medial", new ArrayList<Syndrome>(Arrays.asList(ovarianCyst, endometriosis)));
			SpecificBodyRegion posterior = new SpecificBodyRegion(1, "Posterior", new ArrayList<Syndrome>(Arrays.asList(sacralStressFX)));
			SpecificBodyRegion medial2 = new SpecificBodyRegion(2, "Medial", new ArrayList<Syndrome>(Arrays.asList(ulnarBursitis)));
			SpecificBodyRegion lateral = new SpecificBodyRegion(3, "Lateral", new ArrayList<Syndrome>(Arrays.asList(highAnkleSprain)));

			BodyRegion hip = new BodyRegion(0, "Hip", new ArrayList<SpecificBodyRegion>(Arrays.asList(medial, posterior)));
			BodyRegion forearm = new BodyRegion(1, "Forearm", new ArrayList<SpecificBodyRegion>(Arrays.asList(medial2)));
			BodyRegion foot = new BodyRegion(2, "Foot", new ArrayList<SpecificBodyRegion>(Arrays.asList(lateral)));

			syndromeIdMap.put(0, ovarianCyst);
			syndromeIdMap.put(1, endometriosis);
			syndromeIdMap.put(2, sacralStressFX);
			syndromeIdMap.put(3, ulnarBursitis);
			syndromeIdMap.put(4, highAnkleSprain);
			
			bodyRegionIdMap.put(0, hip);
			bodyRegionIdMap.put(1, forearm);
			bodyRegionIdMap.put(2, foot);
			

			sBodyRegionIdMap.put(0, medial);
			sBodyRegionIdMap.put(1, posterior);
			sBodyRegionIdMap.put(2, medial2);
			sBodyRegionIdMap.put(3, lateral);
			
			
		}
	}
	
	//Get all syndromes in specific body region
	public List getAllSyndromesInSpecificBodyRegion(int sBodyRegionId) {
		
		return sBodyRegionIdMap.get(sBodyRegionId).getListOfSyndromes();
	}
	
	//get all specific body regions in body region id
	public List getAllSpecificBodyRegionsInBodyRegion(int bodyRegionId) {
		
		return bodyRegionIdMap.get(bodyRegionId).getListOfSpecificBodyRegions();
	}
	
	public List getAllSyndromes() {
		List syndromes = new ArrayList(syndromeIdMap.values());
		return syndromes;
	}

	public Syndrome getSyndrome(int id) {
		Syndrome syndrome = syndromeIdMap.get(id);
		return syndrome;
	}

	public Syndrome addSyndrome(Syndrome syndrome) {
		syndrome.setId(syndromeIdMap.size() + 1);
		syndromeIdMap.put(syndrome.getId(), syndrome);
		return syndrome;
	}

	public Syndrome updateSyndrome(Syndrome syndrome) {
		if (syndrome.getId() <= 0)
			return null;
		syndromeIdMap.put(syndrome.getId(), syndrome);
		return syndrome;

	}

	public void deleteSyndrome(int id) {
		syndromeIdMap.remove(id);
	}

	public static HashMap<Integer, Syndrome> getSyndromeIdMap() {
		return syndromeIdMap;
	}
	
	public static HashMap<Integer, BodyRegion> getBodyRegionIdMap() {
		return bodyRegionIdMap;
	}
	
	public static HashMap<Integer, SpecificBodyRegion> getSBodyRegionIdMap() {
		return sBodyRegionIdMap;
	}
}