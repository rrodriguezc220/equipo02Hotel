/**
 * @file: UserController.java
 * @author: (c)2024 Rodriguez
 * @created: 11/3/2024 09:28 p.m.
 */

package com.equipo02.hotel.security.User;

import com.equipo02.hotel.exception.EntityNotFoundException;
import com.equipo02.hotel.security.Auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de usuarios.
 * Este controlador maneja las solicitudes para obtener información de los usuarios.
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    /**
     * Servicio de autenticación.
     * Este campo se utiliza para interactuar con el servicio de autenticación.
     */
    private final AuthService authService;

    /**
     * Método para obtener todos los usuarios.
     * Este método devuelve una lista de todos los usuarios registrados en el sistema.
     *
     * @return Una respuesta ResponseEntity con la lista de usuarios.
     */
    @GetMapping()
    public ResponseEntity<?> obtenerUsuarios() {
        return ResponseEntity.ok(authService.obtenerUsuarios());
    }

    /**
     * Método para obtener un usuario por su ID.
     * Este método devuelve un usuario basado en su ID.
     *
     * @param idUser El ID del usuario.
     * @return Una respuesta ResponseEntity con el usuario solicitado.
     * @throws EntityNotFoundException Si el usuario con el ID proporcionado no puede ser encontrado.
     */
    @GetMapping("/{idUser}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long idUser) throws EntityNotFoundException {
        return ResponseEntity.ok(authService.obtenerUsuarioPorId(idUser));
    }
}