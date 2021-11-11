package com.vuntt1412.epldashboard.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Player {
    @Id
    private Long id;
    private Long playerId;
    private String playerName;
    private LocalDate birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
