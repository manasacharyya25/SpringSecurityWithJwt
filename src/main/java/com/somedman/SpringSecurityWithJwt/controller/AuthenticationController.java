package com.somedman.SpringSecurityWithJwt.controller;

import com.somedman.SpringSecurityWithJwt.auth.ApplicationUserDetailsService;
import com.somedman.SpringSecurityWithJwt.entity.AuthRequest;
import com.somedman.SpringSecurityWithJwt.entity.AuthResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private ApplicationUserDetailsService userDetailsService;

    @PostMapping
    public AuthResponse authenticate(@RequestBody AuthRequest request) throws Exception {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

            final String secretKey = "SECretSeccrettSescertaskshdfas42343rtyerty4565ethet7567845utyjtyu84578rtyui4ru4567SECretSeccrettSescertaskshdfas";

            final String jwtToken = Jwts.builder()
                    .setSubject(userDetails.getUsername())
                    .claim("authorities", userDetails.getAuthorities())
                    .setIssuedAt(new Date())
                    .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                    .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .compact();
            return AuthResponse.builder()
                    .jwtToken(jwtToken)
                    .build();

        } catch (AuthenticationException ex) {
            throw new RuntimeException("User Authentication Failed");
        }
    }
}
