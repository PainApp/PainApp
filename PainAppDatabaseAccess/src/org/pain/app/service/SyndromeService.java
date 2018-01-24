package org.pain.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.pain.app.bean.Syndrome;

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
			Syndrome ovarianCyst = new Syndrome(1, "Hip", "Medial", "Ovarian Cyst");
			Syndrome endometriosis = new Syndrome(2, "Hip","Medial","Endometriosis");
			Syndrome sacralStressFX = new Syndrome(3, "Hip", "Posterior", "Sacral Stress FX");
			Syndrome ulnarBursitis = new Syndrome(4, "Forearm", "Medial", "Ulnar Bursitis");
			Syndrome highAnkleSprain = new Syndrome(5, "Foot", "Lateral", "High Ankle Sprain");

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