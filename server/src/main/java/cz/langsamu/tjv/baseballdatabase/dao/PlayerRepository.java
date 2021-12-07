package cz.langsamu.tjv.baseballdatabase.dao;

import cz.langsamu.tjv.baseballdatabase.domain.Player;
import cz.langsamu.tjv.baseballdatabase.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {


    Optional<Player> findByDateOfBirthAndSecondNameAndFirstName(LocalDate dateOfBirth, String secondName, String firstName);
}
