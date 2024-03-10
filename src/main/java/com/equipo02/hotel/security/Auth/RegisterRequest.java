/**
 * @file: RegisterRequest.java
 * @author: (c) MARCO
 * @created: 9/3/2024 10:27
 */

package com.equipo02.hotel.security.Auth;

import com.equipo02.hotel.security.User.Rol;
import lombok.*;

/**
 * Clase de solicitud de registro.
 * Esta clase representa la información necesaria para registrar un nuevo usuario.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    /**
     * El nombre de usuario para el nuevo usuario.
     */
    String username;

    /**
     * La contraseña para el nuevo usuario.
     */
    String password;

    /**
     * El primer nombre del nuevo usuario.
     */
    String firstname;

    /**
     * El apellido del nuevo usuario.
     */
    String lastname;

    /**
     * El rol del nuevo usuario.
     */
    Rol rol;
}