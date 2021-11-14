package com.vuntt1412.epldashboard.processor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.vuntt1412.epldashboard.domain.Match;
import com.vuntt1412.epldashboard.domain.staging.StagMatch;


public class MatchItemProcessor implements ItemProcessor<StagMatch, Match> {

    private static final Logger log = LoggerFactory.getLogger(MatchItemProcessor.class);

    @Override
    public Match process(final StagMatch stagMatch) throws Exception {
        Match match = new Match();
        match.setId(Long.valueOf(stagMatch.getId()));
        match.setCountryId(Long.valueOf(stagMatch.getCountry_id()));
        match.setLeagueId(Long.valueOf(stagMatch.getLeague_id()));
        match.setSeason(stagMatch.getSeason());
        match.setStage(Integer.valueOf(stagMatch.getStage()));
        match.setDate(LocalDate.parse(stagMatch.getDate(), DateTimeFormatter.ofPattern("dd.MM.yy hh:mm")));
        match.setHomeTeamId(Long.valueOf(stagMatch.getHome_team_api_id()));
        match.setAwayTeamId(Long.valueOf(stagMatch.getAway_team_api_id()));
        match.setHomeTeamGoal(Integer.valueOf(stagMatch.getHome_team_goal()));
        match.setAwayTeamGoal(Integer.valueOf(stagMatch.getAway_team_goal()));
        match.setHomePlayer1(convertToLong(stagMatch.getHome_player_1()));
        match.setHomePlayer2(convertToLong(stagMatch.getHome_player_2()));
        match.setHomePlayer3(convertToLong(stagMatch.getHome_player_3()));
        match.setHomePlayer4(convertToLong(stagMatch.getHome_player_4()));
        match.setHomePlayer5(convertToLong(stagMatch.getHome_player_5()));
        match.setHomePlayer6(convertToLong(stagMatch.getHome_player_6()));
        match.setHomePlayer7(convertToLong(stagMatch.getHome_player_7()));
        match.setHomePlayer8(convertToLong(stagMatch.getHome_player_8()));
        match.setHomePlayer9(convertToLong(stagMatch.getHome_player_9()));
        match.setHomePlayer10(convertToLong(stagMatch.getHome_player_10()));
        match.setHomePlayer11(convertToLong(stagMatch.getHome_player_11()));
        match.setAwayPlayer1(convertToLong(stagMatch.getAway_player_1()));
        match.setAwayPlayer2(convertToLong(stagMatch.getAway_player_2()));
        match.setAwayPlayer3(convertToLong(stagMatch.getAway_player_3()));
        match.setAwayPlayer4(convertToLong(stagMatch.getAway_player_4()));
        match.setAwayPlayer5(convertToLong(stagMatch.getAway_player_5()));
        match.setAwayPlayer6(convertToLong(stagMatch.getAway_player_6()));
        match.setAwayPlayer7(convertToLong(stagMatch.getAway_player_7()));
        match.setAwayPlayer8(convertToLong(stagMatch.getAway_player_8()));
        match.setAwayPlayer9(convertToLong(stagMatch.getAway_player_9()));
        match.setAwayPlayer10(convertToLong(stagMatch.getAway_player_10()));
        match.setAwayPlayer11(convertToLong(stagMatch.getAway_player_11()));
        log.info("Converting (" + stagMatch + ")");
        return match;
    }

    private Long convertToLong(String s) {
        return Optional.ofNullable(s)
                .filter(StringUtils::isNotEmpty)
                .map(Long::valueOf)
                .orElse(null);
    }

}