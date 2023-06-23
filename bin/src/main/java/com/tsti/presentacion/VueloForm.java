/* author: sofifregona */

package com.tsti.presentacion;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;

import com.tsti.entidades.Vuelo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Objeto necesario para insertar, modificar o eliminar un vuelo. 
 */

public class VueloForm {
	
	// Atributos

	@NotNull(message = "El número de vuelo no puede ser nulo")
	private Long nroVuelo;
	
	@NotNull(message = "Debe completar la fecha y hora del vuelo")
	@Future(message = "La fecha y hora no puede ser anterior a la fecha y hora actual")
	private LocalDateTime fechaHora;
	
	@NotNull(message = "Debe completar el número de filas del vuelo")
	@Min(value = 1, message = "El número de filas no puede ser menor a 1")
	private Integer nroFilas;
	
	@NotNull(message = "Debe completar el número de asientos por fila del vuelo")
	@Min(value = 1, message = "El número de asientos no puede ser menor a 1")
	private Integer nroAsientos;
	
	@NotNull(message = "Debe completar el aeropuerto de destino")
	@Size(min = 3, message = "El aeropuerto de destino no puede tener menos de 3 carácteres")
	private String destino;
	
	@NotNull(message = "Debe completar el aeropuerto de origen")
	@Size(min = 3, message = "El aeropuerto de origen no puede tener menos de 3 carácteres")
	private String origen;
	
	private String estado;
	
	// Getters y setters
	
	public Long getNroVuelo() {
		return nroVuelo;
	}
	public void setNroVuelo(Long nroVuelo) {
		this.nroVuelo = nroVuelo;
	}
	
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}
	
	public Integer getNroFilas() {
		return nroFilas;
	}
	public void setNroFilas(Integer nroFilas) {
		this.nroFilas = nroFilas;
	}
	
	public Integer getNroAsientos() {
		return nroAsientos;
	}
	public void setNroAsientos(Integer nroAsientos) {
		this.nroAsientos = nroAsientos;
	}
	
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	// Constructor
	
	public Vuelo toPojo(String estado) {
		Vuelo v = new Vuelo();
		v.setNroVuelo(this.getNroVuelo());
		v.setFechaHora(this.getFechaHora());
		v.setNroFilas(this.getNroFilas());
		v.setNroAsientos(this.getNroAsientos());
		v.setOrigen(this.getOrigen());
		v.setDestino(this.getDestino());
		v.setEstado(estado);
		return v;
	}
	
}