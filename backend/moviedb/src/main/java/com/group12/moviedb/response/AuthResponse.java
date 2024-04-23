package com.group12.moviedb.response;

public class AuthResponse {
    private String token;
    private String message;



    public AuthResponse (String token, String message) {
        super();
        this.token = token;
        this.message = message;
    }

        protected String getToken()  {
            return this.token;
        }

        protected String getMessage() {
            return this.message;
        }

        protected void setToken(String token) {
            this.token = token;
        }

        protected void setMessage(String message) {
            this.message = message;
        }
}
