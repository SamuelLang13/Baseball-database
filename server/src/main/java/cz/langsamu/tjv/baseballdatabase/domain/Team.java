package cz.langsamu.tjv.baseballdatabase.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long teamID;
    private String name;
    private Leagues league;
    private int yearOfEstablish;
    private int numOfWorldSeriesWin;
    @OneToMany
    @JoinColumn
    private Set<Player> players = new HashSet<>();

    public Team(String name, Leagues league, int yearOfEstablish, int numOfWorldSeriesWin) {
        this.name = name;
        this.league = league;
        this.yearOfEstablish = yearOfEstablish;
        this.numOfWorldSeriesWin = numOfWorldSeriesWin;
        this.players = Collections.EMPTY_SET;
    }

    public Team(String name, Leagues league, int yearOfEstablish) {
        this.name = name;
        this.league = league;
        this.yearOfEstablish = yearOfEstablish;
        this.numOfWorldSeriesWin=0;
        this.players = Collections.EMPTY_SET;
    }

    public Team(Long teamID, String name, Leagues league, int yearOfEstablish) {
        this.teamID = teamID;
        this.name = name;
        this.league = league;
        this.yearOfEstablish = yearOfEstablish;
        this.numOfWorldSeriesWin=0;
        this.players = Collections.EMPTY_SET;
    }

    public Team(){

    }

    public Long getTeamID() {
        return teamID;
    }

    public String getName() {
        return name;
    }

    public Leagues getLeague() {
        return league;
    }

    public int getYearOfEstablish() {
        return yearOfEstablish;
    }

    public int getNumOfWorldSeriesWin() {
        return numOfWorldSeriesWin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeague(Leagues league) {
        this.league = league;
    }

    public void setYearOfEstablish(int yearOfEstablish) {
        this.yearOfEstablish = yearOfEstablish;
    }

    public void setNumOfWorldSeriesWin(int numOfWorldSeriesWin) {
        this.numOfWorldSeriesWin = numOfWorldSeriesWin;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public Set<String> getPlayersName(){
        Set<String> surNames = new HashSet<>();
        for (Player player : players) {
            surNames.add(player.getFirstName()+" "+player.getSecondName()+" ID: "+player.getPlayerID());
        }
        return surNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return yearOfEstablish == team.yearOfEstablish && numOfWorldSeriesWin == team.numOfWorldSeriesWin && Objects.equals(teamID, team.teamID) && Objects.equals(name, team.name) && league == team.league;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamID, name, league, yearOfEstablish, numOfWorldSeriesWin);
    }
}
