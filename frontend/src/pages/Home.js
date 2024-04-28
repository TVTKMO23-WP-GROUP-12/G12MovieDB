import React from 'react';
import ShowtimesMenu from '../components/ShowtimesMenu';
import GroupsMenu from '../components/GroupsMenu';
import Upcoming from '../components/Upcoming';
import TopRated from '../components/TopRated';
import Popular from '../components/Popular';

export default function Home() {
  return (
    <div className="home-container">
      <div className="sidebar">
        <GroupsMenu />
      </div>
      <div className="main-content">
        <div className="showtimes-menu">
          <ShowtimesMenu />
        </div>
        <div className="movies-categories">
          <div className="category">
            <h2>Uutuudet</h2>
            <Upcoming />
          </div>
          <div className="category">
            <h2>Nousussa</h2>
            <TopRated />
          </div>
          <div className="category">
            <h2>Suositut</h2>
            <Popular />
          </div>
        </div>
      </div>
    </div>
  );
}