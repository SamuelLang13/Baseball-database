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
    public Set<Player> playersSet = new HashSet<>();

    public Award() {

    }

    public Award(String name) {
        this.name = name;
        this.playersSet = Collections.EMPTY_SET;
    }

    public Award(String name, Set<Player> players){
        this.name = name;
        this. playersSet = players;
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
        return playersSet;
    }

    public void setPlayers(Set<Player> players) {
        this.playersSet = players;
    }

    public Long getAwardID() {
        return awardID;
    }

    public void addPlayer(Player byIdPlayer) {
        playersSet.add(byIdPlayer);
    }

    public Set<String> getPlayersName(){
        Set<String> surNames = new HashSet<>();
        Set<String> empty = new HashSet<>();
        if(playersSet.isEmpty()){
           empty.add("No players!");
           return empty;
        }
        for (Player player : playersSet) {
            surNames.add(player.getFirstName()+" "+player.getSecondName()+" ID: "+player.getPlayerID());
        }
        return surNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Award award = (Award) o;
        return Objects.equals(name, award.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
