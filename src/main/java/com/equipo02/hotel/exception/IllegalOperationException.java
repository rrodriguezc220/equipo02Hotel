/**
 * @file: IllegalOperationException.java
 * @author: (c) 2024 MARCO
 * @created: 29 feb. 2024 17:14:23
 */

package com.equipo02.hotel.exception;

/**
 * Excepción que se lanza cuando se realiza una operación ilegal.
 */
public class IllegalOperationException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor de la excepción.
     *
     * @param message el mensaje de error.
     */
    public IllegalOperationException(String message) {
        super(message);
    }
}