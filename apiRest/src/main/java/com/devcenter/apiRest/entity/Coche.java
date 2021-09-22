package com.devcenter.apiRest.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "coches")
public class Coche {

	@Id
    @Column(name = "coche_id", unique = true, nullable = false, length = 45)
    private int id;
	
	@Column(name = "color", nullable = false)
    private String color;
	
	@Column(name = "nombre_modelo", nullable = false)
    private String nombreModelo;
	
	@JsonIgnore
	@OneToMany(mappedBy="cocheP")
	private Set<Precio> precios;
	
	@Override
	public String toString() {
		return "Coche [id=" + id + ", color=" + color + ", nombreModelo=" + nombreModelo + ", precios=" + precios
				+ ", marcaC=" + marcaC + "]";
	}

	@OneToOne
    @MapsId
    @JoinColumn(name = "marca_id")
	private Marca marcaC;

	public int getId() {
		return id;
	}

	public void setId(int coche_id) {
		this.id = coche_id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getNombreModelo() {
		return nombreModelo;
	}

	public void setNombreModelo(String nombreModelo) {
		this.nombreModelo = nombreModelo;
	}

	public Set<Precio> getPrecios() {
		return precios;
	}

	public void setPrecios(Set<Precio> precios) {
		this.precios = precios;
	}

	public Marca getMarcaC() {
		return marcaC;
	}

	public void setMarcaC(Marca marcaC) {
		this.marcaC = marcaC;
	}
	
	
	
}
