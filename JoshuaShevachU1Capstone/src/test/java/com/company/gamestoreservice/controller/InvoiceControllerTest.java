package com.company.gamestoreservice.controller;

import com.company.gamestoreservice.model.Invoice;
import com.company.gamestoreservice.service.GameStoreService;
import com.company.gamestoreservice.viewmodel.InvoiceViewModel;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(InvoiceController.class)
@AutoConfigureMockMvc(addFilters = false)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DataSource dataSource;
    @MockBean
    private GameStoreService service;

    private final ObjectMapper mapper = new ObjectMapper();

    private Invoice badInvoice;

    private Invoice inputInvoice1;

    private InvoiceViewModel outputInvoiceViewModel1;
    private InvoiceViewModel outputInvoiceViewModel2;

    private final List<InvoiceViewModel> outputAllInvoiceViewModels = new ArrayList<>();

    @Before
    public void setUp() {
        badInvoice = new Invoice();

        inputInvoice1 = new Invoice();
        inputInvoice1.setName("Joshua Shevach");
        inputInvoice1.setCity("Orlando");
        inputInvoice1.setState("FL");
        inputInvoice1.setStreet("1110 Bassano Way");
        inputInvoice1.setZipcode("32828");
        inputInvoice1.setItemId(12);
        inputInvoice1.setItemType("Consoles");
        inputInvoice1.setQuantity(2);

        outputInvoiceViewModel1 = new InvoiceViewModel();
        outputInvoiceViewModel1.setName("Joshua Shevach");
        outputInvoiceViewModel1.setCity("Orlando");
        outputInvoiceViewModel1.setState("FL");
        outputInvoiceViewModel1.setStreet("1110 Bassano Way");
        outputInvoiceViewModel1.setZipcode("32828");

        outputInvoiceViewModel2 = new InvoiceViewModel();
        outputInvoiceViewModel2.setName("Aliyah Phelps");
        outputInvoiceViewModel2.setCity("Orlando");
        outputInvoiceViewModel2.setState("FL");
        outputInvoiceViewModel2.setStreet("1110 Bassano Way");
        outputInvoiceViewModel2.setZipcode("32828");

        outputAllInvoiceViewModels.add(outputInvoiceViewModel1);
        outputAllInvoiceViewModels.add(outputInvoiceViewModel2);

        when(service.saveInvoice(inputInvoice1)).thenReturn(outputInvoiceViewModel1);
        when(service.getInvoice(2)).thenReturn(outputInvoiceViewModel2);
        when(service.getAllInvoices()).thenReturn(outputAllInvoiceViewModels);

    }

    @Test
    public void shouldAddInvoiceAndReturnInvoiceFromJson() throws Exception {
        String inputJson = mapper.writeValueAsString(inputInvoice1);

        String outputJson = mapper.writeValueAsString(outputInvoiceViewModel1);

        mockMvc.perform(post("/invoices")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnInvoiceViewModelWhenGivenAnIdOf2() throws Exception {
        String outputJson = mapper.writeValueAsString(outputInvoiceViewModel2);

        mockMvc.perform(get("/invoices/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnAllInvoicesInListOfInvoiceViewModels() throws Exception {
        String outputJson = mapper.writeValueAsString(outputAllInvoiceViewModels);

        mockMvc.perform(get("/invoices"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturn422WhenGivenABadInvoice() throws Exception {
        String inputJson = mapper.writeValueAsString(badInvoice);

        mockMvc.perform(post("/invoices")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }

}
