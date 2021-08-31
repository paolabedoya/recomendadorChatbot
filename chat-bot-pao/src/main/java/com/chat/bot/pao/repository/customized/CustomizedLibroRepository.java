package com.chat.bot.pao.repository.customized;

import java.util.List;

import com.chat.bot.pao.model.HistorialBusqueda;
import com.chat.bot.pao.model.Libro;

public interface CustomizedLibroRepository {
	
	public List<Libro> findLibroRecomendado(Libro libro);
	
	public List<Libro> findLibroRecomendado(String nombreLibro);
	
	public List<HistorialBusqueda> obtenerListadoHistorico();
	
	public List<HistorialBusqueda> obtenerListadoRecomendados();
}
