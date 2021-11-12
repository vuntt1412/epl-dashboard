package com.vuntt1412.epldashboard.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.vuntt1412.epldashboard.domain.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {

    List<Match> findByHomeTeamIdOrAwayTeamIdOrderByDateDesc(Long homeTeamId, Long awayTeamId, Pageable pageable);

}
