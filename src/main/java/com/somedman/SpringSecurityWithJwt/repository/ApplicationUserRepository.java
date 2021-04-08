package com.somedman.SpringSecurityWithJwt.repository;

import com.somedman.SpringSecurityWithJwt.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Integer>
{
  @Query(value="Select * from public.application_user where username = ?1", nativeQuery = true)
  public ApplicationUser findByUsername(String username);
}
