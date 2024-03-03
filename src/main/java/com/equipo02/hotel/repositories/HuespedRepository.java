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

@Repository
public interface HuespedRepository extends JpaRepository<Huesped, Long> {
	List<Huesped> findByNombreHuesped(String nombreHuesped);
	List<Huesped> findByAval(Huesped aval);
}
