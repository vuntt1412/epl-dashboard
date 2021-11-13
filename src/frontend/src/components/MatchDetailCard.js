import React from 'react';
import {Link} from "react-router-dom";

export const MatchDetailCard = ({match, homeTeamId}) => {
    if (!match) return null;

    const awayTeam = match.homeTeamId === homeTeamId ? match.awayTeamName : match.homeTeamName;
    const awayTeamRoute = `/teams/${awayTeam}`;

    return (
        <div className="MatchDetailCard">
            <h3>{match.date}</h3>
            <h3>vs <Link to={awayTeamRoute}>{awayTeam}</Link></h3>
            <h3>{match.homeTeamGoal}</h3>
            <h3>{match.awayTeamGoal}</h3>
        </div>
    );
}
