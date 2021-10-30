package cz.langsamu.tjv.baseballdatabase.api.controller;

import cz.langsamu.tjv.baseballdatabase.business.StadiumService;
import cz.langsamu.tjv.baseballdatabase.domain.Stadium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//public class StadiumController {
//
//    private final StadiumService stadiumService;
//
//    @Autowired
//    public StadiumController(StadiumService stadiumService) {
//        this.stadiumService = stadiumService;
//    }
//
//    @GetMapping("/stadiums")
//    public List<Stadium> getStadiums(){
//        return stadiumService.getStadiums();
//    }
//
//    @PostMapping("/stadiums")
//    public void registerNewStadium(@RequestBody Stadium stadium){
//        stadiumService.AddNewStadium(stadium);
//    }
//
//    @DeleteMapping(path = "{stadiumID}")
//    public void deleteStadium(@PathVariable("stadiumID")Long id){
//        stadiumService.deleteStadium();
//    }
//
//    @PutMapping
//    public void updateStadium(@PathVariable("stadiumID")long id,
//                              @RequestParam(required=false)String name,
//                              @RequestParam(required = false)int capacity,
//                              @RequestParam(required = false)String city){
//        stadiumService.updateStadium(id,name,capacity,city);
//    }
//
//}
