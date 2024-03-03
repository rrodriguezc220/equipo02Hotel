
package com.equipo02.hotel.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipo02.hotel.domain.Habitacion;
import com.equipo02.hotel.dto.HabitacionDTO;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.IllegalOperationException;
import com.equipo02.hotel.services.HabitacionService;
import com.equipo02.hotel.util.ApiResponse;
/**
 * 
 * @file: HabitacionController.java
 * @author: (c)2024 Cueva
 * @created: 2 mar 2024, 22:40:44
 *
 */
@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {
	/**
	 * 
	 * 
	 * 
	 */
	@Autowired
	private HabitacionService habitacionService;
	
    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping
	public ResponseEntity<?> listarTodos() throws EntityNotFoundException{
		List<Habitacion> habitaciones = habitacionService.listarTodos();
		List<HabitacionDTO> habitacionesDTOs = habitaciones.stream().map(habitacion -> modelMapper.map(habitacion, HabitacionDTO.class)).collect(Collectors.toList());
		ApiResponse<List<HabitacionDTO>> response = new ApiResponse<>(true, "Lista de habitaciones obtenida con éxito.", habitacionesDTOs);
		return ResponseEntity.ok(response);
	}
    
    @GetMapping("/{id}")
	public ResponseEntity<?> buscarPorIdHabitacion(@PathVariable Long id) throws EntityNotFoundException{
    	Habitacion habitacion = habitacionService.buscarPorIdHabitacion(id);
    	HabitacionDTO habitacionDTO = modelMapper.map(habitacion, HabitacionDTO.class);
		ApiResponse<HabitacionDTO> response = new ApiResponse<>(true, "Habitación obtenida con éxito.", habitacionDTO);
		return ResponseEntity.ok(response);
	}
    
    @PostMapping
	public ResponseEntity<?> guardarHabitacion(@RequestBody HabitacionDTO habitacionDTO) throws IllegalOperationException {
		Habitacion habitacion = modelMapper.map(habitacionDTO, Habitacion.class);
		habitacionService.guardarHabitacion(habitacion);
		HabitacionDTO savedHabitacionDTO = modelMapper.map(habitacion, HabitacionDTO.class);
		ApiResponse<HabitacionDTO> response = new ApiResponse<>(true, "Habitación guardada con éxito.", savedHabitacionDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<HabitacionDTO>> actualizarHabitacion(@PathVariable Long id, @RequestBody HabitacionDTO habitacionDTO) throws EntityNotFoundException, IllegalOperationException {
    	Habitacion habitacion = modelMapper.map(habitacionDTO, Habitacion.class);
    	habitacionService.actualizarHabitacion(id, habitacion);
    	HabitacionDTO updatedHabitacionDTO = modelMapper.map(habitacion, HabitacionDTO.class);
    	ApiResponse<HabitacionDTO> response = new ApiResponse<>(true, "Habitación actualizada con éxito.", updatedHabitacionDTO);
		return ResponseEntity.ok(response);	
    }
    
    @DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarHabitacion(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {
    	habitacionService.eliminarHabitacion(id);
    	ApiResponse<String> response = new ApiResponse<>(true, "Habitación eliminada con éxito", null);
		return ResponseEntity.ok(response);
	}
}

