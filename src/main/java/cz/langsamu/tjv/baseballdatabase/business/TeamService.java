package cz.langsamu.tjv.baseballdatabase.business;

import cz.langsamu.tjv.baseballdatabase.dao.TeamRepository;
import cz.langsamu.tjv.baseballdatabase.domain.Leagues;
import cz.langsamu.tjv.baseballdatabase.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService extends AbstractCrudService<Long, Team, TeamRepository> {


    public TeamService(TeamRepository teamRepository) {
        super(teamRepository);
    }

    @Override
    public boolean exists(Team team) {
        Optional<Team> teamByName = repository.findByName(team.getName());
        return teamByName.isEmpty();
    }

}
