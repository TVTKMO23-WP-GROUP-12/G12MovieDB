import React, { useEffect, useState } from 'react';
import './MovieDetail.css';
import { Link, useParams } from 'react-router-dom';
import MovieInfo from '../components/MovieInfo';
import MovieCredits from '../components/MovieCredits';
import MovieReviews from '../components/MovieReviews';
import useFetchMovie from '../hooks/useFetchMovie';
import useFetchMovieCredits from '../hooks/useFetchMovieCredits';

function MovieDetail() {
  const { id } = useParams();
  localStorage.setItem('movieTmdb', id);
  const movie = useFetchMovie(id);
  const credits = useFetchMovieCredits(id);
  const [selectedTab, setSelectedTab] = useState('Reviews');

  return movie ? (
    <>
    <div>
      <MovieInfo movie={movie} />
    </div>
    <div className="movie-tabs-container">
      <div className="movie-tabs">
        <Link 
          className={selectedTab === 'Cast' ? 'active' : ''}
          onClick={() => setSelectedTab('Cast') }>
          <p>Rooleissa</p>
        </Link>
        <Link 
          className={selectedTab === 'Reviews' ? 'active' : ''}
          onClick={() => setSelectedTab('Reviews') }>
          <p>Arvostelut</p>
        </Link>
      </div>
    </div>
    <div className="movie-content-container">
    {selectedTab === 'Cast' && (
      <MovieCredits credits={credits} />
    )}
    {selectedTab === 'Reviews' && (
      <MovieReviews movieId={id} />
    )}
    </div>
    </>
  ) : (
    <div>Loading...</div>
  );
}

export default MovieDetail;