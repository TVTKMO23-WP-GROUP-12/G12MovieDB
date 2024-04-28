package com.group12.moviedb.config;

import java.time.LocalDateTime;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.group12.moviedb.repository.UserRepository;
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
    public User signup(String username, String password, String email) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setEmail(email);
        user.setLastLogin(LocalDateTime.now()); // Set the login time to the current time
        user.setUserDescription(""); 
    
        repo.save(user);
        
        return user;
    }

    /**
     * Login user. Return JWT token or null if not found or wrong password.
     */
    public String login(String username, String password){
     
        User user = repo.findByUsername(username);

        if(user == null || !BCrypt.checkpw(password, user.getPassword())){
            return null;
        }

        Algorithm alg = Algorithm.HMAC256(jwtKey);
        String userId = String.valueOf(user.getId(null)); // Assuming the user ID is stored as 'id' in the User entity
        return JWT.create().withSubject(user.getUsername()).withClaim("userId", userId).sign(alg);
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