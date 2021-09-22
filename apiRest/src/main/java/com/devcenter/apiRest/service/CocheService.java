package com.devcenter.apiRest.service;

import java.util.List;

import com.devcenter.apiRest.entity.Coche;

public interface CocheService {
	public abstract List<Coche> listaTodosLosCoches();
	public abstract Coche obtenerCoche(int coche_id);
	public abstract List<Coche> obtenerCochesColor(String color);
	public abstract List<Coche> obtenerCochesIdModelo(int idInt, String nombre_modelo);
	public abstract List<Coche> obtenerCochesIdColor(int idInt, String color);
	public abstract List<Coche> obtenerCoches3Parametro(int idInt, String color, String nombre_modelo);
	public abstract List<Coche> obtenerCochesColorModelo(String color, String nombre_modelo);
	public abstract List<Coche> obtenerCochesModelo(String nombre_modelo);
	 
}
