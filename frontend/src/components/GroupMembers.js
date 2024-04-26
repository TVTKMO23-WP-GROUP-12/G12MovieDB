import React from 'react';
import { Link } from 'react-router-dom';

const GroupMembers = ({ group }) => {
  return (
    <div className="groupdetail-tabs-content">
      <h2>Jäsenet</h2>
      {group.members.map(member => (
        <div key={member.memberId}>
          <p>
            <Link to={`/users/${member.userId.userId}`}>
              {member.userId.username}
              {member.isAdmin && <span> [ADMIN]</span>}
            </Link>
          </p>
          <p>{member.userId.userDescription}</p>
          <hr></hr>
        </div>
      ))}
    </div>
  );
};

export default GroupMembers;