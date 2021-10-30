package cz.langsamu.tjv.baseballdatabase.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Stadium {
    @Id
    @SequenceGenerator(
            name="Stadium_sequence",
            sequenceName = "stadium_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stadium_sequence"

    )
    private Long stadiumID;
    //@Column(nullable = false)
    private String name;
    //@Column(nullable = false)
    private int capacity;
    //@Column(nullable = false)
    private String city;

    @ManyToOne
    @JoinColumn(name = "teamID",nullable = false)
    private  Team team;

    public Stadium(Long id, String name, int capacity, String city) {
        this.stadiumID = id;
        this.name = name;
        this.capacity = capacity;
        this.city = city;
    }

    public Stadium(String name, int capacity, String city) {
        this.name = name;
        this.capacity = capacity;
        this.city = city;
    }

    public Stadium(){

    }

    public Long getStadiumID() {
        return stadiumID;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getCity() {
        return city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
