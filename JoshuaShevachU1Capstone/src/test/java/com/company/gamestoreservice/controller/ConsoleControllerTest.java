package com.company.gamestoreservice.controller;

//import com.company.gamestoreservice.SecurityConfig;
import com.company.gamestoreservice.model.Console;
import com.company.gamestoreservice.service.GameStoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
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
@WebMvcTest(ConsoleController.class)
@AutoConfigureMockMvc(addFilters = false)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class ConsoleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DataSource dataSource;
    @MockBean
    private GameStoreService service;

    private final ObjectMapper mapper = new ObjectMapper();

    private Console badConsole;

    private Console inputConsole1;

    private Console outputConsole1;
    private Console outputConsole2;

    private final List<Console> outputAllConsoles = new ArrayList<>();
    private final List<Console> outputConsolesByManufacturerMicrosoft = new ArrayList<>();

    @Before
    public void setUp() {
        badConsole = new Console();

        inputConsole1 = new Console();
        inputConsole1.setModel("Switch");
        inputConsole1.setManufacturer("Nintendo");
        inputConsole1.setMemoryAmount("4 GB");
        inputConsole1.setProcessor("Nvidia Tegra X1");
        inputConsole1.setPrice(new BigDecimal("299.00"));
        inputConsole1.setQuantity(1);

        outputConsole1 = new Console();
        outputConsole1.setConsoleId(1);
        outputConsole1.setModel("Switch");
        outputConsole1.setManufacturer("Nintendo");
        outputConsole1.setMemoryAmount("4 GB");
        outputConsole1.setProcessor("Nvidia Tegra X1");
        outputConsole1.setPrice(new BigDecimal("299.00"));
        outputConsole1.setQuantity(1);

        outputConsole2 = new Console();
        outputConsole2.setConsoleId(2);
        outputConsole2.setModel("Xbox 360");
        outputConsole2.setManufacturer("Microsoft");
        outputConsole2.setMemoryAmount("512 MB");
        outputConsole2.setProcessor("Xenon");
        outputConsole2.setPrice(new BigDecimal("175.00"));
        outputConsole2.setQuantity(2);

        outputAllConsoles.add(outputConsole1);
        outputAllConsoles.add(outputConsole2);
        outputConsolesByManufacturerMicrosoft.add(outputConsole2);

        when(service.saveConsole(inputConsole1)).thenReturn(outputConsole1);
        when(service.getConsoleById(2)).thenReturn(outputConsole2);
        when(service.getAllConsoles()).thenReturn(outputAllConsoles);
        when(service.getConsolesByManufacturer("Microsoft")).thenReturn(outputConsolesByManufacturerMicrosoft);

    }

    @Test
    public void shouldAddConsoleAndReturnCreatedConsole() throws Exception {
        String inputJson = mapper.writeValueAsString(inputConsole1);

        String outputJson = mapper.writeValueAsString(outputConsole1);

        mockMvc.perform(post("/consoles")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetConsoleByIdAndReturnConsoleWithJson() throws Exception {

        String outputJson = mapper.writeValueAsString(outputConsole2);

        this.mockMvc.perform(get("/consoles/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }


    @Test
    public void shouldGetAllConsolesAndReturnListFromJson() throws Exception {

        String outputJson = mapper.writeValueAsString(outputAllConsoles);

        mockMvc.perform(get("/consoles"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetConsolesByManufacturer() throws Exception {

        String outputJson = mapper.writeValueAsString(outputConsolesByManufacturerMicrosoft);

        mockMvc.perform(get("/consoles/m/Microsoft"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturn422WhenGivenABadConsole() throws Exception {
        String inputJson = mapper.writeValueAsString(badConsole);

        mockMvc.perform(post("/consoles")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }

}
