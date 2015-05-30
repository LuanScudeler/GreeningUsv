package com.greeningu.ws;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/testeServidor")
public class TesteServidor {
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String teste(){
		return "ONLINE";
	}
}
