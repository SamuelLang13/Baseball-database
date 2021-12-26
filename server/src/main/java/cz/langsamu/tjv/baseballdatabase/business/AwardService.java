package cz.langsamu.tjv.baseballdatabase.business;

import cz.langsamu.tjv.baseballdatabase.api.exception.NoEntityFoundException;
import cz.langsamu.tjv.baseballdatabase.dao.AwardRepository;
import cz.langsamu.tjv.baseballdatabase.dao.PlayerRepository;
import cz.langsamu.tjv.baseballdatabase.domain.Award;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AwardService extends AbstractCrudService<Long, Award, AwardRepository>{

    private final PlayerRepository playerRepository;

    public AwardService(AwardRepository repository, PlayerRepository playerRepository) {
        super(repository);
        this.playerRepository = playerRepository;
    }

    @Transactional
    public void addPlayer(Long playerID,Long awardID){
        Award award = findByIdAward(awardID);
        award.addPlayer(findByIdPlayer(playerID));
    }

    public Award findByIdAward(Long awardID){
        Optional<Award> award = repository.findById(awardID);
        if(award.isEmpty()){
            throw  new NoEntityFoundException("The award with this ID does not exist");
        }
        return award.get();
    }

    public Player findByIdPlayer(Long playerID){
        Optional<Player> player = playerRepository.findById(playerID);
        if(player.isEmpty()){
            throw  new NoEntityFoundException("The award with this ID does not exist");
        }
        return player.get();
    }

    @Override
    public boolean exists(Award entity) {
        Optional<Award> optionalAward = repository.findByName(entity.getName());
        return optionalAward.isPresent();
    }

    @Override
    @Transactional
    public Award update(Long id, Award entity) {
        Award award = getEntityById(id);
        award.setName(entity.getName());
        return award;
    }

}
