package com.tsti.web.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tsti.web.controller.error.MensajeError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsti.service.dto.ClienteResponseDTO;
import com.tsti.persistence.entity.Ciudad;
import com.tsti.persistence.entity.Cliente;
import com.tsti.web.controller.exception.Excepcion;
import com.tsti.service.CiudadService;
import com.tsti.service.ClienteService;

import jakarta.validation.Valid;
/**
 * Recurso Cliente
 * @author dardo
 *
 */
@RestController
@RequestMapping("/clientes")
public class ClienteRestController {
	
	@Autowired
	private ClienteService service; 
	@Autowired
	private CiudadService ciudadService;
	
	
	/**
	 * Permite filtrar clientes. 
	 * Ej1 curl --location --request GET 'http://localhost:8081/clientes?apellido=Perez&&nombre=Juan' Lista las clientes llamadas Perez, Juan
	 * Ej2 curl --location --request GET 'http://localhost:8081/clientes?apellido=Perez' Lista aquellas clientes de apellido PErez
	 * Ej3 curl --location --request GET 'http://localhost:8081/clientes'   Lista todas las clientes
	 * @param apellido
	 * @param nombre
	 * @return
	 * @throws Excepcion 
	 */
	@GetMapping( produces = { MediaType.APPLICATION_JSON_VALUE})
	public List<ClienteResponseDTO> filtrarClientes(@RequestParam(name = "apellido",required = false) String apellido 
			, @RequestParam(name = "nombre",required = false)  @jakarta.validation.constraints.Size(min = 1, max = 20) String nombre) throws Excepcion {
		
		List<Cliente> clientes = service.filtrar(apellido,nombre);
		List<ClienteResponseDTO> dtos=new ArrayList<ClienteResponseDTO>();
		for (Cliente pojo : clientes) {
			
	        dtos.add(buildResponse(pojo));
		}
		return dtos;
//		return clientes;

	}
	
	


