import React, { useEffect, useState } from 'react';
import './GroupDetail.css';
import { Link, useParams } from 'react-router-dom';
import defaultGroupPicture from '../media/defaultGroupPicture.png';
//import { useAuth } from './auth-context'; // Import the useAuth hook from the auth-context file. change the file to the actual parameter

function GroupDetail() {
  const { id } = useParams();
  const [group, setGroup] = useState({ members: [] });
  const [selectedTab, setSelectedTab] = useState('News');
  const [selectedTabLeft, setSelectedTabLeft] = useState('News');
  const [selectedTabRight, setSelectedTabRight] = useState('Movies');
  const [reviews, setReviews] = useState([]);
  const [isMobile, setIsMobile] = useState(window.innerWidth <= 600);

  //const [isMember, setIsMember] = useState(false); //This is for checking if one is already a member of a group
  //const { id: groupId } = useParams(); // get group ID from URL parameter
  //const { user: { id: userId } } = useAuth();// get user ID from auth context
  
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

  /* commented out because the useAuth hook is not yet implemented
  useEffect(() => {
    // Check if the user is a member of the group
    fetch(`http://localhost:8080/group/members/user=${userId}`)
      .then(response => response.json())
      .then(memberships => {
        const isMember = memberships.some(membership => membership.group.id === id);
        setIsMember(isMember);
      })
      .catch(error => console.error('Error:', error));
  }, [userId, id]);

const handleJoinLeaveGroup = () => {
  if (isMember) {
    // Leave group
    fetch(`http://localhost:8080/group/members/${memberId}`, {
      method: 'DELETE',
    })
      .then(() => setIsMember(false))
      .catch(error => console.error('Error:', error));
  } else {
    // Join group
    fetch('http://localhost:8080/group/members', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ groupId, userId })
    })
      .then(response => response.json())
      .then(() => setIsMember(true))
      .catch(error => console.error('Error:', error));
  }
};
  */

  //Main content of the page
  return group ? (
    <div className="groupdetail-container">
    <div className="groupdetail-top">
      <div className="groupdetail-picture">
        <img src={group.groupPicture} alt="Profile" />
        <Link><p className='groupdetail-join-button'>Liity Ryhmään</p></Link>
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
                <React.Fragment key={member.userId.id}>
                  <p>
                    Admin: <Link to={`/users/${member.userId.id}`}>{member.userId.username}</Link>
                  </p>
                  {/* Below finished link when useAuth is implemented. Remove comment and curly brackets to use
                  <Link to="#" onClick={handleJoinLeaveGroup}>{isMember ? 'Poistu ryhmästä' : 'Liity ryhmään'}</Link>
                  */} 
                </React.Fragment>
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
          <Link className={isMobile ? (selectedTab === 'News' ? 'active' : '') : (selectedTabLeft === 'News' ? 'active' : '')} onClick={() => isMobile ? setSelectedTab('News') : setSelectedTabLeft('News')}><p>Uutiset</p></Link>
          <Link className={isMobile ? (selectedTab === 'Members' ? 'active' : '') : (selectedTabLeft === 'Members' ? 'active' : '')} onClick={() => isMobile ? setSelectedTab('Members') : setSelectedTabLeft('Members')}><p>Jäsenet</p></Link>
          <Link className={isMobile ? (selectedTab === 'Shared' ? 'active' : '') : (selectedTabLeft === 'Shared' ? 'active' : '')} onClick={() => isMobile ? setSelectedTab('Shared') : setSelectedTabLeft('Shared')}><p>Jaetut</p></Link>
        </div>
        <div className="groupdetail-tabs-right">
          <Link className={isMobile ? (selectedTab === 'Movies' ? 'active' : '') : (selectedTabRight === 'Movies' ? 'active' : '')} onClick={() => isMobile ? setSelectedTab('Movies') : setSelectedTabRight('Movies')}><p>Elokuvat</p></Link>
          <Link className={isMobile ? (selectedTab === 'Reviews' ? 'active' : '') : (selectedTabRight === 'Reviews' ? 'active' : '')} onClick={() => isMobile ? setSelectedTab('Reviews') : setSelectedTabRight('Reviews')}><p>Arvostelut</p></Link>
          <Link className={isMobile ? (selectedTab === 'Finnkino' ? 'active' : '') : (selectedTabRight === 'Finnkino' ? 'active' : '')} onClick={() => isMobile ? setSelectedTab('Finnkino') : setSelectedTabRight('Finnkino')}><p>Finnkino</p></Link>
        </div>
      </div>
      <div className="groupdetail-content-container">
      <div className="groupdetail-content-left">
        {isMobile ? (
        // Mobile view: Use the selectedTab state variable
        <>
          {selectedTab === 'News' && (
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
          {selectedTab === 'Members' && (
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
          {selectedTab === 'Shared' && (
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
          {selectedTab === 'Movies' && (
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
          {selectedTab === 'Reviews' && (
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
          {selectedTab === 'Finnkino' && (
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
        </>
      ) : (
        // Desktop view: Use the selectedTabLeft state variable
        <>
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
        </>
        )}
      </div>
        
      <div className="groupdetail-content-right">
      {!isMobile && (
        <>
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
        </>
      )}
      </div>
      </div>
    </div>

    ) : (
      <div>Ladataan...</div>
  );
}

export default GroupDetail;