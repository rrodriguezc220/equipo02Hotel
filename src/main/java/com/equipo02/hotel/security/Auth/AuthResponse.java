/**
 * @file: AuthResponse.java
 * @author: (c) MARCO
 * @created: 9/3/2024 10:26
 */
package com.equipo02.hotel.security.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase de respuesta de autenticación.
 * Esta clase representa la respuesta de autenticación después de un inicio de sesión o registro exitoso.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    /**
     * El token de autenticación.
     */
    String token;
}