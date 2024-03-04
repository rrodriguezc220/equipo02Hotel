/**
 * @file: ErrorResponse.java
 * @author: (c) 2024 MARCO
 * @created: 29 feb. 2024 17:19:04
 */
package com.equipo02.hotel.exception;


/**
 * Clase que representa un objeto de respuesta de error con un mensaje.
 */
public class ErrorResponse {
    
    private String message;

    /**
     * Constructor de ErrorResponse.
     *
     * @param message el mensaje de error.
     */
    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }   
}