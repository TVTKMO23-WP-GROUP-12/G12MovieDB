import React, { useEffect, useState } from 'react';
import './GroupDetail.css';
import { Link, useParams } from 'react-router-dom';
import defaultGroupPicture from '../media/defaultGroupPicture.png';

function GroupDetail() {
  const { id } = useParams();
  const [group, setGroup] = useState({ members: [] });

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
      })
      .catch(error => console.error('Error:', error));
  }, [id]);

  return group ? (
  <div className="groupdetail-container">
  <div className="groupdetail-top">
    <div className="groupdetail-picture">
      <img src={group.groupPicture} alt="Profile" />
    </div>
      <div className="groupdetail-bio">
        <h2><Link to={`/group/${group.id}`}>{group.groupName}</Link></h2>
        <p>{group.groupDescription}</p>
      </div>
    </div>
    <div className="groupdetail-members">
      <h2>Members:</h2>
      {group.members.map(member => (
        <div key={member.groupMembersId}>
          <h3><Link to={`/users/${member.userId.id}`}>{member.userId.username}</Link></h3>
          <p>{member.userId.userDescription}</p>
        </div>
      ))}
    </div>
  </div>
  ) : (
    <div>Loading...</div>
  );
}

export default GroupDetail;