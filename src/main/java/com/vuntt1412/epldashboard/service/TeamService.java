package com.vuntt1412.epldashboard.service;

import java.util.List;
import java.util.Map;

import com.vuntt1412.epldashboard.domain.Team;

public interface TeamService {
    /**
     * get team by its name
     *
     * @param teamName
     * @return
     */
    Team getTeam(String teamName);

    /**
     * get team names in team ids
     *
     * @param teamIds
     * @return
     */
    Map<Long, String> getTeamNameByIds(List<Long> teamIds);
}
