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

import com.equipo02.hotel.domain.Habitacion;
import com.equipo02.hotel.domain.Reserva;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.ErrorMessage;
import com.equipo02.hotel.exception.IllegalOperationException;
import com.equipo02.hotel.repositories.HabitacionRepository;
import com.equipo02.hotel.repositories.ReservaRepository;


@Service
public class ReservaServiceImp implements ReservaService {
	
	@Autowired
	private ReservaRepository reservaRepository;
	@Autowired
	private HabitacionRepository habitacionRepository;
	
	
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
		Optional<Reserva> reservaEntity = reservaRepository.findById(idReseva);
		if(reservaEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND);
		return reservaEntity.get();
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
		
		Optional<Reserva> reservaEntity = reservaRepository.findById(idReserva);
		if(reservaEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND);
		
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
	    Optional<Reserva> reservaOptional = reservaRepository.findById(idReserva);
	    if (!reservaOptional.isPresent()) {
	        throw new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND);
	    }
	    Reserva reserva = reservaOptional.get();
	    if (!(reserva.getEmpleado() == null)) {
	        throw new IllegalOperationException("La reserva tiene empleados asignados");
	    }
	    if (!(reserva.getHuesped() == null)) {
	        throw new IllegalOperationException("La reserva tiene huespedes asignados");
	    }
	    if (!reserva.getHabitaciones().isEmpty()) {
	        throw new IllegalOperationException("La reserva tiene habitaciones asignadas");
	    }
	    reservaRepository.deleteById(idReserva);
	}
	
	
	@Override
	@Transactional
	public Reserva asignarHabitacion(Long idReserva, Long idHabitacion) throws EntityNotFoundException, IllegalOperationException {
	    Reserva reserva = buscarPorIdReserva(idReserva);
	    Optional<Habitacion> optionalHabitacion = habitacionRepository.findById(idHabitacion);
	    if (optionalHabitacion.isEmpty()) {
	        throw new EntityNotFoundException(ErrorMessage.HABITACION_NOT_FOUND);
	    }	    
	    Habitacion habitacion = optionalHabitacion.get();
	    if (reserva.getHabitaciones().contains(habitacion)) {
	        throw new IllegalOperationException("La habitación ya está asignada a la reserva");
	    }
	    reserva.getHabitaciones().add(habitacion);
	    return reservaRepository.save(reserva);
	}
	
	@Override
	@Transactional
	public Reserva eliminarHabitacion(Long idReserva, Long idHabitacion) throws EntityNotFoundException, IllegalOperationException {
	    Reserva reserva = buscarPorIdReserva(idReserva);
	    Optional<Habitacion> optionalHabitacion = habitacionRepository.findById(idHabitacion);
	    if (optionalHabitacion.isEmpty()) {
	        throw new EntityNotFoundException(ErrorMessage.HABITACION_NOT_FOUND);
	    }
	    Habitacion habitacion = optionalHabitacion.get();
	    if (!reserva.getHabitaciones().contains(habitacion)) {
	        throw new IllegalOperationException("La habitación no está asignada a la reserva");
	    }
	    reserva.getHabitaciones().remove(habitacion);
	    return reservaRepository.save(reserva);
	}

	
	
	

}


