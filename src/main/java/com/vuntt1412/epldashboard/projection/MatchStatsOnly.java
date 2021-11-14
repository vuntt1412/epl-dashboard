package com.vuntt1412.epldashboard.projection;

public interface MatchStatsOnly {

    Long getId();

    String getSeason();

    Long getHomeTeamId();

    Long getAwayTeamId();

    Integer getHomeTeamGoal();

    Integer getAwayTeamGoal();

}
