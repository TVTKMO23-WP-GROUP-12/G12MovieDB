package com.group12.moviedb.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group12.moviedb.models.User;
import com.group12.moviedb.repository.UserRepository;
import com.group12.moviedb.services.*;


@RestController
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController (UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/users")
    public Iterable<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User findOneUser(@PathVariable int id) {
        return this.userRepository.findById(id);
    }

    @PostMapping("/users")
    public User addOneUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }
    
    @PatchMapping("/users/{id}")
    public User updateOneUser(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        User user = this.userRepository.findById(id);
        updates.forEach((key, value) -> {
            switch (key) {
                  case "username":
                    user.setUsername((String) value);
                    break;
                case "email":
                    user.setEmail((String) value);
                    break;
                case "password":
                    user.setPassword((String) value);
                    break;
                default:
                    break;
            }
        });
    return this.userRepository.save(user);
    }
    
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
      user.setId(id);
      return this.userService.updateUser(user);
  }
    
    @DeleteMapping("/users/{id}")
      public void deleteUser(@PathVariable Long id) {
      userService.deleteUser(id);
  }

}

