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
 * @file: UserController.java
 * @author: (c)2024 Rodriguez
 * @created: 11/3/2024 09:28 p.m.
 */

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @GetMapping()
    public ResponseEntity<?> obtenerUsuarios() {
        return ResponseEntity.ok(authService.obtenerUsuarios());
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long idUser) throws EntityNotFoundException {
        return ResponseEntity.ok(authService.obtenerUsuarioPorId(idUser));
    }
}