package org.pain.app.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.pain.app.bean.*;

/*
 * It is just a helper class which should be replaced by database implementation.
 * It is not very well written class, it is just used for demonstration.
 */
public class SyndromeServiceSql {

	Connection conn;
	Statement stmt;
	String sql;
	ResultSet rs;

	public SyndromeServiceSql() throws SQLException {
		super();
		stmt = conn.createStatement();
	}

	public List getAllSyndromesInSpecificBodyRegion(int sBodyRegionId) throws SQLException {
		stmt.executeQuery("SELECT * FROM syndromes S WHERE S.sbody_region_id=" + sBodyRegionId);
		rs = stmt.getResultSet();
		List syndromes = new ArrayList<String>();
		while (rs.next ())
        {
            String nameVal = rs.getString ("name");
            System.out.println ("Name = " + nameVal);
            syndromes.add(nameVal);
        }

		return syndromes;
	}

	public List getAllSyndromes() throws SQLException {
		stmt.executeQuery("SELECT * FROM syndromes");
		rs = stmt.getResultSet();
		List syndromes = new ArrayList<String>();
		while (rs.next ())
        {
            String nameVal = rs.getString ("name");
            System.out.println ("Name = " + nameVal);
            syndromes.add(nameVal);
        }

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