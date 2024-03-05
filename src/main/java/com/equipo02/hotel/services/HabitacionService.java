/**
 * 
 * @file: HabitacionService.java
 * @author: (c)2024 Cueva
 * @created: 2 mar 2024, 22:46:52
 *
 */
package com.equipo02.hotel.services;
import java.util.List;

import com.equipo02.hotel.domain.Habitacion;
import com.equipo02.hotel.domain.Huesped;
import com.equipo02.hotel.domain.Reserva;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.IllegalOperationException;
/**
 * Interfaz que define los servicios disponibles para la entidad Habitacion.
 */
public interface HabitacionService {
/**
 * Método para obtener una lista de todas las habitaciones.
 * @return Una lista de objetos Habitacion.
 */
	List<Habitacion> listarTodos();
	/**
	 * Método para buscar una habitación por su ID.
	 * @param id El ID de la habitación a buscar.
	 * @return Un objeto Habitacion que representa la habitación encontrada.
	 * @throws EntityNotFoundException si no se encuentra una habitación con el ID proporcionado.
	 */
    Habitacion buscarPorIdHabitacion(Long id) throws EntityNotFoundException;
    /**
     * Método para guardar una nueva habitación.
     * @param habitacion Un objeto Habitacion que representa la habitación a guardar.
     * @return Un objeto Habitacion que representa la habitación guardada.
     * @throws IllegalOperationException si ocurre un error al guardar la habitación.
     */
    Habitacion guardarHabitacion(Habitacion habitacion) throws IllegalOperationException;
    /**
     * Método para actualizar los detalles de una habitación existente.
     * @param id El ID de la habitación a actualizar.
     * @param habitacion Un objeto Habitacion que representa la habitación con los detalles actualizados.
     * @return Un objeto Habitacion que representa la habitación actualizada.
     * @throws EntityNotFoundException si no se encuentra una habitación con el ID proporcionado.
     * @throws IllegalOperationException si ocurre un error al actualizar la habitación.
     */
    Habitacion actualizarHabitacion(Long id, Habitacion habitacion) throws EntityNotFoundException, IllegalOperationException;
    /**
     * Método para eliminar una habitación existente.
     * @param id El ID de la habitación a eliminar.
     * @throws EntityNotFoundException si no se encuentra una habitación con el ID proporcionado.
     * @throws IllegalOperationException si ocurre un error al eliminar la habitación.
     */
    void eliminarHabitacion(Long id) throws EntityNotFoundException, IllegalOperationException;
    /**
     *  Actualiza ciertos campos de un Habitacion existente.
     * @param id El ID de la Habitación que se desea actualizar.
     * @param habitacion Un objeto Habitacion que contiene los campos actualizados de la Habitación.
     * @return Un objeto Habitacion que representa la Habitación actualizada.
     * @throws EntityNotFoundException Si no se encuentra una Habitación con el ID proporcionado.
     */
    Habitacion actualizarCampoHabitacion(Long id, Habitacion habitacion) throws EntityNotFoundException;
    /**
     * Método para obtener una Reserva específica asociada con una Habitación en particular.
     *
     * @param idHabitacion El ID de la Habitación.
     * @param idReserva El ID de la Reserva.
     * @return Un objeto Reserva que representa la Reserva asociada con la Habitación.
     * @throws EntityNotFoundException Si no se encuentra una Habitación con el ID proporcionado o si la Habitación no tiene una Reserva con el ID proporcionado.
     */
    Reserva obtenerReservaDeHabitacion(Long idHabitacion, Long idReserva) throws EntityNotFoundException;
}
