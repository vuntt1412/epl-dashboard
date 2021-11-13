import {React, useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {MatchDetailCard} from "../components/MatchDetailCard";
import {MatchDetailSmallCard} from "../components/MatchDetailSmallCard";

//specifically export this value
export const TeamPage = () => {

    // this state contains team information
    const [team, setTeam] = useState({latestMatches: []});
    const {teamName} = useParams();

    // before return the jsx snippet below, call useEffect and then pass in the function needs to execute when this component loads
    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(`http://localhost:8080/team/${teamName}`);//fetch returns a promise
                const data = await response.json();
                console.log(data)
                setTeam(data);

            }
            fetchMatches();

        }, [teamName]
    );

    if (!team || !team.teamLongName) {
        return <h1>TEAM NOT FOUND</h1>
    }

    return (
        <div className="TeamPage">
            <h1>{team.teamLongName}</h1>
            <p>{team.latestMatches[0].leagueName}</p>
            <MatchDetailCard match={team.latestMatches[0]} teamName={teamName}/>
            {team.latestMatches && team.latestMatches.slice(1).map(m => <MatchDetailSmallCard match={m}
                                                                                              teamName={teamName}/>)}
        </div>
    );
}
