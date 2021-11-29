package cz.langsamu.tjv.baseballdatabase.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import cz.langsamu.tjv.baseballdatabase.domain.Award;
import cz.langsamu.tjv.baseballdatabase.domain.BaseballPositions;
import cz.langsamu.tjv.baseballdatabase.domain.Team;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class PlayerDTO {

    @JsonView(PlayerView.Public.class)
    private Long playerID;
    @JsonView(PlayerView.Public.class)
    private String firstName;
    @JsonView(PlayerView.Public.class)
    private String secondName;
    @JsonView(PlayerView.Public.class)
    private BaseballPositions baseballPosition;
    @JsonView(PlayerView.Public.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d.M.yyyy")
    private LocalDate dateOfBirth;
    @JsonView(PlayerView.Public.class)
    private Long teamID;
    @JsonView(PlayerView.Public.class)
    private Team team;
    @JsonView(PlayerView.Public.class)
    private Set<Award> awards;
    private List<Long> awardIDs;

    public PlayerDTO(Long playerID, String firstName, String secondName, BaseballPositions baseballPosition, LocalDate dateOfBirth, Long teamID) {
        this.playerID = playerID;
        this.firstName = firstName;
        this.secondName = secondName;
        this.baseballPosition = baseballPosition;
        this.dateOfBirth = dateOfBirth;
        this.teamID = teamID;
    }

    public PlayerDTO(Long playerID, String firstName, String secondName, BaseballPositions baseballPosition, LocalDate dateOfBirth,Long teamID, Team team) {
        this.playerID = playerID;
        this.firstName = firstName;
        this.secondName = secondName;
        this.baseballPosition = baseballPosition;
        this.dateOfBirth = dateOfBirth;
        this.teamID = teamID;
        this.team = team;
    }

    public PlayerDTO(Long playerID,String firstName, String secondName, BaseballPositions baseballPosition, LocalDate dateOfBirth, Long teamID, Team team, Set<Award> awards) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.baseballPosition = baseballPosition;
        this.dateOfBirth = dateOfBirth;
        this.teamID = teamID;
        this.team = team;
        this.awards = awards;
    }

    public PlayerDTO(){

    }

    public PlayerDTO(Long playerID, String firstName, String secondName, BaseballPositions baseballPosition, LocalDate dateOfBirth, long teamID, Team team, Set<Award> awards) {

        this.playerID = playerID;
        this.firstName = firstName;
        this.secondName = secondName;
        this.baseballPosition = baseballPosition;
        this.dateOfBirth = dateOfBirth;
        this.teamID = teamID;
        this.team = team;
        this.awards = awards;

    }

    public String getFirstName() {
        return firstName;
    }

    public Long getPlayerID() {
        return playerID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public BaseballPositions getBaseballPosition() {
        return baseballPosition;
    }

    public void setBaseballPosition(BaseballPositions baseballPosition) {
        this.baseballPosition = baseballPosition;
    }

    public void setPlayerID(Long playerID) {
        this.playerID = playerID;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getTeamID() {
        return teamID;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

//    public List<Long> getAwardIDs() {
//        return awardIDs;
//    }
//
//    public void setAwards(Set<Award> awards) {
//        this.awards = awards;
//    }
//
    public Set<Award> getAwards() {
        return awards;
    }



}
