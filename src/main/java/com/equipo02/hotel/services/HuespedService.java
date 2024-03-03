/**
 * @file: HuespedService.java
 * @author: (c)2024 Rodriguez
 * @created: 3 mar. 2024 17:12:52
 */

package com.equipo02.hotel.services;

import java.util.List;

import com.equipo02.hotel.domain.Huesped;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.IllegalOperationException;

public interface HuespedService {
	List<Huesped> listarHuespedes();
	Huesped buscarPorId(Long id) throws EntityNotFoundException;
	Huesped grabar(Huesped huesped);
	Huesped actualizar(Long id, Huesped huesped) throws EntityNotFoundException;
	void eliminar(Long id) throws EntityNotFoundException, IllegalOperationException;
	Huesped asignarAval(Long idHuesped, Long idAval) throws EntityNotFoundException, IllegalOperationException;
	Huesped eliminarAval(Long id) throws EntityNotFoundException, IllegalOperationException;
	Huesped actualizarNombre(Long id, Huesped huesped) throws EntityNotFoundException;
}
