package com.tsti.web.controller;

import com.tsti.persistence.entity.Cliente;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Objeto necesario para insertar o eliminar una persona. 
 * Nótese que en lugar de referenciar al objeto Ciudad, reemplaza ese atributo por el idCiudad, lo cual resulta mas sencillo de representar en JSON
 *
 */
public class ClienteForm {


	@NotNull(message = "el dni no puede ser nulo")
	@Min(7000000)
	private Long dni;
	@NotNull
	@Size(min=2, max=30, message = "apellido demasiado largo")
	private String apellido;
	@NotNull
	@Size(min=2, max=30)
	private String nombre;
	@NotNull
	private Long idCiudad;
	private String domicilio;
	private String email;
	private String fechaNacimiento;
	private String numeroPasaporte;
	private String fechaVencimientoPasaporte;
	
	
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
	public Long getIdCiudad() {
		return idCiudad;
	}
	public void setIdCiudad(Long idCiudad) {
		this.idCiudad = idCiudad;
	}
	
	public Cliente toPojo()
	{
		Cliente p = new Cliente();
		p.setDni(this.getDni());
		p.setApellido(this.getApellido());
		p.setNombre(this.getNombre());
		p.setDni(this.getDni());
		p.setDomicilio(this.getDomicilio());
		p.setEmail(this.getEmail());
		p.setFechaNacimiento(this.getFechaNacimiento());
		p.setNumeroPasaporte(this.getNumeroPasaporte());
		p.setFechaVencimientoPasaporte(this.getFechaVencimientoPasaporte());
		return p;
	}
	
}
