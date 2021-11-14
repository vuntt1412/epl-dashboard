package com.vuntt1412.epldashboard.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vuntt1412.epldashboard.domain.Match;
import com.vuntt1412.epldashboard.domain.Team;
import com.vuntt1412.epldashboard.projection.MatchStatsOnly;
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

    @Override
    public Map<String, Map<String, Integer>> calculateTeamStats(Long teamId) {
        List<MatchStatsOnly> historicalMatches = matchRepository.findByHomeTeamIdOrAwayTeamId(teamId, teamId);
        return historicalMatches.stream()
                .collect(Collectors.groupingBy(MatchStatsOnly::getSeason))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> this.calculateMatchesStats(e.getValue(), teamId)));
    }

    private Map<String, Integer> calculateMatchesStats(List<MatchStatsOnly> matches, Long teamId) {
        Map<String, Integer> matchesStats = new HashMap<>();
        matches.forEach(m -> {
            if (teamId.equals(m.getHomeTeamId())) {
                if (m.getHomeTeamGoal() > m.getAwayTeamGoal()) {
                    matchesStats.put("Wins", matchesStats.getOrDefault("Wins", 0) + 1);
                } else if (m.getHomeTeamGoal() < m.getAwayTeamGoal()) {
                    matchesStats.put("Losses", matchesStats.getOrDefault("Losses", 0) + 1);
                } else {
                    matchesStats.put("Ties", matchesStats.getOrDefault("Ties", 0) + 1);
                }
            } else {
                if (m.getHomeTeamGoal() < m.getAwayTeamGoal()) {
                    matchesStats.put("Wins", matchesStats.getOrDefault("Wins", 0) + 1);
                } else if (m.getHomeTeamGoal() > m.getAwayTeamGoal()) {
                    matchesStats.put("Losses", matchesStats.getOrDefault("Losses", 0) + 1);
                } else {
                    matchesStats.put("Ties", matchesStats.getOrDefault("Ties", 0) + 1);
                }
            }
        });

        return matchesStats;
    }

}
