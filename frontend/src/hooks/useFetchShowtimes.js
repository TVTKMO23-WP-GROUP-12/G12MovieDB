import { useState, useEffect } from 'react';

const formatDate = (date) => {
  const d = new Date(date);
  const day = (`0${d.getDate()}`).slice(-2); // Päivä kahdella numerolla
  const month = (`0${d.getMonth() + 1}`).slice(-2); // Kuukausi kahdella numerolla
  const year = d.getFullYear(); // Vuosi neljällä numerolla
  return `${day}.${month}.${year}`;
};

const useShowtimes = (selectedTheater, selectedDate) => {
  const [showtimes, setShowtimes] = useState([]);
  const [genres, setGenres] = useState([]);
  const [theaters, setTheaters] = useState([]);
  const [dates, setDates] = useState([]);  
  
  useEffect(() => {
    fetch('https://www.finnkino.fi/xml/ScheduleDates/')
      .then(response => response.text())
      .then(xmlData => {
        const parser = new DOMParser();
        const xmlDoc = parser.parseFromString(xmlData, 'text/xml');
        const dateElements = xmlDoc.querySelectorAll('dateTime');
        const dateList = Array.from(dateElements).map(date => date.textContent);
        setDates(dateList);
      })
      .catch(error => console.error('Virhe päivämäärätiedon hakemisessa:', error));
  }, []);

  useEffect(() => {
    const fetchGenres = async () => {
      try {
        const response = await fetch('https://www.finnkino.fi/xml/Events/');
        const xmlData = await response.text();
        const parser = new DOMParser();
        const xmlDoc = parser.parseFromString(xmlData, 'text/xml');
        const genreElements = xmlDoc.querySelectorAll('Event Genres');
        const genreSet = new Set();
        genreElements.forEach(genreElement => {
          genreElement.textContent.split(', ').forEach(genre => genreSet.add(genre.trim()));
        });
        setGenres([...genreSet]);
      } catch (error) {
        console.error('Virhe genrejen hakemisessa:', error);
        setGenres([]);
      }
    };
    fetchGenres();
  }, []);

  useEffect(() => {
    const fetchTheaters = async () => {
      try {
        const response = await fetch('https://www.finnkino.fi/xml/TheatreAreas/');
        const xmlData = await response.text();
        const parser = new DOMParser();
        const xmlDoc = parser.parseFromString(xmlData, 'text/xml');
        const theatreElements = xmlDoc.querySelectorAll('TheatreArea');
        const theatresArray = Array.from(theatreElements).map(theatre => ({
          id: theatre.querySelector('ID').textContent,
          name: theatre.querySelector('Name').textContent,
        }));
        setTheaters(theatresArray);
      } catch (error) {
        console.error('Virhe teattereiden hakemisessa:', error);
        setTheaters([]);
      }
    };
    fetchTheaters();
  }, []);

  useEffect(() => {
    if (selectedTheater && selectedTheater !== 'All' && selectedDate) {
      const fetchFinnkinoShowtimes = async () => {
        try {
          const formattedDate = formatDate(selectedDate);
          const params = new URLSearchParams({
            area: selectedTheater,
            dt: formattedDate
          });
          const url = `https://www.finnkino.fi/xml/Schedule/?${params.toString()}`;
          const response = await fetch(url);
          const xmlData = await response.text();
          const parser = new DOMParser();
          const xmlDoc = parser.parseFromString(xmlData, 'text/xml');
          const shows = xmlDoc.querySelectorAll('Show');
          const showsArray = Array.from(shows).map(show => ({
            StartTime: show.querySelector('dttmShowStart').textContent,
            Title: show.querySelector('Title').textContent,
            Theatre: show.querySelector('Theatre').textContent,
            Genres: show.querySelector('Genres')?.textContent.split(', ') || [],
          }));
          setShowtimes(showsArray);
        } catch (error) {
          console.error('Virhe näytösajat XML-datan hakemisessa:', error);
          setShowtimes([]);
        }
      };
      fetchFinnkinoShowtimes();
    } else {
      setShowtimes([]);
    }
  }, [selectedTheater, selectedDate]);

  return { showtimes, genres, theaters, dates };
};

export default useShowtimes;