package cz.langsamu.tjv.baseballdatabase.dao;

import cz.langsamu.tjv.baseballdatabase.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {

}
