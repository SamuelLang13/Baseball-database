package cz.langsamu.tjv.baseballdatabase.client_console.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class PlayerDTO {

    public Long ID;
    public String firstName;
    public String secondName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d.M.yyyy")
    public LocalDate dateOfBirth;

    public PlayerDTO(Long ID, String firstName, String secondName, LocalDate dateOfBirth) {
        this.ID = ID;
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
