/**
 * @file: HuespedDTO.java
 * @author: (c)2024 Rodriguez
 * @created: 3 mar. 2024 17:21:05
 */

package com.equipo02.hotel.dto;

import java.util.ArrayList;
import java.util.List;

import com.equipo02.hotel.domain.Huesped;
import com.equipo02.hotel.domain.Reserva;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Clase que representa un objeto de transferencia de datos (DTO) para un huésped del hotel.
 */
public class HuespedDTO {

	/**
     * Identificador único del huesped.
     */
	private Long idHuesped;
	
	/**
     * DNI único del huesped.
     */
	@NotBlank(message = "El DNI no puede estar en blanco")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 caracteres")
	@Pattern(regexp = "[0-9]{8}", message = "Formato de DNI no válido")
	private String dniHuesped;
	
	/**
     * Nombre del huesped.
     */
	@NotBlank(message = "El nombre no puede estar en blanco")
	private String nombreHuesped;
	
	/**
     * Dirección del huesped.
     */
	private String direccionHuesped;
	
	/**
     * Teléfono del huesped.
     */
	@NotBlank(message = "El teléfono no puede estar en blanco")
    @Size(min = 9, max = 9, message = "El teléfono debe tener exactamente 9 caracteres")
	@Pattern(regexp = "[0-9]{9}", message = "Formato de teléfono no válido")
	private String telefonoHuesped;
	
	/**
     * Correo del huesped.
     */
	@NotEmpty(message = "El correo no puede estar en blanco")
    @Email(message = "Formato de correo no válido")
	@Column(unique = true)
	private String correoHuesped;
	
	/**
     * Aval asociado al huesped.
     */
	private Huesped aval;
	/**
     * Reservas asociado al huesped.
     */
	private List<Reserva> reservas = new ArrayList<>();

	/**
     * Métodos getters y setters para acceder y modificar los atributos de la clase Huesped.
     */
	public Long getIdHuesped() {
		return idHuesped;
	}

	public void setIdHuesped(Long idHuesped) {
		this.idHuesped = idHuesped;
	}

	public String getDniHuesped() {
		return dniHuesped;
	}

	public void setDniHuesped(String dniHuesped) {
		this.dniHuesped = dniHuesped;
	}

	public String getNombreHuesped() {
		return nombreHuesped;
	}

	public void setNombreHuesped(String nombreHuesped) {
		this.nombreHuesped = nombreHuesped;
	}

	public String getDireccionHuesped() {
		return direccionHuesped;
	}

	public void setDireccionHuesped(String direccionHuesped) {
		this.direccionHuesped = direccionHuesped;
	}

	public String getTelefonoHuesped() {
		return telefonoHuesped;
	}

	public void setTelefonoHuesped(String telefonoHuesped) {
		this.telefonoHuesped = telefonoHuesped;
	}

	public String getCorreoHuesped() {
		return correoHuesped;
	}

	public void setCorreoHuesped(String correoHuesped) {
		this.correoHuesped = correoHuesped;
	}

	public Huesped getAval() {
		return aval;
	}

	public void setAval(Huesped aval) {
		this.aval = aval;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

}
