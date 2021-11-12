import React from 'react';

export const MatchDetailSmallCard = ({match}) => {
    return (
        <div className="MatchDetailSmallCard">
            <h5>{match.homeTeamId} vs {match.awayTeamId}</h5>
        </div>
    );
}
