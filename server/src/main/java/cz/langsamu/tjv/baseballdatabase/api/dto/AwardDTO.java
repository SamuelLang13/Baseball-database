package cz.langsamu.tjv.baseballdatabase.api.dto;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.HashSet;
import java.util.Set;

public class AwardDTO {
    @JsonView(PlayerView.Public.class)
    private final Long awardID;
    @JsonView(PlayerView.Public.class)
    private String name;
    private Set<String> playersD;

    public AwardDTO(Long awardID, String name, Set<String> players) {
        this.awardID = awardID;
        this.name = name;
        this.playersD = players;
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

    public Set<String> getPlayers() {
        Set<String> empty = new HashSet<>();
        if(playersD.isEmpty()){
            empty.add("No players!");
            return empty;
        }
        return playersD;
    }

    public void setPlayers(Set<String> players) {
        this.playersD = players;
    }
}
