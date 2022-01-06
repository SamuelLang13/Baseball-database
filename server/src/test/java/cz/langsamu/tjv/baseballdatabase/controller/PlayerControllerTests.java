package cz.langsamu.tjv.baseballdatabase.controller;

import cz.langsamu.tjv.baseballdatabase.api.controller.AwardController;
import cz.langsamu.tjv.baseballdatabase.api.controller.PlayerController;
import cz.langsamu.tjv.baseballdatabase.business.EntityStateException;
import cz.langsamu.tjv.baseballdatabase.business.PlayerService;
import cz.langsamu.tjv.baseballdatabase.domain.Award;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static cz.langsamu.tjv.baseballdatabase.domain.BaseballPositions.FirstBase;
import static cz.langsamu.tjv.baseballdatabase.domain.BaseballPositions.SecondBase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        Player player1 = new Player(1L,"firstName1","secondName1",FirstBase,LocalDate.of(1999,1,1));
        Player player2 = new Player(2L,"firstName2","secondName2",SecondBase,LocalDate.of(1999,2,2));

        List<Player> players = List.of(player1,player2);

        Mockito.when(service.readAll()).thenReturn(players);

        mockMvc.perform(get("/players"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }
    @Test
    public void testGetOne() throws Exception{
        Player player1 = new Player(1L,"firstName1","secondName1",FirstBase,LocalDate.of(1999,1,1));

        Mockito.when(service.readById(1L)).thenReturn(Optional.of(player1));

        mockMvc.perform(get("/players/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",Matchers.is("firstName1")));

        Mockito.when(service.readById(not(eq(1L)))).thenReturn(Optional.empty());

        mockMvc.perform(get("/players/-1"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testDelete() throws Exception{

        Player player1 = new Player(1L,"firstName1","secondName1",FirstBase,LocalDate.of(1999,1,1));

        Mockito.when(service.readById(not(eq(1L)))).thenReturn(Optional.empty());
        Mockito.when(service.readById(1L)).thenReturn(Optional.of(player1));

        mockMvc.perform(get("/players/-1"))
                .andExpect(status().isNotFound());

        verify(service,never()).deleteById(any());

        mockMvc.perform(delete("/players/1"))
                .andExpect(status().isOk());
        verify(service,times(1)).deleteById(1L);
    }

    @Test
    public void testCreateExisting() throws Exception {
        doThrow(new EntityStateException()).when(service).create(any(Player.class));

        mockMvc.perform(post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"firstname\"," +
                                "\"secondName:\":\"secondName\"," +
                                "\"baseballPosition\":\"FirstBase\"," +
                                "\"dateOfBirth\":\"1.1.1999\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    public void TestCreate() throws Exception{
        Player player1 = new Player(1L,"firstName1","secondName1",FirstBase,LocalDate.of(1999,1,1));
        Long id = player1.getPlayerID();
        Mockito.when(service.create(not(eq(player1)))).thenReturn(new Player(2L,"firstName1","secondName1",FirstBase,LocalDate.of(1999,1,1)));
        Mockito.when(service.create(player1)).thenReturn(player1);

        mockMvc.perform(post("/players")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"firstname\"," +
                        "\"secondName:\":\"secondName\"," +
                        "\"baseballPosition\":\"FirstBase\"," +
                        "\"dateOfBirth\":\"1.1.1999\"}"));
    }
}
