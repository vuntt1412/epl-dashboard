package com.vuntt1412.epldashboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vuntt1412.epldashboard.domain.Team;
import com.vuntt1412.epldashboard.service.MatchService;
import com.vuntt1412.epldashboard.service.TeamService;

@RestController
public class TeamController {

    private final TeamService teamService;
    private final MatchService matchService;

    public TeamController(TeamService teamService, MatchService matchService) {
        this.teamService = teamService;
        this.matchService = matchService;
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = teamService.getTeam(teamName);
        team.setLatestMatches(matchService.getLatestMatches(team.getTeamId(), 5));

        return team;
    }
}
