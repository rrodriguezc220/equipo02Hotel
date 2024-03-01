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

import com.equipo02.hotel.domain.Huesped;
import com.equipo02.hotel.dto.HuespedDTO;
import com.equipo02.hotel.services.HuespedService;
import com.equipo02.hotel.util.ApiResponse;

@RestController
@RequestMapping("/api/huespedes")
public class HuespedController {

	@Autowired
	HuespedService huespedService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<?> obtenerTodos(){
		List<Huesped> huespedes = huespedService.listarHuespedes();
		List<HuespedDTO> huespedesDTOs = huespedes.stream().map(huesped -> modelMapper.map(huesped, HuespedDTO.class)).collect(Collectors.toList());

		ApiResponse<List<HuespedDTO>> response = new ApiResponse<>(true, "Lista de huespedes obtenida con éxito", huespedesDTOs);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
		Huesped huesped = huespedService.buscarPorId(id);
		HuespedDTO huespedDTO = modelMapper.map(huesped, HuespedDTO.class);

		ApiResponse<HuespedDTO> response = new ApiResponse<>(true, "Huesped obtenido con éxito", huespedDTO);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody HuespedDTO huespedDTO) {
		Huesped huesped = modelMapper.map(huespedDTO, Huesped.class);
		huespedService.grabar(huesped);

		HuespedDTO savedHuespedDTO = modelMapper.map(huesped, HuespedDTO.class);
		ApiResponse<HuespedDTO> response = new ApiResponse<>(true, "Huesped guardado con éxito", savedHuespedDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<HuespedDTO>> actualizar(@PathVariable Long id, @RequestBody HuespedDTO huespedDTO) {
		Huesped huesped = modelMapper.map(huespedDTO, Huesped.class);
		huespedService.actualizar(id, huesped);

		HuespedDTO updatedHuespedDTO = modelMapper.map(huesped, HuespedDTO.class);
		ApiResponse<HuespedDTO> response = new ApiResponse<>(true, "Huesped actualizado con éxito", updatedHuespedDTO);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) {
		huespedService.eliminar(id);
		ApiResponse<String> response = new ApiResponse<>(true, "Huesped eliminado con éxito", null);
		return ResponseEntity.ok(response);
	}
}