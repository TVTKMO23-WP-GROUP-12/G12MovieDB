package com.group12.moviedb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.Movie;
import com.group12.moviedb.models.MoviesToWatch;
import com.group12.moviedb.models.MoviesToWatchId;
import com.group12.moviedb.models.User;

import java.util.List; // Import List
import java.util.Optional;

@Repository
public interface MoviesToWatchRepository extends JpaRepository<MoviesToWatch, MoviesToWatchId> {
    List<MoviesToWatch> findByUser(Integer userId); // Modify return type to List<MoviesToWatch>
    MoviesToWatch findByMovie(Integer movieId);
    List<MoviesToWatch> findByUserAndMovie(User user, Optional<Movie> movie);
    void deleteByUser(User user);
    void deleteByMovie(Movie movie);
    void deleteByUserAndMovie(User user, Movie movie);
    void deleteByUserAndMovie(User user, Optional<Movie> movie);
    List<MoviesToWatch> findByMovie(Movie movie);
    List<MoviesToWatch> findByUser(User user);
 
}