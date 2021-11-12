import React from 'react';

export const MatchDetailCard = ({match}) => {
    if (!match) return null;

    return (
        <div className="MatchDetailCard">
            <h3>Match Detail Card</h3>
            <h3>{match.homeTeamId} vs {match.awayTeamId}</h3>
        </div>
    );
}
