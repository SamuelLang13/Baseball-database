package cz.langsamu.tjv.baseballdatabase.api.controller;


import cz.langsamu.tjv.baseballdatabase.api.converter.AwardConverter;
import cz.langsamu.tjv.baseballdatabase.api.converter.TeamConverter;
import cz.langsamu.tjv.baseballdatabase.api.dto.AwardDTO;
import cz.langsamu.tjv.baseballdatabase.api.dto.TeamDTO;
import cz.langsamu.tjv.baseballdatabase.api.exception.NoEntityFoundException;
import cz.langsamu.tjv.baseballdatabase.business.AwardService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/awards")
public class AwardController {

    private final AwardService awardService;

    public AwardController(AwardService awardService) {
        this.awardService = awardService;
    }

    @GetMapping("/awards")
    public Collection<AwardDTO> getAwards(){
        return AwardConverter.fromModels(awardService.readAll());
    }

    @PostMapping("/awards/{awardID}")
    public AwardDTO getOneAward(@PathVariable Long awardID){
        return AwardConverter.fromModel(awardService.readById(awardID).orElseThrow(NoEntityFoundException::new));
    }

    @PostMapping("/awards}")
    public AwardDTO registerNewAward(@RequestBody AwardDTO awardDTO){
        awardService.create(AwardConverter.toModel(awardDTO));
        return getOneAward(awardDTO.getAwardID());
    }

    @DeleteMapping("/awards/{awardID}")
    public void removeAward(@RequestBody AwardDTO awardDTO,@PathVariable Long awardID){
        awardService.deleteById(awardID);
    }

    @PutMapping("/teams/{teamID}")
    public AwardDTO updateAward(@PathVariable Long teamID,
                              @RequestBody AwardDTO awardDTO){
        awardService.update(AwardConverter.toModel(awardDTO));
        return getOneAward(awardDTO.getAwardID());
    }

}
