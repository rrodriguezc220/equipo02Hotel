/**
 * @file: HuespedRepository.java
 * @author: (c)2024 Rodriguez
 * @created: 3 mar. 2024 17:21:39
 */

package com.equipo02.hotel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.equipo02.hotel.domain.Huesped;

/**
 * Repositorio para la entidad Huesped, proporciona operaciones CRUD básicas y operaciones de búsqueda.
 * Extiende JpaRepository que proporciona métodos para acceder y modificar los datos de la entidad Huesped en la base de datos.
 */
@Repository
public interface HuespedRepository extends JpaRepository<Huesped, Long> {
	
	/**
	 * Busca una lista de huéspedes por su nombre.
	 * 
	 * @param nombreHuesped El nombre del huésped a buscar.
	 * @return Una lista de huéspedes que coinciden con el nombre proporcionado.
	 */
	List<Huesped> findByNombreHuesped(String nombreHuesped);
	
	/**
	 * Busca una lista de huéspedes por su aval.
	 * 
	 * @param aval El huésped que actúa como aval.
	 * @return Una lista de huéspedes que tienen el aval proporcionado.
	 */
	List<Huesped> findByAval(Huesped aval);
}
