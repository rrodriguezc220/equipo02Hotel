/**
 * @file: JwtService.java
 * @author: (c) MARCO
 * @created: 9/3/2024 11:19
 */
package com.equipo02.hotel.security.JWT;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Servicio JWT.
 * Este servicio se encarga de generar y validar tokens JWT.
 */
@Service
public class JwtService {

    /**
     * Clave secreta para la firma de los tokens JWT.
     */
    private static final String SECRET_KEY="586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

    /**
     * Método para obtener un token JWT.
     * Este método genera un token JWT para un usuario.
     *
     * @param user Los detalles del usuario.
     * @return El token JWT.
     */
    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    /**
     * Método para obtener un token JWT con reclamaciones adicionales.
     * Este método genera un token JWT para un usuario con reclamaciones adicionales.
     *
     * @param extraClaims Las reclamaciones adicionales.
     * @param user Los detalles del usuario.
     * @return El token JWT.
     */
    private String getToken(Map<String,Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Método para obtener la clave secreta.
     * Este método decodifica la clave secreta para la firma de los tokens JWT.
     *
     * @return La clave secreta.
     */
    private Key getKey() {
        byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Método para obtener el nombre de usuario de un token JWT.
     * Este método extrae el nombre de usuario de un token JWT.
     *
     * @param token El token JWT.
     * @return El nombre de usuario.
     */
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    /**
     * Método para validar un token JWT.
     * Este método valida un token JWT para un usuario.
     *
     * @param token El token JWT.
     * @param userDetails Los detalles del usuario.
     * @return Verdadero si el token es válido, falso en caso contrario.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    /**
     * Método para obtener todas las reclamaciones de un token JWT.
     * Este método extrae todas las reclamaciones de un token JWT.
     *
     * @param token El token JWT.
     * @return Las reclamaciones.
     */
    private Claims getAllClaims(String token)
    {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Método para obtener una reclamación de un token JWT.
     * Este método extrae una reclamación de un token JWT.
     *
     * @param token El token JWT.
     * @param claimsResolver La función para resolver la reclamación.
     * @return La reclamación.
     */
    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Método para obtener la fecha de expiración de un token JWT.
     * Este método extrae la fecha de expiración de un token JWT.
     *
     * @param token El token JWT.
     * @return La fecha de expiración.
     */
    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }

    /**
     * Método para verificar si un token JWT ha expirado.
     * Este método verifica si un token JWT ha expirado.
     *
     * @param token El token JWT.
     * @return Verdadero si el token ha expirado, falso en caso contrario.
     */
    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }
}