import React, { useState } from 'react';
import { FaSearch } from 'react-icons/fa';

export const SearchBar = () => {
  const [input, setInput] = useState('');
  const [searchResults, setSearchResults] = useState([]);

  const fetchData = (value) => {
    fetch("http://localhost:8080/group")
      .then((response) => response.json())
      .then((json) => {
        const results = json.filter((group) => {
          return (
            value &&
            group &&
            group.groupname &&
            group.groupname.toLowerCase().includes(value.toLowerCase())
          );
        });
        setSearchResults(results);
      })
      .catch((error) => console.error('Error fetching data:', error));
  };

  const handleChange = (value) => {
    setInput(value);
    fetchData(value);
  };

  return (
    <div className="wrapper">
      <FaSearch id="search-icon" />
      <input
        placeholder="Hae..."
        value={input}
        onChange={(e) => handleChange(e.target.value)}
      />
      <div>SearchBar</div>
      <div>SearchResults</div>
      {searchResults.map((group) => (
        <div key={group.id}>{group.groupname}</div>
      ))}
    </div>
  );
};

export default SearchBar;