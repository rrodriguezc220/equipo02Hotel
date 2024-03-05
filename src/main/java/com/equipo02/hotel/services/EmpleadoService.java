/**
 * @file: ReservaService.java
 * @author: (c) 2024 Julcamoro
 * @created: 29 feb. 2024 18:10:18
 */
package com.equipo02.hotel.services;

import java.util.List;

import com.equipo02.hotel.domain.Empleado;
import com.equipo02.hotel.domain.Huesped;
import com.equipo02.hotel.domain.Reserva;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.IllegalOperationException;

/**
 * Interfaz que define los servicios relacionados con los empleados en el hotel.
 */
public interface EmpleadoService {
	/**
     * Obtiene una lista de todas los empleados en el sistema.
     * @return Una lista de objetos Empleado.
     */
	List<Empleado> listarEmpleados();
	
	 /**
     * Busca un empleado por su identificador único.
     * @param id El identificador único del empleado.
     * @return El empleado encontrada.
     * @throws EntityNotFoundException Si el empleado con el id dado no es encontrada.
     */
	Empleado buscarPorId(Long id) throws EntityNotFoundException;
	
	/**
     * Guarda un nuevo empleado en el sistema.
     * @param empleado El empleado a ser guardada.
     * @return El empleado guardada.
     * @throws IllegalOperationException Si el empleado es inválido o ya existe en el sistema.
     */
	Empleado grabar(Empleado empleado) throws IllegalOperationException;
	
	 /**
     * Actualiza un empleado existente en el sistema.
     * @param id El identificador único del empleado a actualizar.
     * @param empleado La nueva información del empleado.
     * @return El empleado actualizado.
     * @throws EntityNotFoundException Si el empleado con el id dado no es encontrado.
     * @throws IllegalOperationException Si el empleado es inválido.
     */
	Empleado actualizar(Long id, Empleado empleado) throws EntityNotFoundException, IllegalOperationException;
	
	 /**
     * Elimina un empleado del sistema.
     * @param id El identificador único del empleado a eliminar.
     * @throws EntityNotFoundException Si el empleado con el id dado no es encontrado.
     * @throws IllegalOperationException Si el empleado tiene dependencias que impiden su eliminación.
     */
	void eliminar(Long id) throws EntityNotFoundException, IllegalOperationException;
	
	
	 /**
     * Actualiza un campo específico de un empleado existente en el sistema.
     * @param id El identificador único del empleado a actualizar.
     * @return El empleado actualizada.
     * @throws EntityNotFoundException Si el empleado con el id dado no es encontrado.
     * @throws IllegalOperationException Si el empleado es inválido.
     */
	Empleado actualizarPorCampo(Long id, Empleado empleado) throws EntityNotFoundException;
	
	  /**
     * Método para obtener una Reserva específica asociada con un Empleado en particular.
     *
     * @param idEmpleado El ID del Empleado.
     * @param idReserva El ID de la Reserva.
     * @return Un objeto Reserva que representa la Reserva asociada con el Empleado.
     * @throws EntityNotFoundException Si no se encuentra un Empleado con el ID proporcionado o si el Empleado no tiene una Reserva con el ID proporcionado.
     */
    Reserva obtenerReservaDeEmpleado(Long idEmpleado, Long idReserva) throws EntityNotFoundException;
	
	//	Empleado asignarSupervisor(Long idEmpleado, Long idSupervisor);
}
