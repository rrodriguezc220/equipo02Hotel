/**
 * @file: Huesped.java
 * @author: (c)2024 Rodriguez
 * @created: 3 mar. 2024 17:12:23
 */

package com.equipo02.hotel.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/*import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;*/

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

/**
 * Clase que representa un Huesped en la persistencia
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idHuesped")
public class Huesped {

	/**
     * Identificador único del huesped.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idHuesped;
	
	/**
     * DNI único del huesped.
     */
	@Column(unique = true)
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
	@OneToOne
    @JoinColumn(name = "idAval")
	private Huesped aval;
	
	/**
     * Reservas asociado al huesped.
     */
	@OneToMany(mappedBy = "huesped")
	@JsonManagedReference
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