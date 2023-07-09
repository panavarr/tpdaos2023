package com.tsti.service;

import java.util.Optional;

import com.tsti.persistence.entity.Ciudad;

public interface CiudadService {
	
	
	/**
	 * Obtiene la lista completa de ciudades
	 * @return
	 */
	public java.util.List<Ciudad> findAll();

	/**
	 * Obtiene una ciudad
	 * @param id identificador de la ciudad requerida
	 * @return 
	 */
	public Optional<Ciudad> getById(Long id);
	
}
