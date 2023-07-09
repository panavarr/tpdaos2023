package com.tsti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tsti.service.dto.CotizacionDolar;
import com.tsti.service.dto.TipoCambioDolar;
@Service
public class DolarProxyImpl implements DolarProxy {

	@Override
	public List<CotizacionDolar> getCotizaciones() {
		List<CotizacionDolar> lista=new ArrayList<CotizacionDolar>();
		ResponseEntity<TipoCambioDolar[]> resp = new RestTemplate().getForEntity(
				"https://www.dolarsi.com/api/api.php?type=valoresprincipales",
				TipoCambioDolar[].class
		);
		for (TipoCambioDolar tipoCambio : resp.getBody()) {
			lista.add(tipoCambio.getCasa());
		}
		return lista;
	}
}
