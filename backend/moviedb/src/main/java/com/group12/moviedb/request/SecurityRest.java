package com.group12.moviedb.request;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group12.moviedb.config.CustomSecurityService;
import com.group12.moviedb.models.User;

@RestController
public class SecurityRest<LoginInfo> {
    @Autowired
    CustomSecurityService securityService;

    /**
     * Register user with request parameters
     */
     @CrossOrigin(origins = "http://localhost:3000") 
     @PostMapping("/signup")
     public ResponseEntity<String> signup(
         @RequestParam String username, 
         @RequestParam String password,
         @RequestParam String email)
     {
         User user = securityService.signup(username, password, email);
         return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
     }
    

    @CrossOrigin(origins = "http://localhost:3000") 
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
        @RequestParam String username, 
        @RequestParam String password)
    {
        String token = securityService.login(username, password);

         return createResponse(token);
    }
 
    private ResponseEntity<Map<String,String>> createResponse(String token){
        if(token == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
        Map<String,String> response =  new HashMap<>();
        response.put("token", token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
