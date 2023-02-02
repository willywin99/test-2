package com.mii.server.controllers;

// import java.security.Provider.Service;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mii.server.models.User;
import com.mii.server.models.dto.request.SearchData;
// import com.mii.server.models.dto.request.UserRequest;
// import com.mii.server.services.CountryService;
import com.mii.server.services.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
  private UserService userService;
    
    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }
    
    @GetMapping(value = "/{id}")
    public User getById(@PathVariable int id){
        return userService.getById(id);
    }
    
    @GetMapping("/jpql")
    public List<User> searchByUsername(@RequestBody SearchData searchData){
        return userService.searchByUsername(searchData.getUsername());
    }
    
    @PostMapping("/query")
    public List<User> findByUsername(@RequestBody SearchData searchData){
        return userService.findByUsername(searchData.getSearchKey());
    }
    
    @PostMapping
    public User create(@RequestBody User user){
        return userService.create(user);
    }
    
    @PutMapping(value = "/{id}")
    public User update(@PathVariable int id, @RequestBody User user){
        return userService.update(id, user);
    }
    
    @DeleteMapping(value = "/{id}")
    public User delete(@PathVariable int id){
        return userService.delete(id);
    }
}