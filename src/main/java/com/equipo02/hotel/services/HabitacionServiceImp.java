package com.equipo02.hotel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.equipo02.hotel.domain.Habitacion;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.IllegalOperationException;
import com.equipo02.hotel.repositories.HabitacionRepository;
/**
 * 
 * @file: HabitacionServiceImp.java
 * @author: (c)2024 Cueva
 * @created: 2 mar 2024, 22:51:08
 *
 */
@Service
public class HabitacionServiceImp implements HabitacionService {

	@Autowired
	private HabitacionRepository habitacionRepository;
	
	@Override
	public List<Habitacion> listarTodos() {
		return (List<Habitacion>) habitacionRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Habitacion buscarPorIdHabitacion(Long idHabitacion) throws EntityNotFoundException {
		Optional<Habitacion> habitacion = habitacionRepository.findById(idHabitacion);
		return habitacion.get();
	}

	@Override
	@Transactional
	public Habitacion guardarHabitacion(Habitacion habitacion) throws IllegalOperationException {
		return habitacionRepository.save(habitacion);
	}

	@Override
	@Transactional
	public Habitacion actualizarHabitacion(Long idHabitacion, Habitacion habitacion) throws EntityNotFoundException, IllegalOperationException {
		habitacion.setIdHabitacion(idHabitacion);
		return habitacionRepository.save(habitacion);
	}

	@Override
	@Transactional
	public void eliminarHabitacion(Long idHabitacion) throws EntityNotFoundException, IllegalOperationException {
		habitacionRepository.deleteById(idHabitacion);
	}


}
