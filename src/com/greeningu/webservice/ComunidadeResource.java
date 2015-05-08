package com.greeningu.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("comunidade")
public class ComunidadeResource {
	
	@GET
	@Path("/listarTodas")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarComunidades(){
		//TODO
		return null;
	}

}
