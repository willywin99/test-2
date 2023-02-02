package com.mii.server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mii.server.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
  // JPQL
  @Query("SELECT u FROM User u WHERE u.username = :username")
  public List<User> searchByUsername(String username);
  
  // Query Method
  public Optional<User> findByPassword(String password);
  public List<User> findByUsernameContainsOrderById(String username);

  public Optional<User> findByUsername(String username); 
}
