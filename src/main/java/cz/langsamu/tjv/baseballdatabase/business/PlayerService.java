package cz.langsamu.tjv.baseballdatabase.business;

import cz.langsamu.tjv.baseballdatabase.dao.PlayerRepository;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService extends AbstractCrudService<Long, Player, PlayerRepository> {

    private final PlayerRepository playersRepository;

    //@Autowired


    public PlayerService(PlayerRepository repository, PlayerRepository playersRepository) {
        super(repository);
        this.playersRepository = playersRepository;
    }

    @Override
    public boolean exists(Player entity) {
        return repository.existsById(entity.getPlayerID());
    }

    public List<Player> getPlayers(){
        return playersRepository.findAll();
    }

    /**
     * Function to add new player
     */
    public void addNewPlayer(Player player){
//        Optional<Player> playerById =
//                playersRepository.findById(player.getPlayerID());
//        if(playerById.isPresent())
//        {
//            throw new IllegalStateException("Player is already registered!");
//        }
        playersRepository.save(player);
    }

    /**
     * Function to delete player by id
     */
    public void deletePlayer(Player player){

        if(!playersRepository.existsById(player.getPlayerID())){
            throw new IllegalStateException("Player does not exist!");
        }
        playersRepository.deleteById(player.getPlayerID());

    }

    @Transactional
    public void updatePlayer(Player player){
        Optional<Player> playerById = playersRepository.findById(player.getPlayerID());
        if(playerById.isEmpty()){
            throw new IllegalStateException("ID does not exist in the database!");
        }
        if(player.getFirstName()==null || player.getSecondName()==null ){
            throw new IllegalStateException("The first or second name is empty!");
        }
        player.setFirstName(player.getFirstName());
        player.setSecondName(player.getSecondName());
        player.setBaseballPosition(player.getBaseballPosition());
    }





}
