import React, { useState } from 'react';
import { Link } from 'react-router-dom';

const GroupDetailTop = ({ group }) => {
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
        // Clear the editedDescription state
        setEditedDescription("");
      })
      .catch(error => console.error('Error:', error));
  };

  return (
    <div className="groupdetail-top">
      <div className="groupdetail-picture">
        <img src={group.groupPicture} alt="Profile" />
        <Link><p className='groupdetail-join-button'>Liity Ryhmään</p></Link>
      </div>
      <div></div>
      <div className="groupdetail-bio">
        <h2><Link to={`/group/${group.id}`}>{group.groupName}</Link></h2>
        {
        // Get the admin of the group and add a [ADMIN] tag after their name
        }
        <div className="groupdetail-admin">
          {group.members.map(member => { 
            if (member.isAdmin) {
              return (
                <React.Fragment key={member.userId.id}>
                  <p>
                    Admin: <Link to={`/users/${member.userId.id}`}>{member.userId.username}</Link>
                  </p>
                  {/* Below finished link when useAuth is implemented. Remove comment and curly brackets to use
                  <Link to="#" onClick={handleJoinLeaveGroup}>{isMember ? 'Poistu ryhmästä' : 'Liity ryhmään'}</Link>
                  */} 
                </React.Fragment>
              );
            }      
            return null;
          })}
        </div>
        <div className= "groupdetail-update-description">
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