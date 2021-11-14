package com.vuntt1412.epldashboard.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.*;

import com.vuntt1412.epldashboard.domain.League;
import com.vuntt1412.epldashboard.domain.Match;
import com.vuntt1412.epldashboard.domain.Team;
import com.vuntt1412.epldashboard.service.LeagueService;
import com.vuntt1412.epldashboard.service.MatchService;
import com.vuntt1412.epldashboard.service.TeamService;

@RestController
@CrossOrigin
public class TeamController {

    private final TeamService teamService;
    private final MatchService matchService;
    private final LeagueService leagueService;

    public TeamController(TeamService teamService, MatchService matchService, LeagueService leagueService) {
        this.teamService = teamService;
        this.matchService = matchService;
        this.leagueService = leagueService;
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = teamService.getTeam(teamName);
        List<Match> latestMatches = matchService.getLatestMatches(team.getTeamId(), 5);
        loadTransientMatchFields(latestMatches);
        team.setLatestMatches(latestMatches);

        Map<String, Map<String, Integer>> teamStats = matchService.calculateTeamStats(team.getTeamId());
        team.setTotalMatches(getNumberOfMatchesByCondition(teamStats, null));
        team.setTotalLosses(getNumberOfMatchesByCondition(teamStats, "Losses"));
        team.setTotalWins(getNumberOfMatchesByCondition(teamStats, "Wins"));

        return team;
    }

    private int getNumberOfMatchesByCondition(Map<String, Map<String, Integer>> teamStats, String result) {
        return teamStats.values()
                .stream()
                .flatMap(map -> map.entrySet().stream())
                .filter(entry -> result == null || result.equals(entry.getKey()))
                .map(Map.Entry::getValue)
                .mapToInt(Integer::valueOf)
                .sum();
    }

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesOfTeam(@PathVariable String teamName, @RequestParam int year) {
        List<Match> matches = matchService.getMatchesOfTeamInSeason(teamName, year);
        loadTransientMatchFields(matches);

        return matches;
    }

    private void loadTransientMatchFields(List<Match> matches) {
        List<Long> teamIds = Stream.concat(
                matches.stream().map(Match::getHomeTeamId),
                matches.stream().map(Match::getAwayTeamId))
                .collect(Collectors.toList());
        Map<Long, String> teamNameByIds = teamService.getTeamNameByIds(teamIds);
        String leagueName = leagueService.getLeague(matches.stream().map(Match::getLeagueId).findFirst().orElse(null))
                .map(League::getName).orElse(null);

        matches.stream().forEach(match -> {
            match.setHomeTeamName(teamNameByIds.get(match.getHomeTeamId()));
            match.setAwayTeamName(teamNameByIds.get(match.getAwayTeamId()));
            match.setLeagueName(leagueName);
        });
    }
}
