/**
 * @file: EmpleadoServiceImp.java
 * @author: (c)2024 Julcamoro
 * @created: Mar 03, 2024 8:42:55 PM
 */

package com.equipo02.hotel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.equipo02.hotel.domain.Empleado;
import com.equipo02.hotel.domain.Habitacion;
import com.equipo02.hotel.domain.Huesped;
import com.equipo02.hotel.domain.Reserva;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.ErrorMessage;
import com.equipo02.hotel.exception.IllegalOperationException;

import com.equipo02.hotel.repositories.EmpleadoRepository;
/**
 * Implementación de los servicios disponibles para la entidad Empleado.
 */
@Service
public class EmpleadoServiceImp implements EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRep;
	
	 /**
     * Método para obtener una lista de todos los empleados.
     * @return Una lista de objetos Empleado.
     */
	@Override
	@Transactional(readOnly = true)
	public List<Empleado> listarEmpleados() {
		return empleadoRep.findAll();
	}
	
	/**
     * Método para buscar un empleado por su ID.
     * @param idEmpleado El ID del empleado a buscar.
     * @return Un objeto Empleado que representa el empleado encontrado.
     * @throws EntityNotFoundException si no se encuentra un empleado con el ID proporcionado.
     */
	@Override
	@Transactional(readOnly = true)
	public Empleado buscarPorId(Long idEmpleado) throws EntityNotFoundException {
		Optional<Empleado> empleado = empleadoRep.findById(idEmpleado);
		if(empleado.isEmpty()) 
			throw new EntityNotFoundException(ErrorMessage.EMPLEADO_NOT_FOUND) ;
		return empleado.get();
	}
	
	/**
     * Método para guardar un nuevo empleado.
     * @param empleado Un objeto Empleado que representa el Empleado a guardar.
     * @return Un objeto Empleado que representa el empleado guardado.
     * @throws IllegalOperationException si ocurre un error al guardar el empleado.
     */
	@Override
	@Transactional
	public Empleado grabar(Empleado empleado) throws IllegalOperationException {
		
		Empleado empleadoDni = empleadoRep.findByDniEmpleado(empleado.getDniEmpleado());
		if(empleadoDni != null) {
			throw new IllegalOperationException("El dni del empleado ya existe");
		}
		return empleadoRep.save(empleado);
	}

	/**
     * Método para actualizar los detalles de un empleado existente.
     * @param idHabitacion El ID del empleado a actualizar.
     * @param Un objeto empleado que representa el empleado con los detalles actualizados.
     * @return Un objeto Empleado que representa el empleado actualizado.
     * @throws EntityNotFoundException si no se encuentra un empleado con el ID proporcionado.
     * @throws IllegalOperationException si ocurre un error al actualizar el empleado.
     */
	@Override
	@Transactional
	public Empleado actualizar(Long id, Empleado empleado)throws EntityNotFoundException, IllegalOperationException {
		Optional<Empleado> empEntity = empleadoRep.findById(id);
		if(empEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.EMPLEADO_NOT_FOUND);
		if(empleado.getNombreEmpleado().isBlank()) {
			throw new IllegalOperationException("El nombre del empleado no debe estar en blanco");
		}
		empleado.setIdEmpleado(id);		
		return empleadoRep.save(empleado);
	}

	 /**
     * Método para eliminar un empleado existente.
     * @param idHabitacion El ID del empleado a eliminar.
     * @throws EntityNotFoundException si no se encuentra un empleado con el ID proporcionado.
     * @throws IllegalOperationException si ocurre un error al eliminar el empleado.
     */
	@Override
	@Transactional
	public void eliminar(Long id) throws EntityNotFoundException{
		Optional<Empleado> empEntity = empleadoRep.findById(id);
		if(empEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.EMPLEADO_NOT_FOUND);
		empleadoRep.deleteById(id);
	}

	/**
	 * Método para actualizar los campos de un empleado existente.
	 * @param id El ID de la empleado a actualizar.
	 * @param empleadoDTO Objeto DTO que contiene los campos actualizados del empleado.
	 * @return ResponseEntity con un ApiResponse que contiene un mensaje de éxito junto con el empleado actualizado, junto con el código de estado HTTP 200 (OK) en caso de éxito.
	 * @throws EntityNotFoundException si no se encuentra un empleado con el ID proporcionado.
	 */
	@Override
	public Empleado actualizarPorCampo(Long id, Empleado empleado) throws EntityNotFoundException {
		Optional<Empleado> empleadoEntity = empleadoRep.findById(id);
		if(empleadoEntity.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.EMPLEADO_NOT_FOUND);
		}
		empleado.setIdEmpleado(id);
		
		if(empleado.getNombreEmpleado() == null) {
			empleado.setNombreEmpleado(empleadoEntity.get().getNombreEmpleado());
		} 
		if(empleado.getDniEmpleado() == null) {
			empleado.setDniEmpleado(empleadoEntity.get().getDniEmpleado());
		}
		if(empleado.getDireccionEmpleado() == null) {
			empleado.setDireccionEmpleado(empleadoEntity.get().getDireccionEmpleado());
		}
		if(empleado.getTelefonoEmpleado() == null) {
			empleado.setTelefonoEmpleado(empleadoEntity.get().getTelefonoEmpleado());
		}
		if(empleado.getCorreoEmpleado() == null) {
			empleado.setCorreoEmpleado(empleadoEntity.get().getCorreoEmpleado());
		}		
		return empleadoRep.save(empleado);
	}

	
	/**
	 * Método para obtener una reserva específica de un empleado.
	 *
	 * @param idEmpleado El ID del Empleado.
	 * @param idReserva El ID de la reserva.
	 * @return La reserva solicitada.
	 * @throws EntityNotFoundException Si no se encuentra la reserva en el Empleado.
	 *
	 * Este método busca una habitación por su ID y luego busca una reserva específica dentro de las reservas de ese Empleado.
	 * Si la reserva no se encuentra en el Empleado, se lanza una excepción.
	 */
	@Override
	public Reserva obtenerReservaDeEmpleado(Long idEmpleado, Long idReserva) throws EntityNotFoundException {
		Empleado empleado= buscarPorId(idEmpleado);
	    Optional<Reserva> optionalReserva = empleado.getReservas().stream()
	            .filter(r -> r.getIdReserva().equals(idReserva))
	            .findFirst();
	    if (optionalReserva.isEmpty()) {
	        throw new EntityNotFoundException("Reserva no encontrada en la habitación");
	    }
	    return optionalReserva.get();
	}

	/*@Override
	public Empleado asignarSupervisor(Long idEmpleado, Long idSupervisor) {
		Optional<Empleado> empleadoEntity =  empleadoRep.findById(idEmpleado);
		Optional<Empleado> supervisorEntity = empleadoRep.findById(idSupervisor);
		empleadoEntity.get().setSupervisor(supervisorEntity.get());
		return empleadoRep.save(supervisorEntity.get());
	}*/

}
