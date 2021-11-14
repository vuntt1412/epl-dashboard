import {React, useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {MatchDetailCard} from "../components/MatchDetailCard";
import './MatchPage.scss'
import {YearSelector} from "../components/YearSelector";

export const MatchPage = () => {

    const [matches, setMatches] = useState([]);
    const {teamName, year} = useParams();

    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(`http://localhost:8080/team/${teamName}/matches?year=${year}`);
                const data = await response.json();
                console.log(data)
                setMatches(data);

            }
            fetchMatches();

        }, [teamName, year]
    );

    return (
        <div className="MatchPage">
            <div className="year-selector">
                <h3>Year</h3>
                <YearSelector teamName={teamName}/>
            </div>
            <div>
                <h3 className="page-heading">{teamName} Results {year}</h3>
                {
                    matches.map(m => <MatchDetailCard match={m} teamName={teamName}/>)
                }
            </div>
        </div>
    );
}
