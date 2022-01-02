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
    private Set<String> players;

    public TeamDTO(Long teamID, String name, Leagues league, int yearOfEstablish, int numOfWorldSeriesWin, Set<String> players) {
        this.teamID = teamID;
        this.name = name;
        this.league = league;
        this.yearOfEstablish = yearOfEstablish;
        this.numOfWorldSeriesWin = numOfWorldSeriesWin;
        this.players = players;
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

    public Set<String> getPlayers() {
        return players;
    }

    public void setPlayers(Set<String> players) {
        this.players = players;
    }
}
