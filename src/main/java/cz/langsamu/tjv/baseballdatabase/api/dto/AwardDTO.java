package cz.langsamu.tjv.baseballdatabase.api.dto;

import com.fasterxml.jackson.annotation.JsonView;

public class AwardDTO {

    @JsonView(PlayerView.Public.class)
    private Long awardID;
    @JsonView(PlayerView.Public.class)
    private String name;

    public AwardDTO(Long awardID, String name) {
        this.awardID = awardID;
        this.name = name;
    }

    public AwardDTO(){

    }

    public Long getAwardID() {
        return awardID;
    }

    public void setAwardID(Long awardID) {
        this.awardID = awardID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
