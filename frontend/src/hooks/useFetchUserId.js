import { useState, useEffect } from 'react';

function useFetchUserId(username) {
  const [userId, setUserId] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    if (!username) return;

    fetch(`http://localhost:8080/users/username/${username}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(userData => {
        console.log(userData);
        setUserId(userData.id);
        localStorage.setItem('userId', userData.id); // Save userId to localStorage
      })
      .catch(fetchError => {
        console.error('There has been a problem with your fetch operation:', fetchError);
        setError(fetchError);
      });
  }, [username]); // Dependency array includes username

  return { userId, error };
}

export default useFetchUserId;