import React from 'react';
import ShowtimesMenu from '../components/ShowtimesMenu';
import GroupsMenu from '../components/GroupsMenu';
import Upcoming from '../components/Upcoming';
import TopRated from '../components/TopRated';
import Popular from '../components/Popular';
import useFetchUserId from '../hooks/useFetchUserId';
import './Home.css';

export default function Home() {
  const { username } = useFetchUserId(localStorage.getItem('username'));
  useFetchUserId(username);

  return (
    <div className="container">
      
      <div className="sidebar-left">
        <GroupsMenu />
      </div>

      <div className="movie-content">
        <Upcoming />
        <TopRated />
        <Popular />
      </div>

      <div className="sidebar-right">
        <ShowtimesMenu />
      </div>

    </div>
    
  );
}