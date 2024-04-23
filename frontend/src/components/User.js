import React, { useState } from 'react';
import useFetchUser from '../hooks/useFetchUser';
import './User.css';

function User({ id, setUser: setUserProp }) {
  const [user, setUser] = useState(null);
  const [isEditing, setIsEditing] = useState(false);
  const [editedDescription, setEditedDescription] = useState('');
  useFetchUser(id, setUser);
  const joinedAt = user ? new Date(user.createdAt) : null;
  const formattedDate = joinedAt ? `${("0" + joinedAt.getDate()).slice(-2)}.${("0" + (joinedAt.getMonth() + 1)).slice(-2)}.${joinedAt.getFullYear()}` : '';

  // Function to update the user description
  const submitDescription = () => {
    fetch(`http://localhost:8080/users/${id}`, {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        userDescription: editedDescription,
      }),
    })
      .then(response => response.json())
      .then(data => {
        setUser(prevUser => ({ ...prevUser, userDescription: data.userDescription }));
        // Clear the editedDescription state
        setEditedDescription("");
      })
      .catch(error => console.error('Error:', error));
  };

  if (!user) {
    return <div>Loading...</div>;
  }

  return (
          <div className="userdetail-container">
          <div className="user-info">
            <div className="user-profilepicture">
              <img src={user.profilePicture} alt="Profile" />
            </div>
            <div className="user-details">
              <h1>{user.username}</h1>
              <p>Liittyi: {formattedDate}</p>
              <p>Kirjautui viimeksi: {user.lastLogin}</p>
            </div>
          </div>
          <div className="user-bio">
            <p className="use-bio-text">{user.userDescription}</p>
          </div>

          <div className="user-update-description">
            {isEditing ? (
              <>
                <input
                  type="text"
                  value={editedDescription}
                  onChange={e => setEditedDescription(e.target.value)} 
                />
                <button onClick={() => {
                  setIsEditing(false);
                  submitDescription();
                }}>Tallenna</button>
              </>
            ) : (
              <button onClick={() => {
                setIsEditing(true);
                setEditedDescription(user.userDescription);
              }}>Muokkaa</button>
            )}
          </div>
      </div>  
  );
}

export default User;