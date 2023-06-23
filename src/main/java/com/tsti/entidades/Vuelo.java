/* author: sofifregona */

package com.tsti.entidades;

import java.time.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import javax.validation.constraints.Min;
import javax.validation.constraints.Future;

@Entity
public class Vuelo {


	// Atributos
	@Id
	private Long nroVuelo;
	
	private LocalDateTime fechaHora;
	
	private Integer nroFilas;
	
	private Integer nroAsientos;
	
	private String destino;
	
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
	
}