package com.vuntt1412.epldashboard.service.impl;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vuntt1412.epldashboard.domain.Match;
import com.vuntt1412.epldashboard.repository.MatchRepository;
import com.vuntt1412.epldashboard.service.MatchService;

@Service
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;

    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public List<Match> getLatestMatches(Long teamId, Integer number) {
        Pageable pageable = PageRequest.of(0, number);
        return matchRepository.findByHomeTeamIdOrAwayTeamIdOrderByDateDesc(teamId, teamId, pageable);
    }
}
