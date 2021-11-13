package com.vuntt1412.epldashboard.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.vuntt1412.epldashboard.domain.League;

public interface LeagueService {

    Optional<League> getLeague(Long leagueId);

    Map<Long, String> getLeagueByIds(List<Long> leagueIds);

}
