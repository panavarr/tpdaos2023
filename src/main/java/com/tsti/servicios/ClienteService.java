package com.tsti.servicios;

import java.util.List;
import java.util.Optional;

import com.tsti.entidades.Cliente;


public interface ClienteService {
	
	/**
	 * Devuelve la lista completa de clientes
	 * @return Lista de clientes
	 */
	public List<Cliente> getAll();

	/**
	 * Obtiene una persona a partir de su identidicador
	 * @param id
	 * @return
	 */
	public Optional<Cliente> getById(Long id);

	/**
	 * Actualiza datos de una persona
	 * @param p
	 */
	public void update(Cliente p);

	/**
	 * Inserta una nueva persona
	 * @param p
	 * @throws Exception
	 */
	public void insert(Cliente p) throws Exception;

	/**
	 * Elimina una persona del sistema
	 * @param id dni de la persona a eliminar
	 */
	public void delete(Long id);

	public List<Cliente> filtrar(String apellido, String nombre);

}
