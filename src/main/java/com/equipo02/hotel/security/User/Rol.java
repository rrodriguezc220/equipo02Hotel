/**
 * @file: Rol.java
 * @author: (c) MARCO
 * @created: 9/3/2024 10:58
 */
package com.equipo02.hotel.security.User;

/**
 * Enumeración de roles de usuario.
 * Esta enumeración define los diferentes roles que puede tener un usuario en el sistema.
 */
public enum Rol {
    /**
     * Rol de administrador.
     * Los usuarios con este rol tienen acceso completo al sistema.
     */
    ADMIN,

    /**
     * Rol de empleado.
     * Los usuarios con este rol tienen acceso limitado al sistema.
     */
    EMPLOYEE,

    /**
     * Rol de usuario.
     * Los usuarios con este rol tienen acceso básico al sistema.
     */
    USER
}
