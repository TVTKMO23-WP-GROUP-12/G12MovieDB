import React, { useEffect, useState } from 'react';
import './MovieDetail.css';
import { Link, useParams } from 'react-router-dom';
import MovieInfo from '../components/MovieInfo';
import MovieCredits from '../components/MovieCredits';
import MovieReviews from '../components/MovieReviews';
import useFetchMovie from '../hooks/useFetchMovie';
import usePostMovie from '../hooks/usePostMovie';
import useFetchMovieCredits from '../hooks/useFetchMovieCredits';
import useFetchMovieReviews from '../hooks/useFetchMovieReviews';

function MovieDetail() {
  const { id } = useParams();
  const movie = useFetchMovie(id);
  const credits = useFetchMovieCredits(id);
  const [selectedTab, setSelectedTab] = useState('Reviews');

  usePostMovie();

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