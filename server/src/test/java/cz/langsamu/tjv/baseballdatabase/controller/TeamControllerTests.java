package cz.langsamu.tjv.baseballdatabase.controller;

import cz.langsamu.tjv.baseballdatabase.api.controller.TeamController;
import cz.langsamu.tjv.baseballdatabase.business.EntityStateException;
import cz.langsamu.tjv.baseballdatabase.business.TeamService;
import cz.langsamu.tjv.baseballdatabase.domain.Leagues;
import cz.langsamu.tjv.baseballdatabase.domain.Team;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.Optional;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamController.class)
public class TeamControllerTests {


    @MockBean
    TeamService service;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetAll() throws Exception{
        Team team1 = new Team("name1", Leagues.AmericanLeague,1950,0);
        Team team2 = new Team("name2",Leagues.NationalLeague,1960);

        List<Team> teams = List.of(team1,team2);

        Mockito.when(service.readAll()).thenReturn(teams);

        mockMvc.perform(get("/teams"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }
    @Test
    public void testGetOne() throws Exception{
        Team team = new Team(1L,"name1", Leagues.AmericanLeague,1950);

        Mockito.when(service.readById(1L)).thenReturn(Optional.of(team));

        mockMvc.perform(get("/teams/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",Matchers.is("name1")));

        Mockito.when(service.readById(not(eq(1L)))).thenReturn(Optional.empty());

        mockMvc.perform(get("/teams/-1"))
                .andExpect(status().isNotFound());
    }
    @Test
    public void testDelete() throws Exception{
        Team team = new Team(1L,"name1", Leagues.AmericanLeague,1950);

        Mockito.when(service.readById(not(eq(1L)))).thenReturn(Optional.empty());
        Mockito.when(service.readById(1L)).thenReturn(Optional.of(team));

        mockMvc.perform(get("/teams/-1"))
                .andExpect(status().isNotFound());

        verify(service,never()).deleteById(any());

        mockMvc.perform(delete("/teams/1"))
                .andExpect(status().isOk());
        verify(service,times(1)).deleteById(1L);
    }

    @Test
    public void testCreateExisting() throws Exception {
        doThrow(new EntityStateException()).when(service).create(any(Team.class));

        mockMvc.perform(post("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"name\"," +
                                "\"league:\":\"AmericanLeague\"," +
                                "\"yearOfEstablish\":\"1990\"," +
                                "\"numOfWorldSeriesWin\":\"3\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    public void TestCreate() throws Exception{
        Team team = new Team(1L,"name1", Leagues.AmericanLeague,1950);
        Long id = team.getTeamID();
        Mockito.when(service.create(not(eq(team)))).thenReturn(new Team(2L,"name2",Leagues.NationalLeague,1940));
        Mockito.when(service.create(team)).thenReturn(team);

        mockMvc.perform(post("/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"name\"," +
                        "\"league:\":\"AmericanLeague\"," +
                        "\"yearOfEstablish\":\"1990\"," +
                        "\"numOfWorldSeriesWin\":\"3\"}"));
    }
}
