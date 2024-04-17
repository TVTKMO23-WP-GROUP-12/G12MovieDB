package com.group12.moviedb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    @SuppressWarnings("null")
    void deleteById(Integer id);
    @SuppressWarnings("null")
    Optional<User> findById(Integer id);
    Optional<User>findByLogin(String login);
}
