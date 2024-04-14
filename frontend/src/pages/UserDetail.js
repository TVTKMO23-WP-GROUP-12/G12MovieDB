import React, { useEffect, useState } from 'react';
import './UserDetail.css';
import { Link, useParams } from 'react-router-dom';
import defaultProfilePicture from '../media/defaultProfilePicture.png';

function UserDetail() {
  const { id } = useParams();
  const [user, setUser] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:8080/users/${id}`)
      .then(response => response.json())
      .then(data => {
        setUser({ ...data, profilePicture: data.profilePicture || defaultProfilePicture });
        return Promise.all(data.favorites.map(favorite => 
          fetch(`http://localhost:8080/movie/${favorite.movieId}`)
            .then(response => response.json())
        ));
      })
      .then(movieData => {
        setUser(user => ({
          ...user,
          favorites: user.favorites.map((favorite, index) => ({
            ...favorite,
            title: movieData[index].title
          }))
        }));
      })
      .catch(error => console.error('Error:', error));
  }, [id]);

  return user ? (
    <div className="userdetail-container">
    <div className="user-top">
        <div className="user-profilepicture">
        <img src={user.profilePicture} alt="Profile" />
        </div>
        <div className="user-details">
        <h1>{user.username}</h1>
        <p>Joined: {user.createdAt}</p>
        <p>Last Login: {user.lastLogin}</p>
        </div>
    </div>
    <div className="user-bio">
        <h3>Bio:</h3><p>{user.userDescription}</p>
    </div>
    <div className="user-groups">
        <h3>Groups:</h3>
        <ul>
        {user.groups.map(group => (
            <p key={group.id}>
                <Link to={`/group/${group.id}`}>{group.groupName}</Link>
            </p>
        ))}
        </ul>
    </div>
    <div className="user-favorites">
    <h3>Favorite movies:</h3>
    <ul>
        {user.favorites.map(favorite => (
        <p key={favorite.userId}>
            <Link to={`/movie/${favorite.movieId}`}>{favorite.title}</Link>
        </p>
        ))}
    </ul>
    </div>
    </div>
  ) : (
    <div>Loading...</div>
  );
}

export default UserDetail;