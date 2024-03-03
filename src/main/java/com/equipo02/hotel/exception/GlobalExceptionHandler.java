/**
 * @file: GlobalExceptionHandler.java
 * @author: (c) 2024 MARCO
 * @created: 3 mar. 2024 11:46:36
 */
package com.equipo02.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    
	@ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,
				ex.getMessage(),
				request.getDescription(false));
		
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(IllegalOperationException.class)
    public ResponseEntity<ErrorMessage> handleIllegalOperationException(IllegalOperationException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,
				ex.getMessage(),
				request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	
	
	
}
