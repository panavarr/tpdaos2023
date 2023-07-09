package com.tsti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tsti.service.DolarProxy;

@SpringBootTest
class TestCotizacionDolar {

	@Autowired
	private DolarProxy proxy;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetCotizaciones() {
		
		assertNotNull(proxy.getCotizaciones());
	}

}
