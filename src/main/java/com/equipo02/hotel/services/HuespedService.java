/**
 * @file: HuespedService.java
 * @author: (c)2024 rodriguez 
 * @created: Feb 29, 2024 10:38:30 AM
 */

package com.equipo02.hotel.services;

import java.util.List;

import com.equipo02.hotel.domain.Huesped;

public interface HuespedService {
	List<Huesped> listarHuespedes();
	Huesped buscarPorId(Long id);
	Huesped grabar(Huesped huesped);
	Huesped actualizar(Long id, Huesped huesped);
	void eliminar(Long id);
	Huesped asignarAval(Long idHuesped, Long idAval);
}
