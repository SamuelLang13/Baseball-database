package cz.langsamu.tjv.baseballdatabase.service;

import cz.langsamu.tjv.baseballdatabase.api.exception.NoEntityFoundException;
import cz.langsamu.tjv.baseballdatabase.business.PlayerService;
import cz.langsamu.tjv.baseballdatabase.dao.AwardRepository;
import cz.langsamu.tjv.baseballdatabase.dao.PlayerRepository;
import cz.langsamu.tjv.baseballdatabase.dao.TeamRepository;
import cz.langsamu.tjv.baseballdatabase.domain.Award;
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
public class PlayerServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private AwardRepository awardRepository;

    @InjectMocks
    private PlayerService service;

    @Mock
    private PlayerRepository repository;


    @Test
    void exists() {
        Player player1 = new Player(1L, "firstName1", "secondName1", FirstBase, LocalDate.of(1999, 1, 1));
        Player player2 = new Player(1L, "firstName1", "asdasd", FirstBase, LocalDate.of(1999, 1, 1));

        Mockito.lenient().when(repository.findByDateOfBirthAndSecondNameAndFirstName(not(eq(LocalDate.of(1999, 1, 1))), not(eq("secondName1")), not(eq("firstName1")))).thenReturn(Optional.empty());
        Mockito.lenient().when(repository.findByDateOfBirthAndSecondNameAndFirstName(eq(LocalDate.of(1999, 1, 1)), eq("secondName1"), eq("firstName1"))).thenReturn(Optional.of(player1));

        Assertions.assertTrue(service.exists(player1));
        Assertions.assertFalse(service.exists(player2));
    }

    @Test
    void addAward() {
        //Lenient pretoze Mockito nechcelo pustit tie not a eq
        Award award1 = new Award(1L, "firstAward");
        Award award2 = new Award(2L, "secondAward");

        Mockito.lenient().when(awardRepository.findById(1L)).thenReturn(Optional.of(award1));
        Mockito.lenient().when(awardRepository.findById(2L)).thenReturn(Optional.of(award2));
        Mockito.lenient().when(awardRepository.findById(not(eq(1L)))).thenReturn(Optional.empty());
        Mockito.lenient().when(awardRepository.findById(not(eq(2L)))).thenReturn(Optional.empty());
        Player player1 = new Player(1L, "firstName1", "secondName1", FirstBase, LocalDate.of(1999, 1, 1));

        Mockito.lenient().when(repository.findById(not(eq(1L)))).thenReturn(Optional.empty());
        Mockito.lenient().when(repository.findById(eq(1L))).thenReturn(Optional.of(player1));

        Assertions.assertThrows(NoEntityFoundException.class, () -> service.addAward(3L, 2L));
    }

    @Test
    void findByIdAward() {
        Award award1 = new Award(1L, "firstAward");
        Mockito.lenient().when(awardRepository.findById(1L)).thenReturn(Optional.of(award1));
        Mockito.lenient().when(awardRepository.findById(not(eq(1L)))).thenReturn(Optional.empty());

        Assertions.assertThrows(NoEntityFoundException.class, () -> service.findByIdAward(2L));
        Assertions.assertEquals(award1, service.findByIdAward(1L));
    }

    @Test
    void findByIdPlayer() {
        Player player1 = new Player(1L, "firstName1", "secondName1", FirstBase, LocalDate.of(1999, 1, 1));

        Mockito.lenient().when(repository.findById(not(eq(1L)))).thenReturn(Optional.empty());
        Mockito.lenient().when(repository.findById(eq(1L))).thenReturn(Optional.of(player1));

        Assertions.assertThrows(NoEntityFoundException.class, () -> service.findByIdPlayer(2L));
        Assertions.assertEquals(player1, service.findByIdPlayer(1L));
    }

    @Test
    void findTeam() {
        Team team1 = new Team("name1", Leagues.AmericanLeague, 1950, 0);

        Mockito.lenient().when(teamRepository.findById(not(eq(1L)))).thenReturn(Optional.empty());
        Mockito.lenient().when(teamRepository.findById(eq(1L))).thenReturn(Optional.of(team1));

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.findTeam(2L));
        Assertions.assertEquals(team1, service.findTeam(1L));
    }

    @Test
    void update() {
        Player player1 = new Player(1L, "firstName1", "secondName1", FirstBase, LocalDate.of(1999, 1, 1));
        Player player2 = new Player(1L, "firstName1", "secondNameJou", FirstBase, LocalDate.of(1999, 1, 1));

        Mockito.lenient().when(repository.findById(not(eq(1L)))).thenReturn(Optional.empty());
        Mockito.lenient().when(repository.findById(eq(1L))).thenReturn(Optional.of(player1));

        Assertions.assertThrows(NoEntityFoundException.class, () -> service.update(2L, player1));
        Assertions.assertEquals(player2, service.update(1L, player2));
    }

    @Test
    void addTeam() {
        Player player1 = new Player(1L, "firstName1", "secondName1", FirstBase, LocalDate.of(1999, 1, 1));
        Team team1 = new Team("name1", Leagues.AmericanLeague, 1950, 0);

        Mockito.lenient().when(repository.findById(not(eq(1L)))).thenReturn(Optional.empty());
        Mockito.lenient().when(repository.findById(eq(1L))).thenReturn(Optional.of(player1));

        Mockito.lenient().when(teamRepository.findById(not(eq(1L)))).thenReturn(Optional.empty());
        Mockito.lenient().when(teamRepository.findById(eq(1L))).thenReturn(Optional.of(team1));

        Assertions.assertThrows(NoEntityFoundException.class, () -> service.addTeam(1L, 2L));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addTeam(2L, 1L));

        Assertions.assertDoesNotThrow(() -> service.addTeam(1L, 1L));

    }
}
