import React from 'react';
import {Link} from "react-router-dom";
import "./MatchDetailCard.scss";

export const MatchDetailCard = ({match, teamName}) => {
    if (!match) return null;

    const currentTeam = match.homeTeamName === teamName ? match.homeTeamName : match.awayTeamName;
    const otherTeam = match.homeTeamName === teamName ? match.awayTeamName : match.homeTeamName;
    const otherTeamRoute = `/teams/${otherTeam}`;

    const currentTeamGoal = match.homeTeamName === currentTeam ? match.homeTeamGoal : match.awayTeamGoal;
    const otherTeamGoal = match.homeTeamName === currentTeam ? match.awayTeamGoal : match.homeTeamGoal;
    const hasWon = currentTeamGoal - otherTeamGoal > 0;

    return (
        <div className={hasWon ? 'MatchDetailCard won-card' : 'MatchDetailCard lost-card'}>
            <div className="match-opponent">
                <h3>{currentTeam}</h3>
                <h3><Link to={otherTeamRoute}>{otherTeam}</Link></h3>
            </div>
            <div className="match-result">
                <h3>{currentTeamGoal}</h3>
                <h3>{otherTeamGoal}</h3>
            </div>
            <div className="match-date">
                <p>FT</p>
                <p>{match.date}</p>
            </div>
        </div>
    );
}
