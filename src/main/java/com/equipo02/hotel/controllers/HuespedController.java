/**
 * @file: HuespedController.java
 * @author: (c)2024 Rodriguez
 * @created: 3 mar. 2024 17:13:12
 */

package com.equipo02.hotel.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import com.equipo02.hotel.domain.Habitacion;
import com.equipo02.hotel.domain.Huesped;
import com.equipo02.hotel.domain.Reserva;
import com.equipo02.hotel.dto.HabitacionDTO;
import com.equipo02.hotel.dto.HuespedDTO;
import com.equipo02.hotel.dto.ReservaDTO;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.IllegalOperationException;
import com.equipo02.hotel.services.HuespedService;
import com.equipo02.hotel.util.ApiResponse;

import jakarta.validation.Valid;

/**
 * Controlador REST para manejar las operaciones relacionadas con los huéspedes.
 */
@RestController
@RequestMapping(value = "/api/huespedes", headers = "Api-Version=1")
public class HuespedController {

	@Autowired
	HuespedService huespedService;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Obtiene todos los huéspedes.
	 *
	 * @return Una ResponseEntity con el resultado de la operación.
	 */
	@GetMapping
	public ResponseEntity<?> obtenerTodos(){
		List<Huesped> huespedes = huespedService.listarHuespedes();
		List<HuespedDTO> huespedesDTOs = huespedes.stream().map(huesped -> modelMapper.map(huesped, HuespedDTO.class)).collect(Collectors.toList());

		ApiResponse<List<HuespedDTO>> response = new ApiResponse<>(true, "Lista de huespedes obtenida con éxito", huespedesDTOs);
		return ResponseEntity.ok(response);
	}

