package cz.langsamu.tjv.baseballdatabase.api.converter;

import cz.langsamu.tjv.baseballdatabase.api.dto.AwardDTO;
import cz.langsamu.tjv.baseballdatabase.domain.Award;

import java.util.Collection;

public class AwardConverter {

    public static Award toModel(AwardDTO awardDTO){
        return new Award(awardDTO.getName(),awardDTO.getPlayers());
    }

    public static AwardDTO fromModel(Award award){
        return new AwardDTO(award.getAwardID(), award.getName(), award.getPlayers());
    }

    public static Collection<Award> toModels(Collection<AwardDTO> awardDTO){
        return awardDTO.stream().map(AwardConverter::toModel).toList();
    }

    public static Collection<AwardDTO> fromModels(Collection<Award> award){
        return award.stream().map(AwardConverter::fromModel).toList();
    }

}
