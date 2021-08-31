package com.chat.bot.pao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.bot.pao.agents.util.AgentFactory;
import com.chat.bot.pao.model.HistorialBusqueda;
import com.chat.bot.pao.model.Libro;
import com.chat.bot.pao.model.dto.LibroDTO;
import com.chat.bot.pao.repository.LibroRepository;
import com.chat.bot.pao.service.LibroService;

import akka.actor.ActorRef;

@Service
public class LibroServiceImpl implements LibroService {

	@Autowired 
	private LibroRepository libroRepository; 
	
	private static final long TIEMPO_BUSQUEDA = 3000;
	
	@Override
	public List<Libro> listarLibros() {
		return (List<Libro>) libroRepository.findAll();
	}

	@Override
	public LibroDTO obtenerLibrosByNombre(String nombreLibro) {
		LibroDTO libroDto = new LibroDTO();
		libroDto.setNombreLibro(nombreLibro);
		AgentFactory.recomendador.tell(libroDto, ActorRef.noSender());
		try {
			crearEspada();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return libroDto;
	}
	
	public void crearEspada() throws InterruptedException {
		Thread.sleep(TIEMPO_BUSQUEDA);
	}

	@Override
	public List<HistorialBusqueda> obtenerListadoHistorico() {
		return libroRepository.obtenerListadoHistorico();
	}

	@Override
	public List<HistorialBusqueda> obtenerListadoRecomendados() {
		return libroRepository.obtenerListadoRecomendados();
	}


}
