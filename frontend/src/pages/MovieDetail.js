import React, { useEffect, useState } from 'react';
import './MovieDetail.css';
import { Link, useParams } from 'react-router-dom';

function MovieDetail() {
  const { id } = useParams();
  const [movie, setMovie] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:8080/movie/${id}`)
      .then(response => response.json())
      .then(data => setMovie(data))
      .catch(error => console.error('Error:', error));
  }, [id]);

  return movie ? (
    <div className="movie-container">
      <h2><Link to={`/movie/${movie.id}`}>{movie.title}</Link></h2>
      <p>{movie.title}</p>
      {/* Display other group details here */}
    </div>
  ) : (
    <div>Loading...</div>
  );
}

export default MovieDetail;