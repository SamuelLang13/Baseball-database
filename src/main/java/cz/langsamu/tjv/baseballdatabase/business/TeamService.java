package cz.langsamu.tjv.baseballdatabase.business;

import cz.langsamu.tjv.baseballdatabase.dao.TeamRepository;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import cz.langsamu.tjv.baseballdatabase.domain.Team;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamService extends AbstractCrudService<Long, Team, TeamRepository> {


    public TeamService(TeamRepository teamRepository) {
        super(teamRepository);
    }

    @Override
    public Optional<Team> exists(Team entity) {
        return repository.findByName(entity.getName());
    }

}
