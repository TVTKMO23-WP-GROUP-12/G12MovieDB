import React from 'react';
import './MovieInfo.css';
import noImageAvailable from '../media/noImageAvailable.png';
import { Link } from 'react-router-dom';

function MovieInfo({ movie }) {
    const releaseDate = movie.release_date.split('-').reverse().join('/');

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
                </div>
            </div>
        </div>
        </>
    )
}

export default MovieInfo;