import React from 'react';



const DeleteUser = () => {
  const deleteUser = async () => {
    const userId = localStorage.getItem('userId');
    try {
      const confirmed = window.confirm('Oletko varma että haluat poistaa käyttäjän?');
      if (!confirmed) {
        return; // If user cancels deletion, exit the function
      }

      const response = await fetch(`http://localhost:8080/users/${userId}`, {
        method: 'DELETE',
      });

      if (response.ok) {
        window.location.href('/home');

        console.log('User deleted successfully');
      }
    } catch (error) {
      console.error(error.message);
    }
  };

  return (
    <button onClick={deleteUser}>Poista</button>
  );
};

export default DeleteUser;