package com.equipo02.hotel.security.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @file: LoginRequest.java
 * @author: (c) MARCO
 * @created: 9/3/2024 10:27
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    String username;
    String password;
}