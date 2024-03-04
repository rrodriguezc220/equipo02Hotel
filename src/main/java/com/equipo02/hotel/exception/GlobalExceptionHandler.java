/**
 * @file: GlobalExceptionHandler.java
 * @author: (c) 2024 MARCO
 * @created: 3 mar. 2024 11:46:36
 */
package com.equipo02.hotel.exception;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Clase para manejar excepciones globales en la aplicación.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja la excepción de entidad no encontrada.
     *
     * @param ex      la excepción de entidad no encontrada.
     * @param request la solicitud web.
     * @return ResponseEntity con el mensaje de error y el código de estado NOT FOUND.
     */
	@ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,
				ex.getMessage(),
				request.getDescription(false));
		
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
	
    /**
     * Maneja la excepción de operación ilegal.
     *
     * @param ex      la excepción de operación ilegal.
     * @param request la solicitud web.
     * @return ResponseEntity con el mensaje de error y el código de estado INTERNAL SERVER ERROR.
     */
	@ExceptionHandler(IllegalOperationException.class)
    public ResponseEntity<ErrorMessage> handleIllegalOperationException(IllegalOperationException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,
				ex.getMessage(),
				request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
    /**
     * Maneja la excepción de método no soportado.
     *
     * @param ex      la excepción de método no soportado.
     * @param request la solicitud web.
     * @return ResponseEntity con el mensaje de error y el código de estado METHOD NOT ALLOWED.
     */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorMessage> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, WebRequest request) {
	    String message = "El método " + ex.getMethod() + " no está permitido para esta solicitud";
	    ErrorMessage errorMessage = new ErrorMessage(HttpStatus.METHOD_NOT_ALLOWED,
	            message,
	            request.getDescription(false));
	    return new ResponseEntity<>(errorMessage, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
    /**
     * Maneja la excepción de solicitud incorrecta.
     *
     * @param ex      la excepción de solicitud incorrecta.
     * @param request la solicitud web.
     * @return ResponseEntity con el mensaje de error y el código de estado BAD REQUEST.
     */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorMessage> handleBadRequestException(BadRequestException ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST,
	            ex.getMessage(),
	            request.getDescription(false));
	    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

}
