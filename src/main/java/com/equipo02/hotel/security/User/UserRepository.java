/**
 * @file: UserRepository.java
 * @author: (c) MARCO
 * @created: 9/3/2024 11:02
 */
package com.equipo02.hotel.security.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repositorio de usuario.
 * Esta interfaz permite realizar operaciones de base de datos en la tabla de usuarios.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * Método para buscar un usuario por su nombre de usuario.
     * Este método devuelve un usuario opcional que coincide con el nombre de usuario proporcionado.
     *
     * @param username El nombre de usuario.
     * @return Un usuario opcional.
     */
    Optional<User> findByUsername(String username);
}