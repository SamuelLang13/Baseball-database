package cz.langsamu.tjv.baseballdatabase.domain;

import javax.persistence.*;

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
    private Long id;
    private String name;
    private int capacity;
    private String city;

    public Stadium(Long id, String name, int capacity, String city) {
        this.id = id;
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

    public Long getId() {
        return id;
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
}
