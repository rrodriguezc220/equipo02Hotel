/**
 * @file: ReservaServiceImp.java
 * @author: (c) 2024 MARCO
 * @created: 29 feb. 2024 17:23:03
 */
package com.equipo02.hotel.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.equipo02.hotel.domain.Reserva;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.IllegalOperationException;
import com.equipo02.hotel.repositories.ReservaRepository;


@Service
public class ReservaServiceImp implements ReservaService {
	
	@Autowired
	private ReservaRepository reservaRepository;
	//@Autowired
	//private HabitacionRepository habitacionRepository;
	
	
    /**
     * Devuelve todas las reservas que hay en la base de datos.
     *
     * @return Lista de entidades de tipo Reserva.
     */
	@Override
	@Transactional(readOnly = true)
	public List<Reserva> listarTodos() {
		return (List<Reserva>) reservaRepository.findAll();
	}
	

    /**
     * Devuelve la reserva con la id proporcionada.
     *
     * @param idReserva El id de la reserva a buscar.
     * @return La reserva encontrada.
     * @throws EntityNotFoundException si la reserva con el id asignado no es encontrado en la persistencia.
     */
	@Override
	@Transactional(readOnly = true)
	public Reserva buscarPorIdReserva(Long idReseva) throws EntityNotFoundException {
		Optional<Reserva> reserva = reservaRepository.findById(idReseva);
		//if (reserva.isEmpty()) throw new EntityExistsException(ErrorMessage.RESERVA_NOT_FOUND);
		return reserva.get();
	}
	
	
    /**
     * Guarda una nueva reserva.
     *
     * @param reserva La reserva a ser guardada.
     * @return El objeto reserva luego de persistirlo.
     * @throws IllegalOperationException si el id de la reserva es inválido o ya existe en la persistencia.
     */
	@Override
	@Transactional
	public Reserva guardarReserva(Reserva reserva) throws IllegalOperationException {
		/*
		if(!reservaRepository.findById(reserva.getIdReserva()).isEmpty()) {
			throw new IllegalOperationException("El nombre del departamento ya existe");
		}
		*/
		return reservaRepository.save(reserva);
	}
	
	
    /**
     * Actualiza una reserva existente.
     *
     * @param idReserva El id de la reserva a actualizar.
     * @param reserva   La nueva información de la reserva.
     * @return El objeto reserva actualizado.
     * @throws EntityNotFoundException    si la reserva con el id asignado no es encontrado en la persistencia.
     * @throws IllegalOperationException si el id de la reserva es inválido o ya existe en la persistencia.
     */
	@Override
	@Transactional
	public Reserva actualizarReserva(Long idReserva, Reserva reserva) throws EntityNotFoundException, IllegalOperationException {
		
		/*
		Optional<Reserva> reservaEntity = reservaRepository.findById(idReserva);
		if(reservaEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND);
		*/
		
		/*
	    Reserva reservaActualizada = reservaEntity.get();

	    reservaActualizada.setFechaInicio(reserva.getFechaInicio());
	    reservaActualizada.setFechaFin(reservaActualizada.getFechaFin());
	    reservaActualizada.setEstado(reserva.isEstado());
	    */
		reserva.setIdReserva(idReserva);
		return reservaRepository.save(reserva);
	}
	
	
    /**
     * Elimina una reserva.
     *
     * @param idReserva El id de la reserva a eliminar.
     * @throws EntityNotFoundException    si la reserva con el id asignado no es encontrado en la persistencia.
     * @throws IllegalOperationException si el id de la reserva es inválido o ya existe en la persistencia.
     */

	@Override
	@Transactional
	public void eliminarReserva(Long idReserva) throws EntityNotFoundException, IllegalOperationException {
		/*
		Reserva reserva = reservaRepository.findById(idReserva).orElseThrow(
				()->new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND)
				);
		if (!reserva.getEmpleados().isEmpty()) {
			throw new IllegalOperationException("El departamento tiene empleados asignados");
		}
		if (!(reserva.getHuespedes().isEmpty()) {
			throw new IllegalOperationException("El departamento tiene huespedes asignados");
		}
		if (!(reserva.getHabitaciones().isEmpty()) {
			throw new IllegalOperationException("El departamento tiene habitaciones asignadas");
		}
		*/
		
		reservaRepository.deleteById(idReserva);
	}
	
	
	/*
	public Reserva asignarHabitacion (Long idReserva, Long idHabitacion) throws EntityNotFoundException, IllegalOperationException{
    	
		Optional<Reserva> reservaEntity =  reservaRepository.findById(idReserva);
		Optional<Habitacion> habitacionEntity = habitacionRepository.findById(idHabitacion);
		
		reservaEntity.get().setHabitaciones(habitacionEntity.get());
		return reservaRepository.save(reservaEntity.get());
		
		
    	return null;
    }
    */

}


