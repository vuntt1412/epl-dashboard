package com.vuntt1412.epldashboard.service;

import com.vuntt1412.epldashboard.domain.Team;

public interface TeamService {
    /**
     * get team by its name
     *
     * @param teamName
     * @return
     */
    Team getTeam(String teamName);
}
