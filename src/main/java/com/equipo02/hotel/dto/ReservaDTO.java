/**
 * @file: ReservaDTO.java
 * @author: (c) 2024 MARCO
 * @created: 29 feb. 2024 17:24:22
 */
package com.equipo02.hotel.dto;

import java.util.Date;

/**
 * 
 */
public class ReservaDTO {
	
    /** Identificador único de la reserva. */
    private Long idReserva;
    /** Fecha de inicio de la reserva. */
    private Date fechaInicio;
    /** Fecha de fin de la reserva. */
    private Date fechaFin;
    /** Estado de la reserva. */
    private boolean estado;
    
    /*
    private Huesped huesped;
    private Empleado empleado;    
    private List<Habitacion> habitaciones = new ArrayList<Habitacion>();
    */
    
    
    /**
     * Métodos getters y setters para acceder y modificar los atributos de la clase Reserva.
     */
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
	/*
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
    */
    
}
