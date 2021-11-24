package cz.langsamu.tjv.baseballdatabase.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long teamID;
    private String name;
    private Leagues league;
    private Date yearOfEstablish;
    private int numOfWorldSeriesWin;
    @OneToMany
    @JoinColumn(name = "playerid")
    private Set<Player> players;

    public Team(String name, Leagues league, Date yearOfEstablish, int numOfWorldSeriesWin) {
        this.name = name;
        this.league = league;
        this.yearOfEstablish = yearOfEstablish;
        this.numOfWorldSeriesWin = numOfWorldSeriesWin;
    }

    public Team(String name, Leagues league, Date yearOfEstablish) {
        this.name = name;
        this.league = league;
        this.yearOfEstablish = yearOfEstablish;
        this.numOfWorldSeriesWin=0;
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

    public Date getYearOfEstablish() {
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

    public void setYearOfEstablish(Date yearOfEstablish) {
        this.yearOfEstablish = yearOfEstablish;
    }

    public void setNumOfWorldSeriesWin(int numOfWorldSeriesWin) {
        this.numOfWorldSeriesWin = numOfWorldSeriesWin;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

}
