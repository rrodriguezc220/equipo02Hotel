/**
 * @file: HabitacionDTO.java
 * @author: (c)2024 Cueva
 * @created: 2 mar 2024, 22:44:20
 */
package com.equipo02.hotel.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.equipo02.hotel.domain.Habitacion;
import com.equipo02.hotel.domain.Reserva;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

/**
 * Clase que representa una Habitación en el dominio del hotel en forma de DTO (Data Transfer Object).
 */
public class HabitacionDTO {
	
	/**
     * Identificador único de la habitación.
     */
	private Long idHabitacion;
	/**
     * Tipo de la habitación. 
     */
	@NotNull(message = "El tipo de habitación no puede ser nulo")
    private String tipo;
    
    /**
	  * precio de la habitacion
	  */
	@NotNull(message = "El precio de la habitación no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
	 private BigDecimal precio;
	 /**
	  * decripcionde la habitacion
	  */
	@NotNull(message = "La descripción de la habitación no puede ser nula")
	 private String descripcion;
    /**
     * Indica si la habitación está disponible o no.
     */
    private boolean disponible;
    
    private List<Reserva> reservas = new ArrayList<Reserva>();
    
    /**
     * Métodos getters y setters para acceder y modificar los atributos de la clase Habitación.
     */
	public Long getIdHabitacion() {
		return idHabitacion;
	}
	public void setIdHabitacion(Long idHabitacion) {
		this.idHabitacion = idHabitacion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
    
    
}
