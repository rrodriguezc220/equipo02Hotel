/**
 * @file: LoginRequest.java
 * @author: (c) MARCO
 * @created: 9/3/2024 10:27
 */

package com.equipo02.hotel.security.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase de solicitud de inicio de sesión.
 * Esta clase representa la información necesaria para iniciar sesión.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    /**
     * El nombre de usuario para iniciar sesión.
     */
    String username;

    /**
     * La contraseña para iniciar sesión.
     */
    String password;
}