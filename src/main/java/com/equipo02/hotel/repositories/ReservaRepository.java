/**
 * @file: ReservaRepository.java
 * @author: (c) 2024 MARCO
 * @created: 29 feb. 2024 17:09:17
 */
package com.equipo02.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
=======
import org.springframework.data.repository.CrudRepository;
>>>>>>> bfdd9516e30371d262b96733120f8ba87e1b6ea5
import com.equipo02.hotel.domain.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
