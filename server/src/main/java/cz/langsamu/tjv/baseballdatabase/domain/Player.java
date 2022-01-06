package cz.langsamu.tjv.baseballdatabase.domain;


import javax.persistence.*;
import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playerID;
    private String firstName;
    private String secondName;
    private BaseballPositions baseballPosition;
    private LocalDate dateOfBirth;
    @ManyToOne
    @JoinColumn
    private Team team;

    @ManyToMany
    @JoinTable(name = "player_award_table",
            joinColumns=@JoinColumn(name = "awardid"),
            inverseJoinColumns = @JoinColumn(name = "playerid"))
    public Set<Award> awards = new HashSet<>();

    public Player(){

    }

    public Player(String firstName, String secondName, BaseballPositions baseballPosition, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.baseballPosition = baseballPosition;
        this.dateOfBirth = dateOfBirth;
        this.awards = Collections.EMPTY_SET;
    }

    public Player(Long playerID, String firstName, String secondName, BaseballPositions baseballPosition, LocalDate dateOfBirth) {
        this.playerID = playerID;
        this.firstName = firstName;
        this.secondName = secondName;
        this.baseballPosition = baseballPosition;
        this.dateOfBirth = dateOfBirth;
        this.awards = Collections.EMPTY_SET;
    }

    public Long getPlayerID() {
        return playerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public BaseballPositions getBaseballPosition() {
        return baseballPosition;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Team getTeam() {
        return team;
    }

    public Set<Award> getAwards() {
        return awards;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setBaseballPosition(BaseballPositions baseballPosition) {
        this.baseballPosition = baseballPosition;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setTeam(Team team) {
        this.team = Objects.requireNonNull(team);
    }

    public void setAwards(Set<Award> awards) {
        this.awards = awards;
    }

    public void addAward(Award byIdAward) {
        awards.add(byIdAward);
    }

    public Set<String> getAwardsName(){
        Set<String> awardNames = new HashSet<>();
        for (Award award : awards) {
            awardNames.add(award.getName()+" ID: "+award.getAwardID());
        }
        return awardNames;
    }




}
