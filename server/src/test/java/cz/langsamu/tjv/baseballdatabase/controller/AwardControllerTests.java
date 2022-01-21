package cz.langsamu.tjv.baseballdatabase.controller;
import cz.langsamu.tjv.baseballdatabase.api.controller.AwardController;
import cz.langsamu.tjv.baseballdatabase.api.dto.AwardDTO;
import cz.langsamu.tjv.baseballdatabase.business.AwardService;
import cz.langsamu.tjv.baseballdatabase.business.EntityStateException;
import cz.langsamu.tjv.baseballdatabase.domain.Award;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import cz.langsamu.tjv.baseballdatabase.api.exception.NoEntityFoundException;

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

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        // Optional s nasim ocenenim
        Mockito.when(service.readById(id)).thenReturn(Optional.of(award));
        // JSON reprezentacia ocenenia
        mockMvc.perform(get("/awards/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",Matchers.is("award")));
        // Ak je id ine nez "1", vratime prazdny optional
        Mockito.when(service.readById(not(eq(id)))).thenReturn(Optional.empty());
        // Ak neexistuje dane ocenie vraciame status NOT_FOUND
        mockMvc.perform(get("/awards/-1"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testDelete() throws Exception{

        Award award = new Award(1L,"award");
        Long id = award.getAwardID();

        // Overujeme existenciu ocenenia
        Mockito.when(service.readById(not(eq(id)))).thenReturn(Optional.empty());
        Mockito.when(service.readById(id)).thenReturn(Optional.of(award));

        mockMvc.perform(get("/awards/-1"))
                .andExpect(status().isNotFound());

        verify(service,never()).deleteById(any());

        mockMvc.perform(delete("/awards/1"))
                .andExpect(status().isOk());
        verify(service,times(1)).deleteById(id);
    }

    @Test
    public void testCreateExisting() throws Exception{

        doThrow(new EntityStateException()).when(service).create(any(Award.class));

        mockMvc.perform(post("/awards")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Most valuable player Award\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    public void testCreate() throws Exception{

        Award award = new Award(1L,"award");
        Long id = award.getAwardID();
        Mockito.when(service.create(not(eq(award)))).thenReturn(new Award(2L,"award2"));
        Mockito.when(service.create(award)).thenReturn(award);

        mockMvc.perform(post("/awards")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"award\"}"));

        ArgumentCaptor<Award> argumentCaptor = ArgumentCaptor.forClass(Award.class);
        Mockito.verify(service,Mockito.times(1)).create(argumentCaptor.capture());
        Award awardProvidedService = argumentCaptor.getValue();
        assertEquals("award",awardProvidedService.getName());
    }

    @Test
    public void testUpdateNotExisting() throws Exception{

        Award award = new Award(1L,"award");
        Mockito.when(service.update((not(eq(1L))),any())).thenThrow(NoEntityFoundException.class);
        Mockito.when(service.update(1L,award)).thenReturn(award);
        mockMvc.perform(put("/awards/-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"award3\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdate() throws Exception{
        Award award = new Award(1L,"Rookie of the Year Award");
        Mockito.when(service.update((not(eq(1L))),any())).thenThrow(NoEntityFoundException.class);
        Mockito.when(service.update(1L,award)).thenReturn(award);

        mockMvc.perform(put("/awards/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"name\": \"Rookie of the Year Award\"\n" +
                                "}"))
                .andExpect(status().isNotFound());

        mockMvc.perform(put("/awards/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"name\": \"Rookie of the Year Award\"\n" +
                                "}"))
                .andExpect(status().isOk());
    }

}
