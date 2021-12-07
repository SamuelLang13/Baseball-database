package cz.langsamu.tjv.baseballdatabase.dao;

import cz.langsamu.tjv.baseballdatabase.domain.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AwardRepository extends JpaRepository<Award,Long> {

    Optional<Award> findByName(String name);
}
