package com.equipo02.hotel.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.equipo02.hotel.domain.Habitacion;
import com.equipo02.hotel.exception.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.equipo02.hotel.domain.Empleado;
import com.equipo02.hotel.domain.Reserva;
import com.equipo02.hotel.dto.EmpleadoDTO;
import com.equipo02.hotel.dto.ReservaDTO;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.IllegalOperationException;
import com.equipo02.hotel.services.EmpleadoService;
import com.equipo02.hotel.util.ApiResponse;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
	public ResponseEntity<?> obtenerTodos() throws EntityNotFoundException {
		List<Empleado> empleados = empleadoService.listarEmpleados();
		List<EmpleadoDTO> empleadoDTOS = empleados.stream().map(empleado -> modelMapper.map(empleado, EmpleadoDTO.class)).collect(Collectors.toList());

		for (EmpleadoDTO empleadoDTO : empleadoDTOS) {

			if (!empleadoDTO.getReservas().isEmpty()) {
				for (Reserva reserva : empleadoDTO.getReservas()) {
					Long idReserva = reserva.getIdReserva();
					empleadoDTO.add(linkTo(methodOn(HabitacionController.class).buscarPorIdHabitacion(idReserva)).withRel("reserva"));
				}
			}
		}
		ApiResponse<List<EmpleadoDTO>> response = new ApiResponse<>(true, "Lista de empleados obtenida con éxito.", empleadoDTOS);
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
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id) throws EntityNotFoundException, BadRequestException {
		Empleado empleado = empleadoService.buscarPorId(id);
		EmpleadoDTO empleadoDTO = modelMapper.map(empleado, EmpleadoDTO.class);

		if (!empleadoDTO.getReservas().isEmpty()) {
			for (Reserva reserva : empleado.getReservas()) {
				Long idReserva = reserva.getIdReserva();
				empleadoDTO.add(linkTo(methodOn(ReservaController.class).buscarPorIdReserva(idReserva)).withRel("reserva"));
			}
		}
		ApiResponse<EmpleadoDTO> response = new ApiResponse<>(true, "Empleado obtenido con éxito.", empleadoDTO);

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
	public ResponseEntity<?> guardar(@Valid @RequestBody EmpleadoDTO empleadoDTO, BindingResult result) throws IllegalOperationException {
		
		if(result.hasErrors()) {
    		return validar(result);
    	}
		Empleado empleado= modelMapper.map(empleadoDTO, Empleado.class);
		empleadoService.grabar(empleado);

		EmpleadoDTO savedEmpleadoDTO = modelMapper.map(empleado, EmpleadoDTO.class);
		ApiResponse<EmpleadoDTO> response = new ApiResponse<>(true, "Empleado guardado con éxito", savedEmpleadoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	  /**
     * Método para actualizar los detalles de un empleado existente.
     * 
     * @param id El ID del empleado que se desea actualizar.
     * @param empleadoDTO Un objeto EmpleadoDTO que representa el empleado con los detalles actualizados.
     * @return ResponseEntity que contiene una ApiResponse. La ApiResponse incluye un EmpleadoDTO que representa el empleado actualizado.
     * @throws EntityNotFoundException si no se encuentra un empleado con el ID proporcionado.
     * @throws IllegalOperationException si ocurre un error al actualizar el empleado.
     */
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@Valid @RequestBody EmpleadoDTO empleadoDTO,BindingResult result, @PathVariable Long id) throws EntityNotFoundException, IllegalOperationException
	{
		if(result.hasErrors()) {
    		return validar(result);
    	}
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
	
	/**
     * Método para actualizar los campos de un empleado.
     * @param idEmpleado El ID del empleado que se va a actualizar.
     * @param empleadoDTO Un objeto DTO que contiene los nuevos datos del empleado.
     * @return Una respuesta HTTP que contiene el objeto DTO del empleado actualizado.
     * @throws EntityNotFoundException Si no se encuentra un empleado con el ID proporcionado.
     */
	@PatchMapping("/{idEmpleado}")
	public ResponseEntity<?> buscarCampo(@Valid @RequestBody EmpleadoDTO empleadoDTO,BindingResult result ,@PathVariable Long idEmpleado) throws EntityNotFoundException, IllegalOperationException{
		if(result.hasErrors()) {
			return validar(result);
		}

		Empleado empleado = modelMapper.map(empleadoDTO, Empleado.class);
		empleadoService.actualizarPorCampo(idEmpleado, empleado);

		EmpleadoDTO updatedEmpleadoDTO = modelMapper.map(empleado, EmpleadoDTO.class);
		ApiResponse<EmpleadoDTO> response = new ApiResponse<>(true, "Empleado actualizado con éxito", updatedEmpleadoDTO);
		return ResponseEntity.ok(response);
	}
	
	
	/**
     * Método para obtener una Reserva específica asociada con un Empleado en particular.
     * @param idEmpleado El ID de la Empleado.
     * @param idReserva El ID de la Reserva.
     * @return ResponseEntity que contiene una ApiResponse. La ApiResponse incluye un ReservaDTO que representa la Reserva obtenida.
     * @throws EntityNotFoundException Si no se encuentra una Empleado con el ID proporcionado o si la Empleado no tiene una Reserva con el ID proporcionado.
     */
    @GetMapping("/{idEmpleado}/reservas/{idReserva}")
    public ResponseEntity<?> obtenerReservaDeEmpleado(@PathVariable Long idEmpleado, @PathVariable Long idReserva) throws EntityNotFoundException {
		Reserva reserva = empleadoService.obtenerReservaDeEmpleado(idEmpleado, idReserva);
		ReservaDTO reservaDTO = modelMapper.map(reserva, ReservaDTO.class);
		ApiResponse<ReservaDTO> response = new ApiResponse<>(true, "Reserva obtenida con éxito.", reservaDTO);
		return ResponseEntity.ok(response);
	}
	
	 /**
     * Método para validar los errores de binding result.
     * @param result El objeto BindingResult que contiene los resultados de la validación.
     * @return ResponseEntity que contiene un mapa de los errores. Cada entrada en el mapa tiene el nombre del campo como clave y el mensaje de error como valor.
     */
	 private ResponseEntity<Map<String, String>> validar(BindingResult result) {
	        Map<String, String> errores = new HashMap<>();
	        result.getFieldErrors().forEach(err -> {
	            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
	        });
	        return ResponseEntity.badRequest().body(errores);
	    }
}
