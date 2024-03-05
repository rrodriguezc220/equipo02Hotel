/**
 * 
 * @file: Habitacion.java
 * @author: (c)2024 Cueva
 * @created: 2 mar 2024, 22:42:38
 *
 */
package com.equipo02.hotel.domain;

import java.math.BigDecimal;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
/**
 * Clase que representa una Habitación en el dominio del hotel.
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "idHabitacion")
public class Habitacion {
	
	/**
	  * Identificador único de la habitación.
	  */
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long idHabitacion;
	 
	 /**
	  * Tipo de la habitación.
	  */
	 private String tipo;
	 
	 /**
	  * Indica si la habitación está disponible o no.
	  */
	 private boolean disponible;
	 /**
	  * precio de la habitacion
	  */
	 private BigDecimal precio;
	 /**
	  * decripcionde la habitacion
	  */
	 private String descripcion;
	 /**
	  * Lista de reservas asociadas a la habitación.
	  */
	@ManyToMany(mappedBy = "habitaciones")
    @JsonBackReference
	//@JsonManagedReference
	private List<Reserva> reservas;
	
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
	
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<Reserva> getReservas() {
	    return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
	    this.reservas = reservas;
	}
}
