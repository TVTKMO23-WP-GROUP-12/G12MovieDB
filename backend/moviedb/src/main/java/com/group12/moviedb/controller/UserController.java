package com.group12.moviedb.controller;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.group12.moviedb.services.UserService;

@RestController
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
    @GetMapping("/users/{id}")
    public User findOneUser(@PathVariable int id) {
        return this.userRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/users")
    public User addOneUser(@RequestBody User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return this.userRepository.save(user);
    }
    
    @CrossOrigin(origins = "*")
    @PatchMapping("/users/{id}")
    public User updateOneUser(@PathVariable int UserId, @RequestBody Map<String, Object> updates) {
        User user = this.userRepository.findById(UserId)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
        if (user != null) {
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
        return null;
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable int UserId, @RequestBody User user) {
        user.setId(UserId);
        return this.userService.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int userId) {
        this.userService.deleteUser(userId);
    }
}
