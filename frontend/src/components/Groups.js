import { Link } from 'react-router-dom';

// On: 
// - UserDetail

const Groups = ({ user }) => {
  const groups = user.groups;

  if (!groups) {
    return <div>Loading...</div>;
  }

  return (
    <div className="groupdetail-content">
      <h2>Ryhmät</h2>
      <ul>
        {groups.map(group => (  
        <div key={group.id}>
          <h3>
            <Link to={`/group/${group.id}`}>{group.groupName}</Link>
          </h3>
          <p>{group.groupDescription}</p>
          <hr></hr>
        </div>
      ))}
      </ul>
    </div>
  );
};

export default Groups;