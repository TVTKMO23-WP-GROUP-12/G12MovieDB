import React, { useState } from 'react';
import SearchHandler from '../components/SearchHandler.js'
import languages from '../components/Languages.js';
import noImageAvailable from '../media/noImageAvailable.png';
import './Search.css';
import { Link } from 'react-router-dom';

export default function Search() {
  const [searchTerm, setSearchTerm] = useState('');
  const [movies, setMovies] = useState([]);
  const [tvShows, setTvShows] = useState([]);
  const [isFilterActive, setIsFilterActive] = useState(false);
  const [movieGenre, setMovieGenre] = useState('');
  const [tvGenre, setTvGenre] = useState('');
  const [rating, setRating] = useState('');
  const [releaseYear, setReleaseYear] = useState({ min: '', max: '' });
  const [showMovies, setShowMovies] = useState(true);
  const [showTVShows, setShowTVShows] = useState(true);

  const formatDate = (dateString) => {
    const date = new Date(dateString);
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();
    return `${day}.${month}.${year}`;
  };

  const formatRating = (rating) => {
    if (rating === undefined) {
      // Handle the case where rating is undefined, e.g. by returning a default value
      return 'N/A';
    }
    return rating.toFixed(2);
  };

  const translateLanguage = (code) => {
    return languages[code] || code;
  };

    const handleSearch = async () => {
    await SearchHandler(searchTerm, isFilterActive, movieGenre, tvGenre, rating, releaseYear, setMovies, setTvShows);
  };

  const handleKeyPress = (event) => {
    if (event.key === 'Enter') {
      handleSearch();
    }
  };

  const handleCheckboxChange = (type) => {
    if (type === 'movies') {
      setShowMovies(!showMovies);
    } else if (type === 'tvShows') {
      setShowTVShows(!showTVShows);
    }
  };

  return (
    <div className="search-container">
      <div className="input-filter-container">

        <div className="input-container">
          <h2 className="title">Haku</h2>
          <input
            type="text"
            placeholder="Hakusana"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            onKeyPress={handleKeyPress}
          />
          <button className="search-button" onClick={handleSearch}>Hae</button>
        </div>
        <div className="check-filter-container">
          <div className="check-movie-tv-container">
            <div className="movie-tv-checkbox">
              <input
                type="checkbox"
                id="moviesCheckbox"
                checked={showMovies}
                onChange={() => handleCheckboxChange('movies')}
              />
              <label className="checkbox-label" htmlFor="moviesCheckbox">Näytä elokuvat</label>
            </div>
            <div className="movie-tv-checkbox">
              <input
                type="checkbox"
                id="tvShowsCheckbox"
                checked={showTVShows}
                onChange={() => handleCheckboxChange('tvShows')}
              />
              <label className="checkbox-label" htmlFor="tvShowsCheckbox">Näytä TV-sarjat</label>
            </div>
          </div>
          
          <div className="filter-container">
            <div className="filter-checkbox">
              <input
                type="checkbox"
                id="filterCheckbox"
                checked={isFilterActive}
                onChange={() => setIsFilterActive(!isFilterActive)}
              />
              <label className="checkbox-label" htmlFor="filterCheckbox">Käytä suodattimia</label>
            </div>
            {isFilterActive && (
              <>
                <div className="filter-section">
                  <label className="filter-label" htmlFor="genre">Genre:</label>
                  <select className="select-filter" id="movieGenre" value={movieGenre} onChange={(e) => setMovieGenre(e.target.value)}>
                    <option value="">Elokuvat</option>
                    <option value="28">Action</option>
                    <option value="12">Adventure</option>
                    <option value="16">Animation</option>
                    <option value="35">Comedy</option>
                    <option value="80">Crime</option>
                    <option value="99">Documentary</option>
                    <option value="18">Drama</option>
                    <option value="10751">Family</option>
                    <option value="14">Fantasy</option>
                    <option value="36">History</option>
                    <option value="27">Horror</option>
                    <option value="10402">Music</option>
                    <option value="9648">Mystery</option>
                    <option value="10749">Romance</option>
                    <option value="878">Science Fiction</option>
                    <option value="10770">TV Movie</option>
                    <option value="53">Thriller</option>
                    <option value="10752">War</option>
                    <option value="37">Western</option>
                  </select>
                  <select className="select-filter" id="tvGenre" value={tvGenre} onChange={(e) => setTvGenre(e.target.value)}>
                    <option value="">TV-sarjat</option>
                    <option value="10759">Action & Adventure</option>
                    <option value="16">Animation</option>
                    <option value="35">Comedy</option>
                    <option value="80">Crime</option>
                    <option value="99">Documentary</option>
                    <option value="18">Drama</option>
                    <option value="10751">Family</option>
                    <option value="10762">Kids</option>
                    <option value="9648">Mystery</option>
                    <option value="10763">News</option>
                    <option value="10764">Reality</option>
                    <option value="10765">Sci-Fi & Fantasy</option>
                    <option value="10766">Soap</option>
                    <option value="10767">Talk</option>
                    <option value="10768">War & Politics</option>
                    <option value="37">Western</option>
                  </select>
                </div>
                <div className="filter-section">
                  <label className="filter-label" htmlFor="releaseYear">Julkaisuaika:</label>
                  <select
                    className="select-filter"
                    id="minReleaseYear"
                    value={releaseYear.min}
                    onChange={(e) => setReleaseYear({ ...releaseYear, min: e.target.value })}
                  >
                    <option value="">Min</option>
                    {[...Array(16)].map((_, index) => {
                      const decade = 1880 + index * 10;
                      return <option key={decade} value={decade}>{decade}</option>;
                    })}
                  </select>
                  <select
                    className="select-filter"
                    id="maxReleaseYear"
                    value={releaseYear.max}
                    onChange={(e) => setReleaseYear({ ...releaseYear, max: e.target.value })}
                  >
                    <option value="">Max</option>
                    {[...Array(16)].map((_, index) => {
                      const decade = 1880 + index * 10;
                      return <option key={decade} value={decade}>{decade}</option>;
                    })}
                  </select>
                </div>

                <div className="filter-section">
                  <label className="filter-label" htmlFor="rating">Arvosana:</label>
                  <select
                    className="select-filter"
                    id="rating"
                    value={rating.min}
                    onChange={(e) => setRating({ ...rating, min: parseInt(e.target.value) })}
                  >
                    <option value="">Kaikki</option>
                    {[0, 1, 2, 3, 4, 5, 6, 7, 8, 9].map((value) => (
                      <option key={value} value={value}>{value}</option>
                    ))}
                  </select>
                  <select
                    className="select-filter"
                    id="rating"
                    value={rating.max}
                    onChange={(e) => setRating({ ...rating, max: parseInt(e.target.value) })}
                  >
                    <option value="">Kaikki</option>
                    {[0, 1, 2, 3, 4, 5, 6, 7, 8, 9].map((value) => (
                      <option key={value} value={value}>{value}</option>
                    ))}
                  </select>
                </div>
              </>
            )}

          </div>
        </div>
      </div>
      <div className="results-container">
        {showMovies && (
          <div className="movies-container">
            <h3>Elokuvat</h3>
            <ul>
              {movies.map(movie => (
                <li key={movie.id}>
                  <Link to={`/public/movie/${movie.id}`}>
                    <div className="movie-info">
                      <div className="poster">
                        {movie.poster_path ? (
                          <img className="image" src={`https://image.tmdb.org/t/p/w500/${movie.poster_path}`} alt={movie.title} />
                        ) : (
                            <img src={noImageAvailable} alt="Kuvaa ei saatavilla" />
                        )}
                      </div>
                      <div className="info">
                        <span>Nimi: {movie.title}</span>
                        <span>Kieli: {translateLanguage(movie.original_language)}</span>
                        {movie.release_date === "" ? <span>Julkaistu: Ei tiedossa</span> : <span>Julkaistu: {formatDate(movie.release_date)}</span>}
                        {movie.vote_count === 0 ? <span>Arvosana: Ei arvosteluja</span> : <span>Arvosana: {formatRating(movie.vote_average)}/10</span>}
                      </div>
                    </div>
                  </Link>
                </li>
              ))}
            </ul>
          </div>
        )}
        {showTVShows && (
          <div className="tv-shows-container">
            <h3>TV-sarjat</h3>
            <ul>
              {tvShows.map(tvShow => (
                <li key={tvShow.id}>
                  <Link to={`/tv-show/${tvShow.id}`}>
                    <div className="tv-show-info">
                      <div className="poster">
                        {tvShow.poster_path ? (
                          <img className='image' src={`https://image.tmdb.org/t/p/w500/${tvShow.poster_path}`} alt={tvShow.name} />
                        ) : (
                            <img src={noImageAvailable} alt="Kuvaa ei saatavilla" />
                        )}
                      </div>
                      <div className="info">
                        <span>Nimi: {tvShow.name}</span>
                        <span>Kieli: {translateLanguage(tvShow.original_language)}</span>
                        {tvShow.first_air_date === "" ? <span>Julkaistu: Ei tiedossa</span> : <span>Julkaistu: {formatDate(tvShow.first_air_date)}</span>}
                        {tvShow.vote_count === 0 ? <span>Arvosana: Ei arvosteluja</span> : <span>Arvosana: {formatRating(tvShow.vote_average)}/10</span>}
                      </div>
                    </div>
                  </Link>
                </li>
              ))}
            </ul>
          </div>
        )}
      </div>
    </div>
  );
}
