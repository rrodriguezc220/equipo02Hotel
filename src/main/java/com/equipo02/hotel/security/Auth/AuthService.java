/**
 * @file: AuthService.java
 * @author: (c) MARCO
 * @created: 9/3/2024 10:27
 */

package com.equipo02.hotel.security.Auth;

import com.equipo02.hotel.security.JWT.JwtService;
import com.equipo02.hotel.security.User.Rol;
import com.equipo02.hotel.security.User.User;
import com.equipo02.hotel.security.User.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Servicio de autenticación.
 * Este servicio maneja las operaciones de inicio de sesión y registro.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    /**
     * Repositorio de usuarios.
     * Este campo se utiliza para interactuar con la base de datos de usuarios.
     */
    private final UserRepository userRepository;

    /**
     * Servicio JWT.
     * Este campo se utiliza para generar y validar tokens JWT.
     */
    private final JwtService jwtService;

    /**
     * Codificador de contraseñas.
     * Este campo se utiliza para codificar las contraseñas de los usuarios.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Gestor de autenticación.
     * Este campo se utiliza para autenticar las solicitudes de inicio de sesión.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Método para iniciar sesión.
     * Este método autentica al usuario y genera un token de autenticación.
     *
     * @param request La solicitud de inicio de sesión.
     * @return Una respuesta de autenticación con el token.
     */
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    /**
     * Método para registrarse.
     * Este método registra un nuevo usuario y genera un token de autenticación.
     *
     * @param request La solicitud de registro.
     * @return Una respuesta de autenticación con el token.
     */
    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .rol(request.getRol())
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}