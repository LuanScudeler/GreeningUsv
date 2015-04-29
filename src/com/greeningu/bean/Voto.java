package com.greeningu.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "voto")
public class Voto implements Serializable{
	
	private static final long serialVersionUID = -7758872134617860817L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_voto")
	private Integer idVoto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_postagem")
	private Postagem postagem;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date data;
	
	@Column
	private Integer pontos;

	public Integer getIdVoto() {
		return idVoto;
	}

	public void setIdVoto(Integer idVoto) {
		this.idVoto = idVoto;
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
