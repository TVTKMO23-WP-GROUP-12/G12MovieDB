package com.group12.moviedb.dataTransfer;

import java.util.Optional;

import com.group12.moviedb.mappers.UserMapper;
import com.group12.moviedb.models.User;
import com.group12.moviedb.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {

    private UserMapper userMapper;
    private UserRepository userRepository;

    private Integer id;
    private String username;
    private String email;
    private String login;
    private String token;

    public UserDto findByLogin(String login) {
        Optional<User> userOptional = userRepository.findByLogin(login);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return userMapper.toUserDto(user);
        } else {
            // Handle the case where the user is not found
            return null;
        }
    }
}
