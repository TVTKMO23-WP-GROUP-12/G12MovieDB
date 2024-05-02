package com.group12.moviedb.controller;


import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group12.moviedb.models.User;
import com.group12.moviedb.repository.UserRepository;
import com.group12.moviedb.services.UserService;

@RestController
@ControllerAdvice
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/users")
    public Iterable<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/users/{user_id}")
    public ResponseEntity<?> findOneUser(@PathVariable("user_id") Integer userId) {
    try {
        Optional<User> optionalUser = this.userRepository.findById(userId);
        return optionalUser.map(user -> ResponseEntity.ok(user))
                          .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("An error occurred while fetching the user.");
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/users/username/{username}")
    public ResponseEntity<?> findOneUsername(@PathVariable String username) {
        try {
            User user = this.userRepository.findByUsername(username);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An error occurred while fetching the user.");
        }
    }

    @CrossOrigin(origins = "*") 
    @PostMapping("/users")
    public User addOneUser(@RequestBody User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return this.userRepository.save(user);
    }
    
    @CrossOrigin(origins = "*")
    @PatchMapping("/users/{user_id}")
    public User updateOneUser(@PathVariable("user_id") Integer userId, @RequestBody Map<String, Object> updates) {
        User user = this.userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        String userDescription = (String) updates.get("description");
        user.setUserDescription(userDescription);
        return userRepository.save(user);
    }
    

    @CrossOrigin(origins = "*")
    @DeleteMapping("/users/{user_id}")
      public void deleteUser(@PathVariable("user_id") Integer userId) {
      userService.deleteUser(userId);
  }
}