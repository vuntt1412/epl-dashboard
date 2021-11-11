package com.vuntt1412.epldashboard.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Team {
    @Id
    private Long id;
    private Long teamId;
    private String teamLongName;
    private String teamShortName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamLongName() {
        return teamLongName;
    }

    public void setTeamLongName(String teamLongName) {
        this.teamLongName = teamLongName;
    }

    public String getTeamShortName() {
        return teamShortName;
    }

    public void setTeamShortName(String teamShortName) {
        this.teamShortName = teamShortName;
    }
}
