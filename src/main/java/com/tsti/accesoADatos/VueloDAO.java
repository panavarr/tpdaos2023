/* author: sofifregona */

package com.tsti.accesoADatos;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tsti.entidades.Vuelo;

public interface VueloDAO extends JpaRepository<Vuelo, Long>{
	
	@Query("SELECT v FROM Vuelo v WHERE v.nroVuelo like ?1")
	Vuelo findByNroVuelo(Long nroVuelo);
	
	
}