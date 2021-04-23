package com.company.inventoryservice.controller;

import com.company.inventoryservice.dao.EquipmentLocationRepository;
import com.company.inventoryservice.model.EquipmentLocation;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EquipmentLocationController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class EquipmentLocationControllerTest {

    @MockBean
    EquipmentLocationRepository repo;

    @Autowired
    MockMvc mvc;

    JsonMapper mapper = new JsonMapper();

    EquipmentLocation inputEquipment = new EquipmentLocation();
    EquipmentLocation outputEquipment = new EquipmentLocation();
    EquipmentLocation outputEquipment2 = new EquipmentLocation();
    List<EquipmentLocation> els = new ArrayList<>();
    List<EquipmentLocation> els2 = new ArrayList<>();

    @Before
    public void setUp() {
        inputEquipment.setDescription("Stapler");
        inputEquipment.setLocation("Office");

        outputEquipment.setId(1);
        outputEquipment.setDescription("Stapler");
        outputEquipment.setLocation("Office");

        outputEquipment2.setId(2);
        outputEquipment2.setDescription("Trampoline");
        outputEquipment2.setLocation("Pediatric Wing");

        els.add(outputEquipment);
        els.add(outputEquipment2);

        els2.add(outputEquipment2);

        when(repo.save(inputEquipment)).thenReturn(outputEquipment);
        when(repo.findAll()).thenReturn(els);
        when(repo.findAllByDescription("Trampoline")).thenReturn(els2);
        when(repo.findById(1)).thenReturn(Optional.of(outputEquipment));
    }

    @Test
    public void shouldAddEquipmentLocation() throws Exception {
        String input = mapper.writeValueAsString(inputEquipment);
        String output = mapper.writeValueAsString(outputEquipment);

        mvc.perform(post("/equipment")
                .content(input)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(output));
    }

    @Test
    public void shouldGetEquipmentLocationById() throws Exception {
        String output = mapper.writeValueAsString(outputEquipment);

        mvc.perform(get("/equipment/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(output));
    }

    @Test
    public void shouldGetAllEquipmentLocations() throws Exception {
        String output = mapper.writeValueAsString(els);

        mvc.perform(get("/equipment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(output));
    }

    @Test
    public void shouldGetAllEquipmentLocationsByDescription() throws Exception {
        String output = mapper.writeValueAsString(els2);

        mvc.perform(get("/equipment")
                .param("description", "Trampoline"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(output));
    }

}
