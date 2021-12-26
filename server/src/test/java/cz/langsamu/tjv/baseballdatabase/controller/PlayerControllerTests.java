package cz.langsamu.tjv.baseballdatabase.controller;

import cz.langsamu.tjv.baseballdatabase.api.controller.AwardController;
import cz.langsamu.tjv.baseballdatabase.api.controller.PlayerController;
import cz.langsamu.tjv.baseballdatabase.business.PlayerService;
import cz.langsamu.tjv.baseballdatabase.domain.Award;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static cz.langsamu.tjv.baseballdatabase.domain.BaseballPositions.FirstBase;
import static cz.langsamu.tjv.baseballdatabase.domain.BaseballPositions.SecondBase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTests {

    @MockBean
    PlayerService service;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetAll() throws Exception{
        Player player1 = new Player("firstName1","secondName1",FirstBase,LocalDate.of(1999,1,1));
        Player player2 = new Player("firstName2","secondName2",SecondBase,LocalDate.of(1999,2,2));

        List<Player> players = List.of(player1,player2);

        Mockito.when(service.readAll()).thenReturn(players);

        mockMvc.perform(get("/players"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }
}
