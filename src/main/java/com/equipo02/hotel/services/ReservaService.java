/**
 * @file: ReservaService.java
 * @author: (c) 2024 MARCO
 * @created: 29 feb. 2024 17:10:58
 */
package com.equipo02.hotel.services;

import java.util.List;
import com.equipo02.hotel.domain.Reserva;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.IllegalOperationException;

public interface ReservaService {
	List<Reserva> listarTodos();;
    Reserva buscarPorIdReserva(Long id) throws EntityNotFoundException;
    Reserva guardarReserva(Reserva reserva) throws IllegalOperationException;
    Reserva actualizarReserva(Long id, Reserva reserva) throws EntityNotFoundException, IllegalOperationException;
    void eliminarReserva(Long id) throws EntityNotFoundException, IllegalOperationException;
    //Reserva asignarHabitacion (Long idReserve, Long idHabitacion) throws EntityNotFoundException, IllegalOperationException;
}
