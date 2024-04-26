import React, { useState, useEffect } from 'react';

function UserReview({ userId, token }) {
  const [review, setReview] = useState('');
  const [isEditing, setIsEditing] = useState(false);

  useEffect(() => {
    fetch(`http://localhost:8080/review/user=${userId}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
      .then(response => response.json())
      .then(data => {
        setReview(data);
      })
      .catch(error => console.error('Error:', error));
  }, [userId, token]);

  const handleEdit = () => {
    setIsEditing(true);
  };

  const handleSave = () => {
    fetch(`http://localhost:8080/review/user=${userId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`
      },
      body: JSON.stringify({ review })
    })
      .then(response => response.json())
      .then(data => {
        setReview(data);
        setIsEditing(false);
      })
      .catch(error => console.error('Error:', error));
  };

  return (
    <div>
      {isEditing ? (
        <textarea value={review} onChange={e => setReview(e.target.value)} />
      ) : (
        <p>{review}</p>
      )}
      {isEditing ? (
        <button onClick={handleSave}>Save</button>
      ) : (
        <button onClick={handleEdit}>Edit</button>
      )}
    </div>
  );
}

export default UserReview;