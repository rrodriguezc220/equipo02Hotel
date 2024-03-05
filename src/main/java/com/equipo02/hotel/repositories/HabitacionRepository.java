/**
 * 
 * @file: HabitacionRepository.java
 * @author: (c)2024 Cueva
 * @created: 2 mar 2024, 22:46:01
 *
 */
package com.equipo02.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.equipo02.hotel.domain.Habitacion;
/**
 *  Repositorio para la entidad Habitacion, proporciona operaciones CRUD básicas.
 * Extiende JpaRepository que proporciona métodos para acceder y modificar los datos de la entidad Reserva en la base de datos.
 */
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

}
