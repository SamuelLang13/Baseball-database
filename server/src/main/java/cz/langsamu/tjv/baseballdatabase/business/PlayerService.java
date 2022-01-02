package cz.langsamu.tjv.baseballdatabase.business;

import cz.langsamu.tjv.baseballdatabase.api.exception.NoEntityFoundException;
import cz.langsamu.tjv.baseballdatabase.dao.AwardRepository;
import cz.langsamu.tjv.baseballdatabase.dao.PlayerRepository;
import cz.langsamu.tjv.baseballdatabase.dao.TeamRepository;
import cz.langsamu.tjv.baseballdatabase.domain.Award;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import cz.langsamu.tjv.baseballdatabase.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
public class PlayerService extends AbstractCrudService<Long, Player, PlayerRepository> {

    private final TeamRepository teamRepository;
    private final AwardRepository awardRepository;

    @Autowired
    public PlayerService(PlayerRepository repository, TeamRepository teamRepository, AwardRepository awardRepository) {
        super(repository);
        this.teamRepository = teamRepository;
        this.awardRepository = awardRepository;
    }

    @Override
    public boolean exists(Player entity) {
        Optional<Player> optionalPlayer= repository.findByDateOfBirthAndSecondNameAndFirstName(entity.getDateOfBirth(), entity.getSecondName(), entity.getFirstName());
        return optionalPlayer.isPresent();
    }

    @Transactional
    public void addAward(Long awardID,Long playerID){
        Player player = findByIdPlayer(playerID);
        player.addAward(findByIdAward(awardID));
    }

    public Award findByIdAward(Long awardID){
        Optional<Award> award = awardRepository.findById(awardID);
        if(award.isEmpty()){
            throw  new NoEntityFoundException("The award with this ID does not exist");
        }
        return award.get();
    }

    public Player findByIdPlayer(Long playerID){
        Optional<Player> player = repository.findById(playerID);
        if(player.isEmpty()){
            throw  new NoEntityFoundException("The Player with this ID does not exist");
        }
        return player.get();
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
        player.setBaseballPosition(entity.getBaseballPosition());
        player.setDateOfBirth(entity.getDateOfBirth());
        player.setFirstName(entity.getFirstName());
        player.setSecondName(entity.getSecondName());
        return player;
    }

    @Transactional
    public void addTeam(Long teamID, Long playerID) {
        Player player = findByIdPlayer(playerID);
        player.setTeam(findTeam(teamID));
    }

    public void deletePlayer(Long playerID){
        Player player = findByIdPlayer(playerID);
        Set<Award> awardSet = player.getAwards();
        for (Award award : awardSet) {
            if(award.playersSet.contains(player)){
                award.playersSet.remove(player);
            }
        }
    }
}
