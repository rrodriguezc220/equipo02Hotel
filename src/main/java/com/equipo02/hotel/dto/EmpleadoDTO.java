/**
 * @file: EmpleadoDTO.java
 * @author: (c)2024 Julcamoro
 * @created: 3 mar 2024, 17:44:20
 */
package com.equipo02.hotel.dto;

import java.util.ArrayList;
import java.util.List;

import com.equipo02.hotel.domain.Reserva;
/**
 * Clase que representa un Empleado en el dominio del hotel en forma de DTO (Data Transfer Object).
 */
public class EmpleadoDTO {

	/**
     * Identificador único del empleado.
     */
	private Long idEmpleado;
	
	/**
     * DNI del empleado.
     */
	private String dniEmpleado;
	
	/**
     * Nombre completo del empleado.
     */
	private String nombreEmpleado;
	
	/**
     * Direccion del empleado.
     */
	private String direccionEmpleado;
	
	/**
     * Telefono del empleado.
     */
	private String telefonoEmpleado;
	
	/**
     * Correo del empleado.
     */
	private String correoEmpleado;
	//private Empleado supervisor;
	/**
     * Reservas asignadas a un empleado.
     */
	private List<Reserva> reservas = new ArrayList<Reserva>();

	
	/**
     * Métodos getters y setters para acceder y modificar los atributos de la clase Empleado.
     */
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getDniEmpleado() {
		return dniEmpleado;
	}
	public void setDniEmpleado(String dniEmpleado) {
		this.dniEmpleado = dniEmpleado;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public String getDireccionEmpleado() {
		return direccionEmpleado;
	}
	public void setDireccionEmpleado(String direccionEmpleado) {
		this.direccionEmpleado = direccionEmpleado;
	}
	public String getTelefonoEmpleado() {
		return telefonoEmpleado;
	}
	public void setTelefonoEmpleado(String telefonoEmpleado) {
		this.telefonoEmpleado = telefonoEmpleado;
	}
	public String getCorreoEmpleado() {
		return correoEmpleado;
	}
	public void setCorreoEmpleado(String correoEmpleado) {
		this.correoEmpleado = correoEmpleado;
	}
/*	public Empleado getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Empleado supervisor) {
		this.supervisor = supervisor;
	}*/
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
}
