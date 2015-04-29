package com.greeningu.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.greeningu.bean.Usuario;

@Path("usuario")
public class UsuarioResource {

	@POST
	@Path("/inserir")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String inserirUsuario(String usuario){
		System.out.println(usuario);
		Usuario usr = new Gson().fromJson(usuario, Usuario.class);
//		System.err.println(usr.getNome() + " " + usr.getSobrenome() + " " + " " + 
//		usr.getEmail() + " " + usr.getSenha() + " " + usr.getSexo());
		return "{\"status\":\"ok\"}";
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTeste(){
		return "{\"status\":\"ok\"}";
	}
}