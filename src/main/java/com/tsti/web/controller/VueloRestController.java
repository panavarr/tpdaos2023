/* author: sofifregona */

package com.tsti.web.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.tsti.web.controller.error.MensajeError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsti.service.dto.VueloResponseDTO;
import com.tsti.persistence.entity.Vuelo;
import com.tsti.web.controller.exception.Excepcion;
import com.tsti.service.VueloService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/vuelos")
public class VueloRestController {
	
	@Autowired
	private VueloService service;
	/**
	 * Busca un Vuelo a partir de su nroVuelo
	 * 	curl --location --request GET 'http://localhost:8081/vuelos/101'
	 * @param Long nroVuelo
	 * @return Vuelo or not found
	 * @throws Excepcion 
	 */
	
	@GetMapping(value = "/{nroVuelo}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<VueloResponseDTO> getByNroVuelo(@PathVariable Long nroVuelo) throws Excepcion
	{
		Vuelo v = service.getByNroVuelo(nroVuelo);
		if(v != null)
		{
			Vuelo pojo = v;
			return new ResponseEntity<VueloResponseDTO>(buildResponse(pojo), HttpStatus.OK);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
	}
	
	/**
	 * Inserta un nuevo vuelo en la base de datos
	 * 			curl --location --request POST 'http://localhost:8081/vuelos' 
	 *			--header 'Accept: application/json' 
	 * 			--header 'Content-Type: application/json' 
	 *			--data-raw '{
	 *			    "nroVuelo": 101,
	 *			    "fechaHora": "2023-06-22T15:30:00",
	 *			    "nroFilas": 40,
	 *			    "nroAsientos": 5,
	 *				"origen": "Sauce Viejo",
	 *				"destino": "Buenos Aires"
	 *			}'
	 * @param Vuelo v
	 * @return Vuelo or Error
	 * @throws Exception 
	 */
	@PostMapping
	public ResponseEntity<Object> guardar( @Valid @RequestBody VueloForm form, BindingResult result) throws Exception
	{
		
		if(result.hasErrors())
		{
			//Dos alternativas:
			//throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatearError(result));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( this.formatearError(result));
		}
		
		Vuelo v = form.toPojo("registrado");
		
		service.insert(v);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{nroVuelo}")
				.buildAndExpand(v.getNroVuelo()).toUri(); //Por convención en REST, se devuelve la  url del recurso recién creado

		return ResponseEntity.created(location).build();//201 (Recurso creado correctamente)
	}
	
	/**
	 * Modifica un vuelo existente en la base de datos para reprogramarlo:
	 * 			curl --location --request PUT 'http://localhost:8081/vuelos/101/reprogramar' 
	 *			--header 'Accept: application/json' 
	 * 			--header 'Content-Type: application/json' 
	 *			--data-raw '{
	 *				"nroVuelo": 101,
	 *			    "fechaHora": "2023-08-11T12:10:00"
	 *			}'
	 * @param Vuelo v
	 * @return Vuelo o Error
	 * @throws Excepcion 
	 */
	@PutMapping("/{nroVuelo}/reprogramar")
	public ResponseEntity<Object> reprogramar(@RequestBody VueloForm form, @PathVariable Long nroVuelo) throws Exception
	{
		Vuelo v = service.getByNroVuelo(nroVuelo);
		if(v == null) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra el vuelo que desea modificar.");
		} else {	
			if(!v.getEstado().equals("cancelado")) {
				Vuelo vn = form.toPojo("reprogramado");
				vn.setNroFilas(v.getNroFilas());
				vn.setNroAsientos(v.getNroAsientos());
				vn.setDestino(v.getDestino());
				vn.setOrigen(v.getOrigen());
				
				if(vn.getNroVuelo() != null) {
					if (vn.getNroVuelo() != nroVuelo) {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede puede actualizar el número de vuelo.");
					}
				}
		
				service.update(vn);
				return ResponseEntity.ok(buildResponse(vn));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede reprogramar un vuelo que ha sido cancelado.");
			}
		}
	}
	
	/**
	 * Modifica un vuelo existente en la base de datos para cancelarlo:
	 * 			curl --location --request PUT 'http://localhost:8081/vuelos/101/cancelar' 
	 *			--header 'Accept: application/json' 
	 * 			--header 'Content-Type: application/json' 
	 *			--data-raw '{
	 *				"nroVuelo": 101
	 *			}'
	 * @param Vuelo v
	 * @return Vuelo o Error
	 * @throws Excepcion 
	 */
	@PutMapping("/{nroVuelo}/cancelar")
	public ResponseEntity<Object> cancelar(@RequestBody VueloForm form, @PathVariable Long nroVuelo) throws Exception
	{
		Vuelo v = service.getByNroVuelo(nroVuelo);
		if(v == null) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra el vuelo que desea modificar.");
		} else {	
			if(!v.getEstado().equals("cancelado")) {
				Vuelo vn = form.toPojo("cancelado");
				vn.setNroFilas(v.getNroFilas());
				vn.setNroAsientos(v.getNroAsientos());
				vn.setDestino(v.getDestino());
				vn.setOrigen(v.getOrigen());
				vn.setFechaHora(v.getFechaHora());
				
				if(vn.getNroVuelo() != null) {
					if (vn.getNroVuelo() != nroVuelo) {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede puede actualizar el número de vuelo.");
					}
				}
		
				service.update(vn);
				return ResponseEntity.ok(buildResponse(vn));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede cancelar un vuelo que ya ha sido cancelado.");
			}
		}
	}
	
	/**
	 * Métdo auxiliar que toma los datos del pojo y construye el objeto a devolver en la response, con su hipervinculos
	 * @param pojo
	 * @return
	 * @throws Excepcion 
	 */
	
	private VueloResponseDTO buildResponse(Vuelo pojo) throws Excepcion {
		try {
			VueloResponseDTO dto = new VueloResponseDTO(pojo);
			 //Self link
			Link selfLink = WebMvcLinkBuilder.linkTo(VueloRestController.class)
										.slash(pojo.getNroVuelo())                
										.withSelfRel();
			
			dto.add(selfLink);
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