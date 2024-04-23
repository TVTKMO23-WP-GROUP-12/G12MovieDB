import React, { useState } from 'react';
import useShowtimes from '../hooks/useFetchShowtimes'; 
import './Showtimes.css';

const Showtimes = () => {
  const [selectedTheater, setSelectedTheater] = useState('All');
  const [selectedDate, setSelectedDate] = useState('');
  const [selectedGenre, setSelectedGenre] = useState('All');
  const theaters = [
    { id: 'All', name: 'Kaikki teatterit' },
    { id: '1012', name: 'Espoo' },
    { id: '1039', name: 'Espoo: OMENA' },
    { id: '1038', name: 'Espoo: SELLO' },
    { id: '1002', name: 'Helsinki' },
    { id: '1045', name: 'Helsinki: ITIS' },
    { id: '1031', name: 'Helsinki: KINOPALATSI' },
    { id: '1032', name: 'Helsinki: MAXIM' },
    { id: '1033', name: 'Helsinki: TENNISPALATSI' },
    { id: '1013', name: 'Vantaa: FLAMINGO' },
    { id: '1015', name: 'Jyväskylä: FANTASIA' },
    { id: '1016', name: 'Kuopio: SCALA' },
    { id: '1017', name: 'Lahti: KUVAPALATSI' },
    { id: '1041', name: 'Lappeenranta: STRAND' },
    { id: '1018', name: 'Oulu: PLAZA' },
    { id: '1019', name: 'Pori: PROMENADI' },
    { id: '1021', name: 'Tampere' },
    { id: '1034', name: 'Tampere: CINE ATLAS' },
    { id: '1035', name: 'Tampere: PLEVNA' },
    { id: '1047', name: 'Turku ja Raisio' },
    { id: '1022', name: 'Turku: KINOPALATSI' },
    { id: '1046', name: 'Raisio: LUXE MYLLY' },

  ];

  const { showtimes, genres, dates, } = useShowtimes(selectedTheater, selectedDate);
  const formatTime = (dateTime) => {
    return dateTime.split('T')[1].slice(0, 5);
  };
  
  return (
    <div className="showtimes-page-container">
      <h1>Finnkinon Näytösajat</h1>
      <div className="selects-container">
        <div className="select-container">
          <label htmlFor="theater-select"></label>
          <select
            id="theater-select"
            value={selectedTheater}
            onChange={(e) => setSelectedTheater(e.target.value)}
          >
            <option value="">Teatteri</option>
            {theaters.map((theater) => (
              <option key={theater.id} value={theater.id}>{theater.name}</option>
            ))}
          </select>
        </div>
        <div className="date-picker-container">
          <label htmlFor="date-select"></label>
          <select
            id="date-select"
            value={selectedDate}
            onChange={(e) => setSelectedDate(e.target.value)}
          >
            <option value="">Päivämäärä</option>
            {dates.map((date, index) => (
              <option key={index} value={date}>
                {new Date(date).toLocaleDateString('fi-FI')}
              </option>
            ))}
          </select>
        </div>
        <div className="genre-select-container">
          <label htmlFor="genre-select"></label>
          <select
            id="genre-select"
            value={selectedGenre}
            onChange={(e) => setSelectedGenre(e.target.value)}
          >
            <option value="All">Genret</option>
            {genres.map((genre, index) => (
              <option key={index} value={genre}>{genre}</option>
            ))}
          </select>
        </div>
      </div>
      {selectedTheater && selectedDate ? ( 
        <div className="showtimes-list">
          {showtimes
            .filter(showtime => selectedGenre === 'All' || showtime.Genres.includes(selectedGenre))
            .filter(showtime => new Date(showtime.StartTime) > new Date())
            .slice(0, 5)
            .map((showtime, index) => (
              <div key={index} className="showtime-item">
                <div>{formatTime(showtime.StartTime).toLocaleString('fi-FI')}</div>
                <div>{showtime.Title}</div>
                <div>{showtime.Theatre}</div>
              </div>
            ))}
        </div>
      ) : (
        <div className="prompt-select">
          Valitse teatteri ja päivämäärä nähdäksesi näytösajat
        </div>
      )}
    </div>
  );
};

export default Showtimes;