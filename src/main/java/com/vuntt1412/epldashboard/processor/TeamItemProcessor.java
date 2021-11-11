package com.vuntt1412.epldashboard.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.vuntt1412.epldashboard.domain.Team;
import com.vuntt1412.epldashboard.domain.staging.StagTeam;


public class TeamItemProcessor implements ItemProcessor<StagTeam, Team> {

    private static final Logger log = LoggerFactory.getLogger(TeamItemProcessor.class);

    @Override
    public Team process(final StagTeam stagTeam) throws Exception {
        Team team = new Team();
        team.setId(Long.valueOf(stagTeam.getId()));
        team.setTeamId(Long.valueOf(stagTeam.getTeam_id()));
        team.setTeamLongName(stagTeam.getTeam_long_name());
        team.setTeamShortName(stagTeam.getTeam_short_name());
        return team;
    }

}