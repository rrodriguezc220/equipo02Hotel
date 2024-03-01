/**
 * @file: Reserva.java
 * @author: (c) 2024 MARCO
 * @created: 29 feb. 2024 17:04:34
 */
package com.equipo02.hotel.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Clase que representa una reserva en la persistencia
 */


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "idReserva")
public class Reserva {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;
    
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    private boolean estado;
    
    /*
    @ManyToOne
    @JsonBackReference
    private Huesped huesped;
    
    @ManyToOne
    @JsonBackReference
    private Empleado empleado;    
    

    @ManyToMany
    @JoinTable(name = "habitacion_Reserva",
    		joinColumns = @JoinColumn(name = "idReserva"),
    		inverseJoinColumns = @JoinColumn(name = "idHabitacion"))
    private List<Habitacion> habitaciones = new ArrayList<Habitacion>();

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