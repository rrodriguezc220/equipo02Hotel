/**
 * @file: Empleado.java
 * @author: (c)2024 Julcamoro 
 * @created: Mar 3, 2024 8:33:59 AM
 */

package com.equipo02.hotel.domain;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;

/**
 * Clase que representa un empleado en la persistencia
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idEmpleado")
public class Empleado {
	
	/**
	  * Identificador único del empleado.
	  */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEmpleado;
	
	/**
	  * DNI del empleado
	  */
	@Column(unique = true)
	private String dniEmpleado;
	
	/**
	  * Nombre del empleado
	  */
	private String nombreEmpleado;
	
	/**
	  * Direccipon del empleado
	  */
	private String direccionEmpleado;
	
	/**
	  * Teléfono del empleado
	  */
	private String telefonoEmpleado;
	
	/**
	  * Correo del empleado
	  */
	private String correoEmpleado;
	
	/**
	  * Lista de reservas asociadas al empleado
	  */
    @OneToMany(mappedBy = "empleado")
	private List<Reserva> reservas = new ArrayList<>();

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

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
}