/**
 * @file: Proveedor.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:07
 */

package com.equipo02.hotel.domain.entity;

import lombok.Data;

@Data
public class Proveedor {

    private Long idProveedor;

    private String nombre;

    private String email;

    private String telefono;
}
