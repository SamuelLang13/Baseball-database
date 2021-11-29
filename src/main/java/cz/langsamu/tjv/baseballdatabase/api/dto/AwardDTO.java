package cz.langsamu.tjv.baseballdatabase.api.dto;
import com.fasterxml.jackson.annotation.JsonView;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import java.util.List;
import java.util.Set;

public class AwardDTO {
    @JsonView(PlayerView.Public.class)
    private Long awardID;
    @JsonView(PlayerView.Public.class)
    private String name;
    private List<String> players;

    public AwardDTO(Long awardID, String name) {
        this.awardID = awardID;
        this.name = name;
    }

    public AwardDTO(Long awardID, String name, List<String> players) {
        this.awardID = awardID;
        this.name = name;
        this.players = players;
    }

    public AwardDTO(){

    }

    public Long getAwardID() {
        return awardID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPlayers() {
        return players;
    }
}
