import { useState, useEffect } from 'react';

const useFetchGroups = (userId) => {
  const [groups, setGroups] = useState([]);

  useEffect(() => {
    fetch(`http://localhost:8080/group/members/user=${userId}`)
      .then(response => response.json())
      .then(data => setGroups(data))
      .catch(error => console.error('Error:', error));
  }, [userId]);

  return groups;
};

export default useFetchGroups;