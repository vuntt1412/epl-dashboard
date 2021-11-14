import React from 'react';
import {Link} from 'react-router-dom'
import './MatchDetailSmallCard.scss'

export const MatchDetailSmallCard = ({match, teamName}) => {
    if (!match) return null;

    const currentTeam = match.homeTeamName === teamName ? match.homeTeamName : match.awayTeamName;
    const otherTeam = match.homeTeamName === teamName ? match.awayTeamName : match.homeTeamName;
    const otherTeamRoute = `/teams/${otherTeam}`;

    const currentTeamGoal = match.homeTeamName === currentTeam ? match.homeTeamGoal : match.awayTeamGoal;
    const otherTeamGoal = match.homeTeamName === currentTeam ? match.awayTeamGoal : match.homeTeamGoal;
    const hasWon = currentTeamGoal - otherTeamGoal > 0;

    return (
        <div className={hasWon ? 'MatchDetailSmallCard won-card' : 'MatchDetailSmallCard lost-card'}>
            <div className="match-opponent">
                <h5>{currentTeam}</h5>
                <h5><Link to={otherTeamRoute}>{otherTeam}</Link></h5>
            </div>
            <div className="match-result">
                <h5>{currentTeamGoal}</h5>
                <h5>{otherTeamGoal}</h5>
            </div>
            <div className="match-date">
                <p>FT</p>
                <p>{match.date}</p>
            </div>
        </div>
    );
}
