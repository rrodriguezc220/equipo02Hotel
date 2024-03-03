
package com.equipo02.hotel.domain;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
/**
 * 
 * @file: Habitacion.java
 * @author: (c)2024 Cueva
 * @created: 2 mar 2024, 22:42:38
 *
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "idHabitacion")
public class Habitacion {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long idHabitacion;
	 private String tipo;
	 private boolean disponible;
	 
	 
	/* @ManyToMany(mappedBy = "habitaciones")
	 private List<Reserva> reservas;
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
}
