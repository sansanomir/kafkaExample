package com.devcenter.apiRest.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devcenter.apiRest.entity.Coche;

@Repository("CocheRepository")
public interface CocheRepository extends JpaRepository<Coche, Serializable>{
	public abstract List<Coche> findAll();
	public abstract Coche findById(int coche_id);
	
	@Query(value = "select * from coches"
			+ " where color = :color ", nativeQuery = true)
	public abstract List<Coche> obtenerCochesColor(String color);
	
	@Query(value = "select * from coches"
			+ " where nombre_modelo = :nombre_modelo ", nativeQuery = true)
	public abstract List<Coche> obtenerCochesModelo(String nombre_modelo);
	
	@Query(value = "select * from coches"
			+ " where coche_id = :idInt and :nombre_modelo = :nombre_modelo ", nativeQuery = true)
	public abstract List<Coche> obtenerCochesIdModelo(int idInt, String nombre_modelo);
	
	@Query(value = "select * from coches"
			+ " where coche_id = :idInt and :color = :color ", nativeQuery = true)
	public abstract List<Coche> obtenerCochesIdColor(int idInt, String color);
	
	@Query(value = "select * from coches"
			+ " where coche_id = :idInt and color = :color and nombre_modelo = :nombre_modelo", nativeQuery = true)
	public abstract List<Coche> obtenerCoches3Parametro(int idInt, String color, String nombre_modelo);
	
	@Query(value = "select * from coches"
			+ " where color = :color and nombre_modelo = :nombre_modelo ", nativeQuery = true)
	public abstract List<Coche> obtenerCochesColorModelo(String color, String nombre_modelo);
}
