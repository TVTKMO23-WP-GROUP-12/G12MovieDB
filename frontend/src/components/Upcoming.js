import React from 'react';
import useFetchUpcoming from '../hooks/useFetchUpcoming';
import './MovieSection.css';
import { Link } from 'react-router-dom';

const Upcoming = () => {
  const { movies, loading, error } = useFetchUpcoming();

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;

  return (
    <div className="movie-section">
      <div className="section-title">
        <h2>Uutuudet</h2>
      </div>
        <div className="movies-grid">
          {movies.map(movie => (
            <div key={movie.id} className="movie-card">
              <Link to={`/public/movie/${movie.id}`}>
                <img 
                  src={`https://image.tmdb.org/t/p/w500${movie.poster_path}`} 
                  alt={movie.title} 
                  className="movie-poster"
                />
              </Link>
              <div className="movie-info">
                <h3>{movie.title}</h3>
              </div>
            </div>
          ))}
        </div>
    </div>
  );
};

export default Upcoming;
