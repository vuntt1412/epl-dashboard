package com.vuntt1412.epldashboard.repository;

import org.springframework.data.repository.CrudRepository;

import com.vuntt1412.epldashboard.domain.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

    Team findByTeamLongName(String teamName);

}
