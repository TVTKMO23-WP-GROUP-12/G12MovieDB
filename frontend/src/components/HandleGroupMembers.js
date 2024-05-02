import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import GroupDetailTop from './GroupDetailTop';

const HandleGroupMembers = () => {
  const [userId, setUserId] = useState(''); 
  const [memberId, setMemberId] = useState(''); 

  useEffect(() => {
    const storedUserId = localStorage.getItem('userId');
    const storedMemberId = localStorage.getItem('memberId');
    setUserId(storedUserId);
    setMemberId(storedMemberId);
  }, []);

  return (
    <div>
      <GroupDetailTop userId={userId} memberId={memberId} />
    </div>
  );
};

export default HandleGroupMembers;