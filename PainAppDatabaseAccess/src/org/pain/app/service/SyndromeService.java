package org.pain.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.pain.app.bean.*;

/*
 * It is just a helper class which should be replaced by database implementation.
 * It is not very well written class, it is just used for demonstration.
 */
public class SyndromeService {

	static HashMap<Integer, Syndrome> syndromeIdMap = getSyndromeIdMap();

	public SyndromeService() {
		super();

		if (syndromeIdMap == null) {
			syndromeIdMap = new HashMap<Integer, Syndrome>();
			// Creating some objects of Syndrome while initializing
			BodyRegion hip = new BodyRegion(0, "Hip");
			BodyRegion forearm = new BodyRegion(1, "Forearm");
			BodyRegion foot = new BodyRegion(2, "Foot");
			
			SpecificBodyRegion medial = new SpecificBodyRegion(0, "Medial", hip);
			SpecificBodyRegion posterior = new SpecificBodyRegion(1, "Posterior", hip);
			SpecificBodyRegion medial2 = new SpecificBodyRegion(2, "Medial", forearm);
			SpecificBodyRegion lateral = new SpecificBodyRegion(3, "Lateral", foot);
			
					
			Syndrome ovarianCyst = new Syndrome(0, "Ovarian Cyst", hip, medial);
			Syndrome endometriosis = new Syndrome(1, "Endometriosis", hip, medial);
			Syndrome sacralStressFX = new Syndrome(2, "Sacral Stress FX", hip, posterior);
			Syndrome ulnarBursitis = new Syndrome(3, "Ulnar Bursitis", forearm, medial2);
			Syndrome highAnkleSprain = new Syndrome(4, "High Ankle Sprain", foot, lateral);

			syndromeIdMap.put(0, ovarianCyst);
			syndromeIdMap.put(1, endometriosis);
			syndromeIdMap.put(2, sacralStressFX);
			syndromeIdMap.put(3, ulnarBursitis);
			syndromeIdMap.put(4, highAnkleSprain);
		}
	}
	public List getAllSyndromesInBodyRegion(int bodyRegionId) {
		List syndromes = new ArrayList();
		for(int i = 0; i < syndromeIdMap.size(); i++) {
			if(syndromeIdMap.get(i).getBodyRegion().getId() == bodyRegionId) {
				syndromes.add(syndromeIdMap.get(i));
			}
		}
		return syndromes;
	}
	
	
	public List getAllSyndromesInSpecificBodyRegion(int sBodyRegionId) {
		List sBodyRegions = new ArrayList();
		for(int i = 0; i < syndromeIdMap.size(); i++) {
			if(syndromeIdMap.get(i).getsBodyRegion().getId() == sBodyRegionId) {
				sBodyRegions.add(syndromeIdMap.get(i));
			}
		}
		return sBodyRegions;
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
}