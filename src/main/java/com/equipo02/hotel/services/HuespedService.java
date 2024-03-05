/**
 * @file: HuespedService.java
 * @author: (c)2024 Rodriguez
 * @created: 3 mar. 2024 17:12:52
 */

package com.equipo02.hotel.services;

import java.util.List;

import com.equipo02.hotel.domain.Huesped;
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
	 */
	Huesped grabar(Huesped huesped);
	
	/**
	 * Actualiza los datos de un huésped existente.
	 * 
	 * @param id El ID del huésped a actualizar.
	 * @param huesped Los nuevos datos del huésped.
	 * @return El huésped actualizado.
	 * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
	 */
	Huesped actualizar(Long id, Huesped huesped) throws EntityNotFoundException;
	
	/**
	 * Elimina un huésped por su ID.
	 * 
	 * @param id El ID del huésped a eliminar.
	 * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
	 * @throws IllegalOperationException Si hay operaciones ilegales, como eliminar un huésped que es aval de otro.
	 */
	void eliminar(Long id) throws EntityNotFoundException, IllegalOperationException;
	
	/**
	 * Asigna un aval a un huésped.
	 * 
	 * @param idHuesped El ID del huésped al que se asignará el aval.
	 * @param idAval El ID del huésped que actuará como aval.
	 * @return El huésped con el aval asignado.
	 * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
	 * @throws IllegalOperationException Si hay operaciones ilegales, como asignar un huésped como aval de sí mismo.
	 */
	Huesped asignarAval(Long idHuesped, Long idAval) throws EntityNotFoundException, IllegalOperationException;
	
	/**
	 * Elimina el aval de un huésped.
	 * 
	 * @param id El ID del huésped del que se eliminará el aval.
	 * @return El huésped con el aval eliminado.
	 * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
	 * @throws IllegalOperationException Si hay operaciones ilegales, como eliminar el aval de un huésped que no tiene uno.
	 */
	Huesped eliminarAval(Long id) throws EntityNotFoundException, IllegalOperationException;
	
	/**
	 * Actualiza ciertos campos de un huésped existente.
	 * 
	 * @param id El ID del huésped cuyos campos se actualizarán.
	 * @param huesped El objeto Huesped que contiene los nuevos valores de los campos a actualizar.
	 * @return El huésped actualizado.
	 * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
	 */
	Huesped actualizarPorCampos(Long id, Huesped huesped) throws EntityNotFoundException;
}
