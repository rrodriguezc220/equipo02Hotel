/**
 * @file: User.java
 * @author: (c) MARCO
 * @created: 8/3/2024 22:04
 */
package com.equipo02.hotel.security.User;

import com.equipo02.hotel.security.Auth.LoginRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Clase de usuario.
 * Esta clase representa a un usuario en el sistema.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {

    /**
     * ID del usuario.
     * Este campo es la clave primaria en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idUser;

    /**
     * Nombre de usuario.
     * Este campo es único en la base de datos.
     */
    @Basic
    @Column(unique = true)
    String username;

    /**
     * Contraseña del usuario.
     */
    String password;

    /**
     * Nombre del usuario.
     */
    String firstname;

    /**
     * Apellido del usuario.
     */
    String lastname;

    /**
     * Rol del usuario.
     * Este campo representa el rol del usuario en el sistema.
     */
    @Enumerated(EnumType.STRING)
    Rol rol;

    /**
     * Método para obtener las autoridades del usuario.
     * Este método devuelve las autoridades del usuario en el sistema.
     *
     * @return Las autoridades del usuario.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((rol.name())));
    }

    /**
     * Método para verificar si la cuenta del usuario no ha expirado.
     * Este método devuelve verdadero si la cuenta del usuario no ha expirado.
     *
     * @return Verdadero si la cuenta no ha expirado, falso en caso contrario.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Método para verificar si la cuenta del usuario no está bloqueada.
     * Este método devuelve verdadero si la cuenta del usuario no está bloqueada.
     *
     * @return Verdadero si la cuenta no está bloqueada, falso en caso contrario.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Método para verificar si las credenciales del usuario no han expirado.
     * Este método devuelve verdadero si las credenciales del usuario no han expirado.
     *
     * @return Verdadero si las credenciales no han expirado, falso en caso contrario.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Método para verificar si el usuario está habilitado.
     * Este método devuelve verdadero si el usuario está habilitado.
     *
     * @return Verdadero si el usuario está habilitado, falso en caso contrario.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}