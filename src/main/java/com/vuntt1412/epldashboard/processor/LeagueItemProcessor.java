package com.vuntt1412.epldashboard.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.vuntt1412.epldashboard.domain.League;
import com.vuntt1412.epldashboard.domain.staging.StagLeague;


public class LeagueItemProcessor implements ItemProcessor<StagLeague, League> {

    private static final Logger log = LoggerFactory.getLogger(LeagueItemProcessor.class);

    @Override
    public League process(final StagLeague stagLeague) throws Exception {
        League league = new League();
        league.setId(Long.valueOf(stagLeague.getId()));
        league.setCountryId(Long.valueOf(stagLeague.getCountry_id()));
        league.setName(stagLeague.getName());
        log.info("Converting (" + stagLeague + ")");
        return league;
    }

}