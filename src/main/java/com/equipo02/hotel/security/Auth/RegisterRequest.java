package com.equipo02.hotel.security.Auth;

import lombok.*;

/**
 * @file: RegisterRequest.java
 * @author: (c) MARCO
 * @created: 9/3/2024 10:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String username;
    String password;
    String firstname;
    String lastname;
}