	/**
	 * Obtiene un huésped por su ID.
	 *
	 * @param id El ID del huésped a obtener.
	 * @return Una ResponseEntity con el resultado de la operación.
	 * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id) throws EntityNotFoundException {
		Huesped huesped = huespedService.buscarPorId(id);
		HuespedDTO huespedDTO = modelMapper.map(huesped, HuespedDTO.class);

		ApiResponse<HuespedDTO> response = new ApiResponse<>(true, "Huesped obtenido con éxito", huespedDTO);
		return ResponseEntity.ok(response);
	}

	/**
	 * Guarda un nuevo huésped.
	 *
	 * @param huespedDTO  DTO del huésped a guardar.
	 * @param result Resultado del proceso de validación.
	 * @return Una ResponseEntity con el resultado de la operación.
	 * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
	 * @throws IllegalOperationException Si hay operaciones ilegales.
	 */
	@PostMapping
	public ResponseEntity<?> guardar(@Valid @RequestBody HuespedDTO huespedDTO, BindingResult result) throws EntityNotFoundException, IllegalOperationException{
		if(result.hasErrors()) {
			return validar(result);
		}

		Huesped huesped = modelMapper.map(huespedDTO, Huesped.class);
		huespedService.grabar(huesped);

		HuespedDTO savedHuespedDTO = modelMapper.map(huesped, HuespedDTO.class);
		ApiResponse<HuespedDTO> response = new ApiResponse<>(true, "Huesped guardado con éxito", savedHuespedDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/**
	 * Actualiza un huésped por su ID.
	 *
	 * @param id El ID del huésped a actualizar.
	 * @param huespedDTO DTO del huésped con los datos actualizados.
	 * @param result Resultado del proceso de validación.
	 * @return Una ResponseEntity con el resultado de la operación.
	 * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
	 * @throws IllegalOperationException Si hay operaciones ilegales.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@Valid @RequestBody HuespedDTO huespedDTO, BindingResult result, @PathVariable Long id) throws EntityNotFoundException, IllegalOperationException{
		if(result.hasErrors()) {
			return validar(result);
		}

		Huesped huesped = modelMapper.map(huespedDTO, Huesped.class);
		huespedService.actualizar(id, huesped);

		HuespedDTO updatedHuespedDTO = modelMapper.map(huesped, HuespedDTO.class);
		ApiResponse<HuespedDTO> response = new ApiResponse<>(true, "Huesped actualizado con éxito", updatedHuespedDTO);
		return ResponseEntity.ok(response);
	}

	/**
	 * Elimina un huésped por su ID.
	 *
	 * @param id El ID del huésped a eliminar.
	 * @return Una ResponseEntity con el resultado de la operación.
	 * @throws EntityNotFoundException   Si el huésped no puede ser encontrado.
	 * @throws IllegalOperationException Si hay operaciones ilegales, como eliminar un huésped que es aval de otro.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException{
		huespedService.eliminar(id);
		ApiResponse<String> response = new ApiResponse<>(true, "Huesped eliminado con éxito", null);
		return ResponseEntity.ok(response);
	}

	/**
	 * Asigna un aval a un huésped.
	 *
	 * @param idHuesped El ID del huésped al que se asignará el aval.
	 * @param idAval    El ID del huésped que actuará como aval.
	 * @return Una ResponseEntity con el resultado de la operación.
	 * @throws EntityNotFoundException   Si el huésped no puede ser encontrado.
	 * @throws IllegalOperationException Si hay operaciones ilegales, como asignar un huésped como aval de sí mismo.
	 */
	@PatchMapping("/{idHuesped}/aval/{idAval}")
	public ResponseEntity<?> asignarAval(@PathVariable Long idHuesped, @PathVariable Long idAval) throws EntityNotFoundException, IllegalOperationException{
		Huesped huesped = huespedService.asignarAval(idHuesped, idAval);
		HuespedDTO huespedDTO = modelMapper.map(huesped, HuespedDTO.class);
		ApiResponse<HuespedDTO> response = new ApiResponse<>(true, "Aval asignado con éxito", huespedDTO);
		return ResponseEntity.ok(response);
	}

	/**
	 * Elimina el aval de un huésped.
	 *
	 * @param idHuesped El ID del huésped del que se eliminará el aval.
	 * @return Una ResponseEntity con el resultado de la operación.
	 * @throws EntityNotFoundException   Si el huésped no puede ser encontrado.
	 * @throws IllegalOperationException Si hay operaciones ilegales, como eliminar el aval de un huésped que no tiene uno.
	 */
	@PatchMapping("/aval/{idHuesped}")
	public ResponseEntity<?> eliminarAval(@PathVariable Long idHuesped) throws EntityNotFoundException, IllegalOperationException{
		Huesped huesped = huespedService.eliminarAval(idHuesped);
		HuespedDTO huespedDTO = modelMapper.map(huesped, HuespedDTO.class);
		ApiResponse<HuespedDTO> response = new ApiResponse<>(true, "Aval eliminado con éxito", huespedDTO);
		return ResponseEntity.ok(response);
	}

	/**
	 * Actualiza ciertos campos de un huésped existente.
	 *
	 * @param huespedDTO   El DTO del huésped con los campos a actualizar.
	 * @param result       El resultado de la validación.
	 * @param idHuesped    El ID del huésped a actualizar.
	 * @return Una ResponseEntity con el resultado de la operación.
	 * @throws EntityNotFoundException   Si el huésped no puede ser encontrado.
	 * @throws IllegalOperationException Si hay alguna operación ilegal.
	 */
	@PatchMapping("/{idHuesped}")
	public ResponseEntity<?> actualizarPorCampos(@Valid @RequestBody HuespedDTO huespedDTO, BindingResult result, @PathVariable Long idHuesped) throws EntityNotFoundException, IllegalOperationException{
		if(result.hasErrors()) {
			return validar(result);
		}

		Huesped huesped = modelMapper.map(huespedDTO, Huesped.class);
		huespedService.actualizarPorCampos(idHuesped, huesped);

		HuespedDTO updatedHuespedDTO = modelMapper.map(huesped, HuespedDTO.class);
		ApiResponse<HuespedDTO> response = new ApiResponse<>(true, "Huesped actualizado con éxito", updatedHuespedDTO);
		return ResponseEntity.ok(response);
	}

	//Subservicios

	/**
	 * Obtiene las reservas de un huésped por su ID.
	 *
	 * @param id El ID del huésped.
	 * @return Una ResponseEntity con la lista de reservas del huésped.
	 * @throws EntityNotFoundException Si el huésped no puede ser encontrado.
	 */
	@GetMapping("/{id}/reservas")
	public ResponseEntity<?> obtenerReservasPorHuesped(@PathVariable Long id) throws EntityNotFoundException {
		List<Reserva> reservas = huespedService.obtenerReservasPorHuesped(id);

		List<ReservaDTO> reservasDTOs = reservas.stream().map(reserva -> modelMapper.map(reserva, ReservaDTO.class)).collect(Collectors.toList());

		ApiResponse<List<ReservaDTO>> response = new ApiResponse<>(true, "Lista de reservas del huesped obtenida con éxito", reservasDTOs);

		return ResponseEntity.ok(response);
	}

	/**
	 * Obtiene la reserva de un huésped por su ID de reserva.
	 *
	 * @param idHuesped El ID del huésped.
	 * @param idReserva El ID de la reserva.
	 * @return Una ResponseEntity con la reserva del huésped.
	 * @throws EntityNotFoundException Si el huésped o la reserva no pueden ser encontrados.
	 */
	@GetMapping("/{idHuesped}/reservas/{idReserva}")
	public ResponseEntity<?> obtenerReservasPorHuesped(@PathVariable Long idHuesped, @PathVariable Long idReserva) throws EntityNotFoundException {
		Reserva reserva = huespedService.obtenerReservaDeHuesped(idHuesped, idReserva);

		ReservaDTO reservaDTO = modelMapper.map(reserva, ReservaDTO.class);

		ApiResponse<ReservaDTO> response = new ApiResponse<>(true, "Reserva del huesped obtenido con éxito", reservaDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * Obtiene la lista de habitaciones de una reserva de un huésped.
	 *
	 * @param idHuesped El ID del huésped.
	 * @param idReserva El ID de la reserva.
	 * @return Una ResponseEntity con la lista de habitaciones de la reserva del huésped.
	 * @throws EntityNotFoundException Si el huésped o la reserva no pueden ser encontrados.
	 */
	@GetMapping("/{idHuesped}/reservas/{idReserva}/habitaciones")
	public ResponseEntity<?> obtenerHabitacionesDeReserva(@PathVariable Long idHuesped, @PathVariable Long idReserva) throws EntityNotFoundException {
		List<Habitacion> habitaciones = huespedService.obtenerHabitacionesPorReserva(idHuesped, idReserva);

		List<HabitacionDTO> reservasDTOs = habitaciones.stream().map(habitacion -> modelMapper.map(habitacion, HabitacionDTO.class)).collect(Collectors.toList());

		ApiResponse<List<HabitacionDTO>> response = new ApiResponse<>(true, "Lista de habitaciones de la reserva del huesped obtenida con éxito", reservasDTOs);

		return ResponseEntity.ok(response);
	}

	/**
	 * Obtiene una habitación específica de una reserva de un huésped.
	 *
	 * @param idHuesped    El ID del huésped.
	 * @param idReserva    El ID de la reserva.
	 * @param idHabitacion El ID de la habitación.
	 * @return Una ResponseEntity con la información de la habitación de la reserva del huésped.
	 * @throws EntityNotFoundException Si el huésped, la reserva o la habitación no pueden ser encontrados.
	 */
	@GetMapping("/{idHuesped}/reservas/{idReserva}/habitaciones/{idHabitacion}")
	public ResponseEntity<?> obtenerHabitacionDeReserva(@PathVariable Long idHuesped, @PathVariable Long idReserva, @PathVariable Long idHabitacion) throws EntityNotFoundException {
		Habitacion habitacion = huespedService.obtenerHabitacionDeReserva(idHuesped, idReserva, idHabitacion);

		HabitacionDTO habitacionDTO = modelMapper.map(habitacion, HabitacionDTO.class);

		ApiResponse<HabitacionDTO> response = new ApiResponse<>(true, "Habitación de la reserva del huesped obtenido con éxito", habitacionDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * Método auxiliar para manejar errores de validación.
	 *
	 * @param result El resultado de la validación.
	 * @return Una respuesta ResponseEntity con el mapa de errores.
	 */
	private ResponseEntity<?> validar(BindingResult result) {
		Map<String, String> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), err.getDefaultMessage());
		});
		ApiResponse<Map<String, String>> response = new ApiResponse<>(false, "Errores de validación", errores);
		return ResponseEntity.badRequest().body(response);
	}
}
