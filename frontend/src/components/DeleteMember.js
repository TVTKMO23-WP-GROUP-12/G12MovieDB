import React, { useEffect } from 'react';

function DeleteMember({ memberId, DeleteGroupMember }) {
  const deleteMember = async () => {
    try {
      const confirmed = window.confirm('Oletko varma että haluat poistaa jäsenen?');
      if (!confirmed) {
        return; 
      }
      
      // Call the DeleteGroupMember function passed as a prop
      await DeleteGroupMember(memberId);
      console.log('Deleting member with ID:', memberId);
      // Properly redirect after deletion
      window.location.href = '/home';
      console.log('Member deleted successfully');
    } catch (error) {
      console.error(error.message);
    }
  };

  useEffect(() => {
    // Fetch the group data if needed
    fetch('http://localhost:8080/group')
      .then(response => response.json())
      .then(data => {
        const group = data.find(group => group.id === 'groupId');
        // Now you can use group.id
      })
      .catch(error => console.error('Error fetching groups:', error));
  }, []);

  return (
    <button onClick={deleteMember}>Poista jäsen</button>
  );
}

export default DeleteMember;