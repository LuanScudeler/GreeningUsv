package com.greeningu.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.greeningu.bean.Permissao;
import com.greeningu.bean.Usuario;
import com.greeningu.dao.PermissaoDAO;
import com.greeningu.dao.UsuarioDAO;

@Path("usuario")
public class UsuarioResource {

	@POST
	@Path("/inserir")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String inserirUsuario(String usuario){
		try{
			UsuarioDAO udao = new UsuarioDAO();
			PermissaoDAO pdao = new PermissaoDAO();
			
			Permissao p = pdao.buscaPermissao(1);
			
			System.out.println(p.getTipo());
			
			Usuario usr = new Gson().fromJson(usuario, Usuario.class);
			usr.setPermissao(p);
			udao.salvar(usr);
			System.out.println("cadOk");
			return "{\"status\":\"Usuário(a) cadastrado(a) com sucesso.\"}";
		}catch(Exception e){
			System.err.println(e);
			return "{\"status\":\"Falha ao cadastrar usuário(a).\"}";
		}
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsuario(@PathParam("id") Integer id){
		UsuarioDAO udao = new UsuarioDAO();

		Usuario usuario = udao.buscaUsuario(id);
		
		//System.out.println(usuario.getNome());
		
		Gson gson = new Gson();
		String usr = gson.toJson(usuario, Usuario.class);
		return usr;
	}
	
	@GET
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String excluirUsuario(@PathParam("id") Integer id){
		try{
			UsuarioDAO udao = new UsuarioDAO();
			
			Usuario usuario = udao.buscaUsuario(id); 
			udao.excluir(usuario);
			
			return "ok";//TODO
		}catch(Exception e){
			e.printStackTrace();
			return null;//TODO
		}
	}

}