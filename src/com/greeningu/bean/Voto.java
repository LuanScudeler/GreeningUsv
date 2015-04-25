package com.greeningu.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "voto")
public class Voto implements Serializable{
	
	private static final long serialVersionUID = -7758872134617860817L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_voto")
	private Integer idVoto;
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date data;
	

}
