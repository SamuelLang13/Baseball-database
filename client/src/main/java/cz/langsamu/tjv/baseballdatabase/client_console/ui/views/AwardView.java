package cz.langsamu.tjv.baseballdatabase.client_console.ui.views;

import cz.langsamu.tjv.baseballdatabase.client_console.model.AwardDTO;

import java.util.Collection;

public class AwardView extends View {
    public static void printAllAwards(Collection<AwardDTO> awards){
        awards.forEach(awardDTO -> System.out.println("'"+awardDTO.getAwardID()+"'"+awardDTO.getName()));
    }

    public static void printAward(AwardDTO awardDTO){
        System.out.println("ID: "+awardDTO.getAwardID());
        System.out.println("Award's name: "+awardDTO.getName());
    }
}
