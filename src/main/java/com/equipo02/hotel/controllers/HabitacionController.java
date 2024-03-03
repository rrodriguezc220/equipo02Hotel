/**
 * 
 * @file: HabitacionController.java
 * @author: (c)2024 Cueva
 * @created: 2 mar 2024, 22:40:44
 *
 */
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
 * Controlador REST que maneja las operaciones relacionadas con las habitaciones en el hotel.
 */

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {
	
	@Autowired
	private HabitacionService habitacionService;
	
    @Autowired
    private ModelMapper modelMapper;
    
    
    /**
     * Método para obtener una lista de todas las habitaciones.
     * 
     * @return ResponseEntity que contiene una ApiResponse. La ApiResponse incluye una lista de HabitacionDTO.
     * @throws EntityNotFoundException si no se encuentran habitaciones.
     */
    @GetMapping
	public ResponseEntity<?> listarTodos() throws EntityNotFoundException{
		List<Habitacion> habitaciones = habitacionService.listarTodos();
		List<HabitacionDTO> habitacionesDTOs = habitaciones.stream().map(habitacion -> modelMapper.map(habitacion, HabitacionDTO.class)).collect(Collectors.toList());
		ApiResponse<List<HabitacionDTO>> response = new ApiResponse<>(true, "Lista de habitaciones obtenida con éxito.", habitacionesDTOs);
		return ResponseEntity.ok(response);
	}
    /**
     * Método para obtener los detalles de una habitación específica por su ID.
     * 
     * @param id El ID de la habitación que se desea buscar.
     * @return ResponseEntity que contiene una ApiResponse. La ApiResponse incluye un HabitacionDTO que representa la habitación buscada y contiene los detalles de la habitación.
     * @throws EntityNotFoundException si no se encuentra una habitación con el ID proporcionado.
     */
    @GetMapping("/{id}")
	public ResponseEntity<?> buscarPorIdHabitacion(@PathVariable Long id) throws EntityNotFoundException{
    	Habitacion habitacion = habitacionService.buscarPorIdHabitacion(id);
    	HabitacionDTO habitacionDTO = modelMapper.map(habitacion, HabitacionDTO.class);
		ApiResponse<HabitacionDTO> response = new ApiResponse<>(true, "Habitación obtenida con éxito.", habitacionDTO);
		return ResponseEntity.ok(response);
	}
    /**
     * Método para guardar una nueva habitación.
     * 
     * @param habitacionDTO Un objeto HabitacionDTO que representa la habitación que se desea guardar.
     * @return ResponseEntity que contiene una ApiResponse. La ApiResponse incluye un HabitacionDTO que representa la habitación guardada y contiene los detalles de la habitación.
     * @throws IllegalOperationException si ocurre un error al guardar la habitación.
     */
    @PostMapping
	public ResponseEntity<?> guardarHabitacion(@RequestBody HabitacionDTO habitacionDTO) throws IllegalOperationException {
		Habitacion habitacion = modelMapper.map(habitacionDTO, Habitacion.class);
		habitacionService.guardarHabitacion(habitacion);
		HabitacionDTO savedHabitacionDTO = modelMapper.map(habitacion, HabitacionDTO.class);
		ApiResponse<HabitacionDTO> response = new ApiResponse<>(true, "Habitación guardada con éxito.", savedHabitacionDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
    /**
     * Método para actualizar los detalles de una habitación existente.
     * 
     * @param id El ID de la habitación que se desea actualizar.
     * @param habitacionDTO Un objeto HabitacionDTO que representa la habitación con los detalles actualizados.
     * @return ResponseEntity que contiene una ApiResponse. La ApiResponse incluye un HabitacionDTO que representa la habitación actualizada.
     * @throws EntityNotFoundException si no se encuentra una habitación con el ID proporcionado.
     * @throws IllegalOperationException si ocurre un error al actualizar la habitación.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<HabitacionDTO>> actualizarHabitacion(@PathVariable Long id, @RequestBody HabitacionDTO habitacionDTO) throws EntityNotFoundException, IllegalOperationException {
    	Habitacion habitacion = modelMapper.map(habitacionDTO, Habitacion.class);
    	habitacionService.actualizarHabitacion(id, habitacion);
    	HabitacionDTO updatedHabitacionDTO = modelMapper.map(habitacion, HabitacionDTO.class);
    	ApiResponse<HabitacionDTO> response = new ApiResponse<>(true, "Habitación actualizada con éxito.", updatedHabitacionDTO);
		return ResponseEntity.ok(response);	
    }
    /**
     * Método para eliminar una habitación existente.
     * 
     * @param id El ID de la habitación que se desea eliminar.
     * @return ResponseEntity que contiene una ApiResponse. La ApiResponse incluye un mensaje indicando que la habitación fue eliminada con éxito.
     * @throws EntityNotFoundException si no se encuentra una habitación con el ID proporcionado.
     * @throws IllegalOperationException si ocurre un error al eliminar la habitación.
     */
    @DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarHabitacion(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {
    	habitacionService.eliminarHabitacion(id);
    	ApiResponse<String> response = new ApiResponse<>(true, "Habitación eliminada con éxito", null);
		return ResponseEntity.ok(response);
	}
}

