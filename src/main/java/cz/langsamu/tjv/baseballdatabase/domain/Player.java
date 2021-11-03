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

    public Player(String firstName, String secondName, BaseballPositions baseballPosition, LocalDate dateOfBirth, Team team) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.baseballPosition = baseballPosition;
        this.dateOfBirth = dateOfBirth;
    }

    public Player(String firstName, String secondName, BaseballPositions baseballPosition, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.baseballPosition = baseballPosition;
        this.dateOfBirth = dateOfBirth;
    }

    @ManyToOne
    @JoinColumn(name = "teamID",nullable = true)
    private  Team team;

    @ManyToMany
    @JoinTable(name = "player_award_table",
            joinColumns=@JoinColumn(name = "awardID"),
            inverseJoinColumns = @JoinColumn(name = "playerID"))
    private final Set<Award> awards = new HashSet<>();

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

    public void setPlayerID(Long playerID) {
        this.playerID = playerID;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
