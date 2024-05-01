import React, { useEffect, useState, useCallback } from 'react';
import './UserDetail.css';
import { Link, useParams } from 'react-router-dom';
import Reviews from '../components/Reviews';
import Favorites from '../components/Favorites';
import User from '../components/User';
import Watched from '../components/Watched';
import ToWatch from '../components/ToWatch';
import Groups from '../components/Groups';
import useFetchReviews from '../hooks/useFetchReviews';
import useFetchFavorites from '../hooks/useFetchFavorites';
import useFetchUser from '../hooks/useFetchUser';
import useFetchWatched from '../hooks/useFetchWatched';
import useFetchToWatch from '../hooks/useFetchToWatch';
import useIsMobile from '../hooks/useIsMobile';
import useFetchMovie from '../hooks/useFetchMovie';

function UserDetail() {
  const { id } = useParams();
  const { user, profilePicture } = useFetchUser(id);
  const [isLoading, setIsLoading] = useState(true);
  const [selectedTab, setSelectedTab] = useState('News');
  const [selectedTabLeft, setSelectedTabLeft] = useState('News');
  const [selectedTabRight, setSelectedTabRight] = useState('Reviews');
  const isMobile = useIsMobile();
 localStorage.setItem('userGroup', id);

  return user ?  (
    <div>  
          <User user={user} profilePicture={profilePicture} />
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
            <Groups id={id}/>
          )}
          {
          //call the Favorites component and pass the userId as a prop
          }
          {selectedTab === 'Favorites' && (
            <Favorites id={id}/>
          )}
          {
          //call the Reviews component and pass the userId as a prop
          }
          {selectedTab === 'Reviews' && (
            <Reviews id={id} />
          )}
          {
          //call the Watched component and pass the userId as a prop
          }
          {selectedTab === 'Watched' && (
            <Watched id={id} />
          )}
          {
          //call the ToWatch component and pass the userId as a prop
          }
          {selectedTab === 'ToWatch' && (
            <ToWatch id={id} />
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
          //Fetch a list of groups that the user is in and displays them.
          }
          {selectedTabLeft === 'Groups' && (
            <Groups id={id}/>
          )}
          {
          //call the Favorites component and pass the userId as a prop
          }
          {selectedTabLeft === 'Favorites' && (
            <Favorites id={id} />
          )}
        </>
        )}
      </div>
    <div className="user-content-right">
      {!isMobile && (
        <>
          {
          //call the Reviews component and pass the userId as a prop
          }
          {selectedTabRight === 'Reviews' && (
            <Reviews id={id} />
          )}
          {
          //call the Watched component and pass the userId as a prop
          }
          {selectedTabRight === 'Watched' && (
            <Watched id={id} />
          )}
          {
          //call the ToWatch component and pass the userId as a prop
          }
          {selectedTabRight === 'ToWatch' && (
            <ToWatch id={id} />
          )}
        </>
      )}
      </div>
      </div>
    </div>
  ) : (
    <div>Loading... </div>
  );
}

export default UserDetail;
