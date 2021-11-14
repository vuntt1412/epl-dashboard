import {React, useEffect, useState} from 'react';
import './HomePage.scss';
import {TeamTitle} from "../components/TeamTitle";

export const HomePage = () => {

    const [teams, setTeams] = useState([]);

    useEffect(
        () => {
            const fetchTeams = async () => {
                const response = await fetch(`http://localhost:8080/team`);
                const data = await response.json();
                setTeams(data);
            }
            fetchTeams();

        }, []
    );

    return (
        <div className="HomePage">
            <div className="team-grid">
                {teams.map(team => <TeamTitle key={team.id} teamName={team.teamLongName}/>)}
            </div>
        </div>
    );
}
