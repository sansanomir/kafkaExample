package com.devcenter.apiRest.model;

import java.sql.Date;

public class CocheInfo {
	private int coche_id;
	private int marca_id;
	private float precio;
	private Date fechaInicio;
	private Date fechaFin;
	
	public int getCoche_id() {
		return coche_id;
	}
	public void setCoche_id(int coche_id) {
		this.coche_id = coche_id;
	}
	public int getMarca_id() {
		return marca_id;
	}
	public void setMarca_id(int marca_id) {
		this.marca_id = marca_id;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	@Override
	public String toString() {
		return "CocheInfo [coche_id=" + coche_id + ", marca_id=" + marca_id + ", precio=" + precio + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + "]";
	}
	
	
}
