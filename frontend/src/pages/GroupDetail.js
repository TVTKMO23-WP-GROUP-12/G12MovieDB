import React, { useEffect, useState } from 'react';
import './GroupDetail.css';
import { Link, useParams } from 'react-router-dom';
import defaultGroupPicture from '../media/defaultGroupPicture.png';
import GroupMembers from '../components/GroupMembers';
import GroupReviews from '../components/GroupReviews';
import GroupDetailTop from '../components/GroupDetailTop';
import useIsMobile from '../hooks/useIsMobile';
//import { useAuth } from './auth-context'; // Import the useAuth hook from the auth-context file. change the file to the actual parameter

function GroupDetail() {
  const { id } = useParams();
  const [group, setGroup] = useState({ groupMembers: [] });
  const [selectedTab, setSelectedTab] = useState('News');
  const [selectedTabLeft, setSelectedTabLeft] = useState('News');
  const [selectedTabRight, setSelectedTabRight] = useState('Movies');
  const [reviews, setReviews] = useState([]);
  const isMobile = useIsMobile();

  const [isMember, setIsMember] = useState(false); //This is for checking if one is already a member of a group
  //const { id: groupId } = useParams(); // get group ID from URL parameter
  //const { user: { id: userId } } = useAuth();// get user ID from auth context
  const [memberId, setMemberId] = useState(null);
  const [groupId, setgroupId] = useState(null);
  // Fetch group members and their reviews

  useEffect(() => {
    let groupData; 
    fetch(`http://localhost:8080/group/${id}`)
      .then(response => response.json())
      .then(data => {
        groupData = data;  
        setGroup(prevGroup => ({ ...prevGroup, ...data, groupPicture: data.groupPicture || defaultGroupPicture }));
        return Promise.all(data.groupMembers.map(member =>
          fetch(`http://localhost:8080/users/${member.id}`)
            .then(response => {
              if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
              }
              return response.json();
            })
            .then(userDetails => {
              console.log(`User details for member ${member.id}:`, userDetails);  // Log the user details
              return userDetails;
            })
        ));
      })
      .then(userDetailsArray => {
        const updatedGroupMembers = userDetailsArray.map((userDetails, index) => ({
          ...groupData.groupMembers[index],  
          user: userDetails
        }));
        setGroup(prevGroup => ({ ...prevGroup, groupMembers: updatedGroupMembers }));
      })
      .catch(error => console.error('Error:', error));
  }, [id]);
  useEffect(() => {
    console.log(group.groupMembers);  // Log the groupMembers property of the group state
  }, [group]);  // This useEffect hook will run whenever `group` changes
  useEffect(() => {
    // Check if the user is a member of the group
    const userId = localStorage.getItem('userId');
    fetch(`http://localhost:8080/group/members/user=${userId}`)
      .then(response => response.json())
      .then(memberships => {
        const isMember = memberships.some(membership => membership.group.id === userId);
        setIsMember(isMember);
      })
      .catch(error => console.error('Error:', error));
  }, []);

const handleJoinLeaveGroup = () => {
  const userId = localStorage.getItem('userId'); // Retrieve userId from localStorage

  if (!userId) {
    console.error('User ID not found in localStorage');
    return;
  }
  if (isMember) {
    // Leave group
    fetch(`http://localhost:8080/group/members/${memberId}`, {
      method: 'DELETE',
    })
      .then(() => setIsMember(false))
      .catch(error => console.error('Error:', error));
  } else {
    //Join group
    if(groupId && userId) {
    fetch('http://localhost:8080/group/members', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ groupId, userId })
    })
      .then(response => response.json())
      .then(() => setIsMember(true))
      .catch(error => console.error('Error:', error));
  }
}
};
  

  //Main content of the page
  return group ? (
    <div className="groupdetail-container">
      <GroupDetailTop group={group} />
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
            <GroupMembers group={group} />
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
            <GroupReviews reviews={reviews} users={group.members} />
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
            <GroupMembers group={group} />
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
            <GroupReviews reviews={reviews} users={group.members} />
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