package com.somedman.SpringSecurityWithJwt.entity;

import lombok.Builder;
import lombok.Data;

@Builder
public class AuthResponse {
    public String jwtToken;
}
