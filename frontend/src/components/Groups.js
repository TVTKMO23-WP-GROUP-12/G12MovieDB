import { Link } from 'react-router-dom';

// On: 
// - UserDetail

const Groups = ({ }) => {
  const user = localStorage.getItem('userId');
  const groups = user.groups;

  if (!groups) {
    return <div>Loading...</div>;
  }

  return (
    <div className="user-tabs-content">
      <h2>Ryhmät</h2>
      <ul>
        {groups.map(group => (  
        <div key={group.groupId}>
          <p>
            <Link to={`/public/group/${group.groupId}`}>{group.groupName}</Link>
          </p>
          <p>{group.groupDescription}</p>
          <hr></hr>
        </div>
      ))}
      </ul>
    </div>
  );
};

export default Groups;