package cz.langsamu.tjv.baseballdatabase.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playerID;

    //@Column(nullable = false)
    private String firstName;
    //@Column(nullable = false)
    private String secondName;
    //@Column(nullable = false)
    private BaseballPositions baseballPosition;

    @ManyToMany
    @JoinTable(name = "players_teams_table",
            joinColumns = @JoinColumn(name = "team_ID"),
            inverseJoinColumns = @JoinColumn(name = "player_ID"))
    private Set<Team> teams = new HashSet<>();

    public Player(){

    }

    public Player(Long id, String first_name, String second_name, BaseballPositions baseballPosition) {
        this.playerID = id;
        this.firstName = first_name;
        this.secondName = second_name;
        this.baseballPosition = baseballPosition;
    }

    public Player(String first_name, String second_name, BaseballPositions baseballPosition) {
        this.firstName = first_name;
        this.secondName = second_name;
        this.baseballPosition = baseballPosition;
    }

    public Long getPlayerID() {
        return playerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public BaseballPositions getBaseballPosition() {
        return baseballPosition;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public void setSecondName(String second_name) {
        this.secondName = second_name;
    }

    public void setBaseballPosition(BaseballPositions baseballPosition) {
        this.baseballPosition = baseballPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(playerID, player.playerID) && Objects.equals(firstName, player.firstName) && Objects.equals(secondName, player.secondName) && baseballPosition == player.baseballPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerID, firstName, secondName, baseballPosition);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + playerID +
                ", first_name='" + firstName + '\'' +
                ", second_name='" + secondName + '\'' +
                ", baseballPosition=" + baseballPosition +
                '}';
    }
}
