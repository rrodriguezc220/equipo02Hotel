/**
 * @file: ErrorMassage.java
 * @author: (c) 2024 MARCO
 * @created: 29 feb. 2024 17:20:46
 */
package com.equipo02.hotel.exception;

public class ErrorMessage {
	
	public static final String RESERVA_NOT_FOUND = "La reserva con id proporcionado no fue encontrado";
	public static final String HABITACION_NOT_FOUND = "La habitacion con id proporcionado no fue encontrado";
	public static final String HUESPED_NOT_FOUND = "El huesped con id proporcionado no fue encontrado";
	public static final String EMPLEADO_NOT_FOUND = "El empleado con id proporcionado no fue encontrado";

	
	public ErrorMessage() {
		throw new IllegalStateException ("Utility class");
	}
	
	
}
