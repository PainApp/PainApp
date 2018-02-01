package org.pain.app.controller;
import java.io.InputStream;
/*
http://ip:port/PainAppDatabaseAccess/rest/syndromes/sBodyRegion
*/
import java.sql.SQLException;

//Run on a tomcat server
//Go to http://localhost:8081/PainAppDatabaseAccess/rest/syndromes/

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.pain.app.bean.*;
import org.pain.app.service.SyndromeService;
import org.pain.app.service.SyndromeServiceSql;

@Path("/syndromes")
public class SyndromeController {

	//SyndromeServiceSql syndromeService = new SyndromeServiceSql();
	SyndromeService syndromeService = new SyndromeService();

	
	//He gives a body region id, and he needs back specific body region ids and name for one 
	//Another he gives a specific body region id, and he needs back syndromes
	//given a syndrome, get all the information back for it
	
	
	@POST
	@Path("/sBodyRegion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getSyndromesBySpecificBodyRegionId(SpecificBodyRegion sBodyRegion) throws SQLException {
		List listOfSyndromes = syndromeService.getAllSyndromesInSpecificBodyRegion(sBodyRegion.getId());
		//return listOfSyndromes;
		return Response.ok(listOfSyndromes).build();
	}


	@POST
	@Path("/bodyRegion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getSyndromesByBodyRegionId(BodyRegion bodyRegion) throws SQLException {
		List listOfSpecificBodyRegions = syndromeService.getAllSpecificBodyRegionsInBodyRegion(bodyRegion.getId());
		//return listOfSyndromes;
		return Response.ok(listOfSpecificBodyRegions).build();
	}
	

	@POST
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSyndromesByBodyRegionId(@PathParam("id") int id) throws SQLException {
		return Response.ok(syndromeService.getSyndrome(id)).build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List getSyndromes() throws SQLException {

		List listOfSyndromes = syndromeService.getAllSyndromes();
		return listOfSyndromes;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Syndrome getSyndromeById(@PathParam("id") int id) {
		return syndromeService.getSyndrome(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Syndrome addSyndrome(Syndrome syndrome) {
		return syndromeService.addSyndrome(syndrome);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Syndrome updateSyndrome(Syndrome syndrome) {
		return syndromeService.updateSyndrome(syndrome);

	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteSyndrome(@PathParam("id") int id) {
		syndromeService.deleteSyndrome(id);

	}

}