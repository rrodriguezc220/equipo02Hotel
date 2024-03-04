/**
 * @file: ApiResponse.java
 * @author: (c) 2024 MARCO
 * @created: 29 feb. 2024 17:28:30
 */

package com.equipo02.hotel.util;

/**
 * Clase que representa una respuesta genérica para las solicitudes de la API.
 * @param <T> Tipo de datos de la respuesta.
 */
public class ApiResponse<T> {
    private boolean success; // Indica si la solicitud fue exitosa.
    private String message;   // Mensaje descriptivo de la respuesta.
    private T data;           // Datos de la respuesta.

    /**
     * Constructor de la clase ApiResponse.
     * @param success Indica si la solicitud fue exitosa.
     * @param message Mensaje descriptivo de la respuesta.
     * @param data Datos de la respuesta.
     */
    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    /**
     * Obtiene el valor de success.
     * @return true si la solicitud fue exitosa, false de lo contrario.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Establece el valor de success.
     * @param success true si la solicitud fue exitosa, false de lo contrario.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Obtiene el mensaje descriptivo de la respuesta.
     * @return El mensaje descriptivo de la respuesta.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Establece el mensaje descriptivo de la respuesta.
     * @param message El mensaje descriptivo de la respuesta.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Obtiene los datos de la respuesta.
     * @return Los datos de la respuesta.
     */
    public T getData() {
        return data;
    }

    /**
     * Establece los datos de la respuesta.
     * @param data Los datos de la respuesta.
     */
    public void setData(T data) {
        this.data = data;
    }
}
