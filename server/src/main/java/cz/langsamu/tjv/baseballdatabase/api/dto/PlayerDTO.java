package cz.langsamu.tjv.baseballdatabase.api.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import cz.langsamu.tjv.baseballdatabase.domain.BaseballPositions;
import cz.langsamu.tjv.baseballdatabase.domain.Team;

import java.time.LocalDate;
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
    private Set<String> awards;
    private Team team;

    public PlayerDTO(){

    }

    public PlayerDTO(Long playerID, String firstName, String secondName, BaseballPositions baseballPosition, LocalDate dateOfBirth, Set<String> awards, Team team) {
        this.playerID = playerID;
        this.firstName = firstName;
        this.secondName = secondName;
        this.baseballPosition = baseballPosition;
        this.dateOfBirth = dateOfBirth;
        this.awards = awards;
        this.team = team;
    }

    public PlayerDTO(Long playerID, String firstName, String secondName, BaseballPositions baseballPosition, LocalDate dateOfBirth, Set<String> awards) {
        this.playerID = playerID;
        this.firstName = firstName;
        this.secondName = secondName;
        this.baseballPosition = baseballPosition;
        this.dateOfBirth = dateOfBirth;
        this.awards = awards;
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

    public void setPlayerID(Long playerID) {
        this.playerID = playerID;
    }

    public Set<String> getAwards() {
        return awards;
    }

    public void setAwards(Set<String> awards) {
        this.awards = awards;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
