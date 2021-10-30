package cz.langsamu.tjv.baseballdatabase.api.dto;

import com.fasterxml.jackson.annotation.JsonView;
import cz.langsamu.tjv.baseballdatabase.domain.Leagues;

import java.util.Date;

public class TeamDTO {

    private Long teamID;
    @JsonView(TeamView.Public.class)
    private String name;
    @JsonView(TeamView.Public.class)
    private Leagues league;
    @JsonView(TeamView.Public.class)
    private Date yearOfEstablish;
    @JsonView(TeamView.Public.class)
    private int numOfWorldSeriesWin;

    public TeamDTO(Long teamID, String name, Leagues league, Date yearOfEstablish, int numOfWorldSeriesWin) {
        this.teamID = teamID;
        this.name = name;
        this.league = league;
        this.yearOfEstablish = yearOfEstablish;
        this.numOfWorldSeriesWin = numOfWorldSeriesWin;
    }

    public TeamDTO(){

    }

    public Long getTeamID() {
        return teamID;
    }

    public void setTeamID(Long teamID) {
        this.teamID = teamID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Leagues getLeague() {
        return league;
    }

    public void setLeague(Leagues league) {
        this.league = league;
    }

    public Date getYearOfEstablish() {
        return yearOfEstablish;
    }

    public void setYearOfEstablish(Date yearOfEstablish) {
        this.yearOfEstablish = yearOfEstablish;
    }

    public int getNumOfWorldSeriesWin() {
        return numOfWorldSeriesWin;
    }

    public void setNumOfWorldSeriesWin(int numOfWorldSeriesWin) {
        this.numOfWorldSeriesWin = numOfWorldSeriesWin;
    }
}
