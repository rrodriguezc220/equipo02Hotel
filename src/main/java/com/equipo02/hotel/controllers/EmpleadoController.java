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

import com.equipo02.hotel.domain.Empleado;
import com.equipo02.hotel.dto.EmpleadoDTO;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.IllegalOperationException;
import com.equipo02.hotel.services.EmpleadoService;
import com.equipo02.hotel.util.ApiResponse;
/**
 * Controlador REST que maneja las operaciones relacionadas con los empleados en el hotel.
 */

@RestController
@RequestMapping(value = "/api/empleados", headers = "Api-Version=1")
public class EmpleadoController {
	@Autowired
	EmpleadoService empleadoService;

	@Autowired
	private ModelMapper modelMapper;
	
	 /**
     * Método para obtener una lista de todas los empleados.
     * 
     * @return ResponseEntity que contiene una ApiResponse. La ApiResponse incluye una lista de EmpleadoDTO.
     * @throws EntityNotFoundException si no se encuentran empleados.
     */
	@GetMapping
	public ResponseEntity<?> obtenerTodos(){
		List<Empleado> empleados= empleadoService.listarEmpleados();
		List<EmpleadoDTO> empleadosDTOs = empleados.stream().map
				(empleado-> modelMapper.map(empleado, EmpleadoDTO.class)).collect(Collectors.toList());
		ApiResponse<List<EmpleadoDTO>> response = new ApiResponse<>
		(true, "Lista de empleados obtenida con éxito", empleadosDTOs);
		return ResponseEntity.ok(response);
	}

	  /**
     * Método para obtener los detalles de un empleado específico por su ID.
     * 
     * @param id El ID del empleado que se desea buscar.
     * @return ResponseEntity que contiene una ApiResponse. La ApiResponse incluye un EmpleadoDTO que representa el empleado buscado y contiene los detalles del empleado.
     * @throws EntityNotFoundException si no se encuentra un empleado con el ID proporcionado.
     */
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id) throws EntityNotFoundException {
		Empleado empleado = empleadoService.buscarPorId(id);
		EmpleadoDTO empleadoDTO = modelMapper.map(empleado, EmpleadoDTO.class);

		ApiResponse<EmpleadoDTO> response = new ApiResponse<>(true, "Empleado obtenido con éxito", empleadoDTO);
		return ResponseEntity.ok(response);
	}

	 /**
     * Método para guardar un nuevo empleado.
     * 
     * @param empleadoDTO Un objeto EmpleadoDTO que representa el empleado que se desea guardar.
     * @return ResponseEntity que contiene una ApiResponse. La ApiResponse incluye un EmpleadoDTO que representa el empleado guardado y contiene los detalles del empleado.
     * @throws IllegalOperationException si ocurre un error al guardar el empleado.
     */
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody EmpleadoDTO empleadoDTO) throws IllegalOperationException {
		Empleado empleado= modelMapper.map(empleadoDTO, Empleado.class);
		empleadoService.grabar(empleado);

		EmpleadoDTO savedEmpleadoDTO = modelMapper.map(empleado, EmpleadoDTO.class);
		ApiResponse<EmpleadoDTO> response = new ApiResponse<>(true, "Huesped guardado con éxito", savedEmpleadoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	  /**
     * Método para actualizar los detalles de un empleado existente.
     * 
     * @param id El ID del empleado que se desea actualizar.
     * @param habitacionDTO Un objeto EmpleadoDTO que representa el empleado con los detalles actualizados.
     * @return ResponseEntity que contiene una ApiResponse. La ApiResponse incluye un EmpleadoDTO que representa el empleado actualizado.
     * @throws EntityNotFoundException si no se encuentra un empleado con el ID proporcionado.
     * @throws IllegalOperationException si ocurre un error al actualizar el empleado.
     */
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<EmpleadoDTO>> actualizar(@PathVariable Long id, @RequestBody EmpleadoDTO empleadoDTO) throws EntityNotFoundException, IllegalOperationException {
		Empleado empleado = modelMapper.map(empleadoDTO, Empleado.class);
		empleadoService.actualizar(id, empleado);

		EmpleadoDTO updatedEmpleadoDTO = modelMapper.map(empleado, EmpleadoDTO.class);
		ApiResponse<EmpleadoDTO> response = new ApiResponse<>(true, "Empleado actualizado con éxito", updatedEmpleadoDTO);
		return ResponseEntity.ok(response);
	}

	 /**
     * Método para eliminar una habitación existente.
     * 
     * @param id El ID del empleado que se desea eliminar.
     * @return ResponseEntity que contiene una ApiResponse. La ApiResponse incluye un mensaje indicando que el empleado fue eliminado con éxito.
     * @throws EntityNotFoundException si no se encuentra un empleado con el ID proporcionado.
     * @throws IllegalOperationException si ocurre un error al eliminar el empleado.
     */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {
		empleadoService.eliminar(id);
		ApiResponse<String> response = new ApiResponse<>(true, "Empleado eliminado con éxito", null);
		return ResponseEntity.ok(response);
	}
	
}
