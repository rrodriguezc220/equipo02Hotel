/**
 * @file: AuthController.java
 * @author: (c) MARCO
 * @created: 9/3/2024 10:26
 */

package com.equipo02.hotel.security.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

/**
 * Controlador de autenticación.
 * Este controlador maneja las solicitudes de inicio de sesión y registro.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Método para iniciar sesión.
     * Este método recibe una solicitud de inicio de sesión y devuelve una respuesta de autenticación.
     *
     * @param request La solicitud de inicio de sesión.
     * @return Una respuesta de autenticación.
     */
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    /**
     * Método para registrarse.
     * Este método recibe una solicitud de registro y devuelve una respuesta de autenticación.
     *
     * @param request La solicitud de registro.
     * @return Una respuesta de autenticación.
     */
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
}