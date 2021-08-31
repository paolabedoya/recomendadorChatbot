package com.chat.bot.pao.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.util.ObjectUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HISTORIAL_BUSQUEDAS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialBusqueda implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO_HISTORIAL")
	private Integer codigoHistorial;
	
	@Column
	private String nombreLibro;
	
	@Column
	private String autor;
	
	@Column
	private String genero;
	
	@Column
	private LocalDateTime fechaHoraConsulta;
	
	@Column
	private String foto;
	
	@Transient
	private String fechaHoraConsultaTemp;
	/**
	 * 
	 */

	private static final long serialVersionUID = -2707512104550438728L;
	public static final Locale locale = new Locale("es", "CO");
	
	private String localDateTimeToString(String formato, LocalDateTime fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato, locale);
		return fecha.format(formatter);
	}
	
	@PostLoad
	@PostUpdate
	@PostPersist
	private void init() {				
		if(!ObjectUtils.isEmpty(fechaHoraConsulta)) {
			fechaHoraConsultaTemp = localDateTimeToString("dd/MM/yyyy hh:mm:ss a", fechaHoraConsulta);
		}
	}


}
