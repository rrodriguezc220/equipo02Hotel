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
import com.equipo02.hotel.services.EmpleadoService;
import com.equipo02.hotel.util.ApiResponse;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
	@Autowired
	EmpleadoService empleadoService;

	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<?> obtenerTodos(){
		List<Empleado> empleados= empleadoService.listarEmpleados();
		List<EmpleadoDTO> empleadosDTOs = empleados.stream().map
				(empleado-> modelMapper.map(empleado, EmpleadoDTO.class)).collect(Collectors.toList());
		ApiResponse<List<EmpleadoDTO>> response = new ApiResponse<>
		(true, "Lista de empleados obtenida con éxito", empleadosDTOs);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
		Empleado empleado = empleadoService.buscarPorId(id);
		EmpleadoDTO empleadoDTO = modelMapper.map(empleado, EmpleadoDTO.class);

		ApiResponse<EmpleadoDTO> response = new ApiResponse<>(true, "Empleado obtenido con éxito", empleadoDTO);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody EmpleadoDTO empleadoDTO) {
		Empleado empleado= modelMapper.map(empleadoDTO, Empleado.class);
		empleadoService.grabar(empleado);

		EmpleadoDTO savedEmpleadoDTO = modelMapper.map(empleado, EmpleadoDTO.class);
		ApiResponse<EmpleadoDTO> response = new ApiResponse<>(true, "Huesped guardado con éxito", savedEmpleadoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<EmpleadoDTO>> actualizar(@PathVariable Long id, @RequestBody EmpleadoDTO empleadoDTO) {
		Empleado empleado = modelMapper.map(empleadoDTO, Empleado.class);
		empleadoService.actualizar(id, empleado);

		EmpleadoDTO updatedEmpleadoDTO = modelMapper.map(empleado, EmpleadoDTO.class);
		ApiResponse<EmpleadoDTO> response = new ApiResponse<>(true, "Empleado actualizado con éxito", updatedEmpleadoDTO);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) {
		empleadoService.eliminar(id);
		ApiResponse<String> response = new ApiResponse<>(true, "Empleado eliminado con éxito", null);
		return ResponseEntity.ok(response);
	}
	
}
