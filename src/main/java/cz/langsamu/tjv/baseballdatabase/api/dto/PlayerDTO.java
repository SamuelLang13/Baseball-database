package cz.langsamu.tjv.baseballdatabase.api.dto;

import com.fasterxml.jackson.annotation.JsonView;
import cz.langsamu.tjv.baseballdatabase.domain.BaseballPositions;

public class PlayerDTO {

    private Long playerID;
    @JsonView(PlayerView.Public.class)
    private String firstName;
    @JsonView(PlayerView.Public.class)
    private String secondName;
    @JsonView(PlayerView.Public.class)
    private BaseballPositions baseballPosition;

    public PlayerDTO(Long playerID, String firstName, String secondName, BaseballPositions baseballPosition) {
        this.playerID = playerID;
        this.firstName = firstName;
        this.secondName = secondName;
        this.baseballPosition = baseballPosition;
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
}
