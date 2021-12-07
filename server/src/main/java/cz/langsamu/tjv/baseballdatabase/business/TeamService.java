package cz.langsamu.tjv.baseballdatabase.business;

import cz.langsamu.tjv.baseballdatabase.dao.TeamRepository;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import cz.langsamu.tjv.baseballdatabase.domain.Team;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TeamService extends AbstractCrudService<Long, Team, TeamRepository> {


    public TeamService(TeamRepository teamRepository) {
        super(teamRepository);
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


}
