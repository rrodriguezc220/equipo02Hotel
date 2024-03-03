/**
 * @file: Reserva.java
 * @author: (c) 2024 MARCO
 * @created: 29 feb. 2024 17:04:34
 */
package com.equipo02.hotel.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Clase que representa una reserva en la persistencia
 */


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "idReserva")
public class Reserva {
	
    /**
     * Identificador único de la reserva.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;
    /**
     * Fecha de inicio de la reserva.
     */
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    
    /**
     * Fecha de fin de la reserva.
     */
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    
    
    /**
     * Estado de la reserva (activa/inactiva).
     */
    private boolean estado;
    
    
    /**
     * Huesped asociado a la reserva.
     */
    @ManyToOne
    @JsonBackReference
    private Huesped huesped;
    
    /**
     * Empleado responsable de la reserva.
     */
    @ManyToOne
    private Empleado empleado;    
    
    
    /**
     * Lista de habitaciones asociadas a la reserva.
     */
    @ManyToMany
    @JoinTable(name = "habitacion_reserva",
    		joinColumns = @JoinColumn(name = "idReserva"),
    		inverseJoinColumns = @JoinColumn(name = "idHabitacion"))
    private List<Habitacion> habitaciones = new ArrayList<Habitacion>();
    
    
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