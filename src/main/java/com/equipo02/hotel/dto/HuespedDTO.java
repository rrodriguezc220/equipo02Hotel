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

/**
 * DTO (Data Transfer Object) que representa un huesped del hotel.
 */
public class HuespedDTO {

	/**
     * Identificador único del huesped.
     */
	private Long idHuesped;
	/**
     * DNI único del huesped.
     */
	private String dniHuesped;
	/**
     * Nombre del huesped.
     */
	private String nombreHuesped;
	/**
     * Dirección del huesped.
     */
	private String direccionHuesped;
	/**
     * Teléfono del huesped.
     */
	private String telefonoHuesped;
	/**
     * Correo del huesped.
     */
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
