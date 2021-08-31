package com.chat.bot.pao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.chat.bot.pao.model.Libro;
import com.chat.bot.pao.repository.customized.CustomizedLibroRepository;

@Repository
public interface LibroRepository extends PagingAndSortingRepository<Libro, Integer>, CustomizedLibroRepository {

	
	@Query(value = "Select * from Libros where nombre_libro = ?1", nativeQuery = true)
	List<Libro> findByNombreLibro(String nombreLibro);
	
}
