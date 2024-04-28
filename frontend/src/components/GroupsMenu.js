import React, { useState, useEffect } from 'react';
import './GroupsMenu.css';
import { Link } from 'react-router-dom';

export default function GroupsMenu() {
    const [groups, setGroups] = useState([]);
    const [selectedGroup, setSelectedGroup] = useState('All');
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
      
        fetch('http://localhost:8080/group')
            .then(response => response.json())
            .then(data => {
                setGroups(data);
                console.log(data);
            })
            
            .catch(error => {
                console.error('Error fetching groups:', error);
            });
    }, []); 

    return (
        <div className="groupsmenu-container">
            <div className="groupsmenu-box">
                <h2>Groups</h2>
                <div className="groupsmenu-list">
                {groups.map(group => (
                    <div key={group.groupId}>
                        <h3><Link to={`group/${group.groupId}`}>{group.groupName}</Link></h3>
                        <p>{group.groupDescription}</p>
                    </div>
                ))}
                </div>
            </div>
        </div>
    )
}