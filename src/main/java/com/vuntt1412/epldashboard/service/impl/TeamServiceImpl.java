package com.vuntt1412.epldashboard.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vuntt1412.epldashboard.domain.Team;
import com.vuntt1412.epldashboard.projection.TeamNamesOnly;
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

    @Override
    public Map<Long, String> getTeamNameByIds(List<Long> teamIds) {
        return teamRepository.findByTeamIdIn(teamIds)
                .stream()
                .collect(Collectors.toMap(TeamNamesOnly::getTeamId, TeamNamesOnly::getTeamLongName));
    }
}
