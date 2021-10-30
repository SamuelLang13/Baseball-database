package cz.langsamu.tjv.baseballdatabase.business;

import cz.langsamu.tjv.baseballdatabase.dao.AwardRepository;
import cz.langsamu.tjv.baseballdatabase.dao.PlayerRepository;
import cz.langsamu.tjv.baseballdatabase.domain.Award;
import org.springframework.stereotype.Service;

@Service
public class AwardService extends AbstractCrudService<Long, Award, AwardRepository>{

    private final PlayerRepository playerRepository;

    public AwardService(AwardRepository repository, PlayerRepository playerRepository) {
        super(repository);
        this.playerRepository = playerRepository;
    }

    @Override
    public boolean exists(Award entity) {
        return repository.existsById(entity.getAwardID());
    }
}
