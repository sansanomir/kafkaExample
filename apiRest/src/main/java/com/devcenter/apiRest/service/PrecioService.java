package com.devcenter.apiRest.service;

import java.util.List;

import com.devcenter.apiRest.entity.Coche;
import com.devcenter.apiRest.entity.Precio;

public interface PrecioService {
	public Precio findByCoche_id(Coche cocheP);
	public List<Precio> findPreciosByCoche_idAndDate(String fecha, int coche_id);
	
}
