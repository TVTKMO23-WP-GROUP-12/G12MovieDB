package com.group12.moviedb.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.group12.moviedb.TMDBAPI.movies.MovieCreditsService;
import com.group12.moviedb.TMDBAPI.movies.MovieDetailsService;
import com.group12.moviedb.models.Movie;
import com.group12.moviedb.repository.MovieRepository;

public class MovieControllerTest {
  
    @InjectMocks
    private MovieController MovieController;

    @Mock
    private MovieDetailsService movieDetailsService;
    @Mock
    private MovieCreditsService movieCreditsService;
    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    public void setUp() {
    MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test end.");
    }


    @Test
    void testShouldSuccessfullyCreateOneMovie() {
        
        // Given
        Movie movie = new Movie(
            "something",
            1234 
            );

        Movie createdMovie = new Movie(
            "something",
            1234 
            );
        
            createdMovie.setId(null);
          
        when(movieRepository.save(movie)).thenReturn(createdMovie);

        // When
        Movie result = MovieController.createOneMovie(movie);

        // Then
        verify(movieRepository, times(1)).save(movie);

        // Verify that the returned user object is not null
        assertNotNull(result);

    }
   

    @Test
    void testShouldSuccessfullyFindAllMovies() {
          // Given
          Movie movie1 = new Movie(
            "something1",
            1234 
        );
        Movie movie2 = new Movie(
            "something2",
            1235 
        );
         Movie movie3 = new Movie(
             "something3",
            1236 
        );
        
          List<Movie> expectedMovies = Arrays.asList(movie1, movie2, movie3);
  
          when(movieRepository.findAll()).thenReturn(expectedMovies);
  
          // When
          List<Movie> actualMovies = movieRepository.findAll();
  
          // Then
          assertEquals(expectedMovies.size(), actualMovies.size());
          for (int i = 0; i < expectedMovies.size(); i++) {
              assertEquals(expectedMovies.get(i).getId(), actualMovies.get(i).getId());
              assertEquals(expectedMovies.get(i).getTitle(), actualMovies.get(i).getTitle());
              assertEquals(expectedMovies.get(i).getTmdbId(), actualMovies.get(i).getTmdbId());     
      }

    }

    @Test
    void testShouldSuccessfullyFindOneMovie() {
        // Given
        Integer movieId = 123;
        Movie movie = new Movie();
        movie.setId(movieId);
        Optional<Movie> optionalMovie = Optional.of(movie);
      
        when(movieRepository.findById(movieId)).thenReturn(optionalMovie);
      
        // When
        Optional<Movie> retrievedMovieOptional = movieRepository.findById(movieId);
      
        // Then
        assertTrue(retrievedMovieOptional.isPresent());
        assertEquals(movieId, retrievedMovieOptional.get().getId());
    }

    @Test
    void testShouldSuccessfullyFindOneMovieByTmdbId() {
        Integer tmdbId = 123;
        Movie movie = new Movie();
        movie.setId(tmdbId);
        Optional<Movie> optionalMovie = Optional.of(movie);
      
        when(movieRepository.findById(tmdbId)).thenReturn(optionalMovie);
      
        // When
        Optional<Movie> retrievedMovieOptional = movieRepository.findById(tmdbId);
      
        // Then
        assertTrue(retrievedMovieOptional.isPresent());
        assertEquals(tmdbId, retrievedMovieOptional.get().getId());
    }

    @Test
    void testShouldSuccessfullyGetMovieCredits() {
    // Given
    String tmdbId = "123";
    String credits = "credits";

    // Mock the movieCreditsService to return the expected credits when called with tmdbId
    when(movieCreditsService.getMovieCredits(tmdbId)).thenReturn(credits);

    // When
    String retrievedCredits = movieCreditsService.getMovieCredits(tmdbId);

    // Then
    assertEquals(credits, retrievedCredits);
}

    @Test
    void testShouldSuccessfullyGetMovieDetails() {
        // Given
        Integer tmdbId = 123;
        String details = "details";
    
        // Mock the movieDetailsService to return the expected details when called with tmdbId
        when(movieDetailsService.getMovieDetails(tmdbId)).thenReturn(details);
    
        // When
        String retrievedDetails = movieDetailsService.getMovieDetails(tmdbId);
    
        // Then
        assertEquals(details, retrievedDetails);
    }

    @Test
    void testShouldSuccessfullyUpdateOneMovie() {
                // Given
                Integer movieId = 123;
                Movie movieToUpdate = new Movie(
                    "UpdatedSomething1",
                    1234 
                );

                when(movieRepository.findById(movieId)).thenReturn(Optional.of(movieToUpdate));
                when(movieRepository.save(movieToUpdate)).thenReturn(movieToUpdate);
            
                // When
                Movie updatedMovie = movieRepository.save(movieToUpdate);
        
                // Then
                assertNotNull(updatedMovie);
                assertEquals("UpdatedSomething1", updatedMovie.getTitle());
        }
        

    @Test
    void testShouldSuccessfullyDeleteOneMovie() {

                // Given
                Integer movieId = 123;

                // When
                MovieController.deleteOneMovie(movieId);
            
                // Then
                // Verify that MovieRepository.deleteById was called with the correct movie ID
                verify(movieRepository).deleteById(movieId);
            }

}

