/**
 * @file: ReservaDTO.java
 * @author: (c) 2024 MARCO
 * @created: 29 feb. 2024 17:24:22
 */
package com.equipo02.hotel.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.equipo02.hotel.domain.Empleado;
import com.equipo02.hotel.domain.Habitacion;
import com.equipo02.hotel.domain.Huesped;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

/**
 * DTO (Data Transfer Object) que representa una reserva en el hotel.
 */
public class ReservaDTO {
	
    /** Identificador único de la reserva. */
    private Long idReserva;
    
    /** Fecha de inicio de la reserva. */
    @NotNull(message = "no puede ser nula")
    @Future
    private Date fechaInicio;
    
    /** Fecha de fin de la reserva. */
    @NotNull(message = "no puede ser nula")
    @Future
    private Date fechaFin;
    
    /** Estado de la reserva. */
    @NotNull
    private boolean estado;
    
    /** Huesped asociado a la reserva. */
    @NotNull
    private Huesped huesped;
    
    /** Empleado asociado a la reserva. */
    @NotNull
    private Empleado empleado;    
    
    /** Lista de habitaciones asociadas a la reserva. */
    private List<Habitacion> habitaciones = new ArrayList<>();

    // Getters y setters
    
	public Long getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(Long idReserva) {
		this.idReserva = idReserva;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public Huesped getHuesped() {
		return huesped;
	}
	public void setHuesped(Huesped huesped) {
		this.huesped = huesped;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}
	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}
    
}
