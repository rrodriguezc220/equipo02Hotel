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
/**
 * Interfaz que define los servicios relacionados con las reservas en el hotel.
 */

public interface ReservaService {
    /**
     * Obtiene una lista de todas las reservas en el sistema.
     * @return Una lista de objetos Reserva.
     */
	List<Reserva> listarTodos();;
    
    /**
     * Busca una reserva por su identificador único.
     * @param id El identificador único de la reserva.
     * @return La reserva encontrada.
     * @throws EntityNotFoundException Si la reserva con el id dado no es encontrada.
     */
	Reserva buscarPorIdReserva(Long id) throws EntityNotFoundException;
	
    /**
     * Guarda una nueva reserva en el sistema.
     * @param reserva La reserva a ser guardada.
     * @return La reserva guardada.
     * @throws IllegalOperationException Si la reserva es inválida o ya existe en el sistema.
     */
    Reserva guardarReserva(Reserva reserva) throws IllegalOperationException;
    
    /**
     * Actualiza una reserva existente en el sistema.
     * @param id El identificador único de la reserva a actualizar.
     * @param reserva La nueva información de la reserva.
     * @return La reserva actualizada.
     * @throws EntityNotFoundException Si la reserva con el id dado no es encontrada.
     * @throws IllegalOperationException Si la reserva es inválida.
     */
    Reserva actualizarReserva(Long id, Reserva reserva) throws EntityNotFoundException, IllegalOperationException;
    
    /**
     * Elimina una reserva del sistema.
     * @param id El identificador único de la reserva a eliminar.
     * @throws EntityNotFoundException Si la reserva con el id dado no es encontrada.
     * @throws IllegalOperationException Si la reserva tiene dependencias que impiden su eliminación.
     */
    void eliminarReserva(Long id) throws EntityNotFoundException, IllegalOperationException;
    
    
    //Reserva asignarHabitacion (Long idReserve, Long idHabitacion) throws EntityNotFoundException, IllegalOperationException;
}

