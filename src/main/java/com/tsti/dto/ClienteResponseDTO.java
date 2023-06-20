package com.tsti.dto;

import org.springframework.hateoas.RepresentationModel;

import com.tsti.entidades.Cliente;


/**
 * Objeto utilizado para construir la respuesta de los servicios
 * 
 *
 */
public class ClienteResponseDTO extends RepresentationModel<ClienteResponseDTO> {

	private Long dni;
	private String apellido;
	private String nombre;
	private String domicilio;
	private String email;
	private String fechaNacimiento;
	private String numeroPasaporte;
	private String fechaVencimientoPasaporte;
	
	
	
	
	public ClienteResponseDTO(Cliente pojo) {
		super();
		this.apellido=pojo.getApellido();
		this.nombre=pojo.getNombre();
		this.dni=pojo.getDni();
		this.email=pojo.getEmail();
		this.fechaNacimiento=pojo.getFechaNacimiento();
		this.numeroPasaporte=pojo.getNumeroPasaporte();
		this.fechaVencimientoPasaporte=pojo.getFechaVencimientoPasaporte();
	}
	
	public Long getDni() {
		return dni;
	}
	public void setDni(Long dni) {
		this.dni = dni;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNumeroPasaporte() {
		return numeroPasaporte;
	}
	public void setNumeroPasaporte(String numeroPasaporte) {
		this.numeroPasaporte = numeroPasaporte;
	}
	public String getFechaVencimientoPasaporte() {
		return fechaVencimientoPasaporte;
	}
	public void setFechaVencimientoPasaporte(String fechaVencimientoPasaporte) {
		this.fechaVencimientoPasaporte = fechaVencimientoPasaporte;
	}
	@Override
	public String toString() {
		return dni+" - "+ nombre +" "+ apellido;
	}
	
}
