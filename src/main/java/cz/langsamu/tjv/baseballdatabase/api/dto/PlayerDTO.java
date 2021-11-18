package cz.langsamu.tjv.baseballdatabase.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import cz.langsamu.tjv.baseballdatabase.domain.Award;
import cz.langsamu.tjv.baseballdatabase.domain.BaseballPositions;
import cz.langsamu.tjv.baseballdatabase.domain.Team;

import java.time.LocalDate;

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
    private Team team;
//    private Long awardID;
//    private Award award;

    public PlayerDTO(Long playerID, String firstName, String secondName, BaseballPositions baseballPosition, LocalDate dateOfBirth, Long teamID) {
        this.playerID = playerID;
        this.firstName = firstName;
        this.secondName = secondName;
        this.baseballPosition = baseballPosition;
        this.dateOfBirth = dateOfBirth;
        this.teamID = teamID;
    }

    public PlayerDTO(){

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
}
