import React, { useState, useEffect } from 'react';
import './ShowtimesMenu.css';

export default function ShowtimesMenu() {
  const [showtimes, setShowtimes] = useState([]);
  
  const [selectedTheater, setSelectedTheater] = useState('All');

  // Fetch showtimes based on selected theater
  useEffect(() => {
    const fetchFinnkinoShowtimes = () => {
      const url = selectedTheater === 'All'
        ? 'https://www.finnkino.fi/xml/Schedule/'
        : `https://www.finnkino.fi/xml/Schedule/?area=${selectedTheater}`;
      // Fetch data from url
      fetch(url)
        .then(response => response.text())
        .then(xmlData => {
          //Parse xml data 
          const parser = new DOMParser();
          const xmlDoc = parser.parseFromString(xmlData, 'text/xml');
          //This is where we extract relevant information from xml
          const shows = xmlDoc.querySelectorAll('Show');
          const showsArray = Array.from(shows).map(show => ({
            StartTime: show.querySelector('dttmShowStart').textContent.replace('T', ' ').slice(0, -3), 
            Title: show.querySelector('Title').textContent,
            Theatre: show.querySelector('Theatre').textContent
          }));

          // Update current time so you can see next 5 shows
          const currentTime = new Date();
          const nextShows = showsArray.filter(show => new Date(show.StartTime) > currentTime).slice(0, 10); 
          setShowtimes(nextShows);
        })
        .catch(error => {
          console.error('Virhe XML-datan hakemisessa:', error);
        });
    };
    // Fetch showtimes initially and then set up an interval to fetch them every 60 seconds
    fetchFinnkinoShowtimes();
    const interval = setInterval(fetchFinnkinoShowtimes, 60000);

    return () => clearInterval(interval);
  }, [selectedTheater]);

  // Fetch theater list
  useEffect(() => {
    fetch('https://www.finnkino.fi/xml/ScheduleDates/')
      .then(response => response.text())
      .then(xmlData => {
        const parser = new DOMParser();
        const xmlDoc = parser.parseFromString(xmlData, 'text/xml');
        const theaters = xmlDoc.querySelectorAll('TheatreArea');
        const theaterList = Array.from(theaters).map(theater => ({
          id: theater.querySelector('ID').textContent,
          name: theater.querySelector('Name').textContent
        }));
        console.log(theaterList); // All theaters
      })
      .catch(error => {
        console.error('Virhe XML-datan hakemisessa:', error);
      });
  }, []);
  // Event handler for changing the selected theater
  const handleTheaterChange = event => {
    setSelectedTheater(event.target.value);
  };
  
  // Render component, I searched and placed each theater based on their ID
  // Can maybe remove "Kaikki Teatterit" and "Valitse alue/teatteri" options?
  return (
    <div className="showtimes-container">
      <div className="showtimes-box">
        <h2>Finnkino Showtimes</h2>
        <div className="theater-select">
          <label htmlFor="theater">Valitse teatteri:</label>
          <select id="theater" value={selectedTheater} onChange={handleTheaterChange}>
            <option value="All">Kaikki teatterit</option>
            <option value="1029">Valitse alue/teatteri</option>
            <option value="1014">Pääkaupunkiseutu</option>
            <option value="1012">Espoo</option>
            <option value="1039">Espoo: OMENA</option>
            <option value="1038">Espoo: SELLO</option>
            <option value="1002">Helsinki</option>
            <option value="1045">Helsinki: ITIS</option>
            <option value="1031">Helsinki: KINOPALATSI</option>
            <option value="1032">Helsinki: MAXIM</option>
            <option value="1033">Helsinki: TENNISPALATSI</option>
            <option value="1013">Vantaa: FLAMINGO</option>
            <option value="1015">Jyväskylä: FANTASIA</option>
            <option value="1016">Kuopio: SCALA</option>
            <option value="1017">Lahti: KUVAPALATSI</option>
            <option value="1041">Lappeenranta: STRAND</option>
            <option value="1018">Oulu: PLAZA</option>
            <option value="1019">Pori: PROMENADI</option>
            <option value="1021">Tampere</option>
            <option value="1034">Tampere: CINE ATLAS</option>
            <option value="1035">Tampere: PLEVNA</option>
            <option value="1047">Turku ja Raisio</option>
            <option value="1022">Turku: KINOPALATSI</option>
            <option value="1046">Raisio: LUXE MYLLY</option>
            </select>
            </div>
            <div className="showtimes-list">
            {Array.isArray(showtimes) && showtimes.length > 0 ? (
            showtimes.map((showtime, index) => (
            <div key={index} className="showtime-item">
              <div className="showtime-info">
                <div className="datetime">
                  <p className="date">{showtime.StartTime.split(' ')[0]}</p>
                  <p className="time">{showtime.StartTime.split(' ')[1]}</p>
                </div>
                <p className="movie-title">{showtime.Title}</p>
              </div>
            </div>
          ))
        ) : (
          <p>Ei näytösaikoja saatavilla</p>
        )}
      </div>
    </div>
  </div>
  );
}