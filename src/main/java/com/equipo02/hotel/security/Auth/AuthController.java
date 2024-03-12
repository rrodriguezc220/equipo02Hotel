/**
 * @file: AuthController.java
 * @author: (c) MARCO
 * @created: 9/3/2024 10:26
 */

package com.equipo02.hotel.security.Auth;

import com.equipo02.hotel.dto.HuespedDTO;
import com.equipo02.hotel.exception.IllegalOperationException;
import com.equipo02.hotel.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request, BindingResult result) throws IllegalOperationException
    {
        if (result.hasErrors()) {
            return validar(result);
        }
        return ResponseEntity.ok(authService.register(request));
    }

    /**
     * Método para validar los errores de un BindingResult.
     * Este método recibe un BindingResult, verifica si hay errores y, en caso de que los haya, devuelve una respuesta con los errores.
     *
     * @param result El BindingResult a validar.
     * @return Una respuesta con los errores de validación, si los hay.
     */
    private ResponseEntity<?> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), err.getDefaultMessage());
        });
        ApiResponse<Map<String, String>> response = new ApiResponse<>(false, "Errores de validación", errores);
        return ResponseEntity.badRequest().body(response);
    }
}