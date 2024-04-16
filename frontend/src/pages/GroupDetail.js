import React, { useEffect, useState } from 'react';
import './GroupDetail.css';
import { Link, useParams } from 'react-router-dom';
import defaultGroupPicture from '../media/defaultGroupPicture.png';

function GroupDetail() {
  const { id } = useParams();
  const [group, setGroup] = useState({ members: [] });
  const [selectedTabLeft, setSelectedTabLeft] = useState('News');
  const [selectedTabRight, setSelectedTabRight] = useState('Movies');
  const [reviews, setReviews] = useState([]);
  
  // Fetch group members and their reviews
  useEffect(() => {
    fetch(`http://localhost:8080/group/${id}`)
      .then(response => response.json())
      .then(data => { 
        setGroup(prevGroup => ({ ...prevGroup, ...data, groupPicture: data.groupPicture || defaultGroupPicture }));
        return fetch(`http://localhost:8080/group/members/group=${id}`)
          .then(response => response.json());
      })
      .then(members => {
        setGroup(prevGroup => ({ ...prevGroup, members }));
        const reviewsPromises = members.map(member => 
          fetch(`http://localhost:8080/review/user=${member.userId.id}`)
            .then(response => response.json())
        );
        return Promise.all(reviewsPromises);
      })
      .then(reviewsArrays => {
        const reviews = [].concat(...reviewsArrays);
        setReviews(reviews);
      })
      .catch(error => console.error('Error:', error));
  }, [id]);
  //Main content of the page
  return group ? (
    <div className="groupdetail-container">
    <div className="groupdetail-top">
      <div className="groupdetail-picture">
        <img src={group.groupPicture} alt="Profile" />
      </div>
        <div className="groupdetail-bio">
          <h2><Link to={`/group/${group.id}`}>{group.groupName}</Link></h2>
          <p>{group.groupDescription}</p>
          {
          // Get the admin of the group and add a [ADMIN] tag after their name
          }
          {group.members.map(member => { 
            if (member.isAdmin) {
              return (
                <p key={member.userId.id}>
                  Admin: <Link to={`/users/${member.userId.id}`}>{member.userId.username}</Link>
                </p>
              );
            }
            return null;
          })}
        </div>
      </div>
      {
      // Tabs and their content
      }
      <div className="groupdetail-tabs-container">
        <div className="groupdetail-tabs-left">
            <Link className={selectedTabLeft === 'News' ? 'active' : ''} onClick={() => setSelectedTabLeft('News')}><p>Uutiset</p></Link>
            <Link className={selectedTabLeft === 'Members' ? 'active' : ''} onClick={() => setSelectedTabLeft('Members')}><p>Jäsenet</p></Link>
            <Link className={selectedTabLeft === 'Shared' ? 'active' : ''} onClick={() => setSelectedTabLeft('Shared')}><p>Jaetut</p></Link>
        </div>
        <div className="groupdetail-tabs-right">
            <Link className={selectedTabRight === 'Movies' ? 'active' : ''} onClick={() => setSelectedTabRight('Movies')}><p>Elokuvat</p></Link>
            <Link className={selectedTabRight === 'Reviews' ? 'active' : ''} onClick={() => setSelectedTabRight('Reviews')}><p>Arvostelut</p></Link>
            <Link className={selectedTabRight === 'Finnkino' ? 'active' : ''} onClick={() => setSelectedTabRight('Finnkino')}><p>Finnkino</p></Link>
        </div>
      </div>
      <div className="groupdetail-content-container">
        <div className="groupdetail-content-left">
          {selectedTabLeft === 'News' && (
            <div className="groupdetail-tabs-content">
              <h2>Uutiset</h2>
                <div >
                  <p>Tähän tulee ryhmään liittyvät uutiset</p>
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
          {selectedTabLeft === 'Members' && (
            <div className="groupdetail-tabs-content">
              <h2>Jäsenet</h2>
              {group.members.map(member => (
                <div key={member.groupMembersId}>
                  <p>
                    <Link to={`/users/${member.userId.id}`}>
                      {member.userId.username}
                      {member.isAdmin && <span> [ADMIN]</span>}
                    </Link>
                  </p>
                  <p>{member.userId.userDescription}</p>
                </div>
              ))}
            </div>
          )}
          {selectedTabLeft === 'Shared' && (
            <div className="groupdetail-tabs-content">
              <h2>Jaetut</h2>
                <div>
                  <p>Tähän tulee ryhmään ajetut jutut</p>
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
        </div>
        <div className="groupdetail-content-right">
          {selectedTabRight === 'Movies' && (
            <div className="groupdetail-tabs-content">
              <h2>Elokuvat</h2>
                <div>
                  <p>Tähän tulee ryhmään ajetut elokuvat</p>
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
          //Fetch a list of reviews made by group members and display them. Also links to the movie on /movie/:id
          }
          {selectedTabRight === 'Reviews' && (
            <div className="groupdetail-tabs-content">
              <h2>Arvostelut</h2>
              {reviews.map(review => (
                <div key={review.id}>
                  <p>
                    <Link to={`/movie/${review.movie.id}`}>
                      {review.movie.title}
                    </Link>
                  </p>
                  <p>{review.content}</p>
                </div>
              ))}
            </div>
          )}
          {selectedTabRight === 'Finnkino' && (
            <div className="groupdetail-tabs-content">
              <h2>Finnkino</h2>
                <div>
                  <p>Tähän tulee Finnkinon aikataulut ryhmäläisten elokuvien mukaan</p>
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
        </div>
      </div>
    </div>
    ) : (
      <div>Ladataan...</div>
  );
}

export default GroupDetail;