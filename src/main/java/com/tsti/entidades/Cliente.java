package com.tsti.entidades;

import java.util.Collection;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Entity
public class Cliente {


	@Id
	private Long dni;
	@NotNull
	@Size(min = 1,max = 100, message = "Debe completar el apellido")
	private String apellido;
	
	private String nombre;
	
	private String domicilio;
	
	private String fechaNacimiento;
	
	private String numeroPasaporte;
	
	private String fechaVencimientoPasaporte;
	
	@Email(message = "El e-mail ingresado no es valido")
	private String email;

	@ManyToOne
	private Ciudad ciudad;
	
	
	
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
	
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
