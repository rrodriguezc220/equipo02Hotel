/**
 * 
 * @file: HabitacionController.java
 * @author: (c)2024 Cueva
 * @created: 2 mar 2024, 22:40:44
 *
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
import com.equipo02.hotel.services.HabitacionService;
import com.equipo02.hotel.util.ApiResponse;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Controlador REST que maneja las operaciones relacionadas con las habitaciones en el hotel.
 */

@RestController
@RequestMapping(value ="/api/habitaciones", headers = "Api-Version=1")
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
        for (HabitacionDTO habitacionDTO : habitacionesDTOs) {
            if (!habitacionDTO.getReservas().isEmpty()) {
                for (Reserva reserva : habitacionDTO.getReservas()) {
                    Long idReserva = reserva.getIdReserva();
                    habitacionDTO.add(linkTo(methodOn(ReservaController.class).buscarPorIdReserva(idReserva)).withRel("reserva"));
                }
            }
        }
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
	public ResponseEntity<?> buscarPorIdHabitacion(@PathVariable Long id) throws EntityNotFoundException, BadRequestException{
    	Habitacion habitacion = habitacionService.buscarPorIdHabitacion(id);
    	HabitacionDTO habitacionDTO = modelMapper.map(habitacion, HabitacionDTO.class);

        if (!habitacionDTO.getReservas().isEmpty()) {
            for (Reserva reserva : habitacion.getReservas()) {
                Long idReserva = reserva.getIdReserva();
                habitacionDTO.add(linkTo(methodOn(ReservaController.class).buscarPorIdReserva(idReserva)).withRel("Reserva"));
            }
        }
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
	public ResponseEntity<?> guardarHabitacion(@Valid @RequestBody HabitacionDTO habitacionDTO, BindingResult result) throws IllegalOperationException {
    	if(result.hasErrors()) {
    		return validar(result);
    	}
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
    public ResponseEntity<?> actualizarHabitacion(@Valid @RequestBody HabitacionDTO habitacionDTO,BindingResult result, @PathVariable  Long id) throws EntityNotFoundException, IllegalOperationException {
    	if(result.hasErrors()) {
    		return validar(result);
    	}
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
    /**
     * Método para actualizar los campos de una habitación.
     * @param idHabitacion El ID de la habitación que se va a actualizar.
     * @param habitacionDTO Un objeto DTO que contiene los nuevos datos de la habitación.
     * @return Una respuesta HTTP que contiene el objeto DTO de la habitación actualizada.
     * @throws EntityNotFoundException Si no se encuentra una habitación con el ID proporcionado.
     */
    @PatchMapping("/{idHabitacion}")
    public ResponseEntity<?> actualizarCampoHabitacion(@PathVariable Long idHabitacion, @RequestBody HabitacionDTO habitacionDTO) throws EntityNotFoundException, IllegalOperationException {
        Habitacion habitacion = modelMapper.map(habitacionDTO, Habitacion.class);
        habitacionService.actualizarCampoHabitacion(idHabitacion, habitacion);
        
        HabitacionDTO upHabitacionDTO = modelMapper.map(habitacion, HabitacionDTO.class);
        ApiResponse<HabitacionDTO> response=new ApiResponse<>(true, "Habitacion actualizado con exito", upHabitacionDTO);
        return ResponseEntity.ok(response);
    }
    /**
     * Método para obtener una Reserva específica asociada con una Habitación en particular.
     * @param idHabitacion El ID de la Habitación.
     * @param idReserva El ID de la Reserva.
     * @return ResponseEntity que contiene una ApiResponse. La ApiResponse incluye un ReservaDTO que representa la Reserva obtenida.
     * @throws EntityNotFoundException Si no se encuentra una Habitación con el ID proporcionado o si la Habitación no tiene una Reserva con el ID proporcionado.
     */
    @GetMapping("/{idHabitacion}/reservas/{idReserva}")
    public ResponseEntity<?> obtenerReservaDeHabitacion(@PathVariable Long idHabitacion, @PathVariable Long idReserva) throws EntityNotFoundException {
        Reserva reserva = habitacionService.obtenerReservaDeHabitacion(idHabitacion, idReserva);
        ReservaDTO reservaDTO = modelMapper.map(reserva, ReservaDTO.class);
        ApiResponse<ReservaDTO> response = new ApiResponse<>(true, "Reserva obtenida con éxito.", reservaDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Obtiene todas las reservas de una habitación.
     *
     * @param idHabitacion El id de la habitación.
     * @return ResponseEntity con la lista de reservas de la habitación y un mensaje de éxito.
     * @throws EntityNotFoundException Si la habitación no se encuentra.
     */
    @GetMapping("/{idHabitacion}/reservas")
    public ResponseEntity<?> obtenerReservasDeHabitaciones(@PathVariable Long idHabitacion) throws EntityNotFoundException {
        List<Reserva> reservas = habitacionService.obtenerReservasDeHabitaciones(idHabitacion);
        List<ReservaDTO> reservasDTOs = reservas.stream().map(reserva -> modelMapper.map(reserva, ReservaDTO.class)).collect(Collectors.toList());
        ApiResponse<List<ReservaDTO>> response = new ApiResponse<>(true, "Lista de reservas obtenida con éxito.", reservasDTOs);
        return ResponseEntity.ok(response);
    }
    
    /**
     * Método para validar los errores de binding result.
     * @param result El objeto BindingResult que contiene los resultados de la validación.
     * @return ResponseEntity que contiene un mapa de los errores. Cada entrada en el mapa tiene el nombre del campo como clave y el mensaje de error como valor.
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