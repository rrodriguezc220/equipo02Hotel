/**
 * @file: Proveedor.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:07
 */

package com.equipo02.hotel.domain.entity;

import lombok.Data;

/**
 * Clase Proveedor.
 * Esta clase representa a un proveedor en el sistema.
 */
@Data
public class Proveedor {

    /**
     * El ID del proveedor.
     * Este campo es único para cada proveedor.
     */
    private Long idProveedor;

    /**
     * El nombre del proveedor.
     * Este campo representa el nombre del proveedor.
     */
    private String nombre;

    /**
     * El correo electrónico del proveedor.
     * Este campo representa el correo electrónico del proveedor.
     */
    private String email;

    /**
     * El número de teléfono del proveedor.
     * Este campo representa el número de teléfono del proveedor.
     */
    private String telefono;
}
