package com.devcenter.apiRest.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.devcenter.apiRest.entity.Coche;
import com.devcenter.apiRest.entity.Precio;
import com.devcenter.apiRest.repository.PrecioRepository;
import com.devcenter.apiRest.service.PrecioService;

@Service("PreciosServiceImpl")
public class PreciosServiceImpl implements PrecioService{
	
	@Autowired
	@Qualifier("PrecioRepository")
	private PrecioRepository precioRepository;

	@Override
	public Precio findByCoche_id(Coche cocheP ) {
		return precioRepository.findByCocheP(cocheP);
	}

	@Override
	public List<Precio> findPreciosByCoche_idAndDate(String fecha, int coche_id) {
		return precioRepository.findPreciosByCoche_idAndDate(fecha, coche_id);
	}
	
	
}
