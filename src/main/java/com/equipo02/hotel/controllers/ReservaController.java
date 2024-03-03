/**
 * @file: ReservaController.java
 * @author: (c) 2024 MARCO
 * @created: 29 feb. 2024 18:43:49
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
import com.equipo02.hotel.domain.Reserva;
import com.equipo02.hotel.dto.ReservaDTO;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.IllegalOperationException;
import com.equipo02.hotel.services.ReservaService;
import com.equipo02.hotel.util.ApiResponse;

/**
 * Controlador REST que maneja las operaciones relacionadas con las reservas en el hotel.
 */

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
	
	
	@Autowired
	private ReservaService reservaService;
	
    @Autowired
    private ModelMapper modelMapper;
    
    /**
     * Obtiene todas las reservas.
     *
     * @return ResponseEntity con la lista de reservas y un mensaje de éxito.
     * @throws EntityNotFoundException si no se encuentran reservas.
     */
    @GetMapping
	public ResponseEntity<?> listarTodos() throws EntityNotFoundException{
		List<Reserva> reservas = reservaService.listarTodos();
		List<ReservaDTO> reservasDTOs = reservas.stream().map(reserva -> modelMapper.map(reserva, ReservaDTO.class)).collect(Collectors.toList());
		ApiResponse<List<ReservaDTO>> response = new ApiResponse<>(true, "Lista de reservas obtenida con éxito.", reservasDTOs);
		return ResponseEntity.ok(response);
	}
    
    /**
     * Obtiene una reserva por su id.
     *
     * @param id: El id de la reserva a buscar.
     * @return ResponseEntity con la reserva encontrada y un mensaje de éxito.
     * @throws EntityNotFoundException si la reserva no se encuentra.
     */
    @GetMapping("/{id}")
	public ResponseEntity<?> buscarPorIdReserva(@PathVariable Long id) throws EntityNotFoundException{
    	Reserva reserva = reservaService.buscarPorIdReserva(id);
    	ReservaDTO reservaDTO = modelMapper.map(reserva, ReservaDTO.class);
		ApiResponse<ReservaDTO> response = new ApiResponse<>(true, "Reserva obtenida con éxito.", reservaDTO);
		return ResponseEntity.ok(response);
	}
    
    /**
     * Actualiza una reserva existente.
     *
     * @param id: El id de la reserva a actualizar.
     * @param reservaDTO: La nueva información de la reserva.
     * @return ResponseEntity con la reserva actualizada y un mensaje de éxito.
     * @throws EntityNotFoundException    si la reserva no se encuentra.
     * @throws IllegalOperationException si la operación no es válida.
     */
    
    @PostMapping
	public ResponseEntity<?> guardarReserva(@RequestBody ReservaDTO reservaDTO) throws IllegalOperationException {
		Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);
		reservaService.guardarReserva(reserva);
		ReservaDTO savedReservaDTO = modelMapper.map(reserva, ReservaDTO.class);
		ApiResponse<ReservaDTO> response = new ApiResponse<>(true, "Reserva guardada con éxit.o", savedReservaDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
    
    /**
     * Actualiza una reserva existente.
     *
     * @param id: El id de la reserva a actualizar.
     * @param reservaDTO: La nueva información de la reserva.
     * @return ResponseEntity con la reserva actualizada y un mensaje de éxito.
     * @throws EntityNotFoundException    si la reserva no se encuentra.
     * @throws IllegalOperationException si la operación no es válida.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReservaDTO>> actualizarReserva(@PathVariable Long id, @RequestBody ReservaDTO reservaDTO) throws EntityNotFoundException, IllegalOperationException {
    	Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);
    	reservaService.actualizarReserva(id, reserva);
    	ReservaDTO updatedReservaDTO = modelMapper.map(reserva, ReservaDTO.class);
    	ApiResponse<ReservaDTO> response = new ApiResponse<>(true, "Reserva actualizada con éxito.", updatedReservaDTO);
		return ResponseEntity.ok(response);	
    }
    
    /**
     * Elimina una reserva por su id.
     *
     * @param id: El id de la reserva a eliminar.
     * @return ResponseEntity con un mensaje de éxito.
     * @throws EntityNotFoundException    si la reserva no se encuentra.
     * @throws IllegalOperationException si la operación no es válida.
     */
    @DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarReserva(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {
    	reservaService.eliminarReserva(id);
    	ApiResponse<String> response = new ApiResponse<>(true, "Reserva eliminada con éxito", null);
		return ResponseEntity.ok(response);
	}
    
    
}
