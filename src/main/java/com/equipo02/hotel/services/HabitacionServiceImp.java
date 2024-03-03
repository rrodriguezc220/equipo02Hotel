/**
 * 
 * @file: HabitacionServiceImp.java
 * @author: (c)2024 Cueva
 * @created: 2 mar 2024, 22:51:08
 *
 */
package com.equipo02.hotel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.equipo02.hotel.domain.Habitacion;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.ErrorMessage;
import com.equipo02.hotel.exception.IllegalOperationException;
import com.equipo02.hotel.repositories.HabitacionRepository;
/**
 * Implementación de los servicios disponibles para la entidad Habitacion.
 */
@Service
public class HabitacionServiceImp implements HabitacionService {

	@Autowired
	private HabitacionRepository habitacionRepository;
	 /**
     * Método para obtener una lista de todas las habitaciones.
     * @return Una lista de objetos Habitacion.
     */
	@Override
	public List<Habitacion> listarTodos() {
		return (List<Habitacion>) habitacionRepository.findAll();
	}
	/**
     * Método para buscar una habitación por su ID.
     * @param idHabitacion El ID de la habitación a buscar.
     * @return Un objeto Habitacion que representa la habitación encontrada.
     * @throws EntityNotFoundException si no se encuentra una habitación con el ID proporcionado.
     */
	@Override
	@Transactional(readOnly = true)
	public Habitacion buscarPorIdHabitacion(Long idHabitacion) throws EntityNotFoundException {
		Optional<Habitacion> habitacion = habitacionRepository.findById(idHabitacion);
		if (habitacion.isEmpty()) {
	        throw new EntityNotFoundException(ErrorMessage.HABITACION_NOT_FOUND);
		}
	        return habitacion.get();
	}
	/**
     * Método para guardar una nueva habitación.
     * @param habitacion Un objeto Habitacion que representa la habitación a guardar.
     * @return Un objeto Habitacion que representa la habitación guardada.
     * @throws IllegalOperationException si ocurre un error al guardar la habitación.
     */
	@Override
	@Transactional
	public Habitacion guardarHabitacion(Habitacion habitacion) throws IllegalOperationException {
		return habitacionRepository.save(habitacion);
	}
	/**
     * Método para actualizar los detalles de una habitación existente.
     * @param idHabitacion El ID de la habitación a actualizar.
     * @param habitacion Un objeto Habitacion que representa la habitación con los detalles actualizados.
     * @return Un objeto Habitacion que representa la habitación actualizada.
     * @throws EntityNotFoundException si no se encuentra una habitación con el ID proporcionado.
     * @throws IllegalOperationException si ocurre un error al actualizar la habitación.
     */
	@Override
	@Transactional
	public Habitacion actualizarHabitacion(Long idHabitacion, Habitacion habitacion) throws EntityNotFoundException, IllegalOperationException {
		 Optional<Habitacion> habitacionOptional = habitacionRepository.findById(idHabitacion);
		    if (!habitacionOptional.isPresent()) {
		        throw new EntityNotFoundException(ErrorMessage.HABITACION_NOT_FOUND);
		    }
		habitacion.setIdHabitacion(idHabitacion);
		return habitacionRepository.save(habitacion);
	}
	 /**
     * Método para eliminar una habitación existente.
     * @param idHabitacion El ID de la habitación a eliminar.
     * @throws EntityNotFoundException si no se encuentra una habitación con el ID proporcionado.
     * @throws IllegalOperationException si ocurre un error al eliminar la habitación.
     */
	@Override
	@Transactional
	public void eliminarHabitacion(Long idHabitacion) throws EntityNotFoundException, IllegalOperationException {
		 Optional<Habitacion> habitacionOptional = habitacionRepository.findById(idHabitacion);
		    if (!habitacionOptional.isPresent()) {
		        throw new EntityNotFoundException(ErrorMessage.HABITACION_NOT_FOUND);
		    }
		    
		    Habitacion habitacion = habitacionOptional.get();
		    if (!habitacion.getReservas().isEmpty()) {
		        throw new IllegalOperationException("La habitación tiene reservas asignadas");
		    }
		habitacionRepository.deleteById(idHabitacion);
	}
	@Override
	@Transactional
	public Habitacion actualizarCampoHabitacion(Long id, Habitacion habitacion) throws EntityNotFoundException{
	    Optional<Habitacion> habitacionEntity = habitacionRepository.findById(id);
	    if(habitacionEntity.isEmpty()) {
	        throw new EntityNotFoundException(ErrorMessage.HABITACION_NOT_FOUND);
	    }
	    habitacion.setIdHabitacion(id);
	    if(habitacion.getTipo() == null) {
	        habitacion.setTipo(habitacionEntity.get().getTipo());
	    } 
	    if(habitacion.isDisponible() != habitacionEntity.get().isDisponible()) {
	        habitacion.setDisponible(habitacionEntity.get().isDisponible());
	    }
	    if(habitacion.getPrecio() == null) {
	        habitacion.setPrecio(habitacionEntity.get().getPrecio());
	    }
	    if(habitacion.getDescripcion() == null) {
	        habitacion.setDescripcion(habitacionEntity.get().getDescripcion());
	    }
	    return habitacionRepository.save(habitacion);
	}


}
