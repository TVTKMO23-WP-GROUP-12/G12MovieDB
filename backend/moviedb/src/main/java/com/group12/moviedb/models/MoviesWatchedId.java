package com.group12.moviedb.models;

import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.util.Objects;


@AllArgsConstructor
public class MoviesWatchedId implements Serializable {
        private User user;
        private Movie movie;

        public MoviesWatchedId() {}

        public User getUser() {
            return user;
        }

        public Movie getMovie() {
            return movie;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public void setMovie(Movie movie) {
            this.movie = movie;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MoviesWatchedId)) return false;
            MoviesWatchedId that = (MoviesWatchedId) o;
            return Objects.equals(getUser(), that.getUser()) &&
                    Objects.equals(getMovie(), that.getMovie());
        }
    
        @Override
        public int hashCode() {
            return Objects.hash(getUser(), getMovie());
        }
    }