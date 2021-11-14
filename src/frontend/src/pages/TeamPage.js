import './TeamPage.scss';
import {React, useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {MatchDetailCard} from "../components/MatchDetailCard";
import {MatchDetailSmallCard} from "../components/MatchDetailSmallCard";
import {PieChart} from 'react-minimal-pie-chart';

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
            <div className="team-name-section">
                <p>{team.latestMatches[0].leagueName}</p>
                <div className="team-name"><h1>{team.teamLongName}</h1></div>
            </div>
            <div className="team-statistic-section">
                <PieChart
                    data={[
                        {title: 'Wins', value: 10, color: '#E38627'},
                        {title: 'Losses', value: 15, color: '#C13C37'},
                        {title: 'Ties', value: 20, color: '#6A2135'},
                    ]}
                />;
            </div>
            <div className="match-detail-card-section">
                <h5>Latest matches</h5>
                <MatchDetailCard match={team.latestMatches[0]} teamName={teamName}/>
            </div>
            {team.latestMatches && team.latestMatches.slice(1).map(m => <MatchDetailSmallCard match={m}
                                                                                              teamName={teamName}/>)}
            <div className="more-link">
                <a href="#">See More</a>
            </div>
        </div>
    );
}
