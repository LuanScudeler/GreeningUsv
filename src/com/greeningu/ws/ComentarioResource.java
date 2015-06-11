package com.greeningu.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.greeningu.bean.Comentario;
import com.greeningu.bean.MensagemPadrao;
import com.greeningu.dao.ComentarioDAO;

@Path("comentario")
public class ComentarioResource {

	@POST
	@Path("/inserirComentario")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String salvarPostagem(String json){
		
		Comentario comentario = new Gson().fromJson(json, Comentario.class);
		ComentarioDAO dao = new ComentarioDAO();
		
		if(dao.salvarComentario(comentario) == 1){
			MensagemPadrao mp = new MensagemPadrao();
			mp.setStatus("OK");
			mp.setInfo("Comentario salvo com sucesso.");
			return new Gson().toJson(mp);
		} else {
			MensagemPadrao mp = new MensagemPadrao();
			mp.setStatus("ERRO");
			mp.setInfo("Falha ao inserir comentario");
			return new Gson().toJson(mp);
		}
		
	}
}
