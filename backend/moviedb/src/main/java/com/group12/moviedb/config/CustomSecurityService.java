package com.group12.moviedb.config;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.group12.moviedb.repository.UserRepository;
import com.group12.moviedb.dataSources.UserDto;
import com.group12.moviedb.models.User;


@Service
public class CustomSecurityService {
    
    @Value("${jwt.secret}")
    private String jwtKey;

    UserRepository repo;

    public CustomSecurityService(UserRepository repository){
        this.repo = repository;
    }

    /**
     * Register new user or update existing one
     */
    public User signup(String username, String password){
        
        User user = new User(null, username, BCrypt.hashpw(password, BCrypt.gensalt(10)), password, password);
        repo.save(user);
        return user;
    }
    @RequestMapping(value = {"/login/userDto"}, method = RequestMethod.POST)
    @CrossOrigin(origins = {"*"})
    public UserDto login(@RequestBody UserDto login) {
        return login;
    }
    /**
     * Login user. Return JWT token or null if not found or wrong password.
     */
    public String login(String username, String password){
     
        User user = repo.findByUsername(username).orElse(null);

        if(user == null || !BCrypt.checkpw(password, user.getPassword())){
            return null;
        }

        Algorithm alg = Algorithm.HMAC256(jwtKey);
        return JWT.create().withSubject(user.getUsername()).sign(alg);
    }

    /**
     * Verify jwt token and return username if token is valid
     */
    public String validateJwt(String jwtToken){
        Algorithm alg = Algorithm.HMAC256(jwtKey);
        JWTVerifier verifier = JWT.require(alg).build();

        try {
            DecodedJWT jwt = verifier.verify(jwtToken);
            return jwt.getSubject();
        } catch (JWTVerificationException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
