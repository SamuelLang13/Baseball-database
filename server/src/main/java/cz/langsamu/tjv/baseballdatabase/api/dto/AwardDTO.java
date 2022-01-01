package cz.langsamu.tjv.baseballdatabase.api.dto;
import com.fasterxml.jackson.annotation.JsonView;
import cz.langsamu.tjv.baseballdatabase.domain.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AwardDTO {
    @JsonView(PlayerView.Public.class)
    private final Long awardID;
    @JsonView(PlayerView.Public.class)
    private String name;

    public AwardDTO(Long awardID, String name) {
        this.awardID = awardID;
        this.name = name;
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
}
