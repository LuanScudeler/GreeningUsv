package com.greeningu.ws;

import java.util.ArrayList;

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
import com.greeningu.dao.ComunidadeDAO;
import com.greeningu.dao.UsuarioDAO;
import com.greeningu.log.Log;

@Path("usuario")
public class UsuarioResource {

	private static final String NOME_CLASSE = "UsuarioResource";
	private static final String METODO_INSERIR_USUARIO = "inserirUsuario";
	private static final String METODO_GET_USUARIO = "getUsuario";
	private static final String METODO_LOGIN = "login()";

	@POST
	@Path("/inserir")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String inserirUsuario(String usuario){

		MensagemPadrao mp = new MensagemPadrao();

		Gson gson = new Gson();

		try{

			UsuarioDAO dao = new UsuarioDAO();

			Usuario usr = gson.fromJson(usuario, Usuario.class);

			int status = dao.salvar(usr);

			if(status == 1){
				mp.setStatus("Usu�rio cadastrado com sucesso.");
			}else{
				mp.setStatus("Falha ao cadastrar usu�rio.");
			}



		}catch(Exception e){

			Log.erro(NOME_CLASSE, METODO_INSERIR_USUARIO, e);

			mp.setStatus("Falha ao castrar usu�rio.");

		} finally{

			return gson.toJson(mp);

		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsuario(@PathParam("id") Integer id){
		MensagemPadrao mp = new MensagemPadrao();
		
		Usuario usuario = null;
		
		UsuarioDAO dao = new UsuarioDAO();
		
		try{
			usuario = (Usuario)dao.buscar(id);
			
			if(usuario != null){
				mp.setStatus("OK");
				mp.setInfo(new Gson().toJson(usuario));
			}else{
				mp.setStatus("Usu�rio n�o encontrado");
			}
			
		}catch(Exception e){
			Log.erro(NOME_CLASSE, METODO_GET_USUARIO, e);
			mp.setStatus("Falha ao buscar usu�rio");
		}
		
		return new Gson().toJson(mp);
	}
	
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarUsuarios(){
		UsuarioDAO dao = new UsuarioDAO();
		
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) dao.listar();
		
		String lista = new Gson().toJson(usuarios);
		
		return lista;
	}

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String login(String login){

		MensagemPadrao mensagem = new MensagemPadrao();

		Gson gson = new Gson();

		try{

			UsuarioDAO dao = new UsuarioDAO();


			Usuario usuario = null;

			UsuarioLogin ulogin = gson.fromJson(login, UsuarioLogin.class);


			usuario = dao.buscarPorLogin(ulogin.getLogin());


			if(ulogin.getLogin().equals(usuario.getLogin()) && ulogin.getSenha().equals(usuario.getSenha())){
				mensagem.setStatus("OK");
				mensagem.setInfo(gson.toJson(usuario));
			} else{
				mensagem.setStatus("NOK");
			}

			Log.sucesso(NOME_CLASSE, METODO_LOGIN);

		}catch(Exception e){
			mensagem.setStatus("FALHA");
			Log.erro(NOME_CLASSE, METODO_LOGIN, e);

		} finally{

			return gson.toJson(mensagem);
		}
	}

	@GET
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String excluirUsuario(@PathParam("id") Integer id){
		return null;
	}

}