package com.company.gamestoreservice.controller;

import com.company.gamestoreservice.dao.GameRepo;
import com.company.gamestoreservice.model.Console;
import com.company.gamestoreservice.model.Game;
import com.company.gamestoreservice.service.GameStoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(GameController.class)
@AutoConfigureMockMvc(addFilters = false)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DataSource dataSource;
    @MockBean
    private GameStoreService service;

    private final ObjectMapper mapper = new ObjectMapper();

    private Game badGame;

    private Game inputGame1;

    private Game outputGame1;
    private Game outputGame2;

    private List<Game> outputAllGames = new ArrayList<>();
    private List<Game> outputGamesByRatingEForEveryone = new ArrayList<>();
    private List<Game> outputGamesByStudioSquareEnix = new ArrayList<>();
    private List<Game> outputGamesByTitleKingdomHearts = new ArrayList<>();

    @Before
    public void setUp() {
        badGame = new Game();

        inputGame1 = new Game();
        inputGame1.setTitle("Kingdom Hearts");
        inputGame1.setEsrbRating("E for everyone");
        inputGame1.setDescription("Join Sora on his journey and experience interactions with various Disney, Square Enix and Pixar characters");
        inputGame1.setStudio("Square Enix");
        inputGame1.setQuantity(1);
        inputGame1.setPrice(new BigDecimal("59.99"));

        outputGame1 = new Game();
        outputGame1.setGameId(1);
        outputGame1.setTitle("Kingdom Hearts");
        outputGame1.setEsrbRating("E for everyone");
        outputGame1.setDescription("Join Sora on his journey and experience interactions with various Disney, Square Enix and Pixar characters");
        outputGame1.setStudio("Square Enix");
        outputGame1.setQuantity(1);
        outputGame1.setPrice(new BigDecimal("59.99"));

        outputGame2 = new Game();
        outputGame2.setGameId(2);
        outputGame2.setTitle("Final Fantasy XV");
        outputGame2.setEsrbRating("M for mature");
        outputGame2.setDescription("Join Noctis on his journey");
        outputGame2.setStudio("Square Enix");
        outputGame2.setQuantity(1);
        outputGame2.setPrice(new BigDecimal("59.99"));

        outputAllGames.add(outputGame1);
        outputAllGames.add(outputGame2);
        outputGamesByStudioSquareEnix.add(outputGame1);
        outputGamesByStudioSquareEnix.add(outputGame2);
        outputGamesByTitleKingdomHearts.add(outputGame1);
        outputGamesByRatingEForEveryone.add(outputGame1);

        when(service.saveGame(inputGame1)).thenReturn(outputGame1);
        when(service.getGameById(2)).thenReturn(outputGame2);
        when(service.getAllGames()).thenReturn(outputAllGames);
        when(service.getGamesByTitle("Kingdom Hearts")).thenReturn(outputGamesByTitleKingdomHearts);
        when(service.getGamesByStudio("Square Enix")).thenReturn(outputGamesByStudioSquareEnix);
        when(service.getGamesByEsrbRating("E for everyone")).thenReturn(outputGamesByRatingEForEveryone);

    }

    @Test
    public void shouldAddGameAndReturnCreatedGame() throws Exception {

        String inputJson = mapper.writeValueAsString(inputGame1);

        String outputJson = mapper.writeValueAsString(outputGame1);

        mockMvc.perform(post("/games")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetGameByIdAndReturnGameWithJson() throws Exception {
        String outputJson = mapper.writeValueAsString(outputGame2);

        this.mockMvc.perform(get("/games/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetAllGamesAndReturnListFromJson() throws Exception {
        String outputJson = mapper.writeValueAsString(outputAllGames);

        mockMvc.perform(get("/games"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetGamesByEsrbRatingAndReturnListFromJson() throws Exception {
        String outputJson = mapper.writeValueAsString(outputGamesByRatingEForEveryone);

        mockMvc.perform(get("/games/r/E for everyone"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetGamesByStudioAndReturnListFromJson() throws Exception {
        String outputJson = mapper.writeValueAsString(outputGamesByStudioSquareEnix);

        mockMvc.perform(get("/games/s/Square Enix"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetGamesByTitleAndReturnListFromJson() throws Exception {
        String outputJson = mapper.writeValueAsString(outputGamesByTitleKingdomHearts);

        mockMvc.perform(get("/games/t/Kingdom Hearts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturn422WhenGivenABadGame() throws Exception {
        String inputJson = mapper.writeValueAsString(badGame);

        mockMvc.perform(post("/games")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }

}
