import { Link } from 'react-router-dom';
import useFetchToWatch from '../hooks/useFetchToWatch';

// On: 
// - users own page (User)

const ToWatch = ({ id }) => {
  const toWatchMovies = useFetchToWatch(id);

  return (
    <div className="user-tabs-content">
      <h2>Haluaa katsoa</h2>
      {toWatchMovies.map((toWatchMovies, toWatch) => (
        <div key={toWatch}>
          <p>
            <Link to={`/movie/${toWatchMovies.movieId.id}`}>
              {toWatchMovies.movieId.title}
            </Link>
          </p>
            <div className="note">
              <span className="note-heading">Muistiinpano:</span>
              <p>{toWatchMovies.note}</p>
            </div>
          <hr></hr>
        </div>
      ))}
    </div>
  );
}

export default ToWatch;
