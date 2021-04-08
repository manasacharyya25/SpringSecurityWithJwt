package com.somedman.SpringSecurityWithJwt.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class ApplicationUser implements UserDetails
{
  private String username;
  private String password;
  private boolean isAccountNonExpired;
  private boolean isAccountNonLocked;
  private boolean isCredentialsNonExpired;
  private boolean isEnabled;
  private Collection<GrantedAuthority> authorities;
  private String additionalInformation;

  public ApplicationUser(
      String username,
      String password,
      Collection<GrantedAuthority> authorities,
      String additionalInformation,
      boolean isAccountNonExpired,
      boolean isAccountNonLocked,
      boolean isCredentialsNonExpired,
      boolean isEnabled)
  {
    this.username = username;
    this.password = password;
    this.isAccountNonExpired = isAccountNonExpired;
    this.isAccountNonLocked = isAccountNonLocked;
    this.isCredentialsNonExpired = isCredentialsNonExpired;
    this.isEnabled = isEnabled;
    this.authorities = authorities;
    this.additionalInformation = additionalInformation;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities()
  {
    return authorities;
  }

  @Override
  public String getPassword()
  {
    return password;
  }

  @Override
  public String getUsername()
  {
    return username;
  }

  @Override
  public boolean isAccountNonExpired()
  {
    return isAccountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked()
  {
    return isAccountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired()
  {
    return isCredentialsNonExpired;
  }

  @Override
  public boolean isEnabled()
  {
    return isEnabled;
  }

  public String getAdditionalInformation()
  {
    return additionalInformation;
  }
}
