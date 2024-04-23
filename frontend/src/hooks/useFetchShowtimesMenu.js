import { useState, useEffect } from 'react';

const useFetchShowtimesMenu = (selectedTheater) => {
  const [showtimes, setShowtimes] = useState([]);
  
  useEffect(() => {
    const fetchShowtimes = async () => {
      try {
        const url = selectedTheater === 'All'
          ? 'https://www.finnkino.fi/xml/Schedule/'
          : `https://www.finnkino.fi/xml/Schedule/?area=${selectedTheater}`;

        const response = await fetch(url);
        const xmlData = await response.text();
        const parser = new DOMParser();
        const xmlDoc = parser.parseFromString(xmlData, 'text/xml');
        const shows = xmlDoc.querySelectorAll('Show');
        const showsArray = Array.from(shows).map(show => ({
          StartTime: show.querySelector('dttmShowStart').textContent.replace('T', ' ').slice(0, -3),
          Title: show.querySelector('Title').textContent,
          Theatre: show.querySelector('Theatre').textContent
        }));

        // Filter and set the state only for the next 5 shows based on the current time
        const currentTime = new Date();
        setShowtimes(showsArray.filter(show => new Date(show.StartTime) > currentTime).slice(0, 5));
      } catch (error) {
        console.error('Error fetching showtimes:', error);
      }
    };

    fetchShowtimes();
  }, [selectedTheater]); // Dependency array ensures this effect runs only when selectedTheater changes

  return showtimes;
};

export default useFetchShowtimesMenu;