package com.vuntt1412.epldashboard.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Match {
    @Id
    private Long id;
    private Long countryId;
    private Long leagueId;
    private String season;
    private Integer stage;
    private LocalDate date;
    private Long homeTeamId;
    private Long awayTeamId;
    private Integer homeTeamGoal;
    private Integer awayTeamGoal;
    private Long homePlayer1;
    private Long homePlayer2;
    private Long homePlayer3;
    private Long homePlayer4;
    private Long homePlayer5;
    private Long homePlayer6;
    private Long homePlayer7;
    private Long homePlayer8;
    private Long homePlayer9;
    private Long homePlayer10;
    private Long homePlayer11;
    private Long awayPlayer1;
    private Long awayPlayer2;
    private Long awayPlayer3;
    private Long awayPlayer4;
    private Long awayPlayer5;
    private Long awayPlayer6;
    private Long awayPlayer7;
    private Long awayPlayer8;
    private Long awayPlayer9;
    private Long awayPlayer10;
    private Long awayPlayer11;
    private Integer goal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Long homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Long getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Long awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public Integer getHomeTeamGoal() {
        return homeTeamGoal;
    }

    public void setHomeTeamGoal(Integer homeTeamGoal) {
        this.homeTeamGoal = homeTeamGoal;
    }

    public Integer getAwayTeamGoal() {
        return awayTeamGoal;
    }

    public void setAwayTeamGoal(Integer awayTeamGoal) {
        this.awayTeamGoal = awayTeamGoal;
    }

    public Long getHomePlayer1() {
        return homePlayer1;
    }

    public void setHomePlayer1(Long homePlayer1) {
        this.homePlayer1 = homePlayer1;
    }

    public Long getHomePlayer2() {
        return homePlayer2;
    }

    public void setHomePlayer2(Long homePlayer2) {
        this.homePlayer2 = homePlayer2;
    }

    public Long getHomePlayer3() {
        return homePlayer3;
    }

    public void setHomePlayer3(Long homePlayer3) {
        this.homePlayer3 = homePlayer3;
    }

    public Long getHomePlayer4() {
        return homePlayer4;
    }

    public void setHomePlayer4(Long homePlayer4) {
        this.homePlayer4 = homePlayer4;
    }

    public Long getHomePlayer5() {
        return homePlayer5;
    }

    public void setHomePlayer5(Long homePlayer5) {
        this.homePlayer5 = homePlayer5;
    }

    public Long getHomePlayer6() {
        return homePlayer6;
    }

    public void setHomePlayer6(Long homePlayer6) {
        this.homePlayer6 = homePlayer6;
    }

    public Long getHomePlayer7() {
        return homePlayer7;
    }

    public void setHomePlayer7(Long homePlayer7) {
        this.homePlayer7 = homePlayer7;
    }

    public Long getHomePlayer8() {
        return homePlayer8;
    }

    public void setHomePlayer8(Long homePlayer8) {
        this.homePlayer8 = homePlayer8;
    }

    public Long getHomePlayer9() {
        return homePlayer9;
    }

    public void setHomePlayer9(Long homePlayer9) {
        this.homePlayer9 = homePlayer9;
    }

    public Long getHomePlayer10() {
        return homePlayer10;
    }

    public void setHomePlayer10(Long homePlayer10) {
        this.homePlayer10 = homePlayer10;
    }

    public Long getHomePlayer11() {
        return homePlayer11;
    }

    public void setHomePlayer11(Long homePlayer11) {
        this.homePlayer11 = homePlayer11;
    }

    public Long getAwayPlayer1() {
        return awayPlayer1;
    }

    public void setAwayPlayer1(Long awayPlayer1) {
        this.awayPlayer1 = awayPlayer1;
    }

    public Long getAwayPlayer2() {
        return awayPlayer2;
    }

    public void setAwayPlayer2(Long awayPlayer2) {
        this.awayPlayer2 = awayPlayer2;
    }

    public Long getAwayPlayer3() {
        return awayPlayer3;
    }

    public void setAwayPlayer3(Long awayPlayer3) {
        this.awayPlayer3 = awayPlayer3;
    }

    public Long getAwayPlayer4() {
        return awayPlayer4;
    }

    public void setAwayPlayer4(Long awayPlayer4) {
        this.awayPlayer4 = awayPlayer4;
    }

    public Long getAwayPlayer5() {
        return awayPlayer5;
    }

    public void setAwayPlayer5(Long awayPlayer5) {
        this.awayPlayer5 = awayPlayer5;
    }

    public Long getAwayPlayer6() {
        return awayPlayer6;
    }

    public void setAwayPlayer6(Long awayPlayer6) {
        this.awayPlayer6 = awayPlayer6;
    }

    public Long getAwayPlayer7() {
        return awayPlayer7;
    }

    public void setAwayPlayer7(Long awayPlayer7) {
        this.awayPlayer7 = awayPlayer7;
    }

    public Long getAwayPlayer8() {
        return awayPlayer8;
    }

    public void setAwayPlayer8(Long awayPlayer8) {
        this.awayPlayer8 = awayPlayer8;
    }

    public Long getAwayPlayer9() {
        return awayPlayer9;
    }

    public void setAwayPlayer9(Long awayPlayer9) {
        this.awayPlayer9 = awayPlayer9;
    }

    public Long getAwayPlayer10() {
        return awayPlayer10;
    }

    public void setAwayPlayer10(Long awayPlayer10) {
        this.awayPlayer10 = awayPlayer10;
    }

    public Long getAwayPlayer11() {
        return awayPlayer11;
    }

    public void setAwayPlayer11(Long awayPlayer11) {
        this.awayPlayer11 = awayPlayer11;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }
}
