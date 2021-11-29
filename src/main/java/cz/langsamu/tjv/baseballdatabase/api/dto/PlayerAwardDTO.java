package cz.langsamu.tjv.baseballdatabase.api.dto;

public class PlayerAwardDTO {

    private Long awardID;
    private Long playerID;

    public PlayerAwardDTO(){

    }

    public PlayerAwardDTO(Long awardID, Long playerID) {
        this.awardID = awardID;
        this.playerID = playerID;
    }

    public Long getAwardID() {
        return awardID;
    }

    public Long getPlayerID() {
        return playerID;
    }

    public void setAwardID(Long awardID) {
        this.awardID = awardID;
    }

    public void setPlayerID(Long playerID) {
        this.playerID = playerID;
    }
}
