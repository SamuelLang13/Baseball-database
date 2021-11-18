package cz.langsamu.tjv.baseballdatabase.business;

import cz.langsamu.tjv.baseballdatabase.dao.PlayerRepository;
import cz.langsamu.tjv.baseballdatabase.dao.TeamRepository;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import cz.langsamu.tjv.baseballdatabase.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public boolean exists(Player entity) {
        Optional<Player> optionalPlayer= repository.findByDateOfBirthAndSecondNameAndFirstName(entity.getDateOfBirth(), entity.getSecondName(), entity.getFirstName());
        return optionalPlayer.isPresent();
    }

    public Team findTeam(Long teamID){
        Optional<Team> optionalTeam = teamRepository.findById(teamID);
        if(optionalTeam.isEmpty()){
            throw  new IllegalArgumentException("The team with this ID does not exist");
        }
        return optionalTeam.get();
    }

    @Override
    @Transactional
    public Player update(Long id, Player entity) {
        Player player = getEntityById(id);
        player.setBaseballPosition(player.getBaseballPosition());
        player.setDateOfBirth(player.getDateOfBirth());
        player.setFirstName(player.getFirstName());
        player.setSecondName(player.getSecondName());
        return player;
    }

}
