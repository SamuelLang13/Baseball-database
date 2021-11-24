package cz.langsamu.tjv.baseballdatabase.api.dto;

import com.fasterxml.jackson.annotation.JsonView;
import cz.langsamu.tjv.baseballdatabase.domain.Player;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Set;

public class AwardDTO {
    @JsonView(PlayerView.Public.class)
    private Long awardID;
    @JsonView(PlayerView.Public.class)
    private String name;
    private Set<Player> players;
    private List<Long> playersIDs;


    public AwardDTO(Long awardID, String name) {
        this.awardID = awardID;
        this.name = name;
    }

    public AwardDTO(Long awardID, String name, Set<Player> players) {
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

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public List<Long> getPlayersIDs() {
        return playersIDs;
    }

    public Set<Player> getPlayers() {
        return players;
    }
}
