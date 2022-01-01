package cz.langsamu.tjv.baseballdatabase.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class Award {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long awardID;
    private String name;

    @ManyToMany
    @JoinTable(name = "player_award_table",
                joinColumns=@JoinColumn(name = "playerid"),
                inverseJoinColumns = @JoinColumn(name = "awardid"))
    public Set<Player> players = new HashSet<>();

    public Award() {

    }

    public Award(String name) {
        this.name = name;
        this.players = null;
    }

    public Award(String name, Set<Player> players){
        this.name = name;
        this. players = players;
    }

    public Award(Long awardID, String name) {
        this.awardID = awardID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Long getAwardID() {
        return awardID;
    }

    public void addPlayer(Player byIdPlayer) {
        players.add(byIdPlayer);
    }
}
