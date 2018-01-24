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
			BodyRegion hip = new BodyRegion(1, "Hip");
			BodyRegion forearm = new BodyRegion(2, "Forearm");
			BodyRegion foot = new BodyRegion(3, "Foot");
			
			SpecificBodyRegion medial = new SpecificBodyRegion(1, "Medial", hip);
			SpecificBodyRegion posterior = new SpecificBodyRegion(2, "Posterior", hip);
			SpecificBodyRegion medial2 = new SpecificBodyRegion(3, "Medial", forearm);
			SpecificBodyRegion lateral = new SpecificBodyRegion(4, "Lateral", foot);
			
					
			Syndrome ovarianCyst = new Syndrome(1, "Ovarian Cyst", hip, medial);
			Syndrome endometriosis = new Syndrome(2, "Endometriosis", hip, medial);
			Syndrome sacralStressFX = new Syndrome(3, "Sacral Stress FX", hip, posterior);
			Syndrome ulnarBursitis = new Syndrome(4, "Ulnar Bursitis", forearm, medial2);
			Syndrome highAnkleSprain = new Syndrome(5, "High Ankle Sprain", foot, lateral);

			syndromeIdMap.put(1, ovarianCyst);
			syndromeIdMap.put(2, endometriosis);
			syndromeIdMap.put(3, sacralStressFX);
			syndromeIdMap.put(4, ulnarBursitis);
			syndromeIdMap.put(5, highAnkleSprain);
		}
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