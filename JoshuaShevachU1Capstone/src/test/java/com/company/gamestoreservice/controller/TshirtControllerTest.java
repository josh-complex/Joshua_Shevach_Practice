package com.company.gamestoreservice.controller;

import com.company.gamestoreservice.model.Tshirt;
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
@WebMvcTest(TshirtController.class)
@AutoConfigureMockMvc(addFilters = false)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class TshirtControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DataSource dataSource;
    @MockBean
    private GameStoreService service;

    private final ObjectMapper mapper = new ObjectMapper();

    private Tshirt badTshirt;

    private Tshirt inputTshirt1;

    private Tshirt outputTshirt1;
    private Tshirt outputTshirt2;

    private final List<Tshirt> outputAllTshirts = new ArrayList<>();
    private final List<Tshirt> outputTshirtsByColorWhite = new ArrayList<>();
    private final List<Tshirt> outputTshirtsBySizeMedium = new ArrayList<>();

    @Before
    public void setUp() {
        badTshirt = new Tshirt();

        inputTshirt1 = new Tshirt();
        inputTshirt1.setSize("Medium");
        inputTshirt1.setColor("White");
        inputTshirt1.setDescription("Cool comfort fabrics");
        inputTshirt1.setPrice(new BigDecimal("52.95"));
        inputTshirt1.setQuantity(32);

        outputTshirt1 = new Tshirt();
        outputTshirt1.setTShirtId(1);
        outputTshirt1.setSize("Medium");
        outputTshirt1.setColor("White");
        outputTshirt1.setDescription("Cool comfort fabrics");
        outputTshirt1.setPrice(new BigDecimal("52.95"));
        outputTshirt1.setQuantity(32);

        outputTshirt2 = new Tshirt();
        outputTshirt2.setTShirtId(2);
        outputTshirt2.setSize("Medium");
        outputTshirt2.setColor("Blue");
        outputTshirt2.setDescription("Tech fabrics");
        outputTshirt2.setPrice(new BigDecimal("62.95"));
        outputTshirt2.setQuantity(79);

        outputAllTshirts.add(outputTshirt1);
        outputAllTshirts.add(outputTshirt2);

        outputTshirtsBySizeMedium.add(outputTshirt1);
        outputTshirtsBySizeMedium.add(outputTshirt2);

        outputTshirtsByColorWhite.add(outputTshirt1);

        when(service.saveTshirt(inputTshirt1)).thenReturn(outputTshirt1);
        when(service.getTshirtById(2)).thenReturn(outputTshirt2);
        when(service.getAllTshirts()).thenReturn(outputAllTshirts);
        when(service.getTshirtsByColor("White")).thenReturn(outputTshirtsByColorWhite);
        when(service.getTshirtsBySize("Medium")).thenReturn(outputTshirtsBySizeMedium);

    }

    @Test
    public void shouldAddTshirtToDatabaseAndReturnAddedTshirt() throws Exception {
        String inputJson = mapper.writeValueAsString(inputTshirt1);

        String outputJson = mapper.writeValueAsString(outputTshirt1);

        mockMvc.perform(post("/t-shirts")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetTshirtByIdAndReturnTshirtFromJson() throws Exception {
        String outputJson = mapper.writeValueAsString(outputTshirt2);

        mockMvc.perform(get("/t-shirts/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetAllTshirtsAndReturnListFromJson() throws Exception {
        String outputJson = mapper.writeValueAsString(outputAllTshirts);

        mockMvc.perform(get("/t-shirts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetTshirtsByColorAndReturnListFromJson() throws Exception {
        String outputJson = mapper.writeValueAsString(outputTshirtsByColorWhite);

        mockMvc.perform(get("/t-shirts/c/White"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetTshirtsBySizeAndReturnListFromJson() throws Exception {
        String outputJson = mapper.writeValueAsString(outputTshirtsBySizeMedium);

        mockMvc.perform(get("/t-shirts/s/Medium"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturn422WhenGivenABadTshirt() throws Exception {
        String inputJson = mapper.writeValueAsString(badTshirt);

        mockMvc.perform(post("/t-shirts")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }

}
