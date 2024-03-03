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
import com.equipo02.hotel.repositories.EmpleadoRepository;

@Service
public class EmpleadoServiceImp implements EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRep;
	
	@Override
	@Transactional(readOnly = true)
	public List<Empleado> listarEmpleados() {
		return empleadoRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Empleado buscarPorId(Long id) {
		Optional<Empleado> empleado = empleadoRep.findById(id);
		//if(huesped.isEmpty()) throw new EntityNotFoundException(ErrorMessage.HUESPED_NOT_FOUND) ;
		return empleado.get();
	}

	@Override
	@Transactional
	public Empleado grabar(Empleado empleado) {
		// TODO Auto-generated method stub
		return empleadoRep.save(empleado);
	}

	@Override
	public Empleado actualizar(Long id, Empleado empleado) {
		// TODO Auto-generated method stub
		empleado.setIdEmpleado(id);
		return empleadoRep.save(empleado);
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		empleadoRep.deleteById(id);
	}

	/*@Override
	public Empleado asignarSupervisor(Long idEmpleado, Long idSupervisor) {
		Optional<Empleado> empleadoEntity =  empleadoRep.findById(idEmpleado);
		Optional<Empleado> supervisorEntity = empleadoRep.findById(idSupervisor);
		empleadoEntity.get().setSupervisor(supervisorEntity.get());
		return empleadoRep.save(supervisorEntity.get());
	}*/

}
