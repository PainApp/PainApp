package org.pain.app.controller;

//Run on a tomcat server
//Go to http://localhost:8081/PainAppDatabaseAccess/rest/syndromes/

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.pain.app.bean.Syndrome;
import org.pain.app.service.SyndromeService;

@Path("/syndromes")
public class SyndromeController {

	SyndromeService syndromeService = new SyndromeService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List getSyndromes() {

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