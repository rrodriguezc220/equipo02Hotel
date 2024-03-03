/**
 * @file: ErrorMassage.java
 * @author: (c) 2024 MARCO
 * @created: 29 feb. 2024 17:20:46
 */
package com.equipo02.hotel.exception;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
    public static final String RESERVA_NOT_FOUND = "La reserva con id proporcionado no fue encontrado";
    public static final String HABITACION_NOT_FOUND = "La habitacion con id proporcionado no fue encontrado";
    public static final String HUESPED_NOT_FOUND = "El huesped con id proporcionado no fue encontrado";
    public static final String EMPLEADO_NOT_FOUND = "El empleado con id proporcionado no fue encontrado";

    private int status;
    private String message;
    private String description;

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

    public static ErrorMessage reservaNotFound() {
        return new ErrorMessage(HttpStatus.NOT_FOUND, RESERVA_NOT_FOUND, null);
    }

    public static ErrorMessage habitacionNotFound() {
        return new ErrorMessage(HttpStatus.NOT_FOUND, HABITACION_NOT_FOUND, null);
    }

    public static ErrorMessage huespedNotFound() {
        return new ErrorMessage(HttpStatus.NOT_FOUND, HUESPED_NOT_FOUND, null);
    }

    public static ErrorMessage empleadoNotFound() {
        return new ErrorMessage(HttpStatus.NOT_FOUND, EMPLEADO_NOT_FOUND, null);
    }
}
