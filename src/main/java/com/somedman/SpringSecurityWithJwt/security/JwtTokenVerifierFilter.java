package com.somedman.SpringSecurityWithJwt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenVerifierFilter extends OncePerRequestFilter
{
  @Autowired
  private UserDetailsService userDetailsService;

  JwtTokenVerifierFilter(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }
  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
  {
    //1. Get JWT Token from Request Header
    String authHeader = request.getHeader("Authorization");

    //2. Reject Request if Authorization Header Doesn't Exist or not in Right Format
    if(Strings.isEmpty(authHeader) || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      //3. Get JWT Token From Authorization Header
      String jwtToken = authHeader.replace("Bearer ", "");

      final String secretKey = "SECretSeccrettSescertaskshdfas42343rtyerty4565ethet7567845utyjtyu84578rtyui4ru4567SECretSeccrettSescertaskshdfas";

      //4. Parse Signed JWT Claims
      Jws<Claims> claimsJws = Jwts.parserBuilder()
          .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
          .build()
          .parseClaimsJws(jwtToken);

      //5. Parse Body and Subject
      String username = claimsJws.getBody().getSubject();

      //6. If SecurityContext is not already set up with another Authenticated User
      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
      {
        //7. Load User Details Via User Details Service
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        //8. Set Authentication in SecurityContext
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities()
        );
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
      filterChain.doFilter(request, response);
    } catch (Exception ex) {
      throw ex;
    }
  }
}
