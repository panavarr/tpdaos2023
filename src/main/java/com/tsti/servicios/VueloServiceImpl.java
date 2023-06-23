/* author: sofifregona */

package com.tsti.servicios;

import java.util.List;
import java.util.Set;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsti.accesoADatos.VueloDAO;
import com.tsti.entidades.Vuelo;
import com.tsti.exception.Excepcion;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Service
public class VueloServiceImpl implements VueloService {
	
	@Autowired
	private  Validator validator;
	
	@Autowired
	private VueloDAO vuelo;
	
	@Override
	public List<Vuelo> getAll() {
		return vuelo.findAll();
	}
	
	@Override
	public Vuelo getByNroVuelo(Long nroVuelo) {
		return vuelo.findByNroVuelo(nroVuelo);
	}
	
	@Override
	public void update(Vuelo v) {
		vuelo.save(v);
	}
	
	@Override
	public void insert(Vuelo v) throws Exception {
		
		Set<ConstraintViolation<Vuelo>> cv = validator.validate(v);
		if(cv.size()>0)
		{
			String err="";
			for (ConstraintViolation<Vuelo> constraintViolation : cv) {
				err+=constraintViolation.getPropertyPath()+": "+constraintViolation.getMessage()+"\n";
			}
			throw new Excepcion(err,400);
		}
		else if(getByNroVuelo(v.getNroVuelo()) != null)
		{
			throw new Excepcion("Ya existe un vuelo con este número.",400);
		}
		else
			vuelo.save(v);
	}
	
	@Override
	public void delete(Long nroVuelo) {
		vuelo.deleteById(nroVuelo);
	}
	

}
