package com.group12.moviedb.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group12.moviedb.models.User;
import com.group12.moviedb.repository.UserRepository;
import java.time.Instant;
import java.time.ZoneId;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public User addUser(User user) {
        user.setCreatedAt(Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime());
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        user.setUpdatedAt(Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime());
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
