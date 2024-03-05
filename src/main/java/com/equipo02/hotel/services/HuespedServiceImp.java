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

import com.equipo02.hotel.domain.Huesped;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.ErrorMessage;
import com.equipo02.hotel.exception.IllegalOperationException;
import com.equipo02.hotel.repositories.HuespedRepository;

/**
 * Implementación de la interfaz HuespedService que proporciona operaciones relacionadas con los huéspedes.
 */
@Service
public class HuespedServiceImp implements HuespedService {
	
	@Autowired
	private HuespedRepository huespedRep;
	
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
     * Guarda un nuevo huésped en la base de datos.
     * 
     * @param huesped El huésped que se va a guardar.
     * @return El huésped guardado.
     */
	@Override
	@Transactional
	public Huesped grabar(Huesped huesped){
		return huespedRep.save(huesped);
	}

	/**
     * Actualiza un huésped existente en la base de datos.
     * 
     * @param id El ID del huésped que se va a actualizar.
     * @param huesped El objeto Huesped con los nuevos datos para el huésped.
     * @return El huésped actualizado.
     * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
     */
	@Override
	@Transactional
	public Huesped actualizar(Long id, Huesped huesped) throws EntityNotFoundException{
		Optional<Huesped> huespedEntity = huespedRep.findById(id);
		if(huespedEntity.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.HUESPED_NOT_FOUND);
		}
		huesped.setIdHuesped(id);
		return huespedRep.save(huesped);
	}

	/**
     * Elimina un huésped de la base de datos.
     * 
     * @param id El ID del huésped que se va a eliminar.
     * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
     * @throws IllegalOperationException Si el huésped es aval de otro huésped.
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
     * @throws IllegalOperationException Si se intenta asignar un huésped como aval de sí mismo.
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
     */
	@Override
	@Transactional
	public Huesped actualizarPorCampos(Long id, Huesped huesped) throws EntityNotFoundException{
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
		}
		return huespedRep.save(huesped);
	}
}
