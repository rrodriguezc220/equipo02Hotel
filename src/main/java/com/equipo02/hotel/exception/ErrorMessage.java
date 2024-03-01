/**
 * @file: ErrorMassage.java
 * @author: (c) 2024 MARCO
 * @created: 29 feb. 2024 17:20:46
 */
package com.equipo02.hotel.exception;

public class ErrorMessage {
	
	public static final String RESERVA_NOT_FOUND = "La reserca con id proporcionado no fue encontrado";
	
	
	public ErrorMessage() {
		throw new IllegalStateException ("Utility class");
	}
	
	
}
