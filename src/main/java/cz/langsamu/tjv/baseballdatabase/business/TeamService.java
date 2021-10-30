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
public class TeamService {

    private final TeamRepository teamRepository;

    //@Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    public void addNewTeam(Team team) {
        Optional<Team> teamById =
                teamRepository.findById(team.getTeamID());
        if(teamById.isPresent())
        {
            throw new IllegalStateException("Team is already registered!");
        }
        Optional<Team> teamByName = teamRepository.findByName(team.getName());
        if(teamByName.isPresent()){
            throw new IllegalStateException("Team is already registered!");
        }
        teamRepository.save(team);
    }

    public void deleteTeam(Team team) {
        if(!teamRepository.existsById(team.getTeamID())){
            throw new IllegalStateException("Team does not exist!");
        }
        teamRepository.deleteById(team.getTeamID());
    }

    @Transactional
    public void updateTeam(long id, String name, Leagues league, Date yearOfEstablish, int numOfWorldSeriesWin) {
        Team team = teamRepository.findById(id).orElseThrow(()->new IllegalStateException
                ("The team with id:"+id+"does not exist."));
        if(name==null || name.length()>0){
            throw  new IllegalArgumentException("The team name cannot be empty!");
        }
        Optional<Team> teamsByName = teamRepository.findByName(team.getName());
        if(teamsByName.isPresent()){
            throw new IllegalStateException("Team is already registered!");
        }
        team.setName(name);
        if(numOfWorldSeriesWin<0){
            throw  new IllegalArgumentException("The number of world series win cannot be less than 0!");
        }
        team.setNumOfWorldSeriesWin(numOfWorldSeriesWin);
        team.setYearOfEstablish(yearOfEstablish);
    }
}
