import React, { useEffect, useState } from 'react';
import './MovieInfo.css';
import noImageAvailable from '../media/noImageAvailable.png';
import { Link } from 'react-router-dom';
import usePostMovie from '../hooks/usePostMovie';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faStar as solidStar } from '@fortawesome/free-solid-svg-icons';
import { faStar as regularStar} from '@fortawesome/free-regular-svg-icons';
import { faEye as solidEye } from '@fortawesome/free-solid-svg-icons';
import { faEye as regularEye} from '@fortawesome/free-regular-svg-icons';
import { faVideo as solidVideo } from '@fortawesome/free-solid-svg-icons';
import { faVideoSlash as regularVideo } from '@fortawesome/free-solid-svg-icons';


function MovieInfo({ movie }) {
    const releaseDate = movie.release_date.split('-').reverse().join('/');
    const movieId = localStorage.getItem('movieId');
    const userId = localStorage.getItem('userId');
    const [note, setNote] = useState('');
    const [isFavorite, setIsFavorite] = useState(false);
    const [isToWatch, setIsToWatch] = useState(false);
    const [isWatched, setIsWatched] = useState(false);

      const fetchLists = async () => {
        const favoritesResponse = await fetch(`http://localhost:8080/favorites/${userId}/${movieId}`);
        const toWatchResponse = await fetch(`http://localhost:8080/movies_to_watch/${userId}/${movieId}`);
        const watchedResponse = await fetch(`http://localhost:8080/movies_watched/${userId}/${movieId}`);
    
        const favoritesData = await favoritesResponse.json();
        const toWatchData = await toWatchResponse.json();
        const watchedData = await watchedResponse.json();
    
        setIsFavorite(favoritesData.length > 0);
        setIsToWatch(toWatchData.length > 0);
        setIsWatched(watchedData.length > 0);
    };
    
    const handleFavoriteClick = async () => {
      const wasFavorite = isFavorite;
      setIsFavorite(!wasFavorite);
  
      try {
          if (wasFavorite) {
              await removeFavoriteMovie();
          } else {
              await postMovie();
          }
          await fetchLists();
      } catch (error) {
          console.error(error);
          setIsFavorite(wasFavorite);
      }
    };
    
    const handleWatchedClick = async () => {
        if (isWatched) {
            await removeWatchedMovie();
        } else {
            await postWatched(note);
        }
        setNote('');
        fetchLists();
    };
    
    const handleToWatchClick = async () => {
        if (isToWatch) {
            await removeToWatchMovie();
        } else {
            await postToWatch(note);
        }
        setNote('');
        fetchLists();
    };

    const removeFavoriteMovie = async () => {
      const response = await fetch(`http://localhost:8080/favorites/${userId}/${movieId}`, {
            method: 'DELETE',
        });
        if (!response.ok) {
            throw new Error('Failed to remove movie from favorites');
        }
    };
    
    const removeWatchedMovie = async () => {
        const response = await fetch(`http://localhost:8080/movies_watched/${userId}/${movieId}`, {
            method: 'DELETE',
        });
        if (!response.ok) {
            throw new Error('Failed to remove movie from watched list');
        }
    };
    
    const removeToWatchMovie = async () => {
        const response = await fetch(`http://localhost:8080/movies_to_watch/${userId}/${movieId}`, {
            method: 'DELETE',
        });
        if (!response.ok) {
            throw new Error('Failed to remove movie from to watch list');
        }
    };

    useEffect(() => {
      fetchLists();
    }, [userId, movieId]);

    useEffect(() => {
        if (movie) {
          localStorage.setItem('movieTmdb', movie.id);
          localStorage.setItem('movieTitle', movie.title);
        }
      }, [movie]);

      const postMovie = () => {
        const movieData = {
          movieId: parseInt(movieId),
          userId: parseInt(userId),
          createdAt: new Date(),
          updatedAt: new Date()
        };
    
        fetch('http://localhost:8080/favorites', {
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

      const postWatched = (note) => {
        const movieData = {
          movieId: parseInt(movieId),
          userId: parseInt(userId),
          note: "note"
        };
      
        fetch('http://localhost:8080/movies_watched', {
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
      
      const postToWatch = (note) => {
        const movieData = {
          movieId: parseInt(movieId),
          userId: parseInt(userId),
          note: "note"
        };
      
        fetch('http://localhost:8080/movies_to_watch', {
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
                    <button className="FAbutton" onClick={handleFavoriteClick}>
                        <FontAwesomeIcon icon={isFavorite ? solidStar : regularStar} />
                    </button>
                    <button className="FAbutton" onClick={handleWatchedClick}>
                        <FontAwesomeIcon icon={isWatched ? solidEye : regularEye} />
                    </button>
                    <button className="FAbutton" onClick={handleToWatchClick}>
                        <FontAwesomeIcon icon={isToWatch ? solidVideo : regularVideo} />
                    </button>
                </div>
            </div>
        </div>
        </>
    )
}

export default MovieInfo;