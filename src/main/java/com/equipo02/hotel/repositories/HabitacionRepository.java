/**
 * 
 * @file: HabitacionRepository.java
 * @author: (c)2024 Cueva
 * @created: 2 mar 2024, 22:46:01
 *
 */
package com.equipo02.hotel.repositories;

import org.springframework.data.repository.CrudRepository;

import com.equipo02.hotel.domain.Habitacion;

public interface HabitacionRepository extends CrudRepository<Habitacion, Long> {

}
