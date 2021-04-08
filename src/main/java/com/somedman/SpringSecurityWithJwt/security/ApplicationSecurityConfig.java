package com.somedman.SpringSecurityWithJwt.security;

import com.somedman.SpringSecurityWithJwt.auth.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter
{
  @Autowired
  private ApplicationUserDetailsService appUserDetailsService;

  @Autowired
  private ApplicationPasswordEncoder passwordEncoder;

  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated().and().formLogin();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception
  {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider()
  {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder.passwordEncoder());
    provider.setUserDetailsService(appUserDetailsService);

    return provider;
  }
}
