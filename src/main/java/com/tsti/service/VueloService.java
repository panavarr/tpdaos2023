/* author: sofifregona */ 

package com.tsti.service;

import java.util.List;

import com.tsti.persistence.entity.Vuelo;


public interface VueloService {
	
	/**
	 * Inserta un nuevo vuelo
	 * @param v Vuelo
	 * @throws Exception excepción
	 */
	public void insert(Vuelo v) throws Exception;
	
	/**
	 * Actualiza la fecha y hora de un vuelo
	 * @param v Vuelo
	 */
	public void update(Vuelo v);
	
	/**
	 * Elimina un vuelo
	 * @param nroVuelo Número de vuelo
	 */
	public void delete(Long nroVuelo);

	/**
	 * Devuelve la lista completa de vuelos
	 * @return List<Vuelo>
	 */
	public List<Vuelo> getAll();

	/**
	 * Obtiene los datos de un vuelo a partir del nro de vuelo
	 * @param nroVuelo Número de vuelo
	 * @return Vuelo
	 */
	public Vuelo getByNroVuelo(Long nroVuelo);


}