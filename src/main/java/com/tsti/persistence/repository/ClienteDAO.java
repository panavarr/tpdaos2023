package com.tsti.persistence.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tsti.persistence.entity.Cliente;

public interface ClienteDAO extends JpaRepository<Cliente, Long>{
	
	@Query("SELECT p FROM Cliente p WHERE p.nombre like '%?1%'")
	Collection<Cliente> findClientesLike(String parte);
	
	public List<Cliente> findByApellidoOrNombre(String apellido, String nombre);

	public Cliente findByDni(Long dni);
}
