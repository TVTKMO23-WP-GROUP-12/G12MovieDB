package com.group12.moviedb.rest;

public class AuthResponse {
    private String token;
    private String message;

    public AuthResponse() {

    }

    public AuthResponse (String token, String message) {
        super();
        this.token = token;
        this.message = message;
        
    }
}
