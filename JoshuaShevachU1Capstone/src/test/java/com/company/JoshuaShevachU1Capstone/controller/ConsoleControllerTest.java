package com.company.JoshuaShevachU1Capstone.controller;

import com.company.JoshuaShevachU1Capstone.model.Console;
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
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameStoreService service;

    private final ObjectMapper mapper = new ObjectMapper();

    private Console console;

    @Before
    public void setUp() {
        console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("4 GB");
        console.setProcessor("Nvidia Tegra X1");
        console.setItemType("console");
        console.setPrice(new BigDecimal("299.00"));
        console.setRemainingInventory(1);
    }

    @Test
    public void shouldGetConsoleByIdAndReturnConsoleWithJson() throws Exception {
        //Object to JSON in String
        String outputJson = mapper.writeValueAsString(console);

        // Mocking DAO response
        // This is another way to mock using mockito
        // same as doReturn(returnVal).when(repo).findById(8);
        // We could also set up our mocks in a separate method, if we so chose
        when(service.getConsoleById(8)).thenReturn(console);

        this.mockMvc.perform(get("/consoles/8"))
                .andDo(print())
                .andExpect(status().isOk())
                //use the objectmapper output with the json method
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldAddConsoleAndReturnCreatedConsole() throws Exception {
        String inputJson = mapper.writeValueAsString(console);

        Console console2 = new Console();
        console2.setModel("Switch");
        console2.setManufacturer("Nintendo");
        console2.setMemoryAmount("4 GB");
        console2.setProcessor("Nvidia Tegra X1");
        console2.setItemType("console");
        console2.setPrice(new BigDecimal("299.00"));
        console2.setRemainingInventory(1);

        String outputJson = mapper.writeValueAsString(console2);

        when(service.addConsole(console)).thenReturn(console2);

        mockMvc.perform(post("/consoles")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetAllConsolesAndReturnListFromJson() throws Exception {
        List<Console> consoleList = new ArrayList<Console>() {{
            add(console);
        }};

        String outputJson = mapper.writeValueAsString(consoleList);

        when(service.getAllConsoles()).thenReturn(consoleList);

        mockMvc.perform(get("/consoles"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetConsolesByManufacturer() throws Exception {
        List<Console> consoleList = new ArrayList<Console>() {{
            add(console);
        }};

        String outputJson = mapper.writeValueAsString(consoleList);

        when(service.getConsolesByManufacturer("TestingRequest")).thenReturn(consoleList);

        mockMvc.perform(get("/consoles/m")
                .param("manufacturer", "TestingRequest"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldUpdateConsoleAndReturnNoContent() throws Exception {
        console.setId(1);

        String inputJson = mapper.writeValueAsString(console);

        mockMvc.perform(put("/consoles")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }

    @Test
    public void shouldDeleteConsoleAndReturnNoContent() throws Exception {
        mockMvc.perform(delete("/consoles/1"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }

}
