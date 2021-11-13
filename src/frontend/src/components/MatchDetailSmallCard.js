import React from 'react';
import {Link} from 'react-router-dom'

export const MatchDetailSmallCard = ({match, homeTeamId}) => {
    if (!match) return null;

    const awayTeam = match.homeTeamId === homeTeamId ? match.awayTeamName : match.homeTeamName;
    const awayTeamRoute = `/teams/${awayTeam}`;

    return (
        <div className="MatchDetailSmallCard">
            <h5>vs <Link to={awayTeamRoute}>{awayTeam}</Link></h5>
        </div>
    );
}
