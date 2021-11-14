import {React} from 'react';
import {Link} from 'react-router-dom';

import './TeamTitle.scss';

export const TeamTitle = ({teamName}) => {

    return (
        <div className="TeamTitle">
            <h3>
                <Link to={`/teams/${teamName}`}>
                    {teamName}
                </Link>
            </h3>
        </div>
    )
}