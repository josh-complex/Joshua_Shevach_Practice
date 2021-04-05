package com.company.JoshuaShevachU1Capstone.controller;

import com.company.JoshuaShevachU1Capstone.model.Game;
import com.company.JoshuaShevachU1Capstone.service.GameStoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameStoreService service;

    private final ObjectMapper mapper = new ObjectMapper();

    private Game game;

    @Before
    public void setUp() {
        game = new Game();
        game.setTitle("Kingdom Hearts");
        game.setEsrbRating("E for everyone");
        game.setDescription("Join Sora on his journey and experience interactions with various Disney, Square Enix and Pixar characters");
        game.setStudio("Square Enix");
        game.setRemainingInventory(1);
        game.setPrice(new BigDecimal("59.99"));
    }

    @Test
    public void shouldGetGameByIdAndReturnGameWithJson() throws Exception {
        String outputJson = mapper.writeValueAsString(game);

        when(service.getGameById(8)).thenReturn(game);

        this.mockMvc.perform(get("/games/8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldAddGameAndReturnCreatedGame() throws Exception {
        String inputJson = mapper.writeValueAsString(game);

        Game game2 = new Game();
        game2.setTitle("Kingdom Hearts");
        game2.setEsrbRating("E for everyone");
        game2.setDescription("Join Sora on his journey and experience interactions with various Disney, Square Enix and Pixar characters");
        game2.setStudio("Square Enix");
        game2.setRemainingInventory(1);
        game2.setPrice(new BigDecimal("59.99"));

        String outputJson = mapper.writeValueAsString(game2);

        when(service.addGame(game)).thenReturn(game2);

        mockMvc.perform(post("/games")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetAllGamesAndReturnListFromJson() throws Exception {
        List<Game> gameList = new ArrayList<Game>() {{
            add(game);
        }};

        String outputJson = mapper.writeValueAsString(gameList);

        when(service.getAllGames()).thenReturn(gameList);

        mockMvc.perform(get("/games"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetGamesByEsrbRatingAndReturnListFromJson() throws Exception {
        List<Game> gameList = new ArrayList<Game>() {{
            add(game);
        }};

        String outputJson = mapper.writeValueAsString(gameList);

        when(service.getGamesByEsrbRating("TestingRequest")).thenReturn(gameList);

        mockMvc.perform(get("/games/r")
                .param("rating", "TestingRequest"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetGamesByStudioAndReturnListFromJson() throws Exception {
        List<Game> gameList = new ArrayList<Game>() {{
            add(game);
        }};

        String outputJson = mapper.writeValueAsString(gameList);

        when(service.getGamesByStudio("TestingRequest")).thenReturn(gameList);

        mockMvc.perform(get("/games/s")
                .param("studio", "TestingRequest"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetGamesByTitleAndReturnListFromJson() throws Exception {
        List<Game> gameList = new ArrayList<Game>() {{
            add(game);
        }};

        String outputJson = mapper.writeValueAsString(gameList);

        when(service.getGamesByTitle("TestingRequest")).thenReturn(gameList);

        mockMvc.perform(get("/games/t")
                .param("title", "TestingRequest"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldUpdateGameAndReturnNoContent() throws Exception {
        game.setId(1);

        String inputJson = mapper.writeValueAsString(game);

        mockMvc.perform(put("/games")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }

    @Test
    public void shouldDeleteGameAndReturnNoContent() throws Exception {
        mockMvc.perform(delete("/games/1"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }

}
