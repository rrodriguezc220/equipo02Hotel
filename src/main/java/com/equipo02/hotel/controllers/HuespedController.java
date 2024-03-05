/**
 * @file: HuespedController.java
 * @author: (c)2024 Rodriguez
 * @created: 3 mar. 2024 17:13:12
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipo02.hotel.domain.Huesped;
import com.equipo02.hotel.dto.HuespedDTO;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.IllegalOperationException;
import com.equipo02.hotel.services.HuespedService;
import com.equipo02.hotel.util.ApiResponse;

/**
 * Controlador REST para manejar las operaciones relacionadas con los huéspedes.
 */
@RestController
@RequestMapping("/api/huespedes")
public class HuespedController {

	@Autowired
	HuespedService huespedService;

	@Autowired
	private ModelMapper modelMapper;

	/**
     * Obtiene todos los huéspedes.
     * 
     * @return Una respuesta HTTP que contiene la lista de huéspedes.
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
     * @param id El ID del huésped.
     * @return Una respuesta HTTP que contiene el huésped encontrado.
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
	 * Método para guardar un nuevo huésped.
	 *
	 * @param huespedDTO El objeto HuespedDTO que contiene la información del nuevo huésped.
	 * @return ResponseEntity con un ApiResponse que contiene un mensaje de éxito y el DTO del huésped guardado, junto con el código de estado HTTP 201 (CREATED) en caso de éxito.
	 */
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody HuespedDTO huespedDTO) {
		Huesped huesped = modelMapper.map(huespedDTO, Huesped.class);
		huespedService.grabar(huesped);

		HuespedDTO savedHuespedDTO = modelMapper.map(huesped, HuespedDTO.class);
		ApiResponse<HuespedDTO> response = new ApiResponse<>(true, "Huesped guardado con éxito", savedHuespedDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/**
	 * Método para actualizar la información de un huésped existente.
	 *
	 * @param id El ID del huésped que se actualizará.
	 * @param huespedDTO El objeto HuespedDTO que contiene la información actualizada del huésped.
	 * @return ResponseEntity con un ApiResponse que contiene un mensaje de éxito y el DTO del huésped actualizado, junto con el código de estado HTTP 200 (OK) en caso de éxito.
	 * @throws EntityNotFoundException Si el huésped con el ID proporcionado no se encuentra en la base de datos.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody HuespedDTO huespedDTO) throws EntityNotFoundException{
		Huesped huesped = modelMapper.map(huespedDTO, Huesped.class);
		huespedService.actualizar(id, huesped);

		HuespedDTO updatedHuespedDTO = modelMapper.map(huesped, HuespedDTO.class);
		ApiResponse<HuespedDTO> response = new ApiResponse<>(true, "Huesped actualizado con éxito", updatedHuespedDTO);
		return ResponseEntity.ok(response);
	}

	/**
	 * Método para eliminar un huésped existente de la base de datos.
	 *
	 * @param id El ID del huésped que se eliminará.
	 * @return ResponseEntity con un ApiResponse que contiene un mensaje de éxito y nulo, junto con el código de estado HTTP 200 (OK) en caso de éxito.
	 * @throws EntityNotFoundException Si el huésped con el ID proporcionado no se encuentra en la base de datos.
	 * @throws IllegalOperationException Si el huésped que se intenta eliminar es aval de otro huésped, lo cual no está permitido.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException{
		huespedService.eliminar(id);
		ApiResponse<String> response = new ApiResponse<>(true, "Huesped eliminado con éxito", null);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Método para asignar un aval a un huésped existente en la base de datos.
	 *
	 * @param idHuesped El ID del huésped al que se le asignará el aval.
	 * @param idAval El ID del huésped que actuará como aval.
	 * @return ResponseEntity con un ApiResponse que contiene un mensaje de éxito junto con el huésped actualizado con su nuevo aval, junto con el código de estado HTTP 200 (OK) en caso de éxito.
	 * @throws EntityNotFoundException Si alguno de los huéspedes con los IDs proporcionados no se encuentra en la base de datos.
	 * @throws IllegalOperationException Si se intenta asignar como aval al mismo huésped o si el huésped que se intenta eliminar es aval de otro huésped.
	 */
	@PatchMapping("/{idHuesped}/aval/{idAval}")
	public ResponseEntity<?> asignarAval(@PathVariable Long idHuesped, @PathVariable Long idAval) throws EntityNotFoundException, IllegalOperationException{
		Huesped huesped = huespedService.asignarAval(idHuesped, idAval);
		HuespedDTO huespedDTO = modelMapper.map(huesped, HuespedDTO.class);
		ApiResponse<HuespedDTO> response = new ApiResponse<>(true, "Aval asignado con éxito", huespedDTO);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Método para eliminar el aval de un huésped existente en la base de datos.
	 *
	 * @param idHuesped El ID del huésped del que se eliminará el aval.
	 * @return ResponseEntity con un ApiResponse que contiene un mensaje de éxito junto con el huésped actualizado sin aval, junto con el código de estado HTTP 200 (OK) en caso de éxito.
	 * @throws EntityNotFoundException Si el huésped con el ID proporcionado no se encuentra en la base de datos.
	 * @throws IllegalOperationException Si el huésped no tiene asignado un aval para eliminar.
	 */
	@PatchMapping("/aval/{idHuesped}")
	public ResponseEntity<?> eliminarAval(@PathVariable Long idHuesped) throws EntityNotFoundException, IllegalOperationException{
		Huesped huesped = huespedService.eliminarAval(idHuesped);
		HuespedDTO huespedDTO = modelMapper.map(huesped, HuespedDTO.class);
		ApiResponse<HuespedDTO> response = new ApiResponse<>(true, "Aval eliminado con éxito", huespedDTO);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Método para actualizar los campos de un huésped existente en la base de datos.
	 *
	 * @param idHuesped El ID del huésped que se actualizará.
	 * @param huespedDTO Objeto DTO que contiene los campos actualizados del huésped.
	 * @return ResponseEntity con un ApiResponse que contiene un mensaje de éxito junto con el huésped actualizado, junto con el código de estado HTTP 200 (OK) en caso de éxito.
	 * @throws EntityNotFoundException Si el huésped con el ID proporcionado no se encuentra en la base de datos.
	 * @throws IllegalOperationException Si se intenta asignar un aval a sí mismo como aval o si no se encuentra el aval proporcionado.
	 */
	@PatchMapping("/{idHuesped}")
	public ResponseEntity<?> actualizarPorCampos(@PathVariable Long idHuesped, @RequestBody HuespedDTO huespedDTO) throws EntityNotFoundException, IllegalOperationException{
		Huesped huesped = modelMapper.map(huespedDTO, Huesped.class);
		huespedService.actualizarPorCampos(idHuesped, huesped);

		HuespedDTO updatedHuespedDTO = modelMapper.map(huesped, HuespedDTO.class);
		ApiResponse<HuespedDTO> response = new ApiResponse<>(true, "Huesped actualizado con éxito", updatedHuespedDTO);
		return ResponseEntity.ok(response);
	}
}
