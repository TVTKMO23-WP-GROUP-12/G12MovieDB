package com.group12.moviedb.response;

public class AuthResponse {
    private String token;
    private String message;
    private String userId;



    public AuthResponse (String token, String message, String userId) {
        super();
        this.token = token;
        this.message = message;
        this.userId = userId;
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

        protected String getUserId() {
            return this.userId;
        }

        protected void setUserId(String userId) {
            this.userId = userId;
        }
}
