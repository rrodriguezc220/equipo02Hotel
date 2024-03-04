/**
 * @file: BadRequestException.java
 * @author: (c) 2024 MARCO
 * @created: 3 mar. 2024 19:23:33
 */

package com.equipo02.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Excepción lanzada cuando se recibe una solicitud incorrecta por parte del cliente.
 * Se corresponde con el código de estado HTTP 400 Bad Request.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    /**
     * Identificador único de versión para la serialización.
     * Se utiliza para garantizar que la clase puede ser deserializada correctamente
     * incluso si ha cambiado entre versiones.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Crea una nueva instancia de BadRequestException con un mensaje descriptivo.
     *
     * @param message El mensaje que describe la excepción.
     */
    public BadRequestException(String message) {
        super(message);
    }
}
