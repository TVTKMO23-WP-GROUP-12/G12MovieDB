package com.group12.moviedb.exceptions;

public class NoMoviesWatchedException extends RuntimeException {

    public NoMoviesWatchedException() {
        super();
    }

    public NoMoviesWatchedException(String message) {
        super(message);
    }

    public NoMoviesWatchedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMoviesWatchedException(Throwable cause) {
        super(cause);
    }
}