package cz.langsamu.tjv.baseballdatabase.client_console.model;

import java.util.Set;

public class TeamDTO {

    private Long teamID;
    private String name;
    private Leagues league;
    private int yearOfEstablish;
    private int numOfWorldSeriesWin;
    private Set<String> players;

    public TeamDTO() {
    }

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
