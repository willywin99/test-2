package com.mii.server.services;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.mii.server.models.dto.request.UserRequest;
import com.mii.server.repositories.UserRepository;

import lombok.AllArgsConstructor;
import java.util.List;
import com.mii.server.models.User;

@Service
@AllArgsConstructor
public class UserService {
  private UserRepository userRepository;
  private ModelMapper modelMapper;

  public List<User> getAll() {
      return userRepository.findAll();
  }

  public User getById(Integer id) {
      return userRepository
        .findById(id)
        .orElseThrow(
          () -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "User not found!!!"
          )
      );
  }

  public User create(User user) {
    if (userRepository.findByUsername(user.getPassword()).isPresent()) {
      throw new ResponseStatusException(
        HttpStatus.CONFLICT,
        "Username is already exists..."
      );
    }
    return userRepository.save(user);
  }

  public User update(Integer id, User user) {
      getById(id);
      user.setId(id);
      return userRepository.save(user);
  }

  // JPQL
  public List<User> searchByUsername(String username){
    return userRepository.searchByUsername(username);
}

  // Query Method

  public List<User> findByUsername(String username){
    return userRepository.findByUsernameContainsOrderById(username);
  }

  public User delete(Integer id) {
    User user = getById(id);
    userRepository.delete(user);
    return user;
  }
}