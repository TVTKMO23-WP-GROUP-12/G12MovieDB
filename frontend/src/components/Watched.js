import { Link } from 'react-router-dom';
import useFetchWatched from '../hooks/useFetchWatched';

// On: 
// - users own page (User)

const Watched = ({ id }) => {
  const watchedMovie = useFetchWatched(id);

  return (
    <div className="user-tabs-content">
      <h2>On katsonut</h2>
      {watchedMovie.map((watchedMovie, index) => (
        <div key={index}>
          <p>
            <Link to={`/movie/${watchedMovie.movieId.id}`}>
              {watchedMovie.movieId.title}
            </Link>
          </p>
          <div className="note">
            <span className="note-heading">Muistiinpano:</span>
            <p>{watchedMovie.note}</p>
          </div>
          <hr></hr>
          </div>
      ))}
    </div>
  );
}

export default Watched;
