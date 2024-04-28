import React from 'react';
import { Link } from 'react-router-dom';

const GroupMembers = ({ group }) => {
  return (
    <div className="groupdetail-tabs-content">
      <h2>Jäsenet</h2>
      {group.groupMembers.map(member => (
        <div key={member.id}>
          <p>
            <Link to={`/users/${member.id}`}>
              {member.user.username}
              {member.isAdmin && <span> [ADMIN]</span>}
            </Link>
          </p>
          <p>{member.userDescription}</p>
          <hr></hr>
        </div>
      ))}
    </div>
  );
};

export default GroupMembers;