import React, { useEffect, useState } from 'react';
import './UserDetail.css';
import { Link, useParams } from 'react-router-dom';
import defaultProfilePicture from '../media/defaultProfilePicture.png';

function UserDetail() {
  const { id } = useParams();
  const [user, setUser] = useState(null);
  const [selectedTab, setSelectedTab] = useState('News');
  const [selectedTabLeft, setSelectedTabLeft] = useState('News');
  const [selectedTabRight, setSelectedTabRight] = useState('Reviews');
  const [reviews, setReviews] = useState([]);
  const [watchedMovies, setWatchedMovies] = useState([]);
  const [toWatchMovies, setToWatchMovies] = useState([]);
  const [isMobile, setIsMobile] = useState(window.innerWidth <= 600);
  const [editedDescription, setEditedDescription] = useState("");
  const [isEditing, setIsEditing] = useState(false);
  const joinedAt = user ? new Date(user.createdAt) : null;
  const formattedDate = joinedAt ? `${("0" + joinedAt.getDate()).slice(-2)}.${("0" + (joinedAt.getMonth() + 1)).slice(-2)}.${joinedAt.getFullYear()}` : '';

  // Listener for smartphone window size
  useEffect(() => {
    const handleResize = () => {
      setIsMobile(window.innerWidth <= 600);
    };
    window.addEventListener('resize', handleResize);
    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, []);

  //Fetch user data and favorites
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
      .then(movies => {
        setUser(user => ({
          ...user,
          favorites: user.favorites.map((favorite, index) => ({
            ...favorite,
            title: movies[index].title
          }))
        }));
      })
      .catch(error => console.error('Error:', error));
  }, [id]);
  
  // Fetch user reviews
  useEffect(() => {
    fetch(`http://localhost:8080/review/user=${id}`)
      .then(response => response.json())
      .then(data => {
        setReviews(data);
      })
      .catch(error => console.error('Error:', error));
  }, [id]);

  // Fetch users watched movies
  useEffect(() => {
    fetch(`http://localhost:8080/movies_watched/user/${id}`)
      .then(response => response.json())
      .then(data => {
        setWatchedMovies(data);
      })
      .catch(error => console.error('Error:', error));
  }, [id]);

  // Fetch users to watch movies
  useEffect(() => {
    fetch(`http://localhost:8080/movies_to_watch/user/${id}`)
      .then(response => response.json())
      .then(data => {
        setToWatchMovies(data);
      })
      .catch(error => console.error('Error:', error));
  }, [id]);

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

  return user ? (
    <div className="userdetail-container">
    <div className="user-top">
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
    <div className="user-tabs-container">
    <div className="user-tabs-left">
          <Link className={isMobile ? (selectedTab === 'News' ? 'active' : '') : (selectedTabLeft === 'News' ? 'active' : '')} onClick={() => isMobile ? setSelectedTab('News') : setSelectedTabLeft('News')}><p>Uutiset</p></Link>
          <Link className={isMobile ? (selectedTab === 'Groups' ? 'active' : '') : (selectedTabLeft === 'Groups' ? 'active' : '')} onClick={() => isMobile ? setSelectedTab('Groups') : setSelectedTabLeft('Groups')}><p>Ryhmät</p></Link>
          <Link className={isMobile ? (selectedTab === 'Favorites' ? 'active' : '') : (selectedTabLeft === 'Favorites' ? 'active' : '')} onClick={() => isMobile ? setSelectedTab('Favorites') : setSelectedTabLeft('Favorites')}><p>Suosikit</p></Link>
        </div>
        <div className="user-tabs-right">
        <Link className={isMobile ? (selectedTab === 'Reviews' ? 'active' : '') : (selectedTabRight === 'Reviews' ? 'active' : '')} onClick={() => isMobile ? setSelectedTab('Reviews') : setSelectedTabRight('Reviews')}><p>Arvostelut</p></Link>
          <Link className={isMobile ? (selectedTab === 'Watched' ? 'active' : '') : (selectedTabRight === 'Watched' ? 'active' : '')} onClick={() => isMobile ? setSelectedTab('Watched') : setSelectedTabRight('Watched')}><p>On katsonut</p></Link>
          <Link className={isMobile ? (selectedTab === 'ToWatch' ? 'active' : '') : (selectedTabRight === 'ToWatch' ? 'active' : '')} onClick={() => isMobile ? setSelectedTab('ToWatch') : setSelectedTabRight('ToWatch')}><p>Haluaa katsoa</p></Link>
    </div>
    </div>
    <div className="user-content-container">
    <div className="user-content-left">
    {isMobile ? (
        // Mobile view: Use the selectedTab state variable
        <>
          {selectedTab === 'News' && (
            <div className="user-tabs-content">
              <h2>Uutiset</h2>
                <div >
                  <p>Tähän tulee käyttäjään liittyvät uutiset</p>
                  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
                    sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
                    Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris 
                    nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in 
                    reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla 
                    pariatur. Excepteur sint occaecat cupidatat non proident, sunt in 
                    culpa qui officia deserunt mollit anim id est laborum.</p>
                </div>
            </div>
          )}
          {
          //Fetch a list of groups the user is a member of and display them.
          }
          {selectedTab === 'Groups' && (
            <div className="user-tabs-content">
              <h2>Ryhmät</h2>
                <ul>
                  {user.groups.map(group => (
                    <>
                      <p key={group.id}>
                        <Link to={`/group/${group.id}`}>{group.groupName}</Link>
                      </p>
                      <hr></hr>
                    </>
                  ))}
                </ul>
            </div>
          )}
          {selectedTab === 'Favorites' && (
            <div className="user-tabs-content">
              <h2>Suosikit</h2>
                <ul>
                  {user.favorites.map(favorite => (
                    <>
                      <p key={favorite.userId}>
                        <Link to={`/movie/${favorite.movieId}`}>{favorite.title}</Link>
                      </p>
                      <hr></hr>
                    </>
                  ))}
                </ul>
            </div>
          )}
          {selectedTab === 'Reviews' && (
            <div className="user-tabs-content">
              <h2>Arvostelut</h2>
                {reviews.map(review => (
                  <div key={review.reviewId}>
                    <p>
                      <Link to={`/movie/${review.movie.id}`}>{review.movie.title}</Link>
                    </p>
                    <p>
                      {review.content} | Pisteet: {review.movieScore.score}/5
                    </p>
                    <hr></hr>
                  </div>
                ))}
            </div>
          )}
          {
          //Fetch a list of reviews made by group members and display them. Also links to the movie on /movie/:id
          }
          {selectedTab === 'Watched' && (
            <div className="user-tabs-content">
              <h2>On katsonut</h2>
              {watchedMovies.map((watchedMovie, index) => (
                <div key={index}>
                  <p>
                    <Link to={`/movie/${watchedMovie.movieId.id}`}>
                      {watchedMovie.title}
                    </Link>
                  </p>
                  <div className="note">
                    <span className="note-heading">Muistiinpano:</span>
                    <p>{watchedMovie.note}</p>
                  </div>
                  <hr></hr>
                </div>
              ))}
            </div>
          )}
          {selectedTab === 'ToWatch' && (
            <div className="user-tabs-content">
              <h2>Haluaa katsoa</h2>
              {toWatchMovies.map((toWatchMovies, toWatch) => (
                <div key={toWatch}>
                  <p>
                    <Link to={`/movie/${toWatchMovies.movieId.id}`}>
                      {toWatchMovies.movieId.title}
                    </Link>
                  </p>
                    <div className="note">
                      <span className="note-heading">Muistiinpano:</span>
                      <p>{toWatchMovies.note}</p>
                    </div>
                  <hr></hr>
                </div>
              ))}
            </div>
          )}
        </>
      ) : (
        // Desktop view: Use the selectedTabLeft state variable
        <>
          {selectedTabLeft === 'News' && (
            <div className="user-tabs-content">
              <h2>Uutiset</h2>
                <div >
                  <p>Tähän tulee käyttäjään liittyvät uutiset</p>
                  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
                    sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
                    Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris 
                    nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in 
                    reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla 
                    pariatur. Excepteur sint occaecat cupidatat non proident, sunt in 
                    culpa qui officia deserunt mollit anim id est laborum.</p>
                </div>
            </div>
          )}
          {
          //Fetch a list of group members and display them. Also display admin tag if user is admin
          }
          {selectedTabLeft === 'Groups' && (
            <div className="user-tabs-content">
              <h2>Ryhmät</h2>
                <ul>
                {user.groups.map(group => (
                  <>
                    <p key={group.id}>
                        <Link to={`/group/${group.id}`}>{group.groupName}</Link>
                    </p>
                    <hr></hr>
                  </>
                ))}
                </ul>
            </div>
          )}
          {selectedTabLeft === 'Favorites' && (
            <div className="user-tabs-content">
              <h2>Suosikit</h2>
                <ul>
                  {user.favorites.map(favorite => (
                    <>
                      <p key={favorite.userId}>
                        <Link to={`/movie/${favorite.movieId}`}>
                          {favorite.title}
                        </Link>
                      </p>
                    <hr></hr>
                    </>
                  ))}
                </ul>
            </div>
          )}
        </>
        )}
      </div>
    <div className="user-content-right">
      {!isMobile && (
        <>
          {selectedTabRight === 'Reviews' && (
            <div className="user-tabs-content">
              <h2>Arvostelut</h2>
              {reviews.map(review => (
                <div key={review.reviewId}>
                  <p>
                    Arvosteltu: {review.movieScore.createdAt}
                  </p>
                  <p>
                    <Link to={`/movie/${review.movie.id}`}>
                      {review.movie.title}
                    </Link>
                  </p>
                  <p>{review.content} | Pisteet: {review.movieScore.score}/5</p>
                  <hr></hr>
                </div>
              ))}
            </div>
          )}
          {
          //Fetch a list of reviews made by group members and display them. Also links to the movie on /movie/:id
          }
          {selectedTabRight === 'Watched' && (
            <div className="user-tabs-content">
              <h2>On katsonut</h2>
              {watchedMovies.map((watchedMovies, watched) => (
                <div key={watched}>
                  <p>
                    <Link to={`/movie/${watchedMovies.movieId.id}`}>
                      {watchedMovies.movieId.title}
                    </Link>
                  </p>
                    <div className="note">
                      <span className="note-heading">Muistiinpano:</span>
                      <p>{watchedMovies.note}</p>
                    </div>
                  <hr></hr>
                </div>
              ))}
            </div>
          )}
          {selectedTabRight === 'ToWatch' && (
            <div className="user-tabs-content">
              <h2>haluaa katsoa</h2>
              {toWatchMovies.map((toWatchMovies, toWatch) => (
                <div key={toWatch}>
                    <p>
                      <Link to={`/movie/${toWatchMovies.movieId.id}`}>
                        {toWatchMovies.movieId.title}
                      </Link>
                    </p>
                  <div className="note">
                    <span className="note-heading">Muistiinpano:</span>
                    <p>{toWatchMovies.note}</p>
                  </div>
                  <hr></hr>
                </div>
              ))}
            </div>
          )}
        </>
      )}
      </div>
      </div>
    </div>
  ) : (
    <div>Loading...</div>
  );
}

export default UserDetail;