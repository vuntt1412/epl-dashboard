package com.vuntt1412.epldashboard.service.impl;

import org.springframework.stereotype.Service;

import com.vuntt1412.epldashboard.domain.Team;
import com.vuntt1412.epldashboard.repository.TeamRepository;
import com.vuntt1412.epldashboard.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team getTeam(String teamName) {
        return teamRepository.findByTeamLongName(teamName);
    }
}
