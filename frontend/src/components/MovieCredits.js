import React, { useState } from 'react';
import './MovieInfo.css';
import noImageAvailable from '../media/noImageAvailable.png';
import { Link } from 'react-router-dom';

function MovieCredits({ credits }) {

    const [currentPage, setCurrentPage] = useState(0);
    const actorsPerPage = 5;

    const handlePrevClick = () => {
        if (currentPage > 0) {
            setCurrentPage(currentPage - 1);
        }
    }

    const handleNextClick = () => {
        if (currentPage < Math.ceil(credits.cast.length / actorsPerPage) - 1) {
            setCurrentPage(currentPage + 1);
        }
    }

    return (
    <div className="movie-cast-wrapper">
        <div>
            <h3>Rooleissa:</h3>
        </div>
        <div className="movie-cast-container">
            {credits && credits.cast.slice(currentPage * actorsPerPage, (currentPage + 1) * actorsPerPage).map((actor, index) => (
                <div key={index} className="movie-cast-item">
                    {actor.profile_path ? (
                        <img src={`https://image.tmdb.org/t/p/w500/${actor.profile_path}`} alt={actor.name} />
                    ) : (
                        <img src={noImageAvailable} alt="Kuvaa ei saatavilla" />
                    )}
                    <p>{actor.name}</p>
                </div>
            ))}
        </div>
        <div className="movie-cast-pagination">
            <button className="button" onClick={handlePrevClick}>Edellinen</button>
            <button className="button" onClick={handleNextClick}>Seuraava</button>
        </div>
    </div>
    )
}

export default MovieCredits;