package com.tew.business.resteasy;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.tew.business.AgentesService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Agente;

@Path("/AgentesServicesRs")
public interface AgentesServicesRs extends AgentesService{
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Agente> getAgentes() throws Exception;
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Agente findById(@PathParam("id") Long id) throws EntityNotFoundException;
	
	@DELETE
	@Path("{id}")
	void deleteAgente(@PathParam("id") Long id) throws EntityNotFoundException;
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void saveAgente(Agente agente) throws EntityAlreadyExistsException;

	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void updateAgente(Agente agente) throws EntityNotFoundException;

}
