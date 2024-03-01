/**
 * @file: IllegalOperationException.java
 * @author: (c) 2024 MARCO
 * @created: 29 feb. 2024 17:14:23
 */
package com.equipo02.hotel.exception;
/**
 * Excepcion que se lanza cuando se realiza una operacion ilegal
 */
public class IllegalOperationException extends Exception{

	private static final long serialVersionUID = 1L;

	public IllegalOperationException(String message) {
		super (message);
	}
}
