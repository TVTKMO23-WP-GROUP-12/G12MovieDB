package com.group12.moviedb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    void deleteById(Integer userId);
    Optional<User> findById(User userId);
    User findByUsername(String username);
}
