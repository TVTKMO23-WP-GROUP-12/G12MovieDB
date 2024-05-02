import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import DeleteMember from '../components/DeleteMember';



const GroupDetailTop = ({ group, userId, memberId }) => {
  const [isEditing, setIsEditing] = useState(false);
  const [editedDescription, setEditedDescription] = useState('');


  // Function to update the group description
  const submitDescription = () => {
    fetch(`http://localhost:8080/group/${group.id}`, {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        groupDescription: editedDescription,
      }),
    })
      .then(response => response.json())
      .then(data => {
        group.groupDescription = data.groupDescription;
        setEditedDescription("");
      })
      .catch(error => console.error('Error:', error));
  };

  // Function to handle joining or leaving the group
  const handleJoinLeaveGroup = () => { 
    if (userId === memberId) {
      // Leave group
      console.log('Leave group');
    } else {
      // Join group
      console.log('Join group');
    }
  };

  return (
    <div className="groupdetail-top">
      <div className="groupdetail-picture">
        <img src={group.groupPicture} alt="Profile" />
        <Link><p className='groupdetail-join-button'>Liity Ryhmään</p></Link>
        {memberId && <DeleteMember memberId={memberId} />}
      </div>
      <div></div>
      <div className="groupdetail-bio">
        <h2><Link to={`/group/${group.id}`}>{group.groupName}</Link></h2>
        <div className="groupdetail-admin">
          {Array.isArray(group.members) && group.members.map(member => (
            member.isAdmin && (
              <React.Fragment key={member.userId.userId}>
                <p>
                  Admin: <Link to={`/users/${member.userId.userId}`}>{member.userId.username}</Link>
                </p>
                {/* Conditional rendering for join/leave link */}
                {userId && memberId ? (
                  userId === memberId ? (
                    <Link to="#" onClick={handleJoinLeaveGroup}>Poistu ryhmästä</Link>
                  ) : (
                    <Link to="#" onClick={handleJoinLeaveGroup}>Liity ryhmään</Link>
                  )
                ) : null}
              </React.Fragment>
            )
          ))}
        </div>
        <div className="groupdetail-update-description">
          {isEditing ? (
            <>
              <input
                type="text"
                value={editedDescription}
                onChange={e => setEditedDescription(e.target.value)} 
              />
              <button onClick={() => {
                setIsEditing(false);
                submitDescription();
              }}>Tallenna</button>
            </>
          ) : (
            <>
              <p>{group.groupDescription}</p>
              <button onClick={() => {
                setIsEditing(true);
                setEditedDescription(group.groupDescription);
              }}>Muokkaa</button>
            </>
          )}
        </div>
      </div>
    </div>
  );
};

export default GroupDetailTop;