
package com.equipo02.hotel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.equipo02.hotel.domain.Huesped;

@Repository
public interface HuespedRepository extends JpaRepository<Huesped, Long> {
	List<Huesped> findByNombreHuesped(String nombreHuesped);
}
