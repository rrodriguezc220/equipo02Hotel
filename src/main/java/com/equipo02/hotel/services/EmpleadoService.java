package com.equipo02.hotel.services;

import java.util.List;

import com.equipo02.hotel.domain.Empleado;

public interface EmpleadoService {
	List<Empleado> listarEmpleados();
	Empleado buscarPorId(Long id);
	Empleado grabar(Empleado empleado);
	Empleado actualizar(Long id, Empleado empleado);
	void eliminar(Long id);
//	Empleado asignarSupervisor(Long idEmpleado, Long idSupervisor);
}
