package com.group12.moviedb.request;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.group12.moviedb.config.CustomSecurityService;

@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class UserRequest {


    CustomSecurityService securityService;

    public UserRequest(CustomSecurityService securityService){
        this.securityService = securityService;
    }


        
    @GetMapping("/user")
    public ResponseEntity<String> getUser(@RequestAttribute(name="username") String username){
        //We get here only if the MyTokenFilter gets through and the user is validated.
        return new ResponseEntity<String>("User data for " + username, HttpStatus.OK);
    }
}
