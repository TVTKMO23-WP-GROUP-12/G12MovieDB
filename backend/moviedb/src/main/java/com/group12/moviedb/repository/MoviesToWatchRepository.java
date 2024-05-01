package com.group12.moviedb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.Movie;
import com.group12.moviedb.models.MoviesToWatch;
import com.group12.moviedb.models.MoviesToWatchId;
import com.group12.moviedb.models.User;

@Repository
public interface MoviesToWatchRepository extends JpaRepository<MoviesToWatch, MoviesToWatchId> {
    List<MoviesToWatch> findByUser(User user);
    List<MoviesToWatch> findByMovie(Movie movie);
    List<MoviesToWatch> findByUserAndMovie(User user, Movie movie);
    void deleteByUser(User user);
    void deleteByMovie(Movie movie);
    void deleteByUserAndMovie(User user, Movie movie);  
}
