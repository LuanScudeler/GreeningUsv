package com.greeningu.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.greeningu.bean.MensagemPadrao;
import com.greeningu.bean.Voto;
import com.greeningu.dao.VotoDAO;

@Path("/voto")
public class VotoResource {
	
	@POST
	@Path("/votar/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String votar(String voto){
		
		MensagemPadrao mp = new MensagemPadrao();
		
		
		Gson gson = new Gson();
		
		Voto objVoto = gson.fromJson(voto, Voto.class);
		
		VotoDAO dao = new VotoDAO();
		
		int result = dao.salvar(objVoto);
		
		if(result == 0){
			mp.setStatus("Falha ao realizar voto");
		} else {
			mp.setStatus("Voto realizado com sucesso!");
		}
		
		return gson.toJson(mp);
		
	}

}
