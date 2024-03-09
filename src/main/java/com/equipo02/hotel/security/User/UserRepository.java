/**
 * @file: UserRepository.java
 * @author: (c) MARCO
 * @created: 9/3/2024 11:02
 */
package com.equipo02.hotel.security.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
