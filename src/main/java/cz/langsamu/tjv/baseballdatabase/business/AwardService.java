package cz.langsamu.tjv.baseballdatabase.business;

import cz.langsamu.tjv.baseballdatabase.dao.AwardRepository;
import cz.langsamu.tjv.baseballdatabase.dao.PlayerRepository;
import cz.langsamu.tjv.baseballdatabase.domain.Award;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AwardService extends AbstractCrudService<Long, Award, AwardRepository>{

    private final PlayerRepository playerRepository;

    public AwardService(AwardRepository repository, PlayerRepository playerRepository) {
        super(repository);
        this.playerRepository = playerRepository;
    }

    public Set<Player> findPlayer(List<Long> awardIDs){
        Set<Player> players;
        try {
            players = new HashSet<>(playerRepository.findAllById(awardIDs));
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("The award with this ID does not exist");
        }
        return players;
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
