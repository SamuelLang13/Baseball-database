package cz.langsamu.tjv.baseballdatabase.dao;

import cz.langsamu.tjv.baseballdatabase.domain.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Long> {

}
