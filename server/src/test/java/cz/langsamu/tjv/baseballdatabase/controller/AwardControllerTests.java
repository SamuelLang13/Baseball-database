package cz.langsamu.tjv.baseballdatabase.controller;

import cz.langsamu.tjv.baseballdatabase.api.controller.AwardController;
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
        // Optional s nasim ocenenim
        Mockito.when(service.readById(id)).thenReturn(Optional.of(award));
        // JSON reprezentacia ocenenia
        mockMvc.perform(post("/awards/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",Matchers.is("award")));
        // Ak je id ine nez "1", vratime prazdny optional
        Mockito.when(service.readById(not(eq(id)))).thenReturn(Optional.empty());
        // Ak neexistuje dane ocenie vraciame status NOT_FOUND
        mockMvc.perform(post("/awards/-1"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testDelete() throws Exception{

        Award award = new Award(1L,"award");
        Long id = award.getAwardID();

        // Overujeme existenciu ocenenia
        Mockito.when(service.readById(not(eq(id)))).thenReturn(Optional.empty());
        Mockito.when(service.readById(id)).thenReturn(Optional.of(award));

        mockMvc.perform(post("/awards/-1"))
                .andExpect(status().isNotFound());

        verify(service,never()).deleteById(any());

        mockMvc.perform(delete("/awards/1"))
                .andExpect(status().isOk());
        verify(service,times(1)).deleteById(id);
    }

    @Test
    public void testCreateNotExisting() throws Exception{

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
        Mockito.when(service.readById(not(eq(id)))).thenReturn(Optional.empty());
        Mockito.when(service.readById(id)).thenReturn(Optional.of(award));

//        mockMvc.perform(post("/awards")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"awardID\":\"1\",\"name\":\"award\"}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name",Matchers.is("award")));
//
//        ArgumentCaptor<Award> argumentCaptor = ArgumentCaptor.forClass(Award.class);
//        Mockito.verify(service,Mockito.times(1)).create(argumentCaptor.capture());
//        Award awardProvidedService = argumentCaptor.getValue();
//        assertEquals("award",awardProvidedService.getName());

    }

    @Test
    public void testUpdateNotExisting() throws Exception{

    }

    @Test
    public void testUpdate() throws Exception{

    }

}
