import {React, useEffect, useState} from 'react';
import {MatchDetailCard} from "../components/MatchDetailCard";
import {MatchDetailSmallCard} from "../components/MatchDetailSmallCard";

//specifically export this value
export const TeamPage = () => {

    // this state contains team information
    const [team, setTeam] = useState({latestMatches: []});

    // before return the jsx snippet below, call useEffect and then pass in the function needs to execute when this component loads
    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch('http://localhost:8080/team/Manchester%20United');//fetch returns a promise
                const data = await response.json();
                console.log(data)
                setTeam(data);

            }
            fetchMatches();

        }, []
    );

    return (
        <div className="TeamPage">
            <h1>{team.teamLongName}</h1>
            <MatchDetailCard match={team.latestMatches[0]}/>
            {team.latestMatches && team.latestMatches.slice(1).map(m => <MatchDetailSmallCard match={m}/>)}
        </div>
    );
}
