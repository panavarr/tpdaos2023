/* author: sofifregona */

package com.tsti.persistence.repository;

import com.tsti.persistence.entity.Asiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tsti.persistence.entity.Vuelo;

import java.time.LocalDateTime;
import java.util.List;

public interface VueloDAO extends JpaRepository<Vuelo, Long>{

	@Query("SELECT v FROM Vuelo v WHERE v.nroVuelo like ?1")
	Vuelo findByNroVuelo(Long nroVuelo);

	// Vuelo findByDestino(String destino);

	// Vuelo findByIdAndStartDateAfter(Long nroVuelo, LocalDateTime startDate);
}