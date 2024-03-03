package com.equipo02.hotel.services;
import java.util.List;

import com.equipo02.hotel.domain.Habitacion;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.IllegalOperationException;
/**
 * 
 * @file: HabitacionService.java
 * @author: (c)2024 Cueva
 * @created: 2 mar 2024, 22:46:52
 *
 */
public interface HabitacionService {

	List<Habitacion> listarTodos();
    Habitacion buscarPorIdHabitacion(Long id) throws EntityNotFoundException;
    Habitacion guardarHabitacion(Habitacion habitacion) throws IllegalOperationException;
    Habitacion actualizarHabitacion(Long id, Habitacion habitacion) throws EntityNotFoundException, IllegalOperationException;
    void eliminarHabitacion(Long id) throws EntityNotFoundException, IllegalOperationException;
}
