/**
 * @file: JWTAuthenticationFilter.java
 * @author: (c) MARCO
 * @created: 9/3/2024 10:11
 */

package com.equipo02.hotel.security.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de autenticación JWT.
 * Este filtro se encarga de autenticar las solicitudes HTTP con tokens JWT.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * Servicio JWT.
     * Este campo se utiliza para generar y validar tokens JWT.
     */
    private final JwtService jwtService;

    /**
     * Servicio de detalles de usuario.
     * Este campo se utiliza para cargar los detalles de un usuario.
     */
    private final UserDetailsService userDetailsService;

    /**
     * Método para filtrar las solicitudes HTTP.
     * Este método autentica las solicitudes HTTP con tokens JWT.
     *
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @param filterChain La cadena de filtros.
     * @throws ServletException Si ocurre un error al procesar la solicitud.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = getTokenFromRequest(request);
        final String username;

        if(token == null){
            filterChain.doFilter(request, response);
            return;
        }

        username = jwtService.getUsernameFromToken(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(token, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }
        filterChain.doFilter(request, response);
    }

    /**
     * Método para obtener el token de la solicitud HTTP.
     * Este método extrae el token JWT de la cabecera de autorización de la solicitud HTTP.
     *
     * @param request La solicitud HTTP.
     * @return El token JWT, o null si no se encuentra.
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}