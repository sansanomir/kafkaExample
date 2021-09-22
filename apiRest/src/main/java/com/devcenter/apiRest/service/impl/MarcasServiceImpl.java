package com.devcenter.apiRest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.devcenter.apiRest.entity.Marca;
import com.devcenter.apiRest.repository.MarcaRepository;
import com.devcenter.apiRest.service.MarcaService;

@Service("MarcasServiceImpl")
public class MarcasServiceImpl implements MarcaService{

	@Autowired
	@Qualifier("MarcaRepository")
	
	private MarcaRepository marcaRepository;

	@Override
	public Marca obternerMarca(int marca_id) {
		return marcaRepository.findById(marca_id);
	}
}
