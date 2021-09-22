package com.devcenter.apiRest.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devcenter.apiRest.entity.Coche;
import com.devcenter.apiRest.entity.Precio;

@Repository("PrecioRepository")
public interface PrecioRepository extends JpaRepository<Precio, Serializable>{
	public Precio findByCocheP(Coche cocheP);
	
	@Query(value = "select * from precios"
			+ " where fecha_inicio < :fecha and fecha_fin > :fecha"
			+ "	and coche_id = :coche_id ", nativeQuery = true)
	public List<Precio> findPreciosByCoche_idAndDate(String fecha, int coche_id);
}
