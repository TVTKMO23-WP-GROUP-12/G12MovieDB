import { Link } from 'react-router-dom';
import useFetchGroups from '../hooks/useFetchGroups';

// On: 
// - UserDetail

const Groups = ({ id }) => {
  const groups= useFetchGroups(id);

  if (!groups) {
    return <div>Loading...</div>;
  }

  return (
    <div className="user-tabs-content">
      <h2>Ryhmät</h2>
      <ul>
        {groups.map(group => (  
        <div key={group.groupId}>
          <h3>
            <Link to={`/public/group/${group.group.id}`}>{group.group.groupName}</Link>
          </h3>
          <p>{group.group.groupDescription}</p>
          <hr></hr>
        </div>
      ))}
      </ul>
    </div>
  );
};

export default Groups;