package com.company.JoshuaShevachU1Capstone.controller;

import com.company.JoshuaShevachU1Capstone.model.Tshirt;
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
@WebMvcTest(TshirtController.class)
public class TshirtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameStoreService service;

    private final ObjectMapper mapper = new ObjectMapper();

    private Tshirt tshirt;

    @Before
    public void setUp() {
        tshirt = new Tshirt();
        tshirt.setSize("Medium");
        tshirt.setColor("White");
        tshirt.setDescription("Cool comfort fabrics");
        tshirt.setItemType("T-Shirts");
        tshirt.setPrice(new BigDecimal("52.95"));
        tshirt.setRemainingInventory(32);
    }

    @Test
    public void shouldGetTshirtByIdAndReturnTshirtFromJson() throws Exception {
        String outputJson = mapper.writeValueAsString(tshirt);

        when(service.getTshirtById(27)).thenReturn(tshirt);

        mockMvc.perform(get("/t-shirts/27"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldAddTshirtToDatabaseAndReturnAddedTshirt() throws Exception {
        String inputJson = mapper.writeValueAsString(tshirt);

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setSize("Medium");
        tshirt2.setColor("White");
        tshirt2.setDescription("Cool comfort fabrics");
        tshirt2.setItemType("T-Shirts");
        tshirt2.setPrice(new BigDecimal("52.95"));
        tshirt2.setRemainingInventory(32);

        String outputJson = mapper.writeValueAsString(tshirt);

        when(service.addTshirt(tshirt)).thenReturn(tshirt2);

        mockMvc.perform(post("/t-shirts")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetAllTshirtsAndReturnListFromJson() throws Exception {
        List<Tshirt> tshirtList = new ArrayList<Tshirt>() {{
            add(tshirt);
        }};

        String outputJson = mapper.writeValueAsString(tshirtList);

        when(service.getAllTshirts()).thenReturn(tshirtList);

        mockMvc.perform(get("/t-shirts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetTshirtsByColorAndReturnListFromJson() throws Exception {
        List<Tshirt> tshirtList = new ArrayList<Tshirt>() {{
            add(tshirt);
        }};

        String outputJson = mapper.writeValueAsString(tshirtList);

        when(service.getTshirtsByColor("White")).thenReturn(tshirtList);

        mockMvc.perform(get("/t-shirts/c")
                .param("color", "White"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetTshirtsBySizeAndReturnListFromJson() throws Exception {
        List<Tshirt> tshirtList = new ArrayList<Tshirt>() {{
            add(tshirt);
        }};

        String outputJson = mapper.writeValueAsString(tshirtList);

        when(service.getTshirtsBySize("Medium")).thenReturn(tshirtList);

        mockMvc.perform(get("/t-shirts/s")
                .param("size", "Medium"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldUpdateConsoleAndReturnNoContent() throws Exception {
        tshirt.setId(1);

        String inputJson = mapper.writeValueAsString(tshirt);

        mockMvc.perform(put("/t-shirts")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }

    @Test
    public void shouldDeleteConsoleAndReturnNoContent() throws Exception {
        mockMvc.perform(delete("/t-shirts/1"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }


}
