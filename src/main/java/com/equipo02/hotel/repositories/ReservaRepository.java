/**
 * @file: ReservaRepository.java
 * @author: (c) 2024 MARCO
 * @created: 29 feb. 2024 17:09:17
 */
package com.equipo02.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.equipo02.hotel.domain.Reserva;
/**
 * Repositorio para la entidad Reserva, proporciona operaciones CRUD básicas.
 * Extiende JpaRepository que proporciona métodos para acceder y modificar los datos de la entidad Reserva en la base de datos.
 */
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
