package com.greeningu.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity(name = "postagem")
public class Postagem implements Serializable{

	private static final long serialVersionUID = -8505471156320246074L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_postagem")
	private Integer idPostagem;
	
	@OneToMany(mappedBy = "postagem")
	private List<Voto> votos;
	
	@OneToMany(mappedBy = "postagem")
	private List<Comentario> comentarios;
	
	@Column
	private String titulo;
	
	@Column
	private String descricao;
	
	@Lob
	@Column
	private byte[] imagem;
	
	
	public Postagem(){}

	public Integer getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(Integer idPostagem) {
		this.idPostagem = idPostagem;
	}

	public List<Voto> getVotos() {
		return votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Postagem(Integer id, String titulo, String descricao, byte[] imagem) {
		super();
		this.idPostagem = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.imagem = imagem;
	}

	public Integer getId() {
		return idPostagem;
	}

	public void setId(Integer id) {
		this.idPostagem = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((idPostagem == null) ? 0 : idPostagem.hashCode());
		result = prime * result + Arrays.hashCode(imagem);
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Postagem other = (Postagem) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idPostagem == null) {
			if (other.idPostagem != null)
				return false;
		} else if (!idPostagem.equals(other.idPostagem))
			return false;
		if (!Arrays.equals(imagem, other.imagem))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	
	

}
