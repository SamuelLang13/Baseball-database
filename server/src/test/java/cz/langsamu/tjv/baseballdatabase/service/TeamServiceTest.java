package cz.langsamu.tjv.baseballdatabase.service;

import cz.langsamu.tjv.baseballdatabase.api.exception.NoEntityFoundException;
import cz.langsamu.tjv.baseballdatabase.business.TeamService;
import cz.langsamu.tjv.baseballdatabase.dao.PlayerRepository;
import cz.langsamu.tjv.baseballdatabase.dao.TeamRepository;
import cz.langsamu.tjv.baseballdatabase.domain.Leagues;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import cz.langsamu.tjv.baseballdatabase.domain.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static cz.langsamu.tjv.baseballdatabase.domain.BaseballPositions.FirstBase;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {


    @Mock
    private TeamRepository teamRepository;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private TeamService service;

    @Test
    void exists() {
        Team team1 = new Team("name1", Leagues.AmericanLeague, 1950, 0);
        Team team2 = new Team("nonExistent", Leagues.AmericanLeague, 1950, 0);


        Mockito.lenient().when(teamRepository.findByName(not(eq("name1")))).thenReturn(Optional.empty());
        Mockito.lenient().when(teamRepository.findByName(eq("name1"))).thenReturn(Optional.of(team1));

        Assertions.assertFalse(service.exists(team2));
        Assertions.assertTrue(service.exists(team1));
    }

    @Test
    void update() {
        Team team1 = new Team("name1", Leagues.AmericanLeague, 1950, 0);
        Team team2 = new Team("nonExistent", Leagues.AmericanLeague, 1950, 0);

        Mockito.lenient().when(teamRepository.findById(not(eq(1L)))).thenReturn(Optional.empty());
        Mockito.lenient().when(teamRepository.findById(eq(1L))).thenReturn(Optional.of(team1));

        Assertions.assertThrows(NoEntityFoundException.class, () -> service.update(2L, team2));
        Assertions.assertEquals(team2, service.update(1L, team2));
    }

    @Test
    void findByIdTeam() {
        Team team1 = new Team("name1", Leagues.AmericanLeague, 1950, 0);

        Mockito.lenient().when(teamRepository.findById(not(eq(1L)))).thenReturn(Optional.empty());
        Mockito.lenient().when(teamRepository.findById(eq(1L))).thenReturn(Optional.of(team1));

        Assertions.assertThrows(NoEntityFoundException.class, () -> service.findByIdTeam(2L));
        Assertions.assertEquals(team1, service.findByIdTeam(1L));
    }

    @Test
    void findByIdPlayer() {
        Player player1 = new Player(1L, "firstName1", "secondName1", FirstBase, LocalDate.of(1999, 1, 1));

        Mockito.lenient().when(playerRepository.findById(not(eq(1L)))).thenReturn(Optional.empty());
        Mockito.lenient().when(playerRepository.findById(eq(1L))).thenReturn(Optional.of(player1));

        Assertions.assertThrows(NoEntityFoundException.class, () -> service.findByIdPlayer(2L));
        Assertions.assertEquals(player1, service.findByIdPlayer(1L));
    }

    @Test
    void addPlayer() {
        Player player1 = new Player(1L, "firstName1", "secondName1", FirstBase, LocalDate.of(1999, 1, 1));
        Team team1 = new Team("name1", Leagues.AmericanLeague, 1950, 0);

        Mockito.lenient().when(playerRepository.findById(not(eq(1L)))).thenReturn(Optional.empty());
        Mockito.lenient().when(playerRepository.findById(eq(1L))).thenReturn(Optional.of(player1));

        Mockito.lenient().when(teamRepository.findById(not(eq(1L)))).thenReturn(Optional.empty());
        Mockito.lenient().when(teamRepository.findById(eq(1L))).thenReturn(Optional.of(team1));

        Assertions.assertThrows(NoEntityFoundException.class, () -> service.addPlayer(2L, 1L));
        Assertions.assertThrows(NoEntityFoundException.class, () -> service.addPlayer(1L, 2L));
    }
}
