package cz.langsamu.tjv.baseballdatabase.domain;


import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "team_teamid",nullable = false)
    private  Team team;
    @ManyToMany
    @JoinTable(name = "player_award_table",
            joinColumns=@JoinColumn(name = "award_awardid"),
            inverseJoinColumns = @JoinColumn(name = "player_playerid"))
    private final Set<Award> awards = new HashSet<>();

    public Player(String firstName, String secondName, BaseballPositions baseballPosition,LocalDate dateOfBirth, Team team) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.baseballPosition = baseballPosition;
        this.dateOfBirth = dateOfBirth;
        this.team = team;
    }

    public Player(){

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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getTeamID() {
        //return 24;
        return team.getTeamID();
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
