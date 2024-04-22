import React, { useEffect, useState } from 'react';
import './MovieDetail.css';
import { Link, useParams } from 'react-router-dom';
import MovieInfo from '../components/MovieInfo';
import MovieCredits from '../components/MovieCredits';
import useFetchMovie from '../hooks/useFetchMovie';
import useFetchMovieCredits from '../hooks/useFetchMovieCredits';
import useIsMobile from '../hooks/useIsMobile';
import Movie from './Movie';

function MovieDetail() {
  const { id } = useParams();
  const isMobile = useIsMobile();
  const movie = useFetchMovie(id);
  const credits = useFetchMovieCredits(id);

  return movie ? (
    <>
    <div>
      <MovieInfo movie={movie} />
    </div>
    <div>
      <MovieCredits credits={credits} />
    </div>
    </>
  ) : (
    <div>Loading...</div>
  );
}

export default MovieDetail;