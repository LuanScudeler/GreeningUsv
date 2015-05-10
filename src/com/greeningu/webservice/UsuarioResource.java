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
import com.greeningu.bean.MensagemPadrao;
import com.greeningu.bean.Usuario;
import com.greeningu.bean.UsuarioLogin;
import com.greeningu.crud.UsuarioCRUD;

@Path("usuario")
public class UsuarioResource {

	@POST
	@Path("/inserir")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String inserirUsuario(String usuario){
		try{
			
			Gson gson = new Gson();
			
			UsuarioCRUD uc = new UsuarioCRUD();
			
			Usuario usr = gson.fromJson(usuario, Usuario.class);
			
			uc.salvar(usr);
			
			MensagemPadrao mp = new MensagemPadrao();
			
			mp.setStatus("Usuário cadastrado com sucesso.");
			
			return gson.toJson(mp);
			
		}catch(Exception e){
			System.err.println(e);
			
			MensagemPadrao mp = new MensagemPadrao();
			mp.setStatus("Falha ao cadastrar usuário.");
			
			Gson gson = new Gson();
			
			return gson.toJson(mp);
		}
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsuario(@PathParam("id") Integer id){
		MensagemPadrao mp = new MensagemPadrao();
		mp.setStatus("Ok teste.");
		
		Gson gson = new Gson();
		
		return gson.toJson(mp);
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String login(String login){
		Gson gson = new Gson();
		UsuarioCRUD uc = new UsuarioCRUD();
		MensagemPadrao mensagem = new MensagemPadrao();
		
		UsuarioLogin ulogin = gson.fromJson(login, UsuarioLogin.class);
		
		UsuarioLogin cmp = uc.buscaLogin(ulogin.getLogin());
		
		if(ulogin.getLogin().equals(cmp.getLogin()) && ulogin.getSenha().equals(cmp.getSenha())){
			mensagem.setStatus("OK");
			mensagem.setInfo(gson.toJson(cmp));
		}else{
			mensagem.setStatus("NOK");
		}
		
		return gson.toJson(mensagem);
	}
	
	@GET
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String excluirUsuario(@PathParam("id") Integer id){
		return null;
	}

}