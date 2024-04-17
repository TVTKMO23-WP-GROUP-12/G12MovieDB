import React from 'react';
import ShowtimesMenu from '../components/ShowtimesMenu';
import Welcome from '../components/Welcome';
import UserMessage from '../components/UserMessage';

export default class Home extends React.Component {
  // or do we name it Index?
  render() {
  return (
    <div>
      	<div>
	  <Welcome/>
	  <UserMessage/>
	  </div>
     <div style={{ float: 'right' }}>
	  <ShowtimesMenu /> {}
      </div>
    </div>
  );
 };
}
