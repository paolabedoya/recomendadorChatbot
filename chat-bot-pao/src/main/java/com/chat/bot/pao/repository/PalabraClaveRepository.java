package com.chat.bot.pao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.chat.bot.pao.model.PalabrasClave;

@Repository
public interface PalabraClaveRepository extends PagingAndSortingRepository<PalabrasClave, Integer> {

	@Query(value = "Select * from palabrasclave where nombreclave like ?1", nativeQuery = true)
	public List<PalabrasClave> findByPalabra(String palabra);
}
