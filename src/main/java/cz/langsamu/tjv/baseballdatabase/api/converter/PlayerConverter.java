package cz.langsamu.tjv.baseballdatabase.api.converter;

import cz.langsamu.tjv.baseballdatabase.api.dto.PlayerDTO;
import cz.langsamu.tjv.baseballdatabase.domain.Player;

import java.util.Collection;

public class PlayerConverter {

    public static Player toModel(PlayerDTO playerDTO){
        return new Player(playerDTO.getFirstName(),playerDTO.getSecondName(),playerDTO.getBaseballPosition(),playerDTO.getDateOfBirth(), playerDTO.getTeam());
    }

    public static PlayerDTO fromModel(Player player){
        return new PlayerDTO(player.getPlayerID(),player.getFirstName(), player.getSecondName(), player.getBaseballPosition(),player.getDateOfBirth(), player.getTeamID());
    }

    public static Collection<Player> toModels(Collection<PlayerDTO> playerDTO){
        return playerDTO.stream().map(PlayerConverter::toModel).toList();
    }

    public static Collection<PlayerDTO> fromModels(Collection<Player> player){
        return player.stream().map(PlayerConverter::fromModel).toList();
    }


}
