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

    @GetMapping
    public Collection<AwardDTO> getAwards(){
        return AwardConverter.fromModels(awardService.readAll());
    }

    @PostMapping("/{awardID}")
    public AwardDTO getOneAward(@PathVariable Long awardID){
        return AwardConverter.fromModel(awardService.readById(awardID).orElseThrow(NoEntityFoundException::new));
    }

    @PostMapping
    public AwardDTO registerNewAward(@RequestBody AwardDTO awardDTO){
        awardService.create(AwardConverter.toModel(awardDTO));
        return getOneAward(awardDTO.getAwardID());
    }

    @DeleteMapping("/{awardID}")
    public void removeAward(@PathVariable Long awardID){
        awardService.deleteById(awardID);
    }

    @PutMapping("/{awardsID}")
    public AwardDTO updateAward(@PathVariable Long awardsID,
                              @RequestBody AwardDTO awardDTO){
        return AwardConverter.fromModel(awardService.update(awardsID,AwardConverter.toModel(awardDTO)));
    }

}
