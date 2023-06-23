/* author: sofifregona */ 

package com.tsti.servicios;

import java.util.List;
import java.util.Optional;

import com.tsti.entidades.Vuelo;

import java.time.*;


public interface VueloService {
	
	/**
	 * Inserta un nuevo vuelo
	 * @param Vuelo v
	 * @throws Exception
	 */
	public void insert(Vuelo v) throws Exception;
	
	/**
	 * Actualiza la fecha y hora de un vuelo
	 * @param Vuelo v
	 */
	public void update(Vuelo v);
	
	/**
	 * Elimina un vuelo
	 * @param Long nroVuelo
	 */
	public void delete(Long nroVuelo);

	/**
	 * Devuelve la lista completa de vuelos
	 * @return List<Vuelo>
	 */
	public List<Vuelo> getAll();

	/**
	 * Obtiene los datos de un vuelo a partir del nro de vuelo
	 * @param nroVuelo
	 * @return Vuelo
	 */
	public Vuelo getByNroVuelo(Long nroVuelo);


}