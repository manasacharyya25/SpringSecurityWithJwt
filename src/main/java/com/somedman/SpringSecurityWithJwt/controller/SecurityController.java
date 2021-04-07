package com.somedman.SpringSecurityWithJwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SecurityController
{
  @GetMapping
  public String landingPageView()
  {
    return "landingPage";
  }
}
