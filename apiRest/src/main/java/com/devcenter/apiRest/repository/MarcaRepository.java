package com.devcenter.apiRest.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcenter.apiRest.entity.Marca;

@Repository("MarcaRepository")
public interface MarcaRepository extends JpaRepository<Marca, Serializable>{
	public abstract Marca findById(int marca_id);
}
