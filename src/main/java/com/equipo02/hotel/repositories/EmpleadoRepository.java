package com.equipo02.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.equipo02.hotel.domain.Empleado;
/**
 * 
 * @file: EmpleadoRepository.java
 * @author: (c)2024 Julcamoro
 * @created: 3 mar 2024, 8:17:12
 *
 */
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

}
