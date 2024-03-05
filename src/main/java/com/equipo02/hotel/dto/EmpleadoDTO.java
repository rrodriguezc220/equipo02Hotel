/**
 * @file: EmpleadoDTO.java
 * @author: (c)2024 Julcamoro
 * @created: 3 mar 2024, 17:44:20
 */
package com.equipo02.hotel.dto;

import java.util.ArrayList;
import java.util.List;

import com.equipo02.hotel.domain.Reserva;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	
	@NotBlank(message = "El DNI no puede estar en blanco")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 caracteres")
	@Pattern(regexp = "[0-9]{8}", message = "Formato de DNI no válido")
	private String dniEmpleado;
	
	/**
     * Nombre completo del empleado.
     */
	
	@NotBlank(message = "El nombre del empleado no puede estar en blanco")
	private String nombreEmpleado;
	
	/**
     * Direccion del empleado.
     */
	@NotBlank(message = "La direccion del empleado no puede estar en blanco")
	private String direccionEmpleado;
	
	/**
     * Telefono del empleado.
     */
	@NotBlank(message = "El telefono del empleado no puede estar en blanco")
	@Size(min = 9, max = 9, message = "El telefono debe tener exactamente 9 caracteres")
	@Pattern(regexp = "[0-9]{9}", message = "Formato de telefono no válido")
	private String telefonoEmpleado;
	
	/**
     * Correo del empleado.
     */
	@NotBlank(message = "El correo del empleado no puede estar en blanco")
	@Email(message = "Debe ser una dirección de correo electrónico válida")
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
