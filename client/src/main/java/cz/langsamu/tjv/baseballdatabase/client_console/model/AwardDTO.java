package cz.langsamu.tjv.baseballdatabase.client_console.model;

public class AwardDTO {

    public Long awardID;
    public String name;

    public AwardDTO() {
    }

    public AwardDTO(Long ID, String name) {
        this.awardID = ID;
        this.name = name;
    }

    public Long getAwardID() {
        return awardID;
    }

    public String getName() {
        return name;
    }
}
