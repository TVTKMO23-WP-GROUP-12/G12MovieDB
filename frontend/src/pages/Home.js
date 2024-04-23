import React from 'react';
import ShowtimesMenu from '../components/ShowtimesMenu';
import GroupsMenu from '../components/GroupsMenu';
export default function Home() {
  // or do we name it Index?
  return (
    <div>
      <div style={{ float: 'left' }}>
        <GroupsMenu />
      </div>
      <div style={{ float: 'right' }}>
        <ShowtimesMenu />
      </div>
      <div style={{ float: 'right' }}>
        <ShowtimesMenu />
      </div>
	  </>
  );
  
}
