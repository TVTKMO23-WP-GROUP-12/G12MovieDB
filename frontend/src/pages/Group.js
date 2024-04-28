import React, { useState, useEffect } from 'react';
import './Group.css'
import { Link } from 'react-router-dom';

export default function Group() {
    const [groups, setGroups] = useState([]);
    const [selectedGroup, setSelectedGroup] = useState('All');
    const user = localStorage.getItem('userId');
    
    useEffect(() => {
        fetch('http://localhost:8080/public/group')    //get the groups from the backend
                                                //need to change the url to match our final backend
            .then(response => response.json())
            .then(data => {
                setGroups(data);
            })
            .catch(error => {
                console.error('Error fetching groups:', error);
            });
    }, []); 

    return (
        <div className="group-container">
            <div className="group-box">
                <h2>Ryhmät</h2>
                <div className="group-list">
                {groups.map(group => (
                    <div key={group.id}>
                        <h3><Link to={`/${group.id}`}>{group.groupName}</Link></h3>
                        <p>{group.groupDescription}</p>
                    </div>
                ))}
                </div>
            </div>
        </div>
    )
}
