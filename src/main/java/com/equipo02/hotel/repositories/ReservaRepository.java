/**
 * @file: ReservaRepository.java
 * @author: (c) 2024 MARCO
 * @created: 29 feb. 2024 17:09:17
 */
package com.equipo02.hotel.repositories;

import org.springframework.data.repository.CrudRepository;
import com.equipo02.hotel.domain.Reserva;

public interface ReservaRepository extends CrudRepository<Reserva, Long> {

}
