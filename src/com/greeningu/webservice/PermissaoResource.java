package com.greeningu.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.greeningu.bean.Permissao;
import com.greeningu.dao.PermissaoDAO;

@Path("permissao")
public class PermissaoResource {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPermissao(@PathParam("id") Integer id){
		PermissaoDAO dao = new PermissaoDAO();
		Gson gson = new Gson();
		
		return gson.toJson(dao.buscaPermissao(id),Permissao.class);
	}
}
