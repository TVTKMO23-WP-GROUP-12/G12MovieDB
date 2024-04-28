
import React from 'react';
import useFetchPopular from '../hooks/useFetchPopular';
import './MovieSection.css';

const Popular = () => {
  const { movies, loading, error } = useFetchPopular();

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;

  return (
    <div className="movie-section">
      <div className="movies-grid">
        {movies.map(movie => (
          <div key={movie.id} className="movie-card">
            <img 
              src={`https://image.tmdb.org/t/p/w500${movie.poster_path}`} 
              alt={movie.title} 
              className="movie-poster"
            />
            <div className="movie-info">
              <h3>{movie.title}</h3>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Popular;