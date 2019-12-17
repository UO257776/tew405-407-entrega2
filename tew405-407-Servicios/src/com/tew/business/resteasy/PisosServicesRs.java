package com.tew.business.resteasy;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.tew.business.PisosService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Piso;

@Path("/PisosServicesRs")
public interface PisosServicesRs extends PisosService{
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Piso> getPisos() throws Exception;
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Piso findById(@PathParam("id") Long id) throws EntityNotFoundException;
	
	@DELETE
	@Path("{id}")
	void deletePiso(@PathParam("id") Long id) throws EntityNotFoundException;
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void savePiso(Piso piso) throws EntityAlreadyExistsException;

	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void updatePiso(Piso piso) throws EntityNotFoundException;

}
