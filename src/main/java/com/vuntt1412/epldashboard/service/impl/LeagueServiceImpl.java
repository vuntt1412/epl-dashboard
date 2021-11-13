package com.vuntt1412.epldashboard.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vuntt1412.epldashboard.domain.League;
import com.vuntt1412.epldashboard.projection.LeagueNameOnly;
import com.vuntt1412.epldashboard.repository.LeagueRepository;
import com.vuntt1412.epldashboard.service.LeagueService;

@Service
public class LeagueServiceImpl implements LeagueService {
    private final LeagueRepository leagueRepository;

    public LeagueServiceImpl(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    public Optional<League> getLeague(Long leagueId) {
        return leagueRepository.findById(leagueId);
    }

    @Override
    public Map<Long, String> getLeagueByIds(List<Long> leagueIds) {
        return leagueRepository.findByIdIn(leagueIds)
                .stream()
                .collect(Collectors.toMap(LeagueNameOnly::getId, LeagueNameOnly::getName));
    }
}
