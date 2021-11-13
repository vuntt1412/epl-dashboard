package com.vuntt1412.epldashboard.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vuntt1412.epldashboard.domain.Match;
import com.vuntt1412.epldashboard.domain.Team;
import com.vuntt1412.epldashboard.repository.MatchRepository;
import com.vuntt1412.epldashboard.repository.TeamRepository;
import com.vuntt1412.epldashboard.service.MatchService;

@Service
public class MatchServiceImpl implements MatchService {
    private static final String SEASON_FORMAT = "%s/%s";

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    public MatchServiceImpl(MatchRepository matchRepository, TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Match> getLatestMatches(Long teamId, Integer number) {
        Pageable pageable = PageRequest.of(0, number);
        return matchRepository.findByHomeTeamIdOrAwayTeamIdOrderByDateDesc(teamId, teamId, pageable);
    }

    @Override
    public List<Match> getMatchesOfTeamInSeason(String teamName, int year) {
        Long teamId = Optional.ofNullable(teamRepository.findByTeamLongName(teamName)).map(Team::getTeamId).orElse(null);
        String season = String.format(SEASON_FORMAT, year, year + 1);
        return matchRepository.findByHomeTeamIdOrAwayTeamIdAndSeasonOrderByDateDesc(teamId, season);
    }
}
