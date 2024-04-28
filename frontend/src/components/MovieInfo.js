import React, { useEffect } from 'react';
import './MovieInfo.css';
import noImageAvailable from '../media/noImageAvailable.png';
import { Link } from 'react-router-dom';

function MovieInfo({ movie }) {
    const releaseDate = movie.release_date.split('-').reverse().join('/');

    useEffect(() => {
        if (movie) {
          localStorage.setItem('movieTmdb', movie.id);
          localStorage.setItem('movieTitle', movie.title);
        }
      }, [movie]);

      const postMovie = () => {
        const movieData = {
          tmdbId: localStorage.getItem('movieTmdb'),
          title: localStorage.getItem('movieTitle'),
        };
    
        fetch('http://localhost:8080/public/movie', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(movieData),
        })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error('Error:', error));
      };

    return (
        <><div className="movie-container">
            <div className="movie-container-image">
                {movie && movie.poster_path ? (
                    <img className="image" src={`https://image.tmdb.org/t/p/w500/${movie.poster_path}`} alt={movie.title} />
                ) : (
                    <img src={noImageAvailable} alt="Kuvaa ei saatavilla" />
                )}
            </div>
            <div className="movie-container-info">
                <h2><Link to={`/public/movie/${movie.id}`}>{movie.title}</Link></h2>
                <p><b>Genre: </b>
                    {movie.genres && movie.genres.map((genre, index) => (
                        <span key={index}>{genre.name}{index < movie.genres.length - 1 ? ', ' : ''}</span>
                    ))}
                </p>
                <p><b>Julkaistu: </b>{releaseDate}</p>
                <div className="movie-container-synopsis">
                    <p><b>Synopsis: </b> {movie.overview}</p>
                </div>
                <div className="movie-container-rating">
                    <p><b>Arvosana: </b>{movie.vote_average} ({movie.vote_count} ääntä)</p>
                    <p></p>
                    <button onClick={postMovie}>Lisää tietokantaan</button>
                </div>
            </div>
        </div>
        </>
    )
}

export default MovieInfo;