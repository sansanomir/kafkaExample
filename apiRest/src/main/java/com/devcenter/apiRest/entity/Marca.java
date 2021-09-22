package com.devcenter.apiRest.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "marcas")
public class Marca {

	@Id
    @Column(name = "marca_id", unique = true, nullable = false, length = 45)
    private int id;
	
	@Column(name = "nombre_marca", nullable = false)
    private String nombreMarca;
	
	@JsonIgnore
	@OneToOne(mappedBy = "marcaC", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Coche coche;

	public int getMarca_id() {
		return id;
	}

	public void setMarca_id(int marca_id) {
		this.id = marca_id;
	}

	public String getNombreMarca() {
		return nombreMarca;
	}

	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}
}
