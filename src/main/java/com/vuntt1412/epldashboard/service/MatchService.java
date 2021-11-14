package com.vuntt1412.epldashboard.service;

import java.util.List;
import java.util.Map;

import com.vuntt1412.epldashboard.domain.Match;

public interface MatchService {
    /**
     * get number of recent matches of the team
     *
     * @param teamId
     * @param number
     * @return
     */
    List<Match> getLatestMatches(Long teamId, Integer number);

    /**
     * get all of the matches of the team in the given season. E.g. 2015 -> 2015/2016
     *
     * @param teamName
     * @param year
     * @return
     */
    List<Match> getMatchesOfTeamInSeason(String teamName, int year);

    /**
     * calculate team stats by historical matches
     *
     * @param teamId
     * @return
     */
    Map<String, Map<String, Integer>> calculateTeamStats(Long teamId);
}
