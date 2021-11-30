package cz.langsamu.tjv.baseballdatabase.domain;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playerID;
    private String firstName;
    private String secondName;
    private BaseballPositions baseballPosition;
    private LocalDate dateOfBirth;
    @ManyToOne
    @JoinColumn
    private Team team;

    @ManyToMany
    @JoinTable(name = "player_award_table",
            joinColumns=@JoinColumn(name = "awardid"),
            inverseJoinColumns = @JoinColumn(name = "playerid"))
    private Set<Award> awards = new HashSet<>();

    public Player(String firstName, String secondName, BaseballPositions baseballPosition,LocalDate dateOfBirth, Team team) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.baseballPosition = baseballPosition;
        this.dateOfBirth = dateOfBirth;
        this.team = Objects.requireNonNull(team);
    }

    public Player(String firstName, String secondName, BaseballPositions baseballPosition, LocalDate dateOfBirth, Team team, Set<Award> awards) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.baseballPosition = baseballPosition;
        this.dateOfBirth = dateOfBirth;
        this.team = Objects.requireNonNull(team);
        this.awards = awards;
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
        return team.getTeamID();
    }

    public Team getTeam() {
        return team;
    }

    public Set<Award> getAwards() {
        return awards;
    }

    public void addAward(Award award) {
        this.awards.add(award);
    }

    public List<String> getAwardsName(){
        List<String> awardsName = new ArrayList<>();
        for (Award award : awards) {
            awardsName.add(award.getName());
        }
        return awardsName;
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
