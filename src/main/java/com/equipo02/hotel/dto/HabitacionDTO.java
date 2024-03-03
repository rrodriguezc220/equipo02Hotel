/**
 * @file: HabitacionDTO.java
 * @author: (c)2024 Cueva
 * @created: 2 mar 2024, 22:44:20
 */
package com.equipo02.hotel.dto;
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
    private String tipo;
    /**
     * Indica si la habitación está disponible o no.
     */
    private boolean disponible;
    
    /*
    private List<Reserva> reservas = new ArrayList<Reserva>();
    */
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
	
	/*
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
    */
    
}
