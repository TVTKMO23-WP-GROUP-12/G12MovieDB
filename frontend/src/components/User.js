import React, { useState } from 'react';
import useFetchUser from '../hooks/useFetchUser';
import './User.css';
import DeleteUser from '../components/DeleteUser';
import ShareUser from '../components/ShareUser';


function User({ user, profilePicture }) {
  const userId = localStorage.getItem('userId');
  const [isEditing, setIsEditing] = useState(false);
  const [editedDescription, setEditedDescription] = useState('');
  const [userData, setUserData] = useState ('');

  const joinedAt = user ? new Date(user.createdAt) : null;
  const formattedDate = joinedAt ? `${("0" + joinedAt.getDate()).slice(-2)}.${("0" + (joinedAt.getMonth() + 1)).slice(-2)}.${joinedAt.getFullYear()}` : '';



  // Update the user description
  const submitDescription = () => {
    fetch(`http://localhost:8080/users/${userId}`, {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        description: editedDescription,
      }),
    })
    .then(response => response.json())
    .then(data => {
      // Update user state
      setUserData(prevUser => ({ ...prevUser, description: data.description })); // and this line
      setEditedDescription("");
    })
    .catch(error => console.error('Error:', error));
  };

    // Handle adding a new group
    const handleAddGroup = () => {
      window.location.href = '/add-new-group';
    };
  
  return (
    <div className="userdetail-container">
      <div className="user-info">
        <div className="user-profilepicture">
          {/* Render profile picture with the fetched URL */}
          <img src={profilePicture} alt="Profile" />
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
            console.log(user);
            setEditedDescription(user.userDescription);
          }}>Muokkaa</button>
        )}
        <ShareUser userId={userId} />
        <DeleteUser userId={userId}/>
        <button onClick={handleAddGroup}>Lisää uusi ryhmä</button>
      </div>
   
    </div>
  );
}

export default User;