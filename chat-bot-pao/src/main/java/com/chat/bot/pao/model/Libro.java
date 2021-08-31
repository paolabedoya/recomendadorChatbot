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
@Table(name = "LIBROS")
public class Libro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1640131096625622578L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_LIBRO", unique = true, nullable = false)
	private Integer idLibro;

	@Column(name = "NOMBRE_LIBRO", length = 100, nullable = false)
	private String nombreLibro;

	@Column(name = "GENERO", length = 100, nullable = false)
	private String genero;

	@Column(name = "CASA_EDITORIAL", length = 100, nullable = false)
	private String casaEditorial;

	@Column(name = "AUTOR", length = 100, nullable = false)
	private String autor;

	@Column(name = "LIBRERIA", length = 100, nullable = false)
	private String libreria;

	@Column(name = "IDIOMA_LIBRO", length = 100, nullable = false)
	private String idiomaLibro;
	
	@Column(name = "ANIO_PUBLICACION", length = 4, nullable = false)
	private Integer anioPublicacion;
	
	@Column(name = "PRECIO", nullable = false)
	private Integer precio;
	
	@Column(name = "VALORACIONES", nullable = false)
	private Integer valoraciones;
	
	@Column(name = "FECHA_ACTUALIZACION", nullable = false)
	private LocalDateTime fechaActulizacion;
	
	@Column
	private String foto;

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Integer getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Integer idLibro) {
		this.idLibro = idLibro;
	}

	public String getNombreLibro() {
		return nombreLibro;
	}

	public void setNombreLibro(String nombreLibro) {
		this.nombreLibro = nombreLibro;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCasaEditorial() {
		return casaEditorial;
	}

	public void setCasaEditorial(String casaEditorial) {
		this.casaEditorial = casaEditorial;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getLibreria() {
		return libreria;
	}

	public void setLibreria(String libreria) {
		this.libreria = libreria;
	}

	public String getIdiomaLibro() {
		return idiomaLibro;
	}

	public void setIdiomaLibro(String idiomaLibro) {
		this.idiomaLibro = idiomaLibro;
	}

	public Integer getAnioPublicacion() {
		return anioPublicacion;
	}

	public void setAnioPublicacion(Integer anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Integer getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(Integer valoraciones) {
		this.valoraciones = valoraciones;
	}

	public LocalDateTime getFechaActulizacion() {
		return fechaActulizacion;
	}

	public void setFechaActulizacion(LocalDateTime fechaActulizacion) {
		this.fechaActulizacion = fechaActulizacion;
	}

	@Override
	public String toString() {
		return "Libro [idLibro=" + idLibro + ", nombreLibro=" + nombreLibro + ", genero=" + genero + ", casaEditorial="
				+ casaEditorial + ", autor=" + autor + ", libreria=" + libreria + ", idiomaLibro=" + idiomaLibro
				+ ", anioPublicacion=" + anioPublicacion + ", precio=" + precio + ", valoraciones=" + valoraciones
				+ ", fechaActulizacion=" + fechaActulizacion + "]";
	}

}
