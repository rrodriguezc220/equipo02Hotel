/**
 * 
 * @file: EmpleadoRepository.java
 * @author: (c)2024 Julcamoro
 * @created: 3 mar 2024, 8:17:12
 *
 */
package com.equipo02.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.equipo02.hotel.domain.Empleado;
import com.equipo02.hotel.domain.Huesped;

/**
 * Repositorio para la entidad Empleado, proporciona operaciones CRUD básicas.
 * Extiende JpaRepository que proporciona métodos para acceder y modificar los datos de la entidad Empleado en la base de datos.
 */
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
	Empleado findByDniEmpleado(String dniEmpleado);
}
