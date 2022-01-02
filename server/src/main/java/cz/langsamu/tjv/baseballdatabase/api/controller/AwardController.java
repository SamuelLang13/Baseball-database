package cz.langsamu.tjv.baseballdatabase.api.controller;
import cz.langsamu.tjv.baseballdatabase.api.converter.AwardConverter;
import cz.langsamu.tjv.baseballdatabase.api.dto.AwardDTO;
import cz.langsamu.tjv.baseballdatabase.api.exception.NoEntityFoundException;
import cz.langsamu.tjv.baseballdatabase.business.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/awards")
public class AwardController {

    private final AwardService awardService;

    @Autowired
    public AwardController(AwardService awardService) {
        this.awardService = awardService;
    }

    @GetMapping
    public Collection<AwardDTO> getAwards(){
        return AwardConverter.fromModels(awardService.readAll());
    }

    @GetMapping("/{awardID}")
    public AwardDTO getOneAward(@PathVariable Long awardID){
        return AwardConverter.fromModel(awardService.readById(awardID).orElseThrow(NoEntityFoundException::new));
    }

    @PostMapping
    public AwardDTO registerNewAward(@RequestBody AwardDTO awardDTO){
        return AwardConverter.fromModel(awardService.create(AwardConverter.toModel(awardDTO)));
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
