package com.vuntt1412.epldashboard.domain.staging;

public class StagTeam {
    private String id;
    private String team_id;
    private String team_long_name;
    private String team_short_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getTeam_long_name() {
        return team_long_name;
    }

    public void setTeam_long_name(String team_long_name) {
        this.team_long_name = team_long_name;
    }

    public String getTeam_short_name() {
        return team_short_name;
    }

    public void setTeam_short_name(String team_short_name) {
        this.team_short_name = team_short_name;
    }
}
