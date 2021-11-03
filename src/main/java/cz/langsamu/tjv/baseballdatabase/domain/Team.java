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
    //@Column(nullable = false)
    private String name;
    //@Column(nullable = false)
    private Leagues league;
    @Column(columnDefinition = "number", nullable = false)
    private Date yearOfEstablish;
    @Column(columnDefinition = "number", nullable = false)
    private int numOfWorldSeriesWin;

    public Team(Long id, String name, Leagues league, Date yearOfEstablish, int numOfWorldSeriesWin) {
        this.teamID = id;
        this.name = name;
        this.league = league;
        this.yearOfEstablish = yearOfEstablish;
        this.numOfWorldSeriesWin = numOfWorldSeriesWin;
    }

    public Team(Long id, String name, Leagues league, Date yearOfEstablish) {
        this.teamID = id;
        this.name = name;
        this.league = league;
        this.yearOfEstablish = yearOfEstablish;
        this.numOfWorldSeriesWin=0;
    }

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

}
