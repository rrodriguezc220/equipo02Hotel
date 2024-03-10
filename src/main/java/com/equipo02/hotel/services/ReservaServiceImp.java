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
import com.equipo02.hotel.domain.Empleado;
import com.equipo02.hotel.domain.Habitacion;
import com.equipo02.hotel.domain.Huesped;
import com.equipo02.hotel.domain.Reserva;
import com.equipo02.hotel.exception.BadRequestException;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.ErrorMessage;
import com.equipo02.hotel.exception.IllegalOperationException;
import com.equipo02.hotel.repositories.EmpleadoRepository;
import com.equipo02.hotel.repositories.HabitacionRepository;
import com.equipo02.hotel.repositories.HuespedRepository;
import com.equipo02.hotel.repositories.ReservaRepository;

/**
 * Implementación de los servicios disponibles para la entidad Reserva.
 */
@Service
public class ReservaServiceImp implements ReservaService {
	
	@Autowired
	private ReservaRepository reservaRepository;
	@Autowired
	private HabitacionRepository habitacionRepository;
	@Autowired
	private HuespedRepository huespedRepository;
	@Autowired
	private EmpleadoRepository empleadoRepository;
    
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
     * @param idReseva El id de la reserva a buscar.
     * @return La reserva encontrada.
     * @throws EntityNotFoundException si la reserva con el id asignado no es encontrado en la persistencia.
     */
	@Override
	@Transactional(readOnly = true)
	public Reserva buscarPorIdReserva(Long idReseva) throws EntityNotFoundException, BadRequestException{
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
	    if (reserva.getHuesped() != null && reserva.getHuesped().getIdHuesped() != null) {
	        Huesped huesped = huespedRepository.findById(reserva.getHuesped().getIdHuesped())
	                .orElseThrow(() -> new IllegalOperationException("El huésped especificado no existe"));
	        reserva.setHuesped(huesped);
	    } else {
	        throw new IllegalOperationException("El huésped especificado no es válido");
	    }
	    if (reserva.getEmpleado() != null && reserva.getEmpleado().getIdEmpleado() != null) {
	        Empleado empleado = empleadoRepository.findById(reserva.getEmpleado().getIdEmpleado())
	                .orElseThrow(() -> new IllegalOperationException("El empleado especificado no existe"));
	        reserva.setEmpleado(empleado);
	    } else {
	        throw new IllegalOperationException("El empleado especificado no es válido");
	    }
	    return reservaRepository.save(reserva);
	}
	
	
    /**
     * Actualiza una reserva existente.
     *
     * @param idReserva El ID de la reserva a actualizar.
     * @param reserva   La reserva con los nuevos datos.
     * @return La reserva actualizada.
     * @throws EntityNotFoundException    Si la reserva no se encuentra en la persistencia.
     * @throws IllegalOperationException   Si el ID de la reserva es inválido o ya existe en la persistencia.
     */
	@Override
	@Transactional
	public Reserva actualizarReserva(Long idReserva, Reserva reserva) throws EntityNotFoundException, IllegalOperationException {
	    Optional<Reserva> reservaEntity = reservaRepository.findById(idReserva);
	    if (reservaEntity.isEmpty())
	        throw new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND);
	    if (reserva.getHuesped() != null && reserva.getHuesped().getIdHuesped() != null) {
	        Huesped huesped = huespedRepository.findById(reserva.getHuesped().getIdHuesped())
	                .orElseThrow(() -> new IllegalOperationException("El huésped especificado no existe"));
	        reserva.setHuesped(huesped);
	    } else {
	        throw new IllegalOperationException("El huésped especificado no es válido");
	    }
	    if (reserva.getEmpleado() != null && reserva.getEmpleado().getIdEmpleado() != null) {
	        Empleado empleado = empleadoRepository.findById(reserva.getEmpleado().getIdEmpleado())
	                .orElseThrow(() -> new IllegalOperationException("El empleado especificado no existe"));
	        reserva.setEmpleado(empleado);
	    } else {
	        throw new IllegalOperationException("El empleado especificado no es válido");
	    }
	    reserva.setIdReserva(idReserva);
	    return reservaRepository.save(reserva);
	}

	
    /**
     * Actualiza campos específicos de una reserva existente.
     *
     * @param id      El ID de la reserva a actualizar.
     * @param reserva La reserva con los campos a actualizar.
     * @return La reserva actualizada.
     * @throws EntityNotFoundException    Si la reserva no se encuentra en la persistencia.
     * @throws IllegalOperationException   Si algún campo es inválido o no existe en la persistencia.
     */
	@Override
	@Transactional
	public Reserva actualizarCampoReserva(Long id, Reserva reserva) throws EntityNotFoundException, IllegalOperationException {
	    Optional<Reserva> reservaEntity = reservaRepository.findById(id);
	    if (reservaEntity.isEmpty()) {
	        throw new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND);
	    }
		if (reserva.getHuesped() != null && reserva.getHuesped().getIdHuesped() != null) {
	        Huesped huesped = huespedRepository.findById(reserva.getHuesped().getIdHuesped())
	                .orElseThrow(() -> new IllegalOperationException("El huésped especificado no existe"));
	        reserva.setHuesped(huesped);
	    } else {
	        reserva.setHuesped(reservaEntity.get().getHuesped());
	    }
		if (reserva.getEmpleado() != null && reserva.getEmpleado().getIdEmpleado() != null) {
	        Empleado empleado = empleadoRepository.findById(reserva.getEmpleado().getIdEmpleado())
	                .orElseThrow(() -> new IllegalOperationException("El empleado especificado no existe"));
	        reserva.setEmpleado(empleado);
	    } else {
	        reserva.setEmpleado(reservaEntity.get().getEmpleado());
	    }
		if (reserva.getFechaInicio() != null) {
	        reservaEntity.get().setFechaInicio(reserva.getFechaInicio());
	    }
	    if (reserva.getFechaFin() != null) {
	        reservaEntity.get().setFechaFin(reserva.getFechaFin());
	    }
	    if (reserva.isEstado()) {
	        reservaEntity.get().setEstado(reserva.isEstado());
	    }
		return reservaRepository.save(reservaEntity.get());
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
	    reservaRepository.deleteById(idReserva);
	}
	
    /**
     * Asigna una habitación a una reserva.
     *
     * @param idReserva    El id de la reserva a la que se desea asignar la habitación.
     * @param idHabitacion El id de la habitación a asignar.
     * @return La reserva actualizada con la habitación asignada.
     * @throws EntityNotFoundException    si la reserva o la habitación no se encuentra en la persistencia.
     * @throws IllegalOperationException si la habitación ya está asignada a la reserva.
     */
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
	
    /**
     * Elimina una habitación de una reserva.
     *
     * @param idReserva    El id de la reserva de la que se desea eliminar la habitación.
     * @param idHabitacion El id de la habitación a eliminar.
     * @return La reserva actualizada sin la habitación asignada.
     * @throws EntityNotFoundException    si la reserva o la habitación no se encuentra en la persistencia.
     * @throws IllegalOperationException si la habitación no está asignada a la reserva.
     */
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
	
	
	/**
	 * Obtiene una habitación específica de una reserva.
	 *
	 * @param idReserva    El id de la reserva.
	 * @param idHabitacion El id de la habitación.
	 * @return La habitación encontrada.
	 * @throws EntityNotFoundException si la reserva o la habitación no se encuentra.
	 */
	@Override
	@Transactional(readOnly = true)
	public Habitacion obtenerHabitacionDeReserva(Long idReserva, Long idHabitacion) throws EntityNotFoundException {
	    Reserva reserva = buscarPorIdReserva(idReserva);
	    Optional<Habitacion> optionalHabitacion = reserva.getHabitaciones().stream()
	            .filter(h -> h.getIdHabitacion().equals(idHabitacion))
	            .findFirst();
	    if (optionalHabitacion.isEmpty()) {
	        throw new EntityNotFoundException("Habitación no encontrada en la reserva");
	    }
	    return optionalHabitacion.get();
	}
	
	/**
	 * Obtiene todas las habitaciones de una reserva.
	 *
	 * @param idReserva El id de la reserva.
	 * @return Lista de habitaciones de la reserva.
	 * @throws EntityNotFoundException Si la reserva no se encuentra.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Habitacion> obtenerHabitacionesDeReserva(Long idReserva) throws EntityNotFoundException {
	    Reserva reserva = buscarPorIdReserva(idReserva);
	    return reserva.getHabitaciones();
	}

	
	
	
	
}


