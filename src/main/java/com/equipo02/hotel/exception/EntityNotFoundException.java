/**
 * @file: ErrorMassage.java
 * @author: (c) 2024 MARCO
 * @created: 29 feb. 2024 17:20:46
 */
package com.equipo02.hotel.exception;

/**
 * Excepción lanzada cuando una entidad no se encuentra en la persistencia.
 */

public class EntityNotFoundException extends Exception {
    
	/**
     * Identificador único de versión para la serialización.
     * Se utiliza para garantizar que la clase puede ser deserializada correctamente
     * incluso si ha cambiado entre versiones.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor de la excepción.
     *
     * @param message mensaje que describe la excepción.
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
    
}