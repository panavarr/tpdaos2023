package com.tsti.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsti.persistence.repository.ClienteDAO;
import com.tsti.persistence.entity.Cliente;
import com.tsti.web.controller.exception.Excepcion;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
@Service
public class ClienteServiceImpl implements ClienteService {
	@Autowired
	private  Validator validator;
	
	@Autowired
	private ClienteDAO dao;
	@Override
	public List<Cliente> getAll() {
		return dao.findAll();
	}
	@Override
	public Optional<Cliente> getById(Long id) {
		return  dao.findById(id);
		
	}
	@Override
	public void update(Cliente p) {
		dao.save(p);
	}
	@Override
	public void insert(Cliente p) throws Exception {
		
		Set<ConstraintViolation<Cliente>> cv = validator.validate(p);
		if(cv.size()>0)
		{
			String err="";
			for (ConstraintViolation<Cliente> constraintViolation : cv) {
				err+=constraintViolation.getPropertyPath()+": "+constraintViolation.getMessage()+"\n";
			}
			throw new Excepcion(err,400);
		}
		else if(getById(p.getDni()).isPresent())
		{
			throw new Excepcion("Ya existe una persona con ese dni.",400);
		}
		else
			dao.save(p);
	}
	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}
	@Override
	public List<Cliente> filtrar(String apellido, String nombre) {
		if(apellido==null && nombre==null)
			return dao.findAll();
		else
			return dao.findByApellidoOrNombre(apellido, nombre);
	}

}
