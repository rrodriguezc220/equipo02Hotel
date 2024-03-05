/**
 * @file: HuespedService.java
 * @author: (c)2024 Rodriguez
 * @created: 3 mar. 2024 17:12:52
 */

package com.equipo02.hotel.services;

import java.util.List;

import com.equipo02.hotel.domain.Habitacion;
import com.equipo02.hotel.domain.Huesped;
import com.equipo02.hotel.domain.Reserva;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.IllegalOperationException;

/**
 * Interfaz que define las operaciones de servicio para la entidad Huesped.
 *
 * Proporciona métodos para listar, buscar, grabar, actualizar y eliminar huéspedes,
 * así como para asignar, eliminar y actualizar el aval de un huésped.
 */
public interface HuespedService {
	
	/**
     * Lista todos los huéspedes disponibles.
     * 
     * @return Una lista de todos los huéspedes disponibles.
     */
	List<Huesped> listarHuespedes();
	
	/**
     * Busca un huésped por su ID.
     * 
     * @param id El ID del huésped a buscar.
     * @return El huésped correspondiente al ID proporcionado.
     * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
     */
	Huesped buscarPorId(Long id) throws EntityNotFoundException;
	
	/**
     * Graba un nuevo huésped en la base de datos.
     * 
     * @param huesped El huésped a grabar.
     * @return El huésped grabado.
     * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
     * @throws IllegalOperationException Si hay operaciones ilegales.
     */
	Huesped grabar(Huesped huesped) throws EntityNotFoundException, IllegalOperationException;
	
	/**
     * Actualiza los datos de un huésped existente.
     * 
     * @param id El ID del huésped a actualizar.
     * @param huesped Los nuevos datos del huésped.
     * @return El huésped actualizado.
     * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
     * @throws IllegalOperationException Si hay operaciones ilegales.
     */
	Huesped actualizar(Long id, Huesped huesped) throws EntityNotFoundException, IllegalOperationException;
	
	/**
     * Elimina un huésped por su ID.
     * 
     * @param id El ID del huésped a eliminar.
     * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
     * @throws IllegalOperationException Si hay operaciones ilegales.
     */
	void eliminar(Long id) throws EntityNotFoundException, IllegalOperationException;
	
	/**
     * Asigna un aval a un huésped.
     * 
     * @param idHuesped El ID del huésped al que se asignará el aval.
     * @param idAval El ID del huésped que actuará como aval.
     * @return El huésped con el aval asignado.
     * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
     * @throws IllegalOperationException Si hay operaciones ilegales.
     */
	Huesped asignarAval(Long idHuesped, Long idAval) throws EntityNotFoundException, IllegalOperationException;
	
	/**
     * Elimina el aval de un huésped.
     * 
     * @param id El ID del huésped del que se eliminará el aval.
     * @return El huésped con el aval eliminado.
     * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
     * @throws IllegalOperationException Si hay operaciones ilegales.
     */
	Huesped eliminarAval(Long id) throws EntityNotFoundException, IllegalOperationException;
	
	/**
     * Actualiza ciertos campos de un huésped existente.
     * 
     * @param id El ID del huésped cuyos campos se actualizarán.
     * @param huesped El objeto Huesped que contiene los nuevos valores de los campos a actualizar.
     * @return El huésped actualizado.
     * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
     * @throws IllegalOperationException Si hay operaciones ilegales.
     */
	Huesped actualizarPorCampos(Long id, Huesped huesped) throws EntityNotFoundException, IllegalOperationException;
	
	//Subservicios
	
	/**
     * Obtiene todas las reservas de un huésped.
     * 
     * @param idHuesped El ID del huésped.
     * @return Una lista de todas las reservas del huésped.
     * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
     */
	List<Reserva> obtenerReservasPorHuesped(Long idHuesped) throws EntityNotFoundException;
	
	/**
     * Obtiene una reserva específica de un huésped.
     * 
     * @param idHuesped El ID del huésped.
     * @param idReserva El ID de la reserva.
     * @return La reserva específica del huésped.
     * @throws EntityNotFoundException Si el huésped o la reserva no pueden ser encontrados.
     */
	Reserva obtenerReservaDeHuesped(Long idHuesped, Long idReserva) throws EntityNotFoundException;
	
	/**
     * Obtiene todas las habitaciones reservadas por un huésped en una reserva específica.
     * 
     * @param idHuesped El ID del huésped.
     * @param idReserva El ID de la reserva.
     * @return Una lista de todas las habitaciones reservadas por el huésped en la reserva específica.
     * @throws EntityNotFoundException Si el huésped o la reserva no pueden ser encontrados.
     */
	List<Habitacion> obtenerHabitacionesPorReserva(Long idHuesped, Long idReserva) throws EntityNotFoundException;
	
	/**
     * Obtiene una habitación específica reservada por un huésped en una reserva específica.
     * 
     * @param idHuesped El ID del huésped.
     * @param idReserva El ID de la reserva.
     * @param idHabitacion El ID de la habitación.
     * @return La habitación específica reservada por el huésped en la reserva específica.
     * @throws EntityNotFoundException Si el huésped, la reserva o la habitación no pueden ser encontrados.
     */
	Habitacion obtenerHabitacionDeReserva(Long idHuesped, Long idReserva, Long idHabitacion) throws EntityNotFoundException;
}
