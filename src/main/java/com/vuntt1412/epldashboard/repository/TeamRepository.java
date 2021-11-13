package com.vuntt1412.epldashboard.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vuntt1412.epldashboard.domain.Team;
import com.vuntt1412.epldashboard.projection.TeamNamesOnly;

public interface TeamRepository extends CrudRepository<Team, Long> {

    Team findByTeamLongName(String teamName);

    List<TeamNamesOnly> findByTeamIdIn(List<Long> teamIds);

}
