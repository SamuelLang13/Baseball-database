package cz.langsamu.tjv.baseballdatabase.business;

import cz.langsamu.tjv.baseballdatabase.dao.AwardRepository;
import cz.langsamu.tjv.baseballdatabase.dao.PlayerRepository;
import cz.langsamu.tjv.baseballdatabase.domain.Award;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AwardService extends AbstractCrudService<Long, Award, AwardRepository>{

    private final PlayerRepository playerRepository;

    public AwardService(AwardRepository repository, PlayerRepository playerRepository) {
        super(repository);
        this.playerRepository = playerRepository;
    }

    @Override
    public Optional<Award> exists(Award entity) {
        Optional<Award> optionalAward = repository.findByName(entity.getName());
        return Optional.empty();
    }

}
