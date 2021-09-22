package com.devcenter.apiRest.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "precios")
public class Precio {

	@Id
    @Column(name = "precio_id", unique = true, nullable = false, length = 45)
    private int precio_id;
	
	@Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;
	
	@Column(name = "fecha_fin", nullable = false)
    private Date fechaFin;
	
	@Column(name = "precio", nullable = false)
    private Float precio;

	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="coche_id", nullable = false)
	private Coche cocheP;

	public int getPrecio_id() {
		return precio_id;
	}

	public void setPrecio_id(int precio_id) {
		this.precio_id = precio_id;
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

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Coche getCocheP() {
		return cocheP;
	}

	public void setCocheP(Coche cocheP) {
		this.cocheP = cocheP;
	}
	
}
