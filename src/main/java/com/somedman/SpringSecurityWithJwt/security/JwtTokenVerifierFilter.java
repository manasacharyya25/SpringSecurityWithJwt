package com.somedman.SpringSecurityWithJwt.security;

import com.somedman.SpringSecurityWithJwt.common.JwtUtils;
import io.jsonwebtoken.JwtException;
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

      //4. Parse Body and Subject
      String username = JwtUtils.extractUsername(jwtToken);

      //5. If SecurityContext is not already set up with another Authenticated User
      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
      {
        //6. Load User Details Via User Details Service
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        //7: Validate JWT Token
        if (!JwtUtils.validateToken(jwtToken, userDetails)) {
          throw new JwtException("Unauthorised");
        }

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
