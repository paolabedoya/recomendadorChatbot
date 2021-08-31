package com.chat.bot.pao.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PALABRASCLAVE")
public class PalabrasClave implements Serializable {

	/**
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PALABRA", unique = true, nullable = false)
	private Integer idPalabra;

	@Column(name = "NOMBRECLAVE", length = 100, nullable = false)
	private String nombreclave;


	public Integer getIdPalabra() {
		return idPalabra;
	}

	public void setIdPalabra(Integer idLibro) {
		this.idPalabra = idLibro;
	}

	public String getNombreClave() {
		return nombreclave;
	}

	public void setNombreClave(String nombrelibro) {
		this.nombreclave = nombreclave;
	}


	@Override
	public String toString() {
		return "palabraclave [idPalabra=" + idPalabra + ", nombreclave=" + nombreclave + "]";
	}

}
