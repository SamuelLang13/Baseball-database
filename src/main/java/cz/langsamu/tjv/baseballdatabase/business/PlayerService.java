package cz.langsamu.tjv.baseballdatabase.business;

import cz.langsamu.tjv.baseballdatabase.dao.PlayerRepository;
import cz.langsamu.tjv.baseballdatabase.dao.TeamRepository;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService extends AbstractCrudService<Long, Player, PlayerRepository> {

    private final TeamRepository teamRepository;

    @Autowired
    public PlayerService(PlayerRepository repository, TeamRepository teamRepository) {
        super(repository);
        this.teamRepository = teamRepository;
    }

    @Override
    public Optional<Player> exists(Player entity) {
        return repository.findByDateOfBirthAndSecondNameAndFirstName(entity.getDateOfBirth(), entity.getSecondName(), entity.getFirstName());
    }

}
