
package com.equipo02.hotel.services;

import java.util.List;

import com.equipo02.hotel.domain.Empleado;
import com.equipo02.hotel.domain.Huesped;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.IllegalOperationException;

public interface EmpleadoService {
	List<Empleado> listarEmpleados();
	Empleado buscarPorId(Long id) throws EntityNotFoundException;
	Empleado grabar(Empleado empleado) throws IllegalOperationException;
	Empleado actualizar(Long id, Empleado empleado) throws EntityNotFoundException, IllegalOperationException;
	void eliminar(Long id) throws EntityNotFoundException, IllegalOperationException;
	Empleado actualizarPorCampo(Long id, Empleado empleado) throws EntityNotFoundException;
	//	Empleado asignarSupervisor(Long idEmpleado, Long idSupervisor);
}
