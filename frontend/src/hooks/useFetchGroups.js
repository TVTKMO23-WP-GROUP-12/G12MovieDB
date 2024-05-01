import { useState, useEffect } from 'react';

const useFetchGroups = (id) => {
  const [groups, setGroups] = useState([]);

  useEffect(() => {
    fetch(`http://localhost:8080/group/members/user/${id}`)
      .then(response => response.json())
      .then(data => setGroups(data))
      .catch(error => console.error('Error:', error));
  }, [id]);
  console.log(groups);
  return groups;
};

export default useFetchGroups;