	/**
	 * Busca un Cliente a partir de su dni
	 * 	curl --location --request GET 'http://localhost:8081/clientes/27837171'
	 * @param id DNI del Cliente buscado
	 * @return Cliente encontrada o Not found en otro caso
	 * @throws Excepcion 
	 */
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ClienteResponseDTO> getById(@PathVariable Long id) throws Excepcion
	{
		Optional<Cliente> rta = service.getById(id);
		if(rta.isPresent())
		{
			Cliente pojo=rta.get();
			return new ResponseEntity<ClienteResponseDTO>(buildResponse(pojo), HttpStatus.OK);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	
	
	/**
	 * Inserta un nuevo cliente en la base de datos
	 * 			curl --location --request POST 'http://localhost:8081/clientes' 
	 *			--header 'Accept: application/json' 
	 * 			--header 'Content-Type: application/json' 
	 *			--data-raw '{
	 *			    "dni": 27837171,
	 *			    "apellido": "perez",
	 *			    "nombre": "juan",
	 *			    "idCiudad": 2
	 *			}'
	 * @param p Cliente  a insertar
	 * @return Cliente insertado o error en otro caso
	 * @throws Exception 
	 */
	@PostMapping
	public ResponseEntity<Object> guardar( @Valid @RequestBody ClienteForm form, BindingResult result) throws Exception
	{
		
		if(result.hasErrors())
		{
			//Dos alternativas:
			//throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatearError(result));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( this.formatearError(result));
		}
		
		Cliente p = form.toPojo();
		Optional<Ciudad> c = ciudadService.getById(form.getIdCiudad());
		if(c.isPresent())
			p.setCiudad(c.get());
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("02", "Ciudad Requerida", "La ciudad indicada no se encuentra en la base de datos."));
//				return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La ciudad indicada no se encuentra en la base de datos.");
		}
		
		//ahora inserto el cliente
		service.insert(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{dni}")
				.buildAndExpand(p.getDni()).toUri(); //Por convención en REST, se devuelve la  url del recurso recién creado

		return ResponseEntity.created(location).build();//201 (Recurso creado correctamente)
		

	}
	
	/**
	 * Modifica una persona existente en la base de datos:
	 * 			curl --location --request PUT 'http://localhost:8081/clientes/27837176' 
	 *			--header 'Accept: application/json' 
	 * 			--header 'Content-Type: application/json' 
	 *			--data-raw '{
	 *				"dni": "27837176",
	 *			    "apellido": "Perez",
	 *			    "nombre": "Juan Martin"
	 *			    "idCiudad": 1
	 *			}'
	 * @param p Cliente a modificar
	 * @return Cliente Editada o error en otro caso
	 * @throws Excepcion 
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Object>  actualizar(@RequestBody ClienteForm form, @PathVariable Long id) throws Exception
	{
		Optional<Cliente> rta = service.getById(id);
		if(!rta.isPresent())
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra el Cliente que desea modificar.");
			
		else
		{
			Cliente p = form.toPojo();
			Optional<Ciudad> c = ciudadService.getById(form.getIdCiudad());
			if(p.getDni()!=null) {
				if (p.getDni()!=id) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede puede actualizar el DNI.");
				}
			}
					
			if(c.isPresent()) {
				p.setCiudad(c.get());
				p.setDni(id);
			}
			else
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("02", "Ciudad Requerida", "La ciudad indicada no se encuentra en la base de datos."));
//				return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("La ciudad indicada no se encuentra en la base de datos.");
			
//			if(!p.getDni().equals(id))//El dni es el identificador, con lo cual es el único dato que no permito modificar
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("03", "Dato no editable", "No puede modificar el dni."));
			service.update(p);
			return ResponseEntity.ok(buildResponse(p));
		}
		
	}
	/**
	 * Borra el Cliente con el dni indicado
	 * 	  curl --location --request DELETE 'http://localhost:8081/clientes/27837176'
	 * @param dni Dni del cliente a borrar
	 * @return ok en caso de borrar exitosamente el Cliente, error en otro caso
	 */
	@DeleteMapping("/{dni}")
	public ResponseEntity<String> eliminar(@PathVariable Long dni)
	{
		if(!service.getById(dni).isPresent())
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un cliente con ese dni");
		service.delete(dni);
		
		return ResponseEntity.ok().build();
		
		
	}
	
	
	
	
	/**
	 * Métdo auxiliar que toma los datos del pojo y construye el objeto a devolver en la response, con su hipervinculos
	 * @param pojo
	 * @return
	 * @throws Excepcion 
	 */
	private ClienteResponseDTO buildResponse(Cliente pojo) throws Excepcion {
		try {
			ClienteResponseDTO dto = new ClienteResponseDTO(pojo);
			 //Self link
			Link selfLink = WebMvcLinkBuilder.linkTo(ClienteRestController.class)
										.slash(pojo.getDni())                
										.withSelfRel();
			//Method link: Link al servicio que permitirá navegar hacia la ciudad relacionada al cliente
			Link ciudadLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CiudadRestController.class)
			       													.getById(pojo.getCiudad().getId()))
			       													.withRel("ciudad");
			dto.add(selfLink);
			dto.add(ciudadLink);
			return dto;
		} catch (Exception e) {
			throw new Excepcion(e.getMessage(), 500);
		}
	}
	
	
	private String formatearError(BindingResult result) throws JsonProcessingException
	{
//		primero transformamos la lista de errores devuelta por Java Bean Validation
		List<Map<String, String>> errores= result.getFieldErrors().stream().map(err -> {
															Map<String, String> error= new HashMap<>();
															error.put(err.getField(),err.getDefaultMessage() );
															return error;
														}).collect(Collectors.toList());
		MensajeError e1=new MensajeError();
		e1.setCodigo("01");
		e1.setMensajes(errores);
		
		//ahora usamos la librería Jackson para pasar el objeto a json
		ObjectMapper maper = new ObjectMapper();
		String json = maper.writeValueAsString(e1);
		return json;
	}

	private String getError(String code, String err, String descr) throws JsonProcessingException
	{
		MensajeError e1=new MensajeError();
		e1.setCodigo(code);
		ArrayList<Map<String,String>> errores=new ArrayList<>();
		Map<String, String> error=new HashMap<String, String>();
		error.put(err, descr);
		errores.add(error);
		e1.setMensajes(errores);
		
		//ahora usamos la librería Jackson para pasar el objeto a json
				ObjectMapper maper = new ObjectMapper();
				String json = maper.writeValueAsString(e1);
				return json;
	}
	
	

}
