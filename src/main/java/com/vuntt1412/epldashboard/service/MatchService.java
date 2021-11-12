package com.vuntt1412.epldashboard.service;

import java.util.List;

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
}
