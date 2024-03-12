/**
 * @file: HuespedServiceImp.java
 * @author: (c)2024 Rodriguez
 * @created: 3 mar. 2024 17:05:26
 */

package com.equipo02.hotel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.equipo02.hotel.domain.Habitacion;
import com.equipo02.hotel.domain.Huesped;
import com.equipo02.hotel.domain.Reserva;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.ErrorMessage;
import com.equipo02.hotel.exception.IllegalOperationException;
import com.equipo02.hotel.repositories.HabitacionRepository;
import com.equipo02.hotel.repositories.HuespedRepository;
import com.equipo02.hotel.repositories.ReservaRepository;

/**
 * Implementación de la interfaz HuespedService que proporciona operaciones relacionadas con los huéspedes.
 */
@Service
public class HuespedServiceImp implements HuespedService {
	
	@Autowired
	private HuespedRepository huespedRep;
	
	@Autowired
	private ReservaRepository reservaRep;
	
	@Autowired
	private HabitacionRepository habitacionRep;
	
	/**
     * Lista todos los huéspedes disponibles.
     * 
     * @return Una lista de todos los huéspedes disponibles.
     */
	@Override
	@Transactional
	public List<Huesped> listarHuespedes() {
		return huespedRep.findAll();
	}
	
	/**
     * Busca un huésped por su ID.
     * 
     * @param id El ID del huésped a buscar.
     * @return El huésped correspondiente al ID proporcionado.
     * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
     */
	@Override
	@Transactional
	public Huesped buscarPorId(Long id) throws EntityNotFoundException{
		Optional<Huesped> huesped = huespedRep.findById(id);
		if(huesped.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.HUESPED_NOT_FOUND);
		}
		return huesped.get();
	}

	/**
	 * Graba un nuevo huésped en la base de datos.
	 * 
	 * @param huesped El huésped a grabar.
	 * @return El huésped grabado.
	 * @throws EntityNotFoundException Si el aval no puede ser encontrado.
	 * @throws IllegalOperationException Si hay operaciones ilegales.
	 */
	@Override
	@Transactional
	public Huesped grabar(Huesped huesped) throws EntityNotFoundException, IllegalOperationException{
		if (huesped.getAval() != null) {
			Optional<Huesped> avalEntity = huespedRep.findById(huesped.getAval().getIdHuesped());
			if(avalEntity.isEmpty()) {
				throw new EntityNotFoundException("El aval con id proporcionado no fue encontrado");
			}
			List<Huesped> huespedesConEsteAval = huespedRep.findByAval(avalEntity.get());
			if (!huespedesConEsteAval.isEmpty()) { 
				throw new IllegalOperationException("El aval ya fue designado");
			}
		}
		Huesped huespedDni = huespedRep.findByDniHuesped(huesped.getDniHuesped());
		if(huespedDni != null) {
			throw new IllegalOperationException("El dni del huesped ya existe");
		}
		return huespedRep.save(huesped);
	}

	/**
	 * Actualiza los datos de un huésped existente.
	 * 
	 * @param id El ID del huésped a actualizar.
	 * @param huesped Los nuevos datos del huésped.
	 * @return El huésped actualizado.
	 * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
	 * @throws IllegalOperationException Si hay operaciones ilegales.
	 */
	@Override
	@Transactional
	public Huesped actualizar(Long id, Huesped huesped) throws EntityNotFoundException, IllegalOperationException{
		Optional<Huesped> huespedEntity = huespedRep.findById(id);
		if(huespedEntity.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.HUESPED_NOT_FOUND);
		}
		if (huesped.getAval() != null) {
			Optional<Huesped> avalEntity = huespedRep.findById(huesped.getAval().getIdHuesped());
			if(avalEntity.isEmpty()) {
				throw new EntityNotFoundException("El aval con id proporcionado no fue encontrado");
			}
			if(id == avalEntity.get().getIdHuesped()) {
				throw new IllegalOperationException("El huesped no se puede avalar asi mismo");
			}
			List<Huesped> huespedesConEsteAval = huespedRep.findByAval(avalEntity.get());
			if (!huespedesConEsteAval.isEmpty()) { 
				throw new IllegalOperationException("El aval ya fue designado");
			}
		}
		Huesped huespedDni = huespedRep.findByDniHuesped(huesped.getDniHuesped());
		if(huespedDni != null && huespedDni != huespedEntity.get()) {
			throw new IllegalOperationException("El dni del huesped ya existe");
		}
		huesped.setIdHuesped(id);
		return huespedRep.save(huesped);
	}

	/**
	 * Elimina un huésped por su ID.
	 * 
	 * @param id El ID del huésped a eliminar.
	 * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
	 * @throws IllegalOperationException Si hay operaciones ilegales.
	 */
	@Override
	@Transactional
	public void eliminar(Long id) throws EntityNotFoundException, IllegalOperationException{
		Optional<Huesped> huespedEntity = huespedRep.findById(id);
		if(huespedEntity.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.HUESPED_NOT_FOUND);
		}

		List<Huesped> huespedesConEsteAval = huespedRep.findByAval(huespedEntity.get());

		if (!huespedesConEsteAval.isEmpty()) { 
			throw new IllegalOperationException("No se puede eliminar, el huesped es aval de un cliente");
		}
		huespedRep.deleteById(id);
	}

	/**
	 * Asigna un aval a un huésped.
	 * 
	 * @param idHuesped El ID del huésped al que se asignará el aval.
	 * @param idAval El ID del huésped que actuará como aval.
	 * @return El huésped con el aval asignado.
	 * @throws EntityNotFoundException Si el huésped o el aval no pueden ser encontrados.
	 * @throws IllegalOperationException Si hay operaciones ilegales.
	 */
	@Override
	@Transactional
	public Huesped asignarAval(Long idHuesped, Long idAval) throws EntityNotFoundException, IllegalOperationException{
		Optional<Huesped> huespedEntity =  huespedRep.findById(idHuesped);
		Optional<Huesped> avalEntity = huespedRep.findById(idAval);
		if(huespedEntity.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.HUESPED_NOT_FOUND);
		}
		if(avalEntity.isEmpty()) {
			throw new EntityNotFoundException("El aval con id proporcionado no fue encontrado");
		}
		if(idHuesped == idAval) {
			throw new IllegalOperationException("El huesped no se puede avalar asi mismo");
		}
		List<Huesped> huespedesConEsteAval = huespedRep.findByAval(avalEntity.get());
		if (!huespedesConEsteAval.isEmpty()) { 
			throw new IllegalOperationException("El aval fue designado a otro huesped");
		}
		huespedEntity.get().setAval(avalEntity.get());
		return huespedRep.save(huespedEntity.get());
	}

	/**
	 * Elimina el aval de un huésped.
	 * 
	 * @param id El ID del huésped del que se eliminará el aval.
	 * @return El huésped con el aval eliminado.
	 * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
	 * @throws IllegalOperationException Si el huésped no tiene aval.
	 */
	@Override
	@Transactional
	public Huesped eliminarAval(Long id) throws EntityNotFoundException, IllegalOperationException{
		Optional<Huesped> huespedEntity = huespedRep.findById(id);
		if(huespedEntity.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.HUESPED_NOT_FOUND);
		}
		if(huespedEntity.get().getAval() == null) {
			throw new IllegalOperationException("El huesped no tiene aval");
		}
		huespedEntity.get().setAval(null);
		return huespedRep.save(huespedEntity.get());
	}

	/**
	 * Actualiza ciertos campos de un huésped existente.
	 * 
	 * @param id El ID del huésped cuyos campos se actualizarán.
	 * @param huesped El objeto Huesped que contiene los nuevos valores de los campos a actualizar.
	 * @return El huésped actualizado.
	 * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
	 * @throws IllegalOperationException Si hay operaciones ilegales, como asignar un huésped como aval de sí mismo.
	 */
	@Override
	@Transactional
	public Huesped actualizarPorCampos(Long id, Huesped huesped) throws EntityNotFoundException, IllegalOperationException{
		Optional<Huesped> huespedEntity = huespedRep.findById(id);
		if(huespedEntity.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.HUESPED_NOT_FOUND);
		}
		huesped.setIdHuesped(id);
		if(huesped.getNombreHuesped() == null) {
			huesped.setNombreHuesped(huespedEntity.get().getNombreHuesped());
		} 
		if(huesped.getDniHuesped() == null) {
			huesped.setDniHuesped(huespedEntity.get().getDniHuesped());
		} else {
			Huesped huespedDni = huespedRep.findByDniHuesped(huesped.getDniHuesped());
			if(huespedDni != null) {
				throw new IllegalOperationException("El dni del huesped ya existe");
			}
		}
		if(huesped.getDireccionHuesped() == null) {
			huesped.setDireccionHuesped(huespedEntity.get().getDireccionHuesped());
		}
		if(huesped.getTelefonoHuesped() == null) {
			huesped.setTelefonoHuesped(huespedEntity.get().getTelefonoHuesped());
		}
		if(huesped.getCorreoHuesped() == null) {
			huesped.setCorreoHuesped(huespedEntity.get().getCorreoHuesped());
		}
		if(huesped.getAval() == null) {
			huesped.setAval(huespedEntity.get().getAval());
		} else {
			Optional<Huesped> avalEntity = huespedRep.findById(huesped.getAval().getIdHuesped());
			if(avalEntity.isEmpty()) {
				throw new EntityNotFoundException("El aval con id proporcionado no fue encontrado");
			}
			if(id == avalEntity.get().getIdHuesped()) {
				throw new IllegalOperationException("El huesped no se puede avalar asi mismo");
			}
			List<Huesped> huespedesConEsteAval = huespedRep.findByAval(avalEntity.get());
			if (!huespedesConEsteAval.isEmpty()) { 
				throw new IllegalOperationException("El aval ya fue designado");
			}
		}
		return huespedRep.save(huesped);
	}
	
	//Subservicios
	
	@Override
	@Transactional
    public List<Reserva> obtenerReservasPorHuesped(Long idHuesped) throws EntityNotFoundException {
		Optional<Huesped> huesped = huespedRep.findById(idHuesped);
		if(huesped.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.HUESPED_NOT_FOUND);
		}
		
		List<Reserva> reservas = huesped.get().getReservas();
		
		if(reservas.isEmpty()) {
			throw new EntityNotFoundException("No se encontraron reservas asociadas al huésped");
		}
		
		return reservas;
    }
	
	/**
	 * Obtiene una reserva específica de un huésped.
	 *
	 * @param idHuesped El ID del huésped.
	 * @param idReserva El ID de la reserva.
	 * @return La reserva solicitada.
	 * @throws EntityNotFoundException Si el huésped o la reserva no pueden ser encontrados.
	 */
	@Override
    @Transactional
    public Reserva obtenerReservaDeHuesped(Long idHuesped, Long idReserva) throws EntityNotFoundException {
		Optional<Huesped> huesped = huespedRep.findById(idHuesped);
		if(huesped.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.HUESPED_NOT_FOUND);
		}

		Optional<Reserva> reserva = reservaRep.findById(idReserva);
		if(reserva.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND);
		}

        return reserva.get();
    }
	
	/**
	 * Obtiene las habitaciones asociadas a una reserva de un huésped.
	 *
	 * @param idHuesped El ID del huésped.
	 * @param idReserva El ID de la reserva.
	 * @return La lista de habitaciones asociadas a la reserva.
	 * @throws EntityNotFoundException Si el huésped, la reserva o las habitaciones no pueden ser encontrados.
	 */
	@Override
	@Transactional
	public List<Habitacion> obtenerHabitacionesPorReserva(Long idHuesped, Long idReserva) throws EntityNotFoundException {
		Optional<Huesped> huesped = huespedRep.findById(idHuesped);
		if(huesped.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.HUESPED_NOT_FOUND);
		}
		
		Optional<Reserva> reserva = reservaRep.findById(idReserva);
		if(reserva.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND);
		}
		
		List<Habitacion> habitaciones = reserva.get().getHabitaciones();
		if(habitaciones.isEmpty()) {
			throw new EntityNotFoundException("No se encontraron habitaciones asociadas a la reserva");
		}
		
		return habitaciones;
    }
	
	/**
	 * Obtiene una habitación específica asociada a una reserva de un huésped.
	 *
	 * @param idHuesped El ID del huésped.
	 * @param idReserva El ID de la reserva.
	 * @param idHabitacion El ID de la habitación.
	 * @return La habitación asociada a la reserva.
	 * @throws EntityNotFoundException Si el huésped, la reserva o la habitación no pueden ser encontrados.
	 */
	@Override
    @Transactional
    public Habitacion obtenerHabitacionDeReserva(Long idHuesped, Long idReserva, Long idHabitacion) throws EntityNotFoundException {
		Optional<Huesped> huesped = huespedRep.findById(idHuesped);
		if(huesped.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.HUESPED_NOT_FOUND);
		}

		Optional<Reserva> reserva = reservaRep.findById(idReserva);
		if(reserva.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND);
		}
		
		Optional<Habitacion> habitacion = habitacionRep.findById(idHabitacion);
		if(habitacion.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.HABITACION_NOT_FOUND);
		}

        return habitacion.get();
    }
}
