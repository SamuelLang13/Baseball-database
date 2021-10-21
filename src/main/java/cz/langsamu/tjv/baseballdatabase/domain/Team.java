package cz.langsamu.tjv.baseballdatabase.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Team {
    @Id
    @SequenceGenerator(
            name="team_sequence",
            sequenceName = "team_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "team_sequence"

    )
    private Long id;
    private String name;
    private Leagues league;
    private Date yearOfEstablish;
    private int numOfWorldSeriesWin;

    public Team(Long id, String name, Leagues league, Date yearOfEstablish, int numOfWorldSeriesWin) {
        this.id = id;
        this.name = name;
        this.league = league;
        this.yearOfEstablish = yearOfEstablish;
        this.numOfWorldSeriesWin = numOfWorldSeriesWin;
    }

    public Team(Long id, String name, Leagues league, Date yearOfEstablish) {
        this.id = id;
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

    public Long getId() {
        return id;
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
}
