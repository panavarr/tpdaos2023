/* author: sofifregona */

package com.tsti.service.dto;

import org.springframework.hateoas.RepresentationModel;

import com.tsti.persistence.entity.Vuelo;

import java.time.LocalDateTime;


/**
 * Objeto utilizado para construir la respuesta de los service
 * 
 */

public class VueloResponseDTO extends RepresentationModel<VueloResponseDTO> {

	// Atributos
	
	private Long nroVuelo;
	private LocalDateTime fechaHora;
	private Integer nroFilas;
	private Integer nroAsientos;
	private String destino;
	private String origen;
	private String estado;

	
	public VueloResponseDTO(Vuelo pojo) {
		super();
		this.nroVuelo = pojo.getNroVuelo();
		this.fechaHora = pojo.getFechaHora();
		this.nroFilas = pojo.getNroFilas();
		this.nroAsientos = pojo.getNroAsientos();
		this.destino = pojo.getDestino();
		this.origen = pojo.getOrigen();
		this.estado = pojo.getEstado();
	}
	
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
	@Override
	public String toString() {
		return "Vuelo: "+ nroVuelo +", fecha y hora: "+ fechaHora +", origen: "+ origen +", destino: "+ destino +", estado: "+ estado;
	}
}