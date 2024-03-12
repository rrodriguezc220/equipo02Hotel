/**
 * @file: RegisterRequest.java
 * @author: (c) MARCO
 * @created: 9/3/2024 10:27
 */

package com.equipo02.hotel.security.Auth;

import com.equipo02.hotel.security.User.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "El username es requerido")
    @Email(message = "El username debe ser un correo electrónico")
    String username;

    /**
     * La contraseña para el nuevo usuario.
     */
    @NotBlank(message = "La contraseña es requerida")
    String password;

    /**
     * El primer nombre del nuevo usuario.
     */
    @NotBlank(message = "El firstname es requerido")
    String firstname;

    /**
     * El apellido del nuevo usuario.
     */
    @NotBlank(message = "El lastname es requerido")
    String lastname;

    /**
     * El rol del nuevo usuario.
     */
    @NotNull(message = "El rol es requerido")
    Rol rol;
}