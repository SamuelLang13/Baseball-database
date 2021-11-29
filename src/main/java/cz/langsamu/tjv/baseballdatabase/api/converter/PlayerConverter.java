package cz.langsamu.tjv.baseballdatabase.api.converter;

import cz.langsamu.tjv.baseballdatabase.api.dto.PlayerDTO;
import cz.langsamu.tjv.baseballdatabase.domain.Player;

import java.util.Collection;
import java.util.stream.Collectors;

public class PlayerConverter {

    public static Player toModel(PlayerDTO playerDTO){
        return new Player(playerDTO.getFirstName(),playerDTO.getSecondName(),playerDTO.getBaseballPosition(),playerDTO.getDateOfBirth(), playerDTO.getTeam(),playerDTO.getAwards());
    }

    public static PlayerDTO fromModel(Player player){
        return new PlayerDTO(player.getPlayerID(),player.getFirstName(), player.getSecondName(), player.getBaseballPosition(),player.getDateOfBirth(), player.getTeamID(),player.getTeam(),player.getAwards());
    }

    public static Collection<Player> toModels(Collection<PlayerDTO> playerDTO){
        return playerDTO.stream().map(PlayerConverter::toModel).collect(Collectors.toList());
    }

    public static Collection<PlayerDTO> fromModels(Collection<Player> player){
        return player.stream().map(PlayerConverter::fromModel).collect(Collectors.toList());
    }


}
