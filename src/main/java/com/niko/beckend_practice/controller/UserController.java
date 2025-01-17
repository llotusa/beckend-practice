package com.niko.beckend_practice.controller;


import com.niko.beckend_practice.exception.UserNotFoundException;
import com.niko.beckend_practice.model.User;
import com.niko.beckend_practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("http://localhost:3000")

public class UserController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/user")
  public User newUser(@RequestBody User newUser) {
    return userRepository.save(newUser);
  }

  @GetMapping("/users")
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/user/{id}")
  public User getUserById(@PathVariable Long id) {
    return userRepository.findById(id)
          .orElseThrow(() -> new UserNotFoundException(id));
  }

  public @PutMapping("/user/{id}")
  User updateUser(@RequestBody User newUser, @PathVariable Long id) {
    return userRepository.findById(id)
          .map(user -> {
            user.setUsername(newUser.getUsername());
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            return userRepository.save(user);
          }).orElseThrow(() -> new UserNotFoundException(id));
  }

  @DeleteMapping("/user/{id}")
  public String deleteUser(@PathVariable Long id){
    if(!userRepository.existsById(id)){
      throw new UserNotFoundException(id);
    }
    userRepository.deleteById(id);
    return  "User with id "+id+" has been deleted success.";
  }



}