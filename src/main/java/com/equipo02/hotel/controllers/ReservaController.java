/**
 * @file: ReservaController.java
 * @author: (c) 2024 MARCO
 * @created: 29 feb. 2024 18:43:49
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
import com.equipo02.hotel.domain.Reserva;
import com.equipo02.hotel.dto.HabitacionDTO;
import com.equipo02.hotel.dto.ReservaDTO;
import com.equipo02.hotel.exception.BadRequestException;
import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.exception.IllegalOperationException;
import com.equipo02.hotel.services.ReservaService;
import com.equipo02.hotel.util.ApiResponse;
import jakarta.validation.Valid;

/**
 * Controlador REST que maneja las operaciones relacionadas con las reservas en el hotel.
 */

@RestController
@RequestMapping(value = "/api/reservas", headers = "Api-Version=1")
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
    @GetMapping()
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
    public ResponseEntity<?> buscarPorIdReserva(@PathVariable Long id) throws EntityNotFoundException, BadRequestException{
    	Reserva reserva = reservaService.buscarPorIdReserva(id);
    	ReservaDTO reservaDTO = modelMapper.map(reserva, ReservaDTO.class);
		ApiResponse<ReservaDTO> response = new ApiResponse<>(true, "Reserva obtenida con éxito.", reservaDTO);
		return ResponseEntity.ok(response);
	}
    
    
    /**
     * Agrega una reserva a la persistencia.
     *
     * @param reservaDTO: La nueva información de la reserva.
     * @return ResponseEntity con la reserva agregada y un mensaje de éxito.
     * @throws IllegalOperationException si la operación no es válida.
     */
    @PostMapping()
	public ResponseEntity<?> guardarReserva(@Valid @RequestBody ReservaDTO reservaDTO, BindingResult result) throws IllegalOperationException {
    	if(result.hasErrors()) {
			return validar(result);
		}
    	Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);
		reservaService.guardarReserva(reserva);
		ReservaDTO savedReservaDTO = modelMapper.map(reserva, ReservaDTO.class);
		ApiResponse<ReservaDTO> response = new ApiResponse<>(true, "Reserva guardada con éxito.", savedReservaDTO);
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
    public ResponseEntity<?> actualizarReserva(@Valid @RequestBody ReservaDTO reservaDTO, BindingResult result, @PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {
    	if(result.hasErrors()) {
			return validar(result);
		}
    	Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);
    	reservaService.actualizarReserva(id, reserva);
    	ReservaDTO updatedReservaDTO = modelMapper.map(reserva, ReservaDTO.class);
    	ApiResponse<ReservaDTO> response = new ApiResponse<>(true, "Reserva actualizada con éxito.", updatedReservaDTO);
		return ResponseEntity.ok(response);	
    }
    
    /**
     * Actualiza un campo específico de una reserva.
     *
     * @param id: El id de la reserva a actualizar.
     * @param reservaDTO: La nueva información de la reserva.
     * @return ResponseEntity con la reserva actualizada y un mensaje de éxito.
     * @throws EntityNotFoundException    si la reserva no se encuentra.
     * @throws IllegalOperationException si la operación no es válida.
     */
    @PatchMapping("/{id}")
	public ResponseEntity<?> actualizarCampoReserva(@Valid @RequestBody ReservaDTO reservaDTO, BindingResult result, @PathVariable Long id) throws EntityNotFoundException, IllegalOperationException{
    	if(result.hasErrors()) {
			return validar(result);
		}
    	Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);
    	reservaService.actualizarCampoReserva(id, reserva);
		
    	ReservaDTO updateReservaDTO = modelMapper.map(reserva, ReservaDTO.class);
		ApiResponse<ReservaDTO> response = new ApiResponse<>(true, "Reserva actualizada con éxito", updateReservaDTO);
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
    
    /**
     * Asigna una habitación a una reserva.
     *
     * @param idReserva    El id de la reserva a la que se desea asignar la habitación.
     * @param idHabitacion El id de la habitación a asignar.
     * @return ResponseEntity con la reserva actualizada y un mensaje de éxito.
     * @throws EntityNotFoundException    si la reserva o la habitación no se encuentra en la persistencia.
     * @throws IllegalOperationException si la habitación ya está asignada a la reserva.
     */
    @PutMapping("/{idReserva}/habitaciones/{idHabitacion}")
    public ResponseEntity<?> asignarHabitacion(@PathVariable Long idReserva, @PathVariable Long idHabitacion) throws EntityNotFoundException, IllegalOperationException {
        Reserva reserva = reservaService.asignarHabitacion(idReserva, idHabitacion);
        ReservaDTO reservaDTO = modelMapper.map(reserva, ReservaDTO.class);
        ApiResponse<ReservaDTO> response = new ApiResponse<>(true, "Habitación asignada a la reserva con éxito.", reservaDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Elimina una habitación de una reserva.
     *
     * @param idReserva    El id de la reserva de la que se desea eliminar la habitación.
     * @param idHabitacion El id de la habitación a eliminar.
     * @return ResponseEntity con la reserva actualizada y un mensaje de éxito.
     * @throws EntityNotFoundException    si la reserva o la habitación no se encuentra en la persistencia.
     * @throws IllegalOperationException si la habitación no está asignada a la reserva.
     */
    @DeleteMapping("/{idReserva}/habitaciones/{idHabitacion}")
    public ResponseEntity<?> eliminarHabitacion(@PathVariable Long idReserva, @PathVariable Long idHabitacion) throws EntityNotFoundException, IllegalOperationException {
        Reserva reserva = reservaService.eliminarHabitacion(idReserva, idHabitacion);
        ReservaDTO reservaDTO = modelMapper.map(reserva, ReservaDTO.class);
        ApiResponse<ReservaDTO> response = new ApiResponse<>(true, "Habitación eliminada de la reserva con éxito.", reservaDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Obtiene una habitación específica de una reserva.
     *
     * @param idReserva    El id de la reserva.
     * @param idHabitacion El id de la habitación.
     * @return ResponseEntity con la habitación encontrada y un mensaje de éxito.
     * @throws EntityNotFoundException si la reserva o la habitación no se encuentran.
     */
    @GetMapping("/{idReserva}/habitaciones/{idHabitacion}")
    public ResponseEntity<?> obtenerHabitacionDeReserva(@PathVariable Long idReserva, @PathVariable Long idHabitacion) throws EntityNotFoundException {
        Habitacion habitacion = reservaService.obtenerHabitacionDeReserva(idReserva, idHabitacion);
        HabitacionDTO habitacionDTO = modelMapper.map(habitacion, HabitacionDTO.class);
        ApiResponse<HabitacionDTO> response = new ApiResponse<>(true, "Habitación obtenida con éxito.", habitacionDTO);
        return ResponseEntity.ok(response);
    }
    
    /**
     * Obtiene todas las habitaciones de una reserva.
     *
     * @param idReserva El id de la reserva.
     * @return ResponseEntity con la lista de habitaciones de la reserva y un mensaje de éxito.
     * @throws EntityNotFoundException Si la reserva no se encuentra.
     */
    @GetMapping("/{idReserva}/habitaciones")
    public ResponseEntity<?> obtenerHabitacionesDeReserva(@PathVariable Long idReserva) throws EntityNotFoundException {
        List<Habitacion> habitaciones = reservaService.obtenerHabitacionesDeReserva(idReserva);
        List<HabitacionDTO> habitacionesDTOs = habitaciones.stream().map(habitacion -> modelMapper.map(habitacion, HabitacionDTO.class)).collect(Collectors.toList());
        ApiResponse<List<HabitacionDTO>> response = new ApiResponse<>(true, "Lista de habitaciones obtenida con éxito.", habitacionesDTOs);
        return ResponseEntity.ok(response);
    }
    
    /**Método auxiliar para manejar errores de validación*/
    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
    
    
}


