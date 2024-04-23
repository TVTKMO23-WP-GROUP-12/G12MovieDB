import React, { useState, useEffect } from 'react';
import './ShowtimesMenu.css';

export default function ShowtimesMenu() {
  const [showtimes, setShowtimes] = useState([]);
  const [selectedTheater, setSelectedTheater] = useState('All');
  const theaters = [
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
    { id: '1046', name: 'Raisio: LUXE MYLLY' }
  ];

  useEffect(() => {
    const fetchShowtimes = () => {
      const url = selectedTheater === 'All'
        ? 'https://www.finnkino.fi/xml/Schedule/'
        : `https://www.finnkino.fi/xml/Schedule/?area=${selectedTheater}`;

      fetch(url)
        .then(response => response.text())
        .then(xmlData => {
          const parser = new DOMParser();
          const xmlDoc = parser.parseFromString(xmlData, 'text/xml');
          const shows = xmlDoc.querySelectorAll('Show');
          const mappedShows = Array.from(shows).map(show => ({
            StartTime: show.querySelector('dttmShowStart').textContent.replace('T', ' ').slice(0, -3),
            Title: show.querySelector('Title').textContent,
            Theatre: show.querySelector('Theatre').textContent
          }));
          const currentTime = new Date();
          const nextShows = mappedShows.filter(show => new Date(show.StartTime) > currentTime).slice(0, 5);
          setShowtimes(nextShows);
        })
        .catch(error => console.error('Error fetching XML data:', error));
    };

    fetchShowtimes();
    const interval = setInterval(fetchShowtimes, 60000);
    return () => clearInterval(interval);
  }, [selectedTheater]);

  const handleTheaterChange = event => {
    setSelectedTheater(event.target.value);
  };

  return (
    <div className="showtimes-menu-container">
      <div className="showtimes-box">
        <h2>Finnkino Showtimes</h2>
        <div className="theater-select">
          <label htmlFor="theater"></label>
          <select id="theater" value={selectedTheater} onChange={handleTheaterChange}>
            <option value="All">Kaikki teatterit</option>
            {theaters.map(theater => (
              <option key={theater.id} value={theater.id}>{theater.name}</option>
            ))}
          </select>
        </div>
        <div className="showtimes-list">
          {showtimes.length > 0 ? showtimes.map((showtime, index) => (
            <div key={index} className="showtime-item">
              <div className="datetime">
                <p className="date">{showtime.StartTime.split(' ')[0]}</p>
                <p className="time">{showtime.StartTime.split(' ')[1]}</p>
              </div>
              <p className="movie-title">{showtime.Title}</p>
            </div>
          )) : <p>Ei näytösaikoja saatavilla</p>}
        </div>
      </div>
    </div>
  );
}