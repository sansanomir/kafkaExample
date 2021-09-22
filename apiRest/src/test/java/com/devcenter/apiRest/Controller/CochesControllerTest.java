package com.devcenter.apiRest.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.devcenter.apiRest.entity.Coche;
import com.devcenter.apiRest.service.CocheService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CochesController.class)
public class CochesControllerTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private MockMvc mockMvc;
		
	@Mock
	private CocheService cochesService;
	
	@MockBean
	private CochesController cochesController;
	
	@Test
	void servicio1Test() {
		
	}
	
	@Test
	void servicio2Test() throws Exception {
		Coche coche = null;
		when(cochesService.obtenerCoche(122)).thenReturn(null);
		
		mockMvc.perform(get("/coches/filter?id={coche_id}", "2")).andExpect(status().isOk());
		//mockMvc.perform(get("/coches/filter?id={coche_id}", "122")).andExpect(status().is4xxClientError());
		mockMvc.perform(get("/coches/fil", "12")).andExpect(status().is4xxClientError());

	}
	
	@Test
	void servicio3Test() {
		
	}
}
