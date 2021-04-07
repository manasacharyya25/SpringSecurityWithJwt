package com.somedman.SpringSecurityWithJwt.controller;

import io.prometheus.jmx.shaded.javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SecurityController
{
  @GetMapping
  public String home(HttpServletRequest httpServletRequest)
  {
    return "Logged In";
  }
}
