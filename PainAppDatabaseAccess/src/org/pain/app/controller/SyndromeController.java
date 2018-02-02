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
	
	
	//This method returns all the syndromes in json format for specificed sbodyregion
	//Access with URL: http://localhost:8081/PainAppDatabaseAccess/rest/syndromes/sBodyRegion
	//You give it the sbodyregion in json format 
	//For Example:
	//You give:
	//{"id":0,"name":"Medial","listOfSyndromes":[{"id":0,"name":"Ovarian Cyst"},{"id":1,"name":"Endometriosis"}]}
	//It returns all syndromes in the medial hip like this: 
	//[{"id":0,"name":"Ovarian Cyst"},{"id":1,"name":"Endometriosis"}]
	@POST
	@Path("/sBodyRegion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getSyndromesBySpecificBodyRegionId(SpecificBodyRegion sBodyRegion) throws SQLException {
		System.out.println(sBodyRegion.getId());
		List listOfSyndromes = syndromeService.getAllSyndromesInSpecificBodyRegion(sBodyRegion.getId());
		//return listOfSyndromes;
		return Response.ok(listOfSyndromes).build();
	}

	/*
	 * This method returns all specific body regions in a give body region
	 * Access with this URL:  http://localhost:8081/PainAppDatabaseAccess/rest/syndromes/bodyRegion
	 * You give it the bodyregion in json format
	 * For example:
	 * You give: 
	 * {"id":0,"name":"Hip"}
	 * It returns all specific body regions in the hip like this:
	 * [{"id":0,"name":"Medial","listOfSyndromes":[{"id":0,"name":"Ovarian Cyst"},{"id":1,"name":"Endometriosis"}]},{"id":1,"name":"Posterior","listOfSyndromes":[{"id":2,"name":"Sacral Stress FX"}]}]
	 */
	@POST
	@Path("/bodyRegion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getSpecificBodyRegionByBodyRegionId(BodyRegion bodyRegion) throws SQLException {
		List listOfSpecificBodyRegions = syndromeService.getAllSpecificBodyRegionsInBodyRegion(bodyRegion.getId());
		//return listOfSyndromes;
		return Response.ok(listOfSpecificBodyRegions).build();
	}
	

	/*
	 * This method returns all syndrome information as json give a syndrome id
	 * Access with this URL: http://localhost:8081/PainAppDatabaseAccess/rest/syndromes/{id}
	 * You give nothing except the id in the URL
	 * For example if you give this URL: http://localhost:8081/PainAppDatabaseAccess/rest/syndromes/3
	 * You get back this: 
	 * {"id":3,"bodyRegion":{"id":1,"name":"Forearm"},"sBodyRegion":{"id":2,"name":"Medial","bodyRegion":{"id":1,"name":"Forearm"}},"name":"Ulnar Bursitis"}
	 */
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