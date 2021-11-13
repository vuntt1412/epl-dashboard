package com.vuntt1412.epldashboard.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vuntt1412.epldashboard.domain.League;
import com.vuntt1412.epldashboard.projection.LeagueNameOnly;

public interface LeagueRepository extends CrudRepository<League, Long> {

    List<LeagueNameOnly> findByIdIn(List<Long> leagueIds);

}
