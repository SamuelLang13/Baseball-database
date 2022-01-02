package cz.langsamu.tjv.baseballdatabase.business;

import cz.langsamu.tjv.baseballdatabase.api.exception.NoEntityFoundException;
import cz.langsamu.tjv.baseballdatabase.dao.PlayerRepository;
import cz.langsamu.tjv.baseballdatabase.dao.TeamRepository;
import cz.langsamu.tjv.baseballdatabase.domain.Award;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import cz.langsamu.tjv.baseballdatabase.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TeamService extends AbstractCrudService<Long, Team, TeamRepository> {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public TeamService(TeamRepository repository, TeamRepository teamRepository, PlayerRepository playerRepository) {
        super(repository);
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public boolean exists(Team entity) {
        Optional<Team> optionalTeam = repository.findByName(entity.getName());
        return  optionalTeam.isPresent();
    }

    @Override
    @Transactional
    public Team update(Long id, Team entity){
        Team team = getEntityById(id);
        team.setYearOfEstablish(entity.getYearOfEstablish());
        team.setNumOfWorldSeriesWin(entity.getNumOfWorldSeriesWin());
        team.setName(entity.getName());
        team.setLeague(entity.getLeague());
        return team;
    }


    public Team findByIdTeam(Long teamID) {
        Optional<Team> team = repository.findById(teamID);
        if(team.isEmpty()){
            throw  new NoEntityFoundException("The Team with this ID does not exist");
        }
        return team.get();
    }

    public Player findByIdPlayer(Long playerID){
        Optional<Player> player = playerRepository.findById(playerID);
        if(player.isEmpty()){
            throw  new NoEntityFoundException("The Player with this ID does not exist");
        }
        return player.get();
    }

    @Transactional
    public void addPlayer(Long teamID,Long playerID) {
        Player player = findByIdPlayer(playerID);
        Team team = findByIdTeam(teamID);
        team.addPlayer(player);

    }
}
