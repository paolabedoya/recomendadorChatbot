package com.chat.bot.pao.service;

import java.util.List;

import com.chat.bot.pao.model.HistorialBusqueda;
import com.chat.bot.pao.model.Libro;
import com.chat.bot.pao.model.dto.LibroDTO;

public interface LibroService {

	List<Libro> listarLibros();
	
	LibroDTO obtenerLibrosByNombre(String nombreLibro);
	
	public List<HistorialBusqueda> obtenerListadoHistorico();
	
	public List<HistorialBusqueda> obtenerListadoRecomendados();
	
}
