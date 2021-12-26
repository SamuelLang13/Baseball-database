package cz.langsamu.tjv.baseballdatabase.controller;

import cz.langsamu.tjv.baseballdatabase.business.PlayerService;
import cz.langsamu.tjv.baseballdatabase.domain.Award;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import org.hamcrest.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static cz.langsamu.tjv.baseballdatabase.domain.BaseballPositions.FirstBase;
import static cz.langsamu.tjv.baseballdatabase.domain.BaseballPositions.SecondBase;

public class PlayerControllerTests {

    @MockBean
    PlayerService service;

    @Autowired
    MockMvc mockMvc;

//    Player player1 = new Player("firstName1","secondName1",FirstBase,1.1.1999,1);
//    Player player2 = new Player("firstName2","secondName2",SecondBase,2.2.1999,2);

//    List<Player> players = List.of(player1,player2);

//        Mockito.when(service.readAll()).thenReturn(player);
//
//        mockMvc.perform(get("/awards"))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$", Matchers.hasSize(2)));
}
