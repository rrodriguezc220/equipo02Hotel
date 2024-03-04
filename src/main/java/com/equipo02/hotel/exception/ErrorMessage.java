/**
 * @file: ErrorMassage.java
 * @author: (c) 2024 MARCO
 * @created: 29 feb. 2024 17:20:46
 */
package com.equipo02.hotel.exception;

import org.springframework.http.HttpStatus;

/**
 * Clase que representa un mensaje de error con su respectivo estado, mensaje y descripción.
 */
public class ErrorMessage {
    
    public static final String RESERVA_NOT_FOUND = "La reserva con el ID proporcionado no fue encontrada";
    public static final String HABITACION_NOT_FOUND = "La habitación con el ID proporcionado no fue encontrada";
    public static final String HUESPED_NOT_FOUND = "El huésped con el ID proporcionado no fue encontrado";
    public static final String EMPLEADO_NOT_FOUND = "El empleado con el ID proporcionado no fue encontrado";

    private int status;
    private String message;
    private String description;

    /**
     * Constructor de ErrorMessage.
     *
     * @param status      el estado HTTP.
     * @param message     el mensaje de error.
     * @param description la descripción detallada del error.
     */
    public ErrorMessage(HttpStatus status, String message, String description) {
        this.status = status.value();
        this.message = message;
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Método estático que devuelve un ErrorMessage para una reserva no encontrada.
     *
     * @return un ErrorMessage para una reserva no encontrada.
     */
    public static ErrorMessage reservaNotFound() {
        return new ErrorMessage(HttpStatus.NOT_FOUND, RESERVA_NOT_FOUND, null);
    }

    /**
     * Método estático que devuelve un ErrorMessage para una habitación no encontrada.
     *
     * @return un ErrorMessage para una habitación no encontrada.
     */
    public static ErrorMessage habitacionNotFound() {
        return new ErrorMessage(HttpStatus.NOT_FOUND, HABITACION_NOT_FOUND, null);
    }

    /**
     * Método estático que devuelve un ErrorMessage para un huésped no encontrado.
     *
     * @return un ErrorMessage para un huésped no encontrado.
     */
    public static ErrorMessage huespedNotFound() {
        return new ErrorMessage(HttpStatus.NOT_FOUND, HUESPED_NOT_FOUND, null);
    }

    /**
     * Método estático que devuelve un ErrorMessage para un empleado no encontrado.
     *
     * @return un ErrorMessage para un empleado no encontrado.
     */
    public static ErrorMessage empleadoNotFound() {
        return new ErrorMessage(HttpStatus.NOT_FOUND, EMPLEADO_NOT_FOUND, null);
    }
}