package com.somedman.SpringSecurityWithJwt.auth;

import com.somedman.SpringSecurityWithJwt.entity.ApplicationUser;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ApplicationUserDetailsService implements UserDetailsService
{
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
  {
    return new ApplicationUser("foo",
        passwordEncoder().encode("foo"),
        null,
        "email-address",
        true,
        true,
        true,
        true
        );
  }

  @Bean
  public PasswordEncoder passwordEncoder()
  {
    return new BCryptPasswordEncoder();
  }
}
