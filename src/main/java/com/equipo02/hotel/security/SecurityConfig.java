/**
 * @file: WebSecurityConfig.java
 * @author: (c) MARCO
 * @created: 8/3/2024 22:45
 */
package com.equipo02.hotel.security;

import com.equipo02.hotel.security.JWT.JwtAuthenticationFilter;
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


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( authorize -> authorize
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(sessionManager->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }*/

    private static final String AUTH_PATH = "/auth/**";
    private static final String[] EMPLOYEE_PATHS = {"/api/huespedes/**", "/api/reservas/**", "/api/habitaciones/**"};
    private static final String ADMIN_PATH = "/api/**";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        RequestMatcher employeePaths = new OrRequestMatcher(
                Arrays.stream(EMPLOYEE_PATHS).map(AntPathRequestMatcher::new).collect(Collectors.toList())
        );
        RequestMatcher adminPaths = new AntPathRequestMatcher(ADMIN_PATH);

        try {
            return http
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
