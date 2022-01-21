package cz.langsamu.tjv.baseballdatabase.service;

import cz.langsamu.tjv.baseballdatabase.api.exception.NoEntityFoundException;
import cz.langsamu.tjv.baseballdatabase.business.AwardService;
import cz.langsamu.tjv.baseballdatabase.dao.AwardRepository;
import cz.langsamu.tjv.baseballdatabase.dao.PlayerRepository;
import cz.langsamu.tjv.baseballdatabase.domain.Award;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import org.junit.Assert;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.AdditionalMatchers.not;

@ExtendWith(MockitoExtension.class)
public class AwardServiceTest {

    @Mock
    private PlayerRepository repository;

    @InjectMocks
    private AwardService awardService;

    @Mock
    private AwardRepository awardRepository;

    @Test
    void addPlayer() {
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

        Assertions.assertThrows(NoEntityFoundException.class, () -> awardService.addPlayer(3L, 2L));
    }

    @Test
    void findByIdAward() {
        Award award1 = new Award(1L, "firstAward");
        Mockito.lenient().when(awardRepository.findById(1L)).thenReturn(Optional.of(award1));
        Mockito.lenient().when(awardRepository.findById(not(eq(1L)))).thenReturn(Optional.empty());

        Assertions.assertThrows(NoEntityFoundException.class, () -> awardService.findByIdAward(2L));
        Assertions.assertEquals(award1, awardService.findByIdAward(1L));
    }

    @Test
    void findById() {
        Player player1 = new Player(1L, "firstName1", "secondName1", FirstBase, LocalDate.of(1999, 1, 1));

        Mockito.lenient().when(repository.findById(not(eq(1L)))).thenReturn(Optional.empty());
        Mockito.lenient().when(repository.findById(eq(1L))).thenReturn(Optional.of(player1));

        Assertions.assertThrows(NoEntityFoundException.class, () -> awardService.findByIdPlayer(2L));
        Assertions.assertEquals(player1, awardService.findByIdPlayer(1L));
    }

    @Test
    void exists() {
        Award award1 = new Award(1L, "firstAward");
        Award award2 = new Award(2L, "secondAward");

        Mockito.lenient().when(awardRepository.findByName(eq("firstAward"))).thenReturn(Optional.of(award1));
        Mockito.lenient().when(awardRepository.findByName(not(eq("firstAward")))).thenReturn(Optional.empty());

        Assertions.assertTrue(awardService.exists(award1));
        Assertions.assertFalse(awardService.exists(award2));

    }

    @Test
    void update() {
        Award award1 = new Award(1L, "firstAward");
        Award award2 = new Award( "newAwardName");

        Mockito.lenient().when(awardRepository.findById(1L)).thenReturn(Optional.of(award1));
        Mockito.lenient().when(awardRepository.findById(not(eq(1L)))).thenReturn(Optional.empty());

        Assertions.assertThrows(NoEntityFoundException.class, () -> awardService.update(3L, award1));

        Assertions.assertEquals(award2, awardService.update(1L, award2));
    }



}
