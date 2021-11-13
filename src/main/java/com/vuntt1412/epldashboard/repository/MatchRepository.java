package com.vuntt1412.epldashboard.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vuntt1412.epldashboard.domain.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {

    List<Match> findByHomeTeamIdOrAwayTeamIdOrderByDateDesc(Long homeTeamId, Long awayTeamId, Pageable pageable);

    @Query(value = "select m.* from MATCH m where (m.home_team_id = :teamId or m.away_team_id = :teamId) and m.season = :season order by date desc", nativeQuery = true)
    List<Match> findByHomeTeamIdOrAwayTeamIdAndSeasonOrderByDateDesc(@Param("teamId") Long teamId, @Param("season") String season);

}
