package cz.langsamu.tjv.baseballdatabase.business;

import cz.langsamu.tjv.baseballdatabase.dao.AwardRepository;
import cz.langsamu.tjv.baseballdatabase.dao.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerAwardService {

    private final PlayerRepository playerRepository;
    private final AwardRepository awardRepository;

    @Autowired
    public PlayerAwardService(PlayerRepository playerRepository, AwardRepository awardRepository) {
        this.playerRepository = playerRepository;
        this.awardRepository = awardRepository;
    }

    public void removeId(Long id1, Long id2){
        if(playerRepository.existsById(id1)){

        }
    }



}
