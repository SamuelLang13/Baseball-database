package cz.langsamu.tjv.baseballdatabase.api.dto;

import com.fasterxml.jackson.annotation.JsonView;
import cz.langsamu.tjv.baseballdatabase.domain.Leagues;
import cz.langsamu.tjv.baseballdatabase.domain.Player;

import java.util.Date;
import java.util.Set;

public class TeamDTO {

    private Long teamID;
    @JsonView(TeamView.Public.class)
    private String name;
    @JsonView(TeamView.Public.class)
    private Leagues league;
    @JsonView(TeamView.Public.class)
    private int yearOfEstablish;
    @JsonView(TeamView.Public.class)
    private int numOfWorldSeriesWin;

    public TeamDTO(Long teamID, String name, Leagues league, int yearOfEstablish, int numOfWorldSeriesWin) {
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

    public int getYearOfEstablish() {
        return yearOfEstablish;
    }

    public void setYearOfEstablish(int yearOfEstablish) {
        this.yearOfEstablish = yearOfEstablish;
    }

    public int getNumOfWorldSeriesWin() {
        return numOfWorldSeriesWin;
    }

    public void setNumOfWorldSeriesWin(int numOfWorldSeriesWin) {
        this.numOfWorldSeriesWin = numOfWorldSeriesWin;
    }


    @Override
    public String toString() {
        return "TeamDTO{" +
                "teamID=" + teamID +
                ", name='" + name + '\'' +
                ", league=" + league +
                ", yearOfEstablish=" + yearOfEstablish +
                ", numOfWorldSeriesWin=" + numOfWorldSeriesWin +
                '}';
    }

}
