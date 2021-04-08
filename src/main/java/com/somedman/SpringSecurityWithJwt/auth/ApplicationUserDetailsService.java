package com.somedman.SpringSecurityWithJwt.auth;

import com.somedman.SpringSecurityWithJwt.repository.ApplicationUserRepository;
import com.somedman.SpringSecurityWithJwt.security.ApplicationPasswordEncoder;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class ApplicationUserDetailsService implements UserDetailsService
{

  @Autowired
  private ApplicationUserRepository userRepo;
  @Autowired
  private ApplicationPasswordEncoder appPassEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
  {
    val dbResponse = userRepo.findByUsername(username);
    dbResponse.setPassword(appPassEncoder.passwordEncoder().encode(dbResponse.getPassword()));
    return  dbResponse;
  }
}
