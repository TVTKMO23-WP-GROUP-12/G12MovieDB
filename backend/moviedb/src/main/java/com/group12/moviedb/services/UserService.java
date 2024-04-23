package com.group12.moviedb.services;

import org.springframework.transaction.annotation.Transactional;

import java.nio.CharBuffer;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.group12.moviedb.models.User;
import com.group12.moviedb.mappers.UserMapper;
import com.group12.moviedb.repository.UserRepository;
import com.group12.moviedb.dataSources.CredentialsDto;
import com.group12.moviedb.dataSources.SignUpDto;
import com.group12.moviedb.dataSources.UserDto;
import com.group12.moviedb.exceptions.AppException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper; 
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto findByLogin(String login) {
        User user = userRepository.findByLogin(login)
            .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByLogin(credentialsDto.getLogin())
            .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) { //hashing the password
            return userMapper.toUserDto(user);
        }

        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<User> optionalUser = userRepository.findByLogin(userDto.getLogin());
        
        if(optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword()))); //hashing the password

        User savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserService that = (UserService) o;
        return Objects.equals(userRepository, that.userRepository) &&
                Objects.equals(userMapper, that.userMapper) &&
                Objects.equals(passwordEncoder, that.passwordEncoder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRepository, userMapper, passwordEncoder);
    }

    @Override
    public String toString() {
        return "UserService{" +
                "userRepository=" + userRepository +
                ", userMapper=" + userMapper +
                ", passwordEncoder=" + passwordEncoder +
                '}';
    }

}