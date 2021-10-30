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
                joinColumns=@JoinColumn(name = "playerID"),
                inverseJoinColumns = @JoinColumn(name = "awardID"))
    private final Set<Player> players = new HashSet<>();


    public Award(Long awardID, String name) {
        this.awardID = awardID;
        this.name = name;
    }

    public Award(String name) {
        this.name = name;
    }

    public Award(){

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
