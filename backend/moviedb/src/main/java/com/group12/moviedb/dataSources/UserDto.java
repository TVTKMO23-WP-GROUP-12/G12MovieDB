package com.group12.moviedb.dataSources;


import com.group12.moviedb.mappers.UserMapper;
import com.group12.moviedb.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;
import java.util.Objects;

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



    public UserDto(Integer userId, String username, String email, String login, String token) {
        this.id = userId;
        this.username = username;
        this.email = email;
        this.login = login;
        this.token = token;
    }

  
    public UserMapper getUserMapper() {
        return this.userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserRepository getUserRepository() {
        return this.userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDto userMapper(UserMapper userMapper) {
        setUserMapper(userMapper);
        return this;
    }

    public UserDto userRepository(UserRepository userRepository) {
        setUserRepository(userRepository);
        return this;
    }

    public UserDto id(Integer userId) {
        setId(userId);
        return this;
    }

    public UserDto username(String username) {
        setUsername(username);
        return this;
    }

    public UserDto email(String email) {
        setEmail(email);
        return this;
    }

    public UserDto login(String login) {
        setLogin(login);
        return this;
    }

    public UserDto token(String token) {
        setToken(token);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserDto)) {
            return false;
        }
        UserDto userDto = (UserDto) o;
        return Objects.equals(userMapper, userDto.userMapper) && Objects.equals(userRepository, userDto.userRepository) && Objects.equals(id, userDto.id) && Objects.equals(username, userDto.username) && Objects.equals(email, userDto.email) && Objects.equals(login, userDto.login) && Objects.equals(token, userDto.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userMapper, userRepository, id, username, email, login, token);
    }

    @Override
    public String toString() {
        return "{" +
            " userMapper='" + getUserMapper() + "'" +
            ", userRepository='" + getUserRepository() + "'" +
            ", id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", email='" + getEmail() + "'" +
            ", login='" + getLogin() + "'" +
            ", token='" + getToken() + "'" +
            "}";
    }


    public static String createToken(String login2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createToken'");
    }



}
