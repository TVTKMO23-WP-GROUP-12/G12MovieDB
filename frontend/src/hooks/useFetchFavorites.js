import { useState, useEffect } from 'react';

const useFetchFavorites = (userId) => {
  const [favorites, setFavorites] = useState([]);

  useEffect(() => {
    fetch(`http://localhost:8080/favorites/${userId}`)
      .then(response => response.json())
      .then(data => {
        console.log(data);
        setFavorites(data);
      })
      .catch(error => console.error('Error:', error));
  }, [userId]);

  return favorites;
};

export default useFetchFavorites;