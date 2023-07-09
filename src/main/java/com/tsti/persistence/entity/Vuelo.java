/* author: sofifregona */

package com.tsti.persistence.entity;

import java.time.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Vuelo {


	// Atributos
	@Id
	@Column(name = "nro_vuelo")
	private Long nroVuelo;

	@Column(name = "fecha_hora")
	private LocalDateTime fechaHora;

	@Column(name = "nro_filas")
	private Integer nroFilas;

	@Column(name = "nro_asientos")
	private Integer nroAsientos;

	private String destino;
	
	private String origen;
	
	private String estado;

	@Column(name = "is_internacional")
	private Boolean isInternacional;
	
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

	public Boolean getIsInternacional() {
		return isInternacional;
	}
	public void setIsInternacional(Boolean isInternacional) {
		this.isInternacional = isInternacional;
	}
}