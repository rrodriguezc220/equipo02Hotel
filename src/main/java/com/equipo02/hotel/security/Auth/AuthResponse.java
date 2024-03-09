/**
 * @file: AuthResponse.java
 * @author: (c) MARCO
 * @created: 9/3/2024 10:26
 */
package com.equipo02.hotel.security.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    String token;
}