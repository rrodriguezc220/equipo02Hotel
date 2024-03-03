package com.equipo02.hotel.dto;
/**
 * 
 * @file: HabitacionDTO.java
 * @author: (c)2024 Cueva
 * @created: 2 mar 2024, 22:44:20
 *
 */
public class HabitacionDTO {
	private Long idHabitacion;
    private String tipo;
    private boolean disponible;
    
    /*
    private List<Reserva> reservas = new ArrayList<Reserva>();
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
