/**
 * @file: WebSecurityConfig.java
 * @author: (c) MARCO
 * @created: 8/3/2024 22:45
 */
package com.equipo02.hotel.security;

import com.equipo02.hotel.security.JWT.JwtAuthenticationFilter;
import com.equipo02.hotel.util.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Configuración de seguridad.
 * Esta clase configura la seguridad de la aplicación.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * Filtro de autenticación JWT.
     * Este campo es utilizado para autenticar las solicitudes con JWT.
     */
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Proveedor de autenticación.
     * Este campo es utilizado para autenticar las solicitudes.
     */
    private final AuthenticationProvider authProvider;

    /**
     * Ruta de autenticación.
     * Esta constante define la ruta de autenticación.
     */
    private static final String AUTH_PATH = "/auth/**";

    /**
     * Rutas de empleado.
     * Esta constante define las rutas accesibles para los empleados.
     */
    private static final String[] EMPLOYEE_PATHS = {"/api/huespedes/**", "/api/reservas/**", "/api/habitaciones/**"};

    /**
     * Ruta de administrador.
     * Esta constante define la ruta accesible para los administradores.
     */
    private static final String ADMIN_PATH = "/api/**";

    /**
     * Método para configurar la cadena de filtros de seguridad.
     * Este método configura la cadena de filtros de seguridad de la aplicación.
     *
     * @param http El objeto HttpSecurity.
     * @return La cadena de filtros de seguridad.
     * @throws Exception Si ocurre un error durante la configuración.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        RequestMatcher employeePaths = new OrRequestMatcher(
                Arrays.stream(EMPLOYEE_PATHS).map(AntPathRequestMatcher::new).collect(Collectors.toList())
        );
        RequestMatcher adminPaths = new AntPathRequestMatcher(ADMIN_PATH);

        try {
            return http
                    .exceptionHandling(exceptionHandling -> exceptionHandling
                            .accessDeniedHandler((request, response, ex) -> {
                                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                                response.setContentType("application/json");
                                ApiResponse<String> apiResponse = new ApiResponse<>(false, "Acceso denegado: No tienes permiso para acceder a este recurso.", null);
                                ObjectMapper mapper = new ObjectMapper();
                                String jsonResponse = mapper.writeValueAsString(apiResponse);
                                response.getWriter().write(jsonResponse);
                            }))
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests( authorize -> authorize
                            .requestMatchers(new AntPathRequestMatcher(AUTH_PATH)).permitAll()
                            .requestMatchers(employeePaths).hasAnyAuthority("EMPLOYEE", "ADMIN")
                            .requestMatchers(adminPaths).hasAuthority("ADMIN")
                            .anyRequest().authenticated())
                    .sessionManagement(sessionManager->
                            sessionManager
                                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authenticationProvider(authProvider)
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
        } catch (Exception e) {
            // manejar la excepción
            throw e;
        }
    }
}