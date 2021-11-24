package cz.langsamu.tjv.baseballdatabase.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Player> players = new HashSet<>();
    public Award(String name) {
        this.name = name;
    }

    public Award(String name, Set<Player> players) {
        this.name = name;
        this.players = players;
    }

    public Award(){

    }

    public Long getAwardID() {
        return awardID;
    }

    public String getName() {
        return name;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setName(String name) {
        this.name = name;
    }
}
