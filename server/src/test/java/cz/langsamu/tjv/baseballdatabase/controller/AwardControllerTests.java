package cz.langsamu.tjv.baseballdatabase.controller;

import cz.langsamu.tjv.baseballdatabase.api.controller.AwardController;
import cz.langsamu.tjv.baseballdatabase.business.AwardService;
import cz.langsamu.tjv.baseballdatabase.domain.Award;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AwardController.class)
public class AwardControllerTests {


    @MockBean
    AwardService service;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetAll() throws Exception{
        Award award1 = new Award("firstAward");
        Award award2 = new Award("secondAward");

        List<Award> awards = List.of(award1,award2);

        Mockito.when(service.readAll()).thenReturn(awards);

        mockMvc.perform(get("/awards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void testGetOne() throws Exception{
        Award award = new Award(1L,"award");
        Long id = award.getAwardID();
        Mockito.when(service.readById(id)).thenReturn(Optional.of(award));
        mockMvc.perform(post("/awards/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",Matchers.is("award")));

        Mockito.when(service.readById(not(eq(1L)))).thenReturn(Optional.empty());

        mockMvc.perform(post("/awards/-1"))
                .andExpect(status().isNotFound());

    }

}
