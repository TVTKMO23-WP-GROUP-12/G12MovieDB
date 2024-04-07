package com.group12.moviedb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.Movie;
import com.group12.moviedb.models.MoviesToWatch;
import com.group12.moviedb.models.MoviesToWatchId;
import com.group12.moviedb.models.User;

@Repository
public interface MoviesToWatchRepository extends JpaRepository<MoviesToWatch, MoviesToWatchId> {
    MoviesToWatch findByUser(User user);
    MoviesToWatch findByMovie(Movie movie);
    MoviesToWatch findByUserAndMovie(User user, Movie movie);
    void deleteByUser(User user);
    void deleteByMovie(Movie movie);
    void deleteByUserAndMovie(User user, Movie movie);
}
