package com.chat.bot.pao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.chat.bot.pao.model.Libro;
import com.chat.bot.pao.repository.LibroRepository;

@Service
public class BuscadorService {


	@Autowired
	private LibroRepository libroRepository;

	public void crearEspada() throws InterruptedException {

	}

	public List<Libro> obtenerListaLibrosBynombre(String nombreLibro) {
		return libroRepository.findByNombreLibro(nombreLibro);
	}

	public List<Libro> obtenerListaLibrosRecomendados(List<Libro> lstLisbros, String nombreLibro) {
		List<Libro> lstRecomendados = new ArrayList<>();
		if (!ObjectUtils.isEmpty(lstLisbros)) {
			lstLisbros.forEach(libro -> {
				lstRecomendados.addAll(libroRepository.findLibroRecomendado(libro));
			});
		} else {
			lstRecomendados.addAll(libroRepository.findLibroRecomendado("%" + nombreLibro + "%"));
		}
		return lstRecomendados;
	}
}
