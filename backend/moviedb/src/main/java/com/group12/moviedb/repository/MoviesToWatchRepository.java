package com.group12.moviedb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.MoviesToWatch;
import com.group12.moviedb.models.MoviesToWatchId;

@Repository
public interface MoviesToWatchRepository extends JpaRepository<MoviesToWatch, MoviesToWatchId> {
    MoviesToWatch findByUserId(int userId);
    MoviesToWatch findByMovieId(int movieId);
    MoviesToWatch findByIds(int userId, int movieId);
    MoviesToWatch saveById(int userId);
    MoviesToWatch saveByIds(int userId, int movieId);
    MoviesToWatch updateByUserId(int userId);
    MoviesToWatch updateByMovieId(int movieId);
    MoviesToWatch updateByIds(int userId, int movieId);
    MoviesToWatch updateNoteByIds(int userId, int movieId, String note);
    MoviesToWatch deleteByIds(int userId, int movieId);
    MoviesToWatch deleteByUserId(int userId);
}