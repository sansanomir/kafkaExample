package com.devcenter.apiRest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.devcenter.apiRest.entity.Coche;
import com.devcenter.apiRest.repository.CocheRepository;
import com.devcenter.apiRest.service.CocheService;

@Service("CochesServiceImpl")
public class CochesServiceImpl implements CocheService {

	@Autowired
	@Qualifier("CocheRepository")
	private CocheRepository cocheRepository;

	@Override
	public List<Coche> listaTodosLosCoches() {
		return cocheRepository.findAll();
	}

	@Override
	public Coche obtenerCoche(int coche_id) {
		return cocheRepository.findById(coche_id);
	}

	@Override
	public List<Coche> obtenerCochesColor(String color) {
		return cocheRepository.obtenerCochesColor(color);
	}

	@Override
	public List<Coche> obtenerCochesIdModelo(int idInt, String nombre_modelo) {
		return cocheRepository.obtenerCochesIdModelo(idInt, nombre_modelo);
	}

	@Override
	public List<Coche> obtenerCochesIdColor(int idInt, String color) {
		return cocheRepository.obtenerCochesIdColor(idInt, color);
	}

	@Override
	public List<Coche> obtenerCoches3Parametro(int idInt, String color, String nombre_modelo) {
		return cocheRepository.obtenerCoches3Parametro(idInt,color, nombre_modelo);
	}

	@Override
	public List<Coche> obtenerCochesColorModelo(String color, String nombre_modelo) {
		return cocheRepository.obtenerCochesColorModelo(color, nombre_modelo);
	}

	@Override
	public List<Coche> obtenerCochesModelo(String nombre_modelo) {
		return cocheRepository.obtenerCochesModelo(nombre_modelo);
	}
}
