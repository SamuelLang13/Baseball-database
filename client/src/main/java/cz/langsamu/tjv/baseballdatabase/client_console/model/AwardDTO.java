package cz.langsamu.tjv.baseballdatabase.client_console.model;

public class AwardDTO {

    public Long ID;
    public String name;

    public AwardDTO(Long ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public Long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
}
