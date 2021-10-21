package cz.langsamu.tjv.baseballdatabase.domain;


import javax.persistence.*;

@Entity
@Table
public class Player {
    @Id
    @SequenceGenerator(
            name="player_sequence",
            sequenceName = "player_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "player_sequence"

    )
    private Long id;
    private String first_name;
    private String second_name;
    private BaseballPositions baseballPosition;

    public Player(){

    }

    public Player(Long id, String first_name, String second_name, BaseballPositions baseballPosition) {
        this.id = id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.baseballPosition = baseballPosition;
    }

    public Player(String first_name, String second_name, BaseballPositions baseballPosition) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.baseballPosition = baseballPosition;
    }

    public Long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public BaseballPositions getBaseballPosition() {
        return baseballPosition;
    }
}
