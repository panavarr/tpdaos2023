package com.tsti.servicios;

import java.util.List;

import com.tsti.dto.CotizacionDolar;


public interface DolarProxy {
	
	/**
	 * Devuelve la lista de cotizaciones del día
	 * @return Lista de cotizaciones
	 */
	public List<CotizacionDolar> getCotizaciones();

	

}